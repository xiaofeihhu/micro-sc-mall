package com.znv.mall.producer.controller;

import com.znv.mall.core.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Auther: yf
 * @Date: 2019-6-29
 * @Description:
 */
@Api("并发接口测试类")
@RestController
public class ConcurentTestController {

    @GetMapping("/concurentTest1")
    @ApiOperation("高并发测试接口1")
    public Result concurentTest1(@RequestParam(value="param1",required = false) String param1) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.success(param1);
    }
}
