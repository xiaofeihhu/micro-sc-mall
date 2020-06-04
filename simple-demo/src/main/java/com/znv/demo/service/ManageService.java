package com.znv.demo.service;

import com.znv.demo.common.bean.Result;

import java.util.List;
import java.util.Map;

/**
 * @author znv
 * @ClassName: ManageService
 * @Description:
 * @date 2018/5/18 14:56
 */
public interface ManageService {

    Result startServer(String id)throws Exception;
    
    List<Map<String,Object>> queryServer(Map<String, Object> recordMap);

}
