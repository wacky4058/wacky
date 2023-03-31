package com.shao.wacky.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLogin {

    boolean login() default true; //是否需要登录 默认登录

    boolean logger() default false; // 是否打印日志
}