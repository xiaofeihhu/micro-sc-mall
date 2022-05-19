package com.znv.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.znv.demo.common.utils.DateUtil;
import com.znv.demo.common.utils.RedisUtil;
import com.znv.demo.service.ManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Api("缓存cache管理接口")
@Slf4j
public class CacheManageController {

    private static final String cacheCountZSetKey = "cache:"+"keyHitCount";

    @Autowired
    ManageService manageService;

    @ApiOperation("获取缓存命中次数列表")
    @GetMapping("/getCacheHitCountList")
    public JSONObject getCacheHitCountList() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",cacheCountZSetKey);
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = RedisUtil.zReverseRangeWithScores(cacheCountZSetKey, 0, -1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTuples.iterator();
        JSONArray jsonArray = new JSONArray();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> next = iterator.next();
            JSONObject jsonObjectTmp = new JSONObject();
            jsonObjectTmp.put("key",next.getValue());
            jsonObjectTmp.put("count",next.getScore());
            jsonArray.add(jsonObjectTmp);
        }
        jsonObject.put("list",jsonArray);
        return jsonObject;
    }

}
