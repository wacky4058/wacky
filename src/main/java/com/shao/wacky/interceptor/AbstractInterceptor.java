package com.shao.wacky.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.shao.wacky.utils.ParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 切面拦截工具类
 *
 * @author: chengdu
 * @date: 2023/3/20
 */
@Slf4j
public abstract class AbstractInterceptor {

    /**
     * 获取 HttpServletRequest
     */
    protected HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        return attributes.getRequest();
    }

    /**
     * 获取请求头
     */
    protected String getRequestHeader(String headerKey) {
        return getHttpServletRequest().getHeader(headerKey);
    }

    protected RequireLogin getH5RequestHandler(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        return method.getAnnotation(RequireLogin.class);
    }

    // 获取请求路径
    protected String getRequestUrl(HttpServletRequest req) {
        return req.getRequestURI();
    }

    // 获取请求头
    protected Map<String, List<String>> getRequestHeader(HttpServletRequest req) {
        // 需要保存的header头
        List<String> logHeaderNames = Arrays.asList("referer", "origin", "request-origion",
                "host", "x-requested-with", "user-agent", "auth-token");

        Map<String, List<String>> headers = new HashMap<>(16);
        Enumeration<String> headerNames = req.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                // 不需要记录的信息直接跳过
                if (name == null || !logHeaderNames.contains(name.toLowerCase())) {
                    continue;
                }
                String value = req.getHeader(name);

                if (!name.isEmpty() && value != null) {
                    List<String> valueList = new ArrayList<>();
                    if (headers.containsKey(name)) {
                        headers.get(name).add(value);
                    }
                    valueList.add(value);
                    headers.put(name, valueList);
                }
            }
        }
        return Collections.unmodifiableMap(headers);
    }

    /**
     * 获取请求参数
     */
    protected Map<String, Object> getRequestParams(JoinPoint joinPoint, HttpServletRequest request) {
        String method = request.getMethod();
        Object[] args = joinPoint.getArgs();
        Map<String, Object> params = new HashMap<>(16);
        // 获取请求参数集合并进行遍历拼接
        if (args.length > 0) {
            if ("POST".equals(method)) {
                Object object = args[0];
                params = BeanUtil.beanToMap(object, false, true);
            } else if ("GET".equals(method)) {
                params = ParamUtils.paramToMap(request.getQueryString());
            }
        }

        return params;
    }

    /**
     * 获取返回结果
     */
    protected String getResponseBody(Object result) {
        String resultObj = "";
        try {
            resultObj = JSON.toJSONString(result);
            resultObj = resultObj.length() > 1024 ? resultObj.substring(0, 1023) : resultObj;
        } catch (Exception ignored) {
        }

        return resultObj;
    }

}
