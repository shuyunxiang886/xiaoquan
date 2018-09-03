package com.shuyunxiang.dao;

import com.shuyunxiang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    User getUser(String username);
    void insertUser(User user);
}
