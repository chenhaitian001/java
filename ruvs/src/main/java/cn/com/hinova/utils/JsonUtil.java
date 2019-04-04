package cn.com.hinova.utils;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper mapper=new ObjectMapper();
	public static <T> T parser(String jsonStr,Class<T> valueType){
		
		try {
			return mapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static String toStr(Object obj){
		
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
