package com.shao.wacky.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shao.wacky.entity.User;
import com.shao.wacky.mapper.UserMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wacky
 * @since 2020-03-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>{

    public User findByName(String userName){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        queryWrapper.eq(User::getUserName, userName);
        return getOne(queryWrapper);
    }
}
