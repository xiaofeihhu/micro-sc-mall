package com.znv.mall.logistics.provider;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.logistics.provider.fallback.TestServiceFallback;
import com.znv.mall.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "producer",fallback = TestServiceFallback.class)
public interface TestServiceProvider {

    @GetMapping("/config")
    Result getTestConfig();

    @GetMapping("/test1")
    Result getValue1(@RequestParam(value = "param1", required = false) String param1,
                     @RequestParam(value = "param2", required = false) String param2);

    @GetMapping(value = "/test2",consumes = MediaType.APPLICATION_JSON_VALUE)
    Result getValue2(@RequestBody JSONObject jsonObject);
}
