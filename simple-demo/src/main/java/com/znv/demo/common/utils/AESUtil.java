package com.znv.demo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;


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

    /**
     * AES加密
     * @param content 明文
     * @param encryptKey 秘钥，必须为16个字符组成
     * @return 密文
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(encryptKey)) {
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        byte[] encryptStr = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptStr);
    }

    /**
     * AES解密
     * @param encryptStr 密文
     * @param decryptKey 秘钥，必须为16个字符组成
     * @return 明文
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        if (StringUtils.isEmpty(encryptStr) || StringUtils.isEmpty(decryptKey)) {
            return null;
        }

        byte[] encryptByte = Base64.getDecoder().decode(encryptStr);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptByte);
        return new String(decryptBytes);
    }

    public static void main(String[] args) {
        String origin = "888888";
        String encrypt = null;
        try {
            encrypt = AESUtil.aesEncrypt(origin, "cBssbHB3ZA==HKXT");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s = "832AA27302AD26A46525E43047254047";
        String decrypt = null;
        try {
            decrypt = AESUtil.aesDecrypt(s, "cBssbHB3ZA==HKXT");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String decrypt = AESUtil.decrypt(encrypt, "dhf0049001262");
        System.out.println(origin);
        System.out.println(encrypt);
        System.out.println(decrypt);
    }
}

