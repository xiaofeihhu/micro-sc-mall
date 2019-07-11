package com.znv.mall.authorization.service.impl;

import com.znv.mall.authorization.dao.RoleMapper;
import com.znv.mall.authorization.entity.Role;
import com.znv.mall.authorization.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<Role> queryUserRolesByUserId(long userId) {
        return roleMapper.queryByUserId(userId);
    }

}
