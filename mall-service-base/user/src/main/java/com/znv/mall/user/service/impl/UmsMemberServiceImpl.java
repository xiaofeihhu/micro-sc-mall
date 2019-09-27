package com.znv.mall.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.znv.mall.core.entity.vo.Result;
import com.znv.mall.core.util.ThreadPoolHelper;
import com.znv.mall.servicecommon.constants.ServiceCommonConstants;
import com.znv.mall.servicecommon.util.RedisUtil;
import com.znv.mall.user.bean.UmsMember;
import com.znv.mall.user.bean.UmsMemberLoginLog;
import com.znv.mall.user.dao.UmsMemberLoginLogMapper;
import com.znv.mall.user.dao.UmsMemberMapper;
import com.znv.mall.user.service.IUmsMemberService;
import com.znv.mall.user.util.SecretUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static com.znv.mall.servicecommon.constants.ServiceCommonConstants.*;

/**
 * @Auther: yf
 * @Date: 2019-6-19
 * @Description:
 */
@Service
@Slf4j
public class UmsMemberServiceImpl implements IUmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Autowired
    UmsMemberLoginLogMapper umsMemberLoginLogMapper;

    @Autowired
    SecretUtil secretUtil;

    @Value("${user.login.failed.maxcount:3}")
    int maxFailedCount;

    @Value("${user.login.failed.expireseconds:300}")
    int expireSeconds;

    @Override
    public Result login(JSONObject loginJson, HttpServletRequest httpServletRequest) {
        String userName = loginJson.getString("userName");
        String password = loginJson.getString("password");
        UmsMember umsMemberParam = new UmsMember();
        umsMemberParam.setUsername(userName);
        List<UmsMember> umsMembers = umsMemberMapper.selectByCondition(umsMemberParam);
        if (CollectionUtils.isEmpty(umsMembers)) {
            Result.fail("该用户不存在");
        }

        UmsMember curUmsMember = umsMembers.get(0);
        // 判断登录失败次数是否大于规定次数
        String userKey = ServiceCommonConstants.REDIS_KEY_LOGIN_FAIL_COUNT + curUmsMember.getId();
        if (RedisUtil.hasKey(userKey)) {
            int failedCount = (int) RedisUtil.get(userKey);
            if (failedCount > maxFailedCount) {
                return Result.fail(String.format("密码错误超过%s次，用户已锁定,请过%s秒再试",maxFailedCount,expireSeconds));
            }
        }
        if (secretUtil.encrypt(password).equals(curUmsMember.getPassword())) {
            // 清空失败次数
            RedisUtil.del(userKey);
            HttpSession httpSession = httpServletRequest.getSession();
//            httpSession.setMaxInactiveInterval(-1); //admin角色的用户session永不超时
            httpSession.setAttribute(httpSession.getId(),userName);

            // 异步记录登录日志
            ThreadPoolHelper.getInstance().execute(()->insertLoginLog(curUmsMember,httpServletRequest));

            // TODO 获取用户的角色和权限等信息放入session

            return Result.success(httpSession.getId());
        }

        // 密码错误的场景下，往redis中增加错误次数，并设置有效期
        long failedCount = RedisUtil.incr(userKey,1);
        RedisUtil.expire(userKey,expireSeconds);
        if (failedCount > maxFailedCount) {
            return Result.fail(String.format("密码错误超过%s次，用户已锁定,请过%s秒再试",maxFailedCount,expireSeconds));
        }

        return Result.fail("密码错误");
    }

    private void insertLoginLog(UmsMember umsMember,HttpServletRequest httpServletRequest) {
        UmsMemberLoginLog umsMemberLoginLog = new UmsMemberLoginLog();
        umsMemberLoginLog.setMemberId(umsMember.getId());
        umsMemberLoginLog.setCity(umsMember.getCity());
        umsMemberLoginLog.setCreateTime(new Date());
        umsMemberLoginLog.setIp(httpServletRequest.getRemoteAddr());
        umsMemberLoginLogMapper.insert(umsMemberLoginLog);
    }


    @Override
    public Result register(String username, String password, String telephone, String authCode) {

        // 校验验证码
        if (!checkAuthCode(telephone, authCode)) {
            return Result.fail("验证码错误！");
        }

        // 验证该用户名或手机号是否已被注册
        UmsMember umsMemberParam = new UmsMember();
        umsMemberParam.setUsername(username);
        umsMemberParam.setPhone(telephone);
        List<UmsMember> existUmsMembers = umsMemberMapper.selectByCondition(umsMemberParam);
        if (existUmsMembers!=null && existUmsMembers.size()>0) {
            return Result.fail("该用户已存在！");
        }

        // 注册成功 写入数据库
        umsMemberParam.setPassword(secretUtil.encrypt(password));
        umsMemberMapper.insert(umsMemberParam);
        // 异步设置会员默认等级


        return Result.success("注册成功！");
    }

    private boolean checkAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }

        // 验证码过了有效期
        if (!RedisUtil.hasKey(REDIS_KEY_REGISTER_AUTHCODE_PREFIX+telephone)) {
            return false;
        }
        // 校验是否相等
        if (authCode.equals(String.valueOf(RedisUtil.get(REDIS_KEY_REGISTER_AUTHCODE_PREFIX+telephone)))) {
            return true;
        }

        return false;
    }

    @Override
    public Result generateAuthCode(String telphone) {
        // 生成6位数验证码 并存入redis 设置5分钟过期时间
        String randomCode = String.format("%06d", RandomUtils.nextInt(1000000));
        RedisUtil.set(REDIS_KEY_REGISTER_AUTHCODE_PREFIX+telphone,randomCode,REDIS_KEY_REGISTER_AUTHCODE_EXPIRE);

        return Result.success(randomCode);
    }

}
