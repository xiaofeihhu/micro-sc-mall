package com.znv.mall.mapper;

import com.znv.mall.model.SmsCoupon;

public interface SmsCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsCoupon record);

    int insertSelective(SmsCoupon record);

    SmsCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsCoupon record);

    int updateByPrimaryKey(SmsCoupon record);
}