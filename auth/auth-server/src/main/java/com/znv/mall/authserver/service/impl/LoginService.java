package com.znv.mall.authserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.authserver.service.ILoginService;
import com.znv.mall.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Auther: yf
 * @Date: 2019-6-19
 * @Description:
 */
@Service
@Slf4j
public class LoginService implements ILoginService{
    @Override
    public Result login(JSONObject loginJson, HttpSession httpSession) {
        String userName = loginJson.getString("userName");
        String password = loginJson.getString("password");
        if ("admin".equalsIgnoreCase(userName)
                && "admin".equalsIgnoreCase(password)) {
            log.info("login success!! userName:{}, password:{}",userName,password);
            httpSession.setMaxInactiveInterval(-1); //admin用户session永不超时
            httpSession.setAttribute(httpSession.getId(),userName);
            return Result.success(httpSession.getId());
        }

        if ("guest".equalsIgnoreCase(userName)
                && "guest".equalsIgnoreCase(password)) {
            log.info("login success!! userName:{}, password:{}",userName,password);
            // guest用户使用配置的session超时时间
            httpSession.setAttribute(httpSession.getId(),userName);
            return Result.success(httpSession.getId());
        }
        return Result.fail();
    }
}
