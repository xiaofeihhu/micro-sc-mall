package com.znv.mall.user.service;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.core.entity.vo.Result;
import com.znv.mall.user.bean.UmsMember;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther: yf
 * @Date: 2019-6-19
 * @Description:
 */
public interface IUmsMemberService {

    Result login(JSONObject loginJson, HttpServletRequest httpServletRequest);

    Result register( String username,
                     String password,
                     String telephone,
                     String authCode);

    Result generateAuthCode(String telphone);
}
