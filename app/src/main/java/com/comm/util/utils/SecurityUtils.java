package com.comm.util.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.text.TextUtils;
import android.util.Base64;


public class SecurityUtils {

	public static final String VIPARA = "0102030405060708";
	
	public static final String PASSWORD_CONFIG_KEY = "casanube_!@#$%^&";



	public static String MD5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void checkPassword(String passwd) {
		if(TextUtils.isEmpty(passwd) || passwd.length() != 16) {
			throw new RuntimeException("密码位数必须是16位");
		}
	}

//	public static String AESEncodeAllPlatform(String passwd, String content) {
//		checkPassword(passwd);
//		Cipher cipher = null;
//		byte[] byteContent;
//		try {
//			byteContent = content.getBytes("UTF-8");
//			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
//			SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
//			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
//			byte[] encryptedBytes = cipher.doFinal(byteContent);
//			String AES_encode = Base64.getEncoder().encodeToString(encryptedBytes);
//			return AES_encode;
//		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
//				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
//			//Logger.error(e);
//			throw new RuntimeException(e);
//		}
//	}
	
	public static String AESDecodeAllPlatform(String passwd, String content) {
		checkPassword(passwd);
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
			SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] byteContent = Base64.decode(content,Base64.DEFAULT);
			byte[] byteDecode = cipher.doFinal(byteContent);
			String AES_decode = new String(byteDecode, StandardCharsets.UTF_8);
			return AES_decode;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			//Logger.error(e);
			throw new RuntimeException(e);
		}
	
	}

	private static String toHex(byte[] bytes) {
		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}

	public static void main(String[] args) {
//		String str = AESEncodeAllPlatform(PASSWORD_CONFIG_KEY, "gggggggggggggggggggggggggggggggggggggggggg");
		String sffNew ="ugyetMkRIHwHrjeoE169dB3LIji5gWPUmmsRfHERkgo4Ht+M4mBjTdl4Vy/Uq+m9Ko20Pe4kZIYpCi0Wa0pBqpXqXxsUidEnIvd8o+m07wmkqy1TMV3Cl01UDQMTkKhWJJUJnUFJYiQwGrjeYVQsFcoBZ28nS2ovqSILGGKPV/eOCfpNm58B0wWPXljF4zZ9Q+mL2w3WnUhIF0UuGHrl7CAcHczIMsIqP+wOk1vcdkbLOEBu7Z/T7iIhyhNt3Flh3vSCGvRJq28mguN4pWR29ADDL+F8HMUqJW99bjolOck=";

		String content = AESDecodeAllPlatform(PASSWORD_CONFIG_KEY, sffNew);




//		System.out.println(str);
		System.out.println(content);
	}
}
