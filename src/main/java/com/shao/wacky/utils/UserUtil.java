package com.shao.wacky.utils;


import javax.servlet.http.HttpServletRequest;
import com.shao.wacky.vo.LoginUserInfo;

/**
 * 用户相关工具类
 * @author chengdu
 * @date 2023/3/20
 */
public class UserUtil {

    /**
     * Attribute 缓存登录信息 LoginUserInfo
     */
    private final static String ATTRIBUTE_TOKEN = "token";

    public static void setH5UserInfo(HttpServletRequest req, LoginUserInfo loginUserInfo) {
        req.setAttribute(ATTRIBUTE_TOKEN, loginUserInfo);
    }

    public static LoginUserInfo getH5UserInfo() {
        HttpServletRequest request = RequestContextUtil.request();
        Assert.isNotNull(request, "参数错误");

        Object attribute = request.getAttribute(ATTRIBUTE_TOKEN);
        Assert.isNotNull(attribute, "token 失效");

        return (LoginUserInfo) attribute;
    }

}
