package com.shao.wacky.controller;

import com.shao.wacky.annotation.NeedLogin;
import com.shao.wacky.exception.WackyException;
import com.shao.wacky.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @NeedLogin(value = NeedLogin.need)
    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
        if (0==id){
            logger.debug("id未输入正确");
            throw new WackyException(400,"请输入正确的id");
        }
        return userService.Sel(id).toString();
    }




}
