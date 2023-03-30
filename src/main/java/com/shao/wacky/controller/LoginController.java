package com.shao.wacky.controller;

import com.shao.wacky.entity.User;
import com.shao.wacky.utils.JWTUtil;
import com.shao.wacky.vo.LoginBody;
import com.shao.wacky.vo.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录接口类
 */
@RestController
public class LoginController {

    @PostMapping("login")
    public ResultVo<Map<String, Object>> login(@Validated @RequestBody LoginBody form) {
        // 用户登录
        //String accessToken = sysLoginService.login(form.getUsername(), form.getPassword());

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();

        rspMap.put("access_token", JWTUtil.getToken(new User()));
        return ResultVo.ok(rspMap);
    }

}
