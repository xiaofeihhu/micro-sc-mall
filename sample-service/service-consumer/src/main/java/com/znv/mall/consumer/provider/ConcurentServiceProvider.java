package com.znv.mall.consumer.provider;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.consumer.provider.fallback.ConcurentServiceFallback;
import com.znv.mall.consumer.provider.fallback.TestServiceFallback;
import com.znv.mall.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "producer",fallback = ConcurentServiceFallback.class)
public interface ConcurentServiceProvider {

    @GetMapping("/concurentTest1")
    Result concurentTest(@RequestParam(value="param1",required = false) String param1);
}
