package com.znv.demo.controller;

import com.znv.demo.common.utils.DateUtil;
import com.znv.demo.service.ManageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api("缓存cache测试接口")
@Slf4j
@RequestMapping("/redisCache")
public class RedisCacheController {

    @Autowired
    ManageService manageService;

    @GetMapping("/getValueWithNoParam")
    public String getValueWithNoParam() {
        return "hello--" +  DateUtil.getStringDate();
    }

    @GetMapping("/getId")
    public String getId(@RequestParam String id) {
        log.info("get id:{}",id);
        return "hello--" + id + "-" + DateUtil.getStringDate();
    }

    @GetMapping("/getValueByAnyParam")
    public String getValueByAnyParam(@RequestParam(value = "param1", required = false) String param1,
                                      @RequestParam(value = "param2", required = false) String param2,
                                      @RequestParam(value = "param3", required = false) String param3) {
        log.info("getValueByAnyParam:{},{},{}",param1,param2,param3);
        return "hello--" + param1 + "-" + param2 + "-" + param3 + DateUtil.getStringDate();
    }

    @GetMapping("/getValueByMoreParam")
    public String getValueByMoreParam(@RequestParam String param1,
                                      @RequestParam int param2,
                                      @RequestParam Date param3) {
        log.info("getValueByMoreParam:{},{},{}",param1,param2,param3);
        return "hello--" + param1 + "-" + param2 + "-" + param3 + DateUtil.getStringDate();
    }

    @GetMapping("/getIdName")
    public String getIdName(@RequestParam String id, @RequestParam String name) {
        return manageService.getIdNameWithCache(id, name);
    }

    @PostMapping("/updateId")
    public String updateId(@RequestParam String id) {
        log.info("update id:{}",id);
        return "hello--" + id;
    }

    @PostMapping("/deleteId")
    public void deleteId(@RequestParam String id) {
        log.info("delete id:{}",id);
    }

    @GetMapping("/getLists")
    public List<Map<String,Object>> getLists(@RequestParam String id) {
        log.info("get List:{}",id);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("value1",new Date());
        list.add(map);
        return list;
    }

    @PostMapping("/updateLists")
    public List<Map<String,Object>> updateLists(@RequestParam String id) {
        log.info("update List:{}",id);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("value1",new Date());
        list.add(map);
        return list;
    }

    @PostMapping("/deleteLists")
    public void deleteLists() {
        log.info("delete cacheList");
    }
}
