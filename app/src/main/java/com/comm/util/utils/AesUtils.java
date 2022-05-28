package com.comm.util.utils;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class AesUtils {
	
	public static String Encrypt(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
//		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		
		byte[] byts = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		IvParameterSpec iv = new IvParameterSpec(byts);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());

		return Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	

	// 解密
	public static String Decrypt(String sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes(StandardCharsets.US_ASCII);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			IvParameterSpec iv = new IvParameterSpec(
//					"0102030405060708".getBytes());
			byte[] byts = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec iv = new IvParameterSpec(byts);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
	}
}