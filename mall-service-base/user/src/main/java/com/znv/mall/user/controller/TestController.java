package com.znv.mall.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.user.service.ITestService;
import com.znv.mall.core.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
@Slf4j
@Api("接口测试类")
public class TestController {
    @Autowired
    ITestService iTestService;

    @GetMapping("config")
    @ApiOperation("测试config中心")
    public Result getTestConfig(HttpServletRequest request) {
        return iTestService.getTestConfig();
    }

    @GetMapping("test1")
    @ApiOperation("测试RequestParam")
    public Result getValue1(@RequestParam(value="param1",required = false) String param1,
                                @RequestParam(value="param2",required = false) String param2) {
        return iTestService.getValue1(param1, param2);
    }

    @GetMapping("test2")
    @ApiOperation("测试RequestBody")
    public Result getValue2(@RequestBody JSONObject jsonObject) {
        return iTestService.getValue2(jsonObject);
    }

    @GetMapping("/sessionInfo")
    @ApiOperation("获取session信息接口")
    public Result getSessionInfo(HttpServletRequest httpServletRequest) {
        return Result.success(httpServletRequest.getSession().getAttribute(httpServletRequest.getSession().getId()));
    }
}
