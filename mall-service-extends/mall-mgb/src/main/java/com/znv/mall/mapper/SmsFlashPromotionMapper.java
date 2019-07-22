package com.znv.mall.mapper;

import com.znv.mall.model.SmsFlashPromotion;

public interface SmsFlashPromotionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsFlashPromotion record);

    int insertSelective(SmsFlashPromotion record);

    SmsFlashPromotion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsFlashPromotion record);

    int updateByPrimaryKey(SmsFlashPromotion record);
}