package com.shao.wacky.controller;

import com.shao.wacky.entity.User;


import com.shao.wacky.service.UserServiceImpl;
import com.shao.wacky.utils.PasswordUtil;
import com.shao.wacky.vo.RegisterBody;
import com.shao.wacky.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserServiceImpl userService;


    /**
     * 用户注册
     *
     * @param form
     * @return
     */
    @RequestMapping("/register")
    public ResultVo register(@Validated @RequestBody RegisterBody form) {
        //对密码加密
        String pwdEncode = PasswordUtil.encode(form.getPassword());
        User user = new User();
        user.setPassWord(pwdEncode);
        user.setUserName(form.getUsername());
        user.setRealName(form.getRealName());
        userService.save(user);
        return ResultVo.ok();
    }





}
