package com.znv.mall.gate.controller;

import com.znv.mall.core.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("测试接口类")
public class TestController {

    @GetMapping("/test")
    @ApiOperation("测试接口方法类")
    public Result test() {
        return Result.success("hello,world!");
    }
}
