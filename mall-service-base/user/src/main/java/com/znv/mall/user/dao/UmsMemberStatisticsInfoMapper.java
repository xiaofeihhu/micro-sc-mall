package com.znv.mall.user.dao;

import com.znv.mall.user.bean.UmsMemberStatisticsInfo;

public interface UmsMemberStatisticsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberStatisticsInfo record);

    int insertSelective(UmsMemberStatisticsInfo record);

    UmsMemberStatisticsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberStatisticsInfo record);

    int updateByPrimaryKey(UmsMemberStatisticsInfo record);
}