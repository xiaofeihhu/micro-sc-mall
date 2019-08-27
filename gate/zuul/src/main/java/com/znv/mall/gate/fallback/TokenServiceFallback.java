package com.znv.mall.gate.fallback;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.gate.feign.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenServiceFallback implements ITokenService{
    @Override
    public JSONObject verifyToken(String token) {
        log.error("调用{}异常:{}","verifyToken",token);
        throw new RuntimeException("verifyToken ERROR");
//        return null;
    }
}
