package com.znv.mall.user.dao;

import com.znv.mall.user.bean.UmsAdminLoginLog;

public interface UmsAdminLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminLoginLog record);

    int insertSelective(UmsAdminLoginLog record);

    UmsAdminLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdminLoginLog record);

    int updateByPrimaryKey(UmsAdminLoginLog record);
}