package com.znv.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author znv
 * @Description: xxx描述该类主要说明
 */
@Slf4j
@Controller
@Api(tags = "测试用接口类")
public class TestController {

    @ApiOperation(value = "swagger页面")
    @RequestMapping(value = "/swagger",method = RequestMethod.GET)
    public String swagger(){
        return "redirect:swagger-ui.html";
    }

    @ApiOperation(value = "websocket页面")
    @RequestMapping(value = "/websocket",method = RequestMethod.GET)
 	public String websocket() {
 		return "websocket";
 	}
}
