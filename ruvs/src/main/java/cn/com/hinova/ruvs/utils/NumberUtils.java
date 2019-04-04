package cn.com.hinova.ruvs.utils;

import java.text.DecimalFormat;

public class NumberUtils {

	public static Integer str2int(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception ex) {
			return null;
		}
	}

	public static Long str2long(String str) {
		try {
			return Long.parseLong(str);
		} catch (Exception ex) {
			return null;
		}
	}

	public static Float str2float(String str) {
		try {
			return Float.parseFloat(str);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static Float str2float(String str,float defaultValue) {
		try {
			return Float.parseFloat(str);
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	public static Double str2double(String str) {
		try {
			return Double.parseDouble(str);
		} catch (Exception ex) {
			return null;
		}
	}

	public static Integer toInt(String string, int i) {
		try {
			Integer value = Integer.parseInt(string);
			if (value == null) {
				value = i;
			}
			return value;
		} catch (Exception e) {
			return i;
		}
	}

	
	public static String format(double d,String fmt){
		DecimalFormat  dfmt=new DecimalFormat(fmt);
		return dfmt.format(d);
	}
	
	
}
