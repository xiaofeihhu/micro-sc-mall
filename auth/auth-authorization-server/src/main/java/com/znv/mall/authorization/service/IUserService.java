package com.znv.mall.authorization.service;

import com.znv.mall.authorization.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    @Cacheable(value = "#id")
    User getByUsername(String username);
}
