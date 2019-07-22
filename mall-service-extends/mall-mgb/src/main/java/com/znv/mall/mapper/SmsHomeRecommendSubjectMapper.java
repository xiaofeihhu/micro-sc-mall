package com.znv.mall.mapper;

import com.znv.mall.model.SmsHomeRecommendSubject;

public interface SmsHomeRecommendSubjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeRecommendSubject record);

    int insertSelective(SmsHomeRecommendSubject record);

    SmsHomeRecommendSubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsHomeRecommendSubject record);

    int updateByPrimaryKey(SmsHomeRecommendSubject record);
}