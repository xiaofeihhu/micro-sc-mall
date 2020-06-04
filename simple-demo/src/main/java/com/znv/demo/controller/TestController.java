package com.znv.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author znv
 * @Description: xxx描述该类主要说明
 */
@Slf4j
@Controller
@Api(tags = "测试用接口类")
public class TestController {

    @ApiOperation(value = "swagger页面")
    @GetMapping(value = "/swagger")
    public String swagger(){
        return "redirect:swagger-ui.html";
    }

    @ApiOperation(value = "websocket页面")
    @GetMapping("/websocket")
 	public String websocket() {
 		return "websocket";
 	}
}
