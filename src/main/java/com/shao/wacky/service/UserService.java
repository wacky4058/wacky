package com.shao.wacky.service;
import com.shao.wacky.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wacky
 * @since 2020-03-02
 */
public interface UserService extends IService<User> {
     User Sel(int id);
}
