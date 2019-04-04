package cn.com.hinova.ruvs.utils;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtils {

	
	public static String encode(String str){
		return Base64.encodeBase64String(DESUtils.encrypt(str.getBytes(), DESUtils.KEY));
	}
	
	public static String decode(String str) throws Exception{
		return new String(DESUtils.decrypt(Base64.decodeBase64(str), DESUtils.KEY));
	}
	
}
