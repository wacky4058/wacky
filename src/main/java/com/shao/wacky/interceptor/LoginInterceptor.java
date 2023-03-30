package com.shao.wacky.interceptor;

import com.alibaba.fastjson.JSON;
import com.shao.wacky.utils.*;
import com.shao.wacky.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Slf4j
@Aspect
@Component
public class LoginInterceptor extends AbstractInterceptor {

    private static final ThreadLocal<Long> REQUEST_TIME = new ThreadLocal<>();

    /**
     * 在Controller 加日志切面
     */
    @Pointcut("execution(* com.shao.wacky.controller..*(..))")
    public void around() {
    }

    // 请求进入前
    @Before("around()")
    public void doBefore(JoinPoint joinPoint) {
        // 记录请求时间
        setRequestTime(System.currentTimeMillis());

        HttpServletRequest req = getHttpServletRequest();
        RequireLogin requireLogin = getH5RequestHandler(joinPoint);
        if (requireLogin == null) {
            return;
        }
        // 打印日志
        if (requireLogin.logger()) {
            Map<String, Object> requestParams = getRequestParams(joinPoint, req);

            // 请求参数打印
            log.info("请求入参: IP:{} URI:{} HEADER:{} PARAMS:{}",
                    IPUtil.getIpAddr(req), getRequestUrl(req),
                    getRequestHeader(req), JSON.toJSONString(requestParams));
        }
        // 登录: 解决不需要登录的接口获取用户登录信息的场景
        LoginUserInfo loginUserInfo = this.login(req);
        // 校验登录
        Assert.isFalse(requireLogin.login() && loginUserInfo == null,"token 失效");

    }

    // 请求正常返回
    @AfterReturning(value = "around()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        RequireLogin requireLogin = getH5RequestHandler(joinPoint);
        if (requireLogin == null) {
            return;
        }
        if (requireLogin.logger()) {
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest req = attributes.getRequest();
                Map<String, Object> requestParams = getRequestParams(joinPoint, req);

                // 处理完请求,返回内容
                log.info("请求出参= IP:{} URI:{} PARAMS:{} RESULT:{} TIME:{}",
                        IPUtil.getIpAddr(req), getRequestUrl(req), JSON.toJSONString(requestParams),
                        getResponseBody(result), getRequestTaking());
            } finally {
                removeThreadLocal();
            }
        }
    }

    // 请求返回异常
    @AfterThrowing(value = "around()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        RequireLogin requireLogin = getH5RequestHandler(joinPoint);
        if (requireLogin == null) {
            return;
        }
        if (requireLogin.logger()) {
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest req = attributes.getRequest();
                Map<String, Object> requestParams = getRequestParams(joinPoint, req);

                // 这里可以捕获异常,但无法处理异常,异常还是会抛给 JVM
                // 处理完请求,返回内容
                log.error("请求出参= IP:{} URI:{} PARAMS:{} ERROR:{} TIME:{}",
                        IPUtil.getIpAddr(req), getRequestUrl(req), JSON.toJSONString(requestParams),
                        e.getMessage(), getRequestTaking(), e);

            } finally {
                removeThreadLocal();
            }
        }
    }

    /**
     * 登录
     */
    private LoginUserInfo login(HttpServletRequest req) {
        String token = RequestContextUtil.getRequestToken(req);
        if (StringUtils.isBlank(token)) {
           return null;
        }
        //从token中解析出username，然后从数据库取出用户信息
        Long userId = JWTUtil.verify(token).getClaims().get("userId").asLong();
        String username = JWTUtil.verify(token).getClaims().get("userName").asString();
        String realName = JWTUtil.verify(token).getClaims().get("realName").asString();
        if (null==userId){
            return null;
        }
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setUserId(userId);
        loginUserInfo.setUserName(username);
        loginUserInfo.setRealName(realName);
        UserUtil.setH5UserInfo(req, loginUserInfo);
        return loginUserInfo;
    }

    // 获取请求耗时,单位毫秒
    private Long getRequestTaking() {
        Long endTime = System.currentTimeMillis();
        return endTime - REQUEST_TIME.get();
    }

    private static void setRequestTime(Long requestTime) {
        REQUEST_TIME.remove();
        REQUEST_TIME.set(requestTime);
    }

    // 清除本地线程的数据
    private static void removeThreadLocal() {
        REQUEST_TIME.remove();
    }
}
