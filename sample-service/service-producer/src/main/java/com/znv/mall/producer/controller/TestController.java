package com.znv.mall.producer.controller;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.core.entity.vo.Result;
import com.znv.mall.extensions.mail.MailBean;
import com.znv.mall.extensions.mail.MailUtil;
import com.znv.mall.extensions.mq.KafkaDataProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api("测试接口类")
@RestController
@RefreshScope
@Slf4j
public class TestController {

    @Value("${producer.config1}")
    String testConfig;

    @Value("${key_common}")
    String commonKey1;

    @Value("${key_1}")
    String key1;

    @Autowired
    KafkaDataProducer kafkaDataProducer;

    @Autowired
    MailUtil mailUtil;

    @ApiOperation("获取配置中心-动态配置")
    @GetMapping("config")
    public Result getTestConfig(HttpServletRequest request) {
        log.info("token:{}",request.getHeader("Authorization"));
        return Result.success(testConfig);
    }

    @ApiOperation("获取配置中心-公共配置")
    @GetMapping("commonKey1")
    public Result getCommonKey1() {
        return Result.success(commonKey1+"-"+key1);
    }

    @ApiOperation("传多个参数")
    @GetMapping("test1")
    public Result getValue1(@RequestParam(value="param1",required = false) String param1,
                                @RequestParam(value="param2",required = false) String param2) {
        return Result.success(param1 + "-" + param2);
    }

    @ApiOperation("传json参数")
    @PostMapping("test2")
    public Result getValue2(@RequestBody JSONObject jsonObject,HttpServletRequest httpServletRequest) {
        return Result.success("hello-" + jsonObject.toJSONString());
    }

    @GetMapping("/sessionInfo")
    @ApiOperation("获取session信息接口")
    public Result getSessionInfo(HttpServletRequest httpServletRequest) {
        return Result.success(httpServletRequest.getSession().getAttribute(httpServletRequest.getSession().getId()));
    }

    @PostMapping("/sessionInfo")
    @ApiOperation("设置session")
    public Result setSessionInfo(String value , HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute(httpServletRequest.getSession().getId(),value);
        return Result.success(httpServletRequest.getSession().getAttribute(httpServletRequest.getSession().getId()));
    }

    @PostMapping("/sendKafka")
    @ApiOperation("测试发送kafka数据接口")
    public Result sendDataToKafka(@RequestParam(value="msg",required = false) String msg) {
        kafkaDataProducer.send("topic1",msg);
        return Result.success();
    }

    @PostMapping("/sendMail")
    @ApiOperation("测试发送邮件")
    public Result sendMail(@RequestBody MailBean mailBean) {
        mailUtil.sendSimpleMail(mailBean);
        return Result.success();
    }
}
