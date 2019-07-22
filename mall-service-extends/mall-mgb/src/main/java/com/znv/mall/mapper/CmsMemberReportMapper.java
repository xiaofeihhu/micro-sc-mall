package com.znv.mall.mapper;

import com.znv.mall.model.CmsMemberReport;

public interface CmsMemberReportMapper {
    int insert(CmsMemberReport record);

    int insertSelective(CmsMemberReport record);
}