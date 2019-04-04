package cn.com.hinova.ruvs.utils;

public class StringUtils {
	
	public static String strs(String...strs){
		StringBuilder sb=new StringBuilder();
		for (String str : strs) {
			sb.append(str);
		}
		return sb.toString();
	}

}
