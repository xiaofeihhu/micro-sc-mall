package com.znv.mall.user.provider.fallback;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.user.provider.TestServiceProvider;
import com.znv.mall.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Component
public class TestServiceFallback implements TestServiceProvider{
    @Override
    public Result getTestConfig() {
        log.error("getTestConfig fallback");
        return Result.fail("xxx");
    }

    @Override
    public Result getValue1(@RequestParam(value="param1",required = false) String param1,
                            @RequestParam(value="param2",required = false) String param2) {
        log.error("getValue1 fallback");
        return Result.fail("getValue1--xxx");
    }

    @Override
    public Result getValue2(@RequestBody JSONObject jsonObject) {
        log.error("getValue2 fallback");
        return Result.fail("getValue2--xxx");
    }
}
