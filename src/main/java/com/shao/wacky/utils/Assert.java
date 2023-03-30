package com.shao.wacky.utils;


import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import com.shao.wacky.exception.WackyException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Assert {

    /**
     * 断言是否为真,如果为 {@code false} 抛出 {@code WackyException} 异常
     *
     * @param expression 表达式
     * @param message    错误提示, 支持 slf4j 的 {} 语法
     * @param params     {} 对应的参数
     */
    public static void isTrue(boolean expression, String message, Object... params) throws WackyException {
        if (!expression) {
            log.error(message, params);
            throw new WackyException(500,StrFormatter.format(message, params));
        }
    }

    /**
     * 断言是否为真,如果为 {@code false} 抛出 {@code WackyException} 异常
     */
    public static void isTrue(boolean expression, String message) throws WackyException {
        if (!expression) {
            throw new WackyException(message);
        }
    }



    /**
     * 断言是否为假,如果为 {@code true} 抛出 {@code WackyException} 异常
     */
    public static void isFalse(boolean expression, String message, Object... params) throws WackyException {
        isTrue(!expression, message, params);
    }

    /**
     * 断言是否为假,如果为 {@code true} 抛出 {@code WackyException} 异常
     */
    public static void isFalse(boolean expression, String message) throws WackyException {
        isTrue(!expression, message);
    }



    /**
     * 断言是否为空,如果不为空抛出 {@code WackyException} 异常
     */
    public static void isBlank(String obj, String errorMsg) throws WackyException {
        if (StrUtil.isNotBlank(obj)) {
            throw new WackyException(errorMsg);
        }
    }

    /**
     * 断言是否不为空,如果为空抛出 {@code WackyException} 异常
     */
    public static void isNotBlank(String obj, String errorMsg) throws WackyException {
        if (StrUtil.isBlank(obj)) {
            throw new WackyException(errorMsg);
        }
    }


    /**
     * 断言是否为空,如果不为空抛出 {@code WackyException} 异常
     */
    public static void isNull(Object obj, String errorMsg) throws WackyException {
        if (obj != null) {
            throw new WackyException(errorMsg);
        }
    }
    /**
     * 断言是否为空,如果不为空抛出 {@code WackyException} 异常
     */
    public static void isNotNull(Object obj, String errorMsg) throws WackyException {
        if (obj == null) {
            throw new WackyException(errorMsg);
        }
    }


}
