package com.comm.util.utils;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class EnDecryptionUtil {

    public static final String key = "casanube_20180117_plmoknijbhvygctfcrdxeszwaq";

//    public static SecretKey generateKey(String seed) throws Exception {
//        // 获取秘钥生成器
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        // 通过种子初始化
//        SecureRandom secureRandom = new SecureRandom();
//        secureRandom.setSeed(seed.getBytes("UTF-8"));
////        keyGenerator.init(128, secureRandom);
//        // 生成秘钥并返回
//        return keyGenerator.generateKey();
//    }
//
//    public static byte[] decript(byte[] content,SecretKey secretKey)throws  Exception{
//        //密钥
//        byte[] enCodeFormat = secretKey.getEncoded();
//        //创建Aes密钥
//        SecretKeySpec key = new SecretKeySpec(enCodeFormat,"AES");
//        //创建密码器
//        Cipher cipher = Cipher.getInstance("AES");
//        //初始化解密器
//        cipher.init(Cipher.DECRYPT_MODE,key);
//        //解密
//        return  cipher.doFinal(content);
//    }
//    private static final String CipherMode = "AES/ECB/PKCS5Padding";使用ECB加密，不需要设置IV，但是不安全
private static final String CipherMode = "AES/CFB/NoPadding";//使用CFB加密，需要设置IV

    /**
     * 对字符串加密
     *
     * @param key  密钥
     * @param data 源字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String key, String data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, new IvParameterSpec(
                    new byte[cipher.getBlockSize()]));
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对字符串解密
     *
     * @param key  密钥
     * @param data 已被加密的字符串
     * @return 解密得到的字符串
     */
    public static String decrypt(String key, String data) throws Exception {
        try {
//            byte[] encrypted1 = Base64.decode(data.getBytes(), Base64.DEFAULT);
            Cipher cipher = Cipher.getInstance(CipherMode);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keyspec, new IvParameterSpec(
                    new byte[cipher.getBlockSize()]));
            byte[] original = cipher.doFinal(data.getBytes());
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
