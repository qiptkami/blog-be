package com.qipt.service.impl;

import com.qipt.mapper.UserMapper;
import com.qipt.pojo.User;
import com.qipt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectOne(String username) {
        return userMapper.selectOne(username);
    }
}
