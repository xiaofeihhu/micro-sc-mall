package com.znv.mall.authserver.rest;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.authserver.service.ILoginService;
import com.znv.mall.core.entity.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther: yf
 * @Date: 2019-6-19
 * @Description: 登录相关接口类
 */
@Slf4j
@RestController
@Api("登录登出类")
public class LoginController {

    @Autowired
    ILoginService iLoginService;

    @PostMapping("/login")
    @ApiOperation(value="登录接口",notes="{\n" +
            "  \"userName\": \"guest\",\n" +
            "  \"password\": \"guest\"\n" +
            "}")
    public Result login(@RequestBody JSONObject loginJson, HttpServletRequest httpServletRequest) {
        return iLoginService.login(loginJson,httpServletRequest.getSession());
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
