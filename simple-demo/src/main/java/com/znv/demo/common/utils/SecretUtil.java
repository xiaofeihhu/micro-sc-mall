package com.znv.demo.common.utils;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 加密解密工具类
 * </p>
 *
 */
@Component
public class SecretUtil {
    @Autowired
    private StringEncryptor encryptor;

    /**
     * 生成加密密码
     */
    public String encrypt(String password) {
        // 加密后的密码(注意：配置上去的时候需要加 ENC(加密密码))
        return encryptor.encrypt(password);
    }

    /**
     * 生成加密密码
     */
    public String decrypt(String password) {
        // 解密后的密码
        return encryptor.decrypt(password);
    }

    /**
     * 测试加密密码
     */
    public void test() {
        // 你的邮箱密码
        String password = "zxm10";
        // 加密后的密码(注意：配置上去的时候需要加 ENC(加密密码))
        String encryptPassword = encryptor.encrypt(password);
        String decryptPassword = encryptor.decrypt(encryptPassword);

        System.out.println("password = " + password);
        System.out.println("encryptPassword = " + encryptPassword);
        System.out.println("decryptPassword = " + decryptPassword);
    }

}
