package com.shao.wacky.controller;

import com.shao.wacky.interceptor.RequireLogin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "hello world!";
    }
}
