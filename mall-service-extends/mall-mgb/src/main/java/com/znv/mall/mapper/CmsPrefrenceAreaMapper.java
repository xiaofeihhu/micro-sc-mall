package com.znv.mall.mapper;

import com.znv.mall.model.CmsPrefrenceArea;

public interface CmsPrefrenceAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmsPrefrenceArea record);

    int insertSelective(CmsPrefrenceArea record);

    CmsPrefrenceArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsPrefrenceArea record);

    int updateByPrimaryKeyWithBLOBs(CmsPrefrenceArea record);

    int updateByPrimaryKey(CmsPrefrenceArea record);
}