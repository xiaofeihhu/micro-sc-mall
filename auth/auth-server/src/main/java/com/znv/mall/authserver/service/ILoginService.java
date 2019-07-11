package com.znv.mall.authserver.service;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.core.entity.vo.Result;

import javax.servlet.http.HttpSession;

/**
 * @Auther: yf
 * @Date: 2019-6-19
 * @Description:
 */
public interface ILoginService {

    Result login(JSONObject loginJson, HttpSession httpSession);
}
