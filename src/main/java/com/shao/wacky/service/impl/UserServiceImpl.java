package com.shao.wacky.service.impl;

import com.shao.wacky.entity.User;
import com.shao.wacky.mapper.UserMapper;
import com.shao.wacky.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;
    @Override
    public User Sel(int id) {
        return userMapper.Sel(id);
    }
}
