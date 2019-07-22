package com.znv.mall.mapper;

import com.znv.mall.model.OmsOrderReturnReason;

public interface OmsOrderReturnReasonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderReturnReason record);

    int insertSelective(OmsOrderReturnReason record);

    OmsOrderReturnReason selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderReturnReason record);

    int updateByPrimaryKey(OmsOrderReturnReason record);
}