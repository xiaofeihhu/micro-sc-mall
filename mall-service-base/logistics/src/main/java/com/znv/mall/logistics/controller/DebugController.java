package com.znv.mall.logistics.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: yf
 * @Date: 2019-6-26
 * @Description:
 */
@Controller
@Api("debug接口测试类")
@Slf4j
public class DebugController {

    @GetMapping("websocket")
    @ApiOperation("websocket页面跳转")
    public String websocket(Model model,HttpServletRequest httpServletRequest) {

        model.addAttribute("sessionId",httpServletRequest.getSession().getId());
        return "websocket";
    }
}
