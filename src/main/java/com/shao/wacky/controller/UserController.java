package com.shao.wacky.controller;

import com.shao.wacky.annotation.NeedLogin;
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
        logger.info("日志打印");
        return userService.Sel(id).toString();
    }


}
