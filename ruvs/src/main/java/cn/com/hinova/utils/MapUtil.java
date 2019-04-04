package cn.com.hinova.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

	public static Map<String,String> toMap(String...strs){
		Map<String,String> map=new HashMap<String, String>();
		for (int i=0;i<strs.length;i+=2) {
			map.put(strs[i], strs[i+1]);
		}
		return map;
	}
	
	public static Object get(Map<Object,Object> map,Object key,Object defaultValue){
		Object obj=map.get(key);
		if(obj==null){
			return defaultValue;
		}else{
			return obj;
		}
	}

	public static Map<Object, Object> toMap(Object...objs) {
		Map<Object,Object> map=new HashMap<Object, Object>();
		for (int i=0;i<objs.length;i+=2) {
			map.put(objs[i], objs[i+1]);
		}
		return map;
	}
}
