package com.znv.mall.user.service;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.core.entity.vo.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ITestService {
    Result getTestConfig();

    Result getValue1(@RequestParam(value = "param1", required = false) String param1,
                     @RequestParam(value = "param2", required = false) String param2);

    Result getValue2(@RequestBody JSONObject jsonObject);
}
