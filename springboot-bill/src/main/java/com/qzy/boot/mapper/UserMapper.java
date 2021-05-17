package com.qzy.boot.mapper;

import com.qzy.boot.entities.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUsers(User user);

    User getUserById(Integer id);

    int updateUser(User user);
}
