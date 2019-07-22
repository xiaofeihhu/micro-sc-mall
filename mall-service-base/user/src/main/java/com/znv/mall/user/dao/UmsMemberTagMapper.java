package com.znv.mall.user.dao;

import com.znv.mall.user.bean.UmsMemberTag;

public interface UmsMemberTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberTag record);

    int insertSelective(UmsMemberTag record);

    UmsMemberTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberTag record);

    int updateByPrimaryKey(UmsMemberTag record);
}