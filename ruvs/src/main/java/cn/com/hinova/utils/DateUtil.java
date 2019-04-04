package cn.com.hinova.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static SimpleDateFormat getFmt(String fmtstr){
		return new SimpleDateFormat(fmtstr);
	}


	public static Date parserDate(String str,SimpleDateFormat fmt){
		try {
			return fmt.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	public static String getYMDHMSS() {
		String strYMDHMSS = "";
		Date currentDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		strYMDHMSS = formatter.format(currentDateTime);
		return strYMDHMSS;
	}
}
