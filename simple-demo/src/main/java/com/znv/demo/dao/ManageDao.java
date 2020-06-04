package com.znv.demo.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author znv
 * @ClassName:
 * @Description: xx描述xx
 * @date 2018/5/17 11:14
 */
@Repository
public interface ManageDao {
    /**
     * 根据查询字段查询监控服务所有信息
     * @param recordMap 查询条件
     * @return 查询结果
     */
    List<Map<String,Object>> queryServer(Map<String, Object> recordMap);

    /**
     * 修改服务信息
     * @param recordMap
     */
    void updateServer(Map<String, Object> recordMap);



}
