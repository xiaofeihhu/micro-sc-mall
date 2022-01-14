package com.znv.demo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Api("缓存cache测试接口")
@Slf4j
public class CacheController {

    @GetMapping("/getId")
    @Cacheable(cacheNames = "cache")
    public String getId(@RequestParam String id) {
        log.info("get id:{}",id);
        return "hello--" + id;
    }

    @PostMapping("/updateId")
    @CachePut(cacheNames = "cache", key = "#id")
    public String updateId(@RequestParam String id) {
        log.info("update id:{}",id);
        return "hello--" + id;
    }

    @PostMapping("/deleteId")
    @CacheEvict(cacheNames = "cache", key = "#id")
    public void deleteId(@RequestParam String id) {
        log.info("delete id:{}",id);
    }

    @GetMapping("/getLists")
    @Cacheable(cacheNames = "cacheList")
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
    @CachePut(cacheNames = "cacheList", key = "#id")
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
    @CacheEvict(value = "cacheList",allEntries = true)
    public void deleteLists() {
        log.info("delete cacheList");
    }
}
