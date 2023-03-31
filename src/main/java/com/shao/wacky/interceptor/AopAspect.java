package com.shao.wacky.interceptor;

import com.shao.wacky.domain.ServiceLogDomain;
import com.shao.wacky.entity.User;
import com.shao.wacky.exception.WackyException;
import com.shao.wacky.utils.DateUtils;
import com.shao.wacky.utils.ParamUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAspect {

    private static Logger logger = LoggerFactory.getLogger(AopAspect.class);

    @Pointcut("execution(public * com.shao.wacky.service..*.*(..))")
    public void serviceAspect() {

    }
    @Around("serviceAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        ServiceLogDomain log = new ServiceLogDomain();
        log.setStatrTime(DateUtils.currentTime(DateUtils.TIME_FORMAT_HH_MM_SS));
        String className = pjp.getTarget().getClass().getName();
        log.setRequestClass(className);
        String methodName = pjp.getSignature().getName();   //方法名
        log.setRequestMethod(methodName);
        String[] parameterNamesArgs = ((MethodSignature) pjp.getSignature()).getParameterNames();  //参数名
        Object[] args = pjp.getArgs(); // 获取方法参数
        log.setRequestArgs(ParamUtils.getParams(parameterNamesArgs,args));
        Object result = pjp.proceed();
        log.setResult(result);
        log.setEndTime(DateUtils.currentTime(DateUtils.TIME_FORMAT_HH_MM_SS));
        logger.info(log.toString());
        System.out.println(log.toString());
        return  result;
    }
}
