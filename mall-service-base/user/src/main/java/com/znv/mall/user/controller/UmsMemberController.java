package com.znv.mall.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.core.entity.vo.Result;
import com.znv.mall.user.service.IUmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: yf
 * @Date: 2019-6-19
 * @Description: 登录相关接口类
 */
@Slf4j
@RestController
@Api("登录登出类")
public class UmsMemberController {

    @Autowired
    IUmsMemberService iUmsMemberService;

    @PostMapping("/login")
    @ApiOperation(value="登录接口",notes="{\n" +
            "  \"userName\": \"guest\",\n" +
            "  \"password\": \"guest\"\n" +
            "}")
    public Result login(@RequestBody JSONObject loginJson, HttpServletRequest httpServletRequest) {
        return iUmsMemberService.login(loginJson,httpServletRequest);
    }

    @GetMapping("/sessionInfo")
    @ApiOperation("获取session信息接口")
    public Result getSessionInfo(HttpServletRequest httpServletRequest) {
        return Result.success(httpServletRequest.getSession().getAttribute(httpServletRequest.getSession().getId()));
    }

    @PostMapping("/logout")
    @ApiOperation("登出接口")
    public Result logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute(httpServletRequest.getSession().getId());
        return Result.success();
    }
}
