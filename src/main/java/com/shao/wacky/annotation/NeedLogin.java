package com.shao.wacky.annotation;
import java.lang.annotation.*;
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLogin {

    public boolean need=true;

    public boolean not=false;
    boolean value();

}
