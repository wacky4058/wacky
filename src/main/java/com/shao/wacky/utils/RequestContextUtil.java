package com.shao.wacky.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class RequestContextUtil {

    public static HttpServletRequest request() {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return requestAttributes == null ? null : requestAttributes.getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    public static HttpServletResponse response() {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return requestAttributes == null ? null : requestAttributes.getResponse();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取请求的token
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }

        return token;
    }

    public static String getRequestToken() {
        HttpServletRequest request = request();
        if (Objects.isNull(request)) {
            return null;
        }
        return getRequestToken(request);
    }

    /**
     * 生成RequestId
     */
    public static String genRequestId() {
        long nanoTime = System.nanoTime();
        String nanoTime100 = nanoTime + "" + RandomUtils.nextInt(0, 1000);
        nanoTime100 = nanoTime100.substring(4);
        return Long.toString(Long.parseLong(nanoTime100), Character.MAX_RADIX);
    }
}

