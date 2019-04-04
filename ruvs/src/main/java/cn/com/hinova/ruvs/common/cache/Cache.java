package cn.com.hinova.ruvs.common.cache;

import java.util.Map;

public class Cache {

	public static String basePath="/data/wwwroot/java-project";

	public static ThreadLocal<Map<String,String[]>> paramMapLocal=new ThreadLocal<Map<String,String[]>>();
	
}
