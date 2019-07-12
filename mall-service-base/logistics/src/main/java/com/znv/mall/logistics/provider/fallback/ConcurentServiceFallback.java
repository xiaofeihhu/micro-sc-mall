package com.znv.mall.logistics.provider.fallback;

import com.znv.mall.logistics.provider.ConcurentServiceProvider;
import com.znv.mall.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Component
public class ConcurentServiceFallback implements ConcurentServiceProvider {

    @Override
    public Result concurentTest(@RequestParam(value="param1",required = false) String param1) {
        return Result.fail("concurentTest--failed");
    }
}
