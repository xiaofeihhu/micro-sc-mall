package com.znv.demo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


/**
 * @Description:AES加密工具
 */
@Slf4j
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    //默认的加密算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");
// 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
// 加密
            byte[] result = cipher.doFinal(byteContent);

            //Base64是一种基于64个可打印字符来表示二进制数据的表示方法。
            //通过Base64转码返回
            return Base64Utils.encodeToString(result);
        } catch (Exception ex) {
            log.error("Exception", ex);
        }

        return null;
    }

    /**
     * * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            //Base64是一种基于64个可打印字符来表示二进制数据的表示方法。
            byte[] result = cipher.doFinal(Base64Utils.decodeFromString(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            log.error("Exception", ex);
        }
        return null;
    }

    /**
     * * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            //AES 要求密钥长度为 128
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            random.setSeed(password.getBytes());
            kg.init(128, random);

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (Exception ex) {
            log.error("Exception", ex);
        }
        return null;
    }

    public static void main(String[] args) {
        String origin = "杨杨";
        String encrypt = AESUtil.encrypt(origin, "dhf0049001262");
        String decrypt = AESUtil.decrypt(encrypt, "dhf0049001262");
        System.out.println(origin);
        System.out.println(encrypt);
        System.out.println(decrypt);
    }
}

