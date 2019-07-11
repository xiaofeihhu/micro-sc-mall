package com.znv.mall.authorization.service.impl;

import com.znv.mall.authorization.dao.UserMapper;
import com.znv.mall.authorization.entity.User;
import com.znv.mall.authorization.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }
}
