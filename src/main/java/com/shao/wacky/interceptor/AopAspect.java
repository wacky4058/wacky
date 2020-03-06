package com.shao.wacky.interceptor;

import com.shao.wacky.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAspect {

    @Pointcut("execution(public * com.shao.wacky.service..*.*(..))")
    public void serviceAspect() {

    }
    @Around("serviceAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("--------进入切面--------");
        String className = pjp.getTarget().getClass().getName();  //类名
        System.out.println("--------className:"+className+"-----");
        String methodName = pjp.getSignature().getName();   //方法名
        System.out.println("--------methodName:"+methodName+"-----");
        String[] parameterNamesArgs = ((MethodSignature) pjp.getSignature()).getParameterNames();  //参数名
        System.out.println("--------parameterNamesArgs:"+parameterNamesArgs+"-----");
        Object[] args = pjp.getArgs(); // 获取方法参数
        System.out.println("--------args:"+args+"-----");
        System.out.println("--------pjp.proceed():"+pjp.proceed().getClass().getName()+"-----");
        System.out.println(User.class.equals(pjp.proceed().getClass())); //返回类型
        return  pjp.proceed();
    }
}
