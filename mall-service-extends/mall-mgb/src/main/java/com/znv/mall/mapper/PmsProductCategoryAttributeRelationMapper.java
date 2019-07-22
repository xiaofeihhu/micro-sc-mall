package com.znv.mall.mapper;

import com.znv.mall.model.PmsProductCategoryAttributeRelation;

public interface PmsProductCategoryAttributeRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductCategoryAttributeRelation record);

    int insertSelective(PmsProductCategoryAttributeRelation record);

    PmsProductCategoryAttributeRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductCategoryAttributeRelation record);

    int updateByPrimaryKey(PmsProductCategoryAttributeRelation record);
}