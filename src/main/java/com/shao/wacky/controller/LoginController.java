package com.shao.wacky.controller;

import com.shao.wacky.annotation.RequireLogin;
import com.shao.wacky.entity.User;

import com.shao.wacky.service.UserServiceImpl;
import com.shao.wacky.utils.JWTUtil;
import com.shao.wacky.utils.PasswordUtil;
import com.shao.wacky.vo.LoginBody;
import com.shao.wacky.vo.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录接口类
 */
@RestController
public class LoginController {

    @Resource
    UserServiceImpl userService;

    @PostMapping("login")
    @RequireLogin(login = false)
    public ResultVo<Map<String, Object>> login(@Validated @RequestBody LoginBody form) {
        // 根据用户名 获取用户对象
        User user = userService.findByName(form.getUsername());
        //验证密码
        if (null==user || !PasswordUtil.matches(form.getPassword(),user.getPassWord())){
            return ResultVo.fail("用户名或密码不存在");
        }
        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put("access_token", JWTUtil.getToken(user));
        return ResultVo.ok(rspMap);
    }

}
