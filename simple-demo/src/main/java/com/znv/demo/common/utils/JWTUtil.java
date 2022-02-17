package com.znv.demo.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {


    /**
     * 默认本地密钥
     * @notice: 非常重要，请勿泄露
     */
    private static final String SECRET = "doyoulikevanyouxi?"; //乱打的

    /**
     * 默认有效时间单位，为分钟
     */
    private static final int TIME_TYPE = Calendar.MINUTE;

    /**
     * 默认有效时间长度，同http Session时长，为60分钟
     */
    private static final int TIME_AMOUNT = 600;

    /**
     * 全自定生成令牌
     * @param payload payload部分
     * @param secret 本地密钥
     * @param timeType 时间类型：按Calender类中的常量传入:
     *         Calendar.YEAR;
     *         Calendar.MONTH;
     *         Calendar.HOUR;
     *         Calendar.MINUTE;
     *         Calendar.SECOND;等
     * @param expiredTime 过期时间，单位由 timeType 决定
     * @return 令牌
     */
    public static String generateToken(Map<String,String> payload, String secret, int timeType, int expiredTime){
        JWTCreator.Builder builder = JWT.create();

        //payload部分
        payload.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(timeType,expiredTime);

        //设置超时时间
        builder.withExpiresAt(instance.getTime());

        //签名
        return builder.sign(Algorithm.HMAC256(secret)).toString();
    }

    /**
     * 生成token
     * @param payload payload部分
     * @return 令牌
     */
    public static String generateToken(Map<String,String> payload){
        return generateToken(payload,SECRET,TIME_TYPE,TIME_AMOUNT);
    }



    /**
     * 验证令牌合法性
     * @param token 令牌
     * @return
     */
    public static void verify(String token) {
        //如果有任何验证异常，此处都会抛出异常
        JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * 自定义密钥解析
     * @param token 令牌
     * @param secret 密钥
     * @return 结果
     */
    public static DecodedJWT parseToken(String token, String secret) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
        return decodedJWT;
    }

    /**
     * 解析令牌
     * 当令牌不合法将抛出错误
     * @param token
     * @return
     */
    public static DecodedJWT parseToken(String token) {
        return parseToken(token,SECRET);
    }

    /**
     * 解析令牌获得payload,值为claims形式
     * @param token
     * @param secret
     * @return
     */
    public static Map<String, Claim> getPayloadClaims(String token, String secret){
        DecodedJWT decodedJWT = parseToken(token,secret);
        return decodedJWT.getClaims();
    }

    /**
     * 默认解析令牌获得payload,值为claims形式
     * @param token 令牌
     * @return
     */
    public static Map<String,Claim> getPayloadClaims(String token){
        return getPayloadClaims(token,SECRET);
    }

    /**
     * 解析令牌获得payload,值为String形式
     * @param token 令牌
     * @return
     */
    public static Map<String,String> getPayloadString(String token,String secret){
        Map<String, Claim> claims = getPayloadClaims(token,secret);
        Map<String,String> payload = new HashMap<>();
        claims.forEach((k,v)->{
            if("exp".equals(k)){
                payload.put(k,v.asDate().toString());
            }
            else {
                payload.put(k, v.asString());
            }
        });

        return payload;
    }
    /**
     * 默认解析令牌获得payload,值为String形式
     * @param token 令牌
     * @return
     */
    public static Map<String,String> getPayloadString(String token){
        return getPayloadString(token,SECRET);
    }


//    /**
//     * 通过用户实体生成令牌
//     * @param user 用户实体
//     * @return
//     */
//    public static String generateUserToken(Account user){
//        return generateUserToken(user.getId());
//    }

    /**
     * 通过用户id生成令牌
     * @param accountId 用户id
     * @return
     */
    public static String generateUserToken(Integer accountId){
        return generateUserToken(accountId.toString());
    }


    /**
     *  通过用户id生成令牌
     * @param accountId 用户id
     * @return
     */
    public static String generateUserToken(String accountId){
        Map<String,String> payload = new HashMap<>();
        payload.put("accountId",accountId);
        return generateToken(payload);
    }

    /**
     * 从令牌中解析出用户id
     * @param token 令牌
     * @return
     */
    public static String parseUserToken(String token){
        Map<String, String> payload = getPayloadString(token);
        return payload.get("accountId");
    }

    public static void main(String[] args) {
        String accountId = "znv";
        String jwt = generateUserToken(accountId);
        System.out.println(jwt);
        System.out.println(getPayloadString(jwt));
    }
}
