package com.znv.mall.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.consumer.jwt.Audience;
import com.znv.mall.consumer.jwt.HS256JwtTokenUtil;
import com.znv.mall.consumer.jwt.JwtIgnore;
import com.znv.mall.consumer.service.ITestService;
import com.znv.mall.core.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RefreshScope
@Slf4j
@Api("consumer接口测试类")
public class TestController {
    @Autowired
    ITestService iTestService;

    @Autowired
    private Audience audience;

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

    @PostMapping("test2")
    @ApiOperation("测试RequestBody")
    public Result getValue2(@RequestBody JSONObject jsonObject,HttpServletRequest httpServletRequest) {
        return iTestService.getValue2(jsonObject);
    }

    @GetMapping("/sessionInfo")
    @ApiOperation("获取session")
    public Result getSessionInfo(HttpServletRequest httpServletRequest) {
        return Result.success(httpServletRequest.getSession().getAttribute(httpServletRequest.getSession().getId()));
    }

    @GetMapping("/sessionInfoFromProducer")
    @ApiOperation("获取session从Producer")
    public Result getSessionInfoFromProducer(HttpServletRequest httpServletRequest) {
        return iTestService.getSessionInfo();
    }

    @PostMapping("/sessionInfo")
    @ApiOperation("设置session")
    public Result setSessionInfo(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute(httpServletRequest.getSession().getId(),"abc123");
        return Result.success(httpServletRequest.getSession().getAttribute(httpServletRequest.getSession().getId()));
    }

    @PostMapping("/login")
    @ApiOperation("JWT登录")
    @JwtIgnore
    public Result login(HttpServletResponse response, String username, String password) {
        // 这里模拟测试, 默认登录成功，返回用户ID和角色信息
        String userId = UUID.randomUUID().toString();
        String role = "admin";

        // 创建token
        String token = HS256JwtTokenUtil.createJWT(userId, username, role, audience);
        log.info("### 登录成功, token={} ###", token);

        // 将token放在响应头
        response.setHeader(HS256JwtTokenUtil.AUTH_HEADER_KEY, HS256JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        return Result.success(result);
    }

}
