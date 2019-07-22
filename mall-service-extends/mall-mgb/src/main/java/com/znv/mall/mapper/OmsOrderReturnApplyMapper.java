package com.znv.mall.mapper;

import com.znv.mall.model.OmsOrderReturnApply;

public interface OmsOrderReturnApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderReturnApply record);

    int insertSelective(OmsOrderReturnApply record);

    OmsOrderReturnApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderReturnApply record);

    int updateByPrimaryKey(OmsOrderReturnApply record);
}