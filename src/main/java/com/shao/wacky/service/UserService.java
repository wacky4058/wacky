package com.shao.wacky.service;

import com.shao.wacky.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User Sel(int id);
}
