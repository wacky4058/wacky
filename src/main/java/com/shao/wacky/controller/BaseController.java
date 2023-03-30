package com.shao.wacky.controller;


import com.shao.wacky.utils.UserUtil;
import com.shao.wacky.vo.LoginUserInfo;
import com.shao.wacky.vo.ResultVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.shao.wacky.utils.Assert;

/**
 * web层通用数据处理
 * 所有的Controller需继承BaseController
 *
 * @author wackk
 */
public class BaseController {

    /**
     * 通用的 操作成功
     */
    public static ResultVo<Void> SUCCESS() {
        return ResultVo.ok();
    }

   /* *//**
     * 通用的 操作成功
     *//*
    public static R<RetCode> SUCCESS(RetCode code) {
        return R.ok(code.getI18nStr());
    }*/

    /**
     * 通用的 操作成功时的 返回对象
     */
    public static <T> ResultVo<T> SUCCESS(T result) {
        return ResultVo.ok(result);
    }

    /**
     * 通用的错误提示方法
     *
     * @param msg 错误信息
     */
    public static ResultVo ERROR(String msg) {
        return ResultVo.fail(msg);
    }


    /**
     * 通用的错误提示方法
     *
     * @param msg     错误信息
     * @param retCode 编码
     */
    public static ResultVo ERROR(Integer retCode, String msg) {
        return ResultVo.fail(retCode, msg);
    }


    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取用户ID
     */
    public Long getUserId() {
        LoginUserInfo loginUserInfo = UserUtil.getH5UserInfo();
        Assert.isNotNull(loginUserInfo, "获取用户信息失败");
        return loginUserInfo.getUserId();
    }

    /**
     * 获取登录用户信息
     */
    public LoginUserInfo getUserInfo() {
        LoginUserInfo loginUserInfo = UserUtil.getH5UserInfo();
        Assert.isNotNull(loginUserInfo, "获取用户信息失败");
        return loginUserInfo;
    }



    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }
}
