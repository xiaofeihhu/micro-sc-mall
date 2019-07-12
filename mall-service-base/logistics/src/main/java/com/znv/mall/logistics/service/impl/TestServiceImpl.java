package com.znv.mall.logistics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.logistics.provider.TestServiceProvider;
import com.znv.mall.logistics.service.ITestService;
import com.znv.mall.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Slf4j
public class TestServiceImpl implements ITestService{

    @Autowired
    TestServiceProvider testServiceProvider;

    @Override
    public Result getTestConfig() {
        return testServiceProvider.getTestConfig();
    }

    @Override
    public Result getValue1(@RequestParam(value="param1",required = false) String param1,
                            @RequestParam(value="param2",required = false) String param2) {
        return testServiceProvider.getValue1(param1, param2);
    }

    @Override
    public Result getValue2(@RequestBody JSONObject jsonObject) {
        return testServiceProvider.getValue2(jsonObject);
    }
}
