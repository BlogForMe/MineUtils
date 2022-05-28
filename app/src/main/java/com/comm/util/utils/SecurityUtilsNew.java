package com.comm.util.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class SecurityUtilsNew {
//	private static final //logger //logger = //logger.get//logger(SecurityUtils.class);
	

	public static final String PASSWORD_KEY = "casanube_20180117_plmoknijbhvygctfcrdxeszwaq";

    public static final String keyBytes = "abcdefgabcdefg12";


	public static String AESEncode(String passwd,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
//            KeyGenerator keygen=KeyGenerator.getInstance("AES");
//            //2.根据ecnodeRules规则初始化密钥生成器
//            //生成一个128位的随机源,根据传入的字节数组
//            SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
//            random.setSeed(passwd.getBytes());
//            keygen.init(128, random);
//              //3.产生原始对称密钥
//            SecretKey original_key=keygen.generateKey();
//              //4.获得原始对称密钥的字节数组
//            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(getKey(passwd), "AES");
              //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=content.getBytes(StandardCharsets.UTF_8);
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);
          //10.将加密后的数据转换为字符串
            String AES_encode= Base64.getEncoder().encodeToString(byte_AES);
            return AES_encode;
        } catch (NoSuchAlgorithmException e) {
            //logger.error(e);
        } catch (NoSuchPaddingException e) {
            //logger.error(e);
        } catch (InvalidKeyException e) {
            //logger.error(e);
        } catch (IllegalBlockSizeException e) {
            //logger.error(e);
        } catch (BadPaddingException e) {
            //logger.error(e);
        }

        //如果有错就返加nulll
        return null;         
    }
    /*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDecode(String passwd,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
//            KeyGenerator keygen=KeyGenerator.getInstance("AES");
//            //2.根据ecnodeRules规则初始化密钥生成器
//            //生成一个128位的随机源,根据传入的字节数组
//            SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
//            random.setSeed(passwd.getBytes());
//            keygen.init(128, random);
//              //3.产生原始对称密钥
//            SecretKey original_key=keygen.generateKey();
//              //4.获得原始对称密钥的字节数组
//            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(getKey(passwd), "AES");
              //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byte_content=  Base64.getDecoder().decode(content);
            /*
             * 解密
             */
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode= new String(byte_decode, StandardCharsets.UTF_8);
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            //logger.error(e);
        } catch (NoSuchPaddingException e) {
            //logger.error(e);
        } catch (InvalidKeyException e) {
            //logger.error(e);
        } catch (IllegalBlockSizeException e) {
            //logger.error(e);
        } catch (BadPaddingException e) {
            //logger.error(e);
        }
        
        //如果有错就返加nulll
        return null;         
    }

    private static byte[] getKey(String password) {
        byte[] rByte = null;
        if (password!=null) {
            rByte = password.getBytes();
        }else{
            rByte = new byte[24];
        }
        return rByte;
    }

    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(PASSWORD_KEY.length());

        System.out.println(keyBytes.length());
    }
   

}
