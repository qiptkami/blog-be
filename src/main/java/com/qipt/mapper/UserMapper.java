package com.qipt.mapper;

import com.qipt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectOne(String username);
}
