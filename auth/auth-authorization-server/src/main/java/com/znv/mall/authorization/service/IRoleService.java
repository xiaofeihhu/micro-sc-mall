package com.znv.mall.authorization.service;

import com.znv.mall.authorization.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IRoleService {

    Set<Role> queryUserRolesByUserId(long userId);

}
