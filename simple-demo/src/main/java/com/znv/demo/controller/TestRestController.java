package com.znv.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.znv.demo.common.bean.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author znv
 * @Description: xxx描述该类主要说明
 */
@Slf4j
@RestController
@RequestMapping("/rest")
@Api(tags = "测试REST风格接口")
public class TestRestController {

    @ApiOperation(value = "返回结果为Result")
    @GetMapping(value = "/test1")
    @SentinelResource(value = "test",blockHandler = "exceptionHandler", fallback = "testFallback")
    public Result test1(@RequestParam(name = "id") String id){
        log.debug("-----"+id+"-------");
        return new Result(id);
    }

    @ApiOperation(value = "返回结果为String")
    @GetMapping(value = "/test2")
    public String test2(@RequestParam(name = "id",required = false) String id){
        log.debug("-----"+id+"-------");
        return id;
    }

    @ApiOperation(value = "返回结果为List")
    @GetMapping(value = "/test3")
    public List<Map<String,Object>> test3(){

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("key1","value1");
        map1.put("key2","value2");
        list.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("key3","value3");
        map2.put("key4","value4");
        list.add(map2);

        return list;
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String testFallback(String s) {
        return String.format("Halooooo %s", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(String s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }
}
