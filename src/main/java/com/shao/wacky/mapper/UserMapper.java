package com.shao.wacky.mapper;
import com.shao.wacky.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User Sel(int id);
}
