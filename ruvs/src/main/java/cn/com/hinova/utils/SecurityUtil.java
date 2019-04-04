package cn.com.hinova.utils;

import java.io.UnsupportedEncodingException;

/**
 * 加密工具类
 *
 */
public class SecurityUtil {
	
	
	private static byte STATIC_SAL=0X04;
	private static String CHARACTER_ENCODING="UTF-8";
	

	/**
	 * 
	 * @param str 需要加密的字符串
	 * @return 已加密的字符串
	 */
	public static String encrypt(String str){
		
		try {
			byte[] sbytes=str.getBytes(CHARACTER_ENCODING);
			byte[] bytes=new byte[sbytes.length+1];
			
			byte sal=(byte) (System.nanoTime()%0XFF);
			for (int i = 0; i < sbytes.length; i++) {
				bytes[i]=(byte) (sbytes[i]^sal);
			}
			bytes[bytes.length-1]=(byte) (sal^STATIC_SAL);
			return bytes2hex(bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 
	 * @param str 需要解密的字符串
	 * @return 已解密的字符串
	 */
	public static String decrypt(String hexStr){
		
		try {
			
			byte[] bytes=hex2bytes(hexStr);
			
			byte sal=(byte) (bytes[bytes.length-1]^STATIC_SAL);
			
			byte[] sbytes=new byte[bytes.length-1];
			for (int i = 0; i < bytes.length-1; i++) {
				sbytes[i]=(byte) (bytes[i]^sal);
			}
			
			return new String(sbytes,CHARACTER_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String bytes2hex(byte[] bytes){
		StringBuilder sb=new StringBuilder(bytes.length);
		for (byte b : bytes) {
			String hex=Integer.toHexString(b&0xff);
			if(hex.length()==1){sb.append("0");}
			sb.append(hex);
		}
		return sb.toString().toUpperCase();
	}
	
	public static byte[] hex2bytes(String hex){
		
		if((!hex.matches("[0-9A-Z]+"))||hex.length()%2!=0){
			throw new RuntimeException("hex 字符串异常");
		}
		hex=hex.toLowerCase();
		byte[] bytes =new byte[hex.length()/2];
		for (int i = 0; i < hex.length(); i+=2) {
			bytes[i/2]=(byte) (Integer.parseInt(hex.substring(i, i+2), 16)&0xff);
		}
		return bytes;
	}
	
}
