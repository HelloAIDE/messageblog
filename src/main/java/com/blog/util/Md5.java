package com.blog.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Md5 {
	private static final String salt="blog";
	//加盐md5加密
	public static String saltMD5(String str){
		try {
			byte[] data = str.getBytes("utf-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
			md.update(salt.getBytes("utf-8"));
			byte[] md5=md.digest();
			String code= Base64.encodeBase64String(md5);
			return code;
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException();
		}
	}
	//加密base64位
	public static String base64(String str){
		try {
			byte[] data = str.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
			
			byte[] md5=md.digest();
			String code= Base64.encodeBase64String(md5);
			return code;
			
			
			
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException();
		}
	}
	//md5加密
	public static String md5(String str){
		try {
			
			
			byte[] data = str.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
			
			byte[] md5=md.digest();
			//设置编码格式为md5
			String code= Hex.encodeHexString(md5);
			return code;
			
			
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
		}
	}
}
