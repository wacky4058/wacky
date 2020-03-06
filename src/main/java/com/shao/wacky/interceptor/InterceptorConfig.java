package com.shao.wacky.interceptor;


import com.shao.wacky.annotation.NeedLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorConfig implements HandlerInterceptor{

    //在Controller执行之前调用，如果返回false，controller不执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("---------preHandle--------");
        if (handler instanceof HandlerMethod && ((HandlerMethod)handler).getMethodAnnotation(NeedLogin.class).value()){
            System.out.println("---------needLogin--------");
            return true;
        }
        return true;
    }

    //controller执行之后，且页面渲染之前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("---------postHandle--------");
    }

    //页面渲染之后调用，一般用于资源清理操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("---------afterCompletion--------");
    }
}
