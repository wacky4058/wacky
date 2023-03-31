package com.shao.wacky.controller;

import com.shao.wacky.entity.User;


import com.shao.wacky.annotation.RequireLogin;
import com.shao.wacky.service.UserServiceImpl;
import com.shao.wacky.utils.PasswordUtil;
import com.shao.wacky.vo.RegisterBody;
import com.shao.wacky.vo.ResultVo;
import com.shao.wacky.vo.UpPasswordBody;
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

    /**
     * 修改密码
     * @return
     */
    @RequestMapping("/updatePassword")
    @RequireLogin
    public ResultVo updatePassword(@Validated @RequestBody UpPasswordBody form){
        User user = userService.getById(form.getUserId());
        if (null==user){
            return ResultVo.fail("用户不存在");
        }
        //验证密码
        if (!PasswordUtil.matches(form.getOldPassword(),user.getPassWord())){
            return ResultVo.fail("旧密码不正确");
        }
        user.setPassWord(PasswordUtil.encode(form.getNewPassword()));
        userService.updateById(user);
        return ResultVo.ok();
    }





}
