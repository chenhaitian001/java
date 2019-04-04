package cn.com.hinova.ruvs.utils;

import java.util.HashMap;
import java.util.Map;

import cn.com.hinova.ruvs.common.exception.CFException;

public class MapUtils {


	public static Map<Object,Object> toMap(Object...objs){
		if(objs.length%2!=0){
			throw new CFException(StringUtils.strs("toMap 的参数一定要是偶数，您传了 [ ",objs.length+""," ] 个"));
		}
		Map<Object,Object> objMap=new HashMap<Object,Object>();
		for (int i = 0; i < objs.length; i+=2) {
			objMap.put(objs[i], objs[i+1]);
		}
		return objMap;
	}
	

	public static Map<String,String> toMap(String...strs){
		if(strs.length%2!=0){
			throw new CFException(StringUtils.strs("toMap 的参数一定要是偶数，您传了 [ ",strs.length+""," ] 个"));
		}
		Map<String,String> objMap=new HashMap<String,String>();
		for (int i = 0; i < strs.length; i+=2) {
			objMap.put(strs[i], strs[i+1]);
		}
		return objMap;
	}
}
