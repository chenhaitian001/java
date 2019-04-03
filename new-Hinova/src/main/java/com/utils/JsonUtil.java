package com.utils;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

/**
 * Json工具类      jackson包
 * 
 * @author xiangf
 * 
 */
public class JsonUtil {

	private static ObjectMapper objectMapper;

	/**
	 * 初始化ObjectMapper，jackson核心类
	 */  
	static {
		objectMapper = new ObjectMapper();
		// --- 默认配置 ---
		// 当找不到对应的序列化器时，忽略此字段
		//objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		// 当bean中没有对应属性时，忽略此字段
		//objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 配置日期类型序列化格式
		//objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper.setDateFormat(df);
	}

	/**
	 * 将Java对象序列化为Json
	 * 
	 * @return Json字符串
	 * @throws Exception
	 */
	public static String toJson(Object obj) {
		if (obj != null) {
			try {
				return objectMapper.writeValueAsString(obj);
			} catch (Exception e) {
				e.printStackTrace();
				return obj.toString();
			}
		} else {
			return null;
		}
	}

	public static <T> T listFromJson(String json, Class<?> clazz) {
		try {
			return (T) objectMapper.readValue(json,
					objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Json对象反序列化
	 * 
	 * @param json
	 *            Json字符串
	 * @param clazz
	 *            对象类型
	 * @return 反序列化后的对象
	 * @throws Exception
	 */
	public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
		return objectMapper.readValue(json, clazz);
	}

	/**
	 * Json对象反序列化
	 * 
	 * @param is
	 *            输入流
	 * @param clazz
	 *            对象类型
	 * @return 反序列化后的对象
	 * @throws Exception
	 */
	public static <T> T fromJson(InputStream is, Class<T> clazz) throws Exception {
		return objectMapper.readValue(is, clazz);
	}

	/**
	 * Json对象反序列化
	 * 
	 * @param json
	 *            json字节数组
	 * @param clazz
	 *            对象类型
	 * @return 反序列化后的对象
	 * @throws Exception
	 */
	public static <T> T fromJson(byte[] json, Class<T> clazz) throws Exception {
		return objectMapper.readValue(json, clazz);
	}

	/**
	 * Json对象反序列化
	 * 
	 * @param json
	 *            Json字符串
	 * @param clazz
	 *            对象类型
	 * @return 反序列化后的对象
	 * @throws Exception
	 */
	public static Object objectFromJson(String json, Class<?> clazz) throws Exception {
		return objectMapper.readValue(json, clazz);
	}

	/**
	 * Json对象反序列化
	 * 
	 * @param is
	 *            输入流
	 * @param clazz
	 *            对象类型
	 * @return 反序列化后的对象
	 * @throws Exception
	 */
	public static Object objectFromJson(InputStream is, Class<?> clazz) throws Exception {
		return objectMapper.readValue(is, clazz);
	}

	/**
	 * Json对象反序列化
	 * 
	 * @param json
	 *            json字节数组
	 * @param clazz
	 *            对象类型
	 * @return 反序列化后的对象
	 * @throws Exception
	 */
	public static Object objectFromJson(byte[] json, Class<?> clazz) throws Exception {
		return objectMapper.readValue(json, clazz);
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static void setObjectMapper(ObjectMapper objectMapper) {
		JsonUtil.objectMapper = objectMapper;
	}
}
