package com.znv.mall.logistics.provider;

import com.znv.mall.logistics.provider.fallback.ConcurentServiceFallback;
import com.znv.mall.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "producer",fallback = ConcurentServiceFallback.class)
public interface ConcurentServiceProvider {

    @GetMapping("/concurentTest1")
    Result concurentTest(@RequestParam(value = "param1", required = false) String param1);
}
