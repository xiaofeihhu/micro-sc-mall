package com.znv.mall.mapper;

import com.znv.mall.model.PmsProductAttributeCategory;

public interface PmsProductAttributeCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttributeCategory record);

    int insertSelective(PmsProductAttributeCategory record);

    PmsProductAttributeCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttributeCategory record);

    int updateByPrimaryKey(PmsProductAttributeCategory record);
}