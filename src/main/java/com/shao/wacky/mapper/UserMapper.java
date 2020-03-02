package com.shao.wacky.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shao.wacky.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wacky
 * @since 2020-03-02
 */
public interface UserMapper extends BaseMapper<User> {
    User Sel(int id);
}