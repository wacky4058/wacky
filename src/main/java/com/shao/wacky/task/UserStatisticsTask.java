package com.shao.wacky.task;

import com.shao.wacky.entity.User;
import com.shao.wacky.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 使用定时任务demo
 */
@Slf4j
@Component("userStatisticsTask")
public class UserStatisticsTask {

    @Autowired
    private UserServiceImpl userService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void doTask() {
        System.out.println("定时任务开启=-----------------------------------");
        List<User> users = userService.findAll();
        System.out.println("当前注册用户数为："+users.size());
        System.out.println("定时任务结束=-----------------------------------");
    }

}
