package com.shao.wacky.service.impl;

import com.shao.wacky.entity.User;
import com.shao.wacky.mapper.UserMapper;
import com.shao.wacky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User Sel(int id) {
        return userMapper.Sel(id);
    }
}
