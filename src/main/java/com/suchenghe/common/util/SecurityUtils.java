package com.suchenghe.common.util;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Java加密算法
 *
 * @author SuChenghe
 * @date 2018/05/03 15:10
 */

public class SecurityUtils {

    public SecurityUtils() {
    }

    /**
     * HMAC：密钥生成
     *
     * @return 密钥
     * @throws Exception
     */
    public static byte[] getSecretKeyForHmac() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGenerator.generateKey();
        byte[] keyBytes = key.getEncoded();
        return keyBytes;
    }

    /**
     * HMAC：将数据加密
     *
     * @param key  密钥
     * @param data
     * @return 加密数据
     * @throws Exception
     */
    public static String encryptByHmac(byte[] key, byte[] data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);
        byte[] resultBytes = mac.doFinal(data);
        String resultString = byte2hex(resultBytes);
        return resultString;
    }

    /**
     * MD5加密
     *
     * @param str
     * @return 加密数据
     */
    public static String encryptByMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] e = md.digest(str.getBytes());
            return byte2hex(e);
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException(var3);
        }
    }

    /**
     * SHA-1加密
     *
     * @param str
     * @return 加密数据
     */
    public static String encryptBySHA1(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] e = md.digest(str.getBytes());
            return byte2hex(e);
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException(var3);
        }
    }

    /**
     * SHA-256加密
     *
     * @param str
     * @return 加密数据
     */
    public static String encryptBySHA256(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] e = md.digest(str.getBytes());
            return byte2hex(e);
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException(var3);
        }
    }

    private static String byte2hex(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int n = 0; null != b && n < b.length; ++n) {
            String tmp = Integer.toHexString(b[n] & 0xff);
            if (tmp.length() == 1) {
                builder.append('0');
            }
            builder.append(tmp);
        }
        return builder.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if (b.length % 2 != 0) {
            throw new IllegalArgumentException();
        } else {
            byte[] b2 = new byte[b.length / 2];
            for (int n = 0; n < b.length; n += 2) {
                String item = new String(b, n, 2);
                b2[n / 2] = (byte) Integer.parseInt(item, 16);
            }
            return b2;
        }
    }
}
