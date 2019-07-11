package com.znv.mall.gate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@Api("swagger页面跳转")
public class SwaggerUiController {

    @GetMapping("/swagger")
    @ApiOperation("swagger页面跳转")
    public String swagger(HttpSession session) {
        session.setAttribute("username", "swagger");
        return "redirect:swagger-ui.html";
    }
}
