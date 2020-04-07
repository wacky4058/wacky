package com.shao.wacky.controller;

import com.shao.wacky.annotation.NeedLogin;
import com.shao.wacky.entity.User;
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
    @RequestMapping("getUser")
    public String GetUser(Integer id){

        if (null==id){
            logger.debug("id未输入正确");
            throw new WackyException(400,"请输入正确的id");
        }
        User u = userService.Sel(id);
        if (null==u){
            throw new WackyException(401,"未找到数据");
        }else{
            return null==u?"":u.toString();
        }

    }




}
