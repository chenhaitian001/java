package cn.com.hinova.ruvs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	private static Logger logger=LoggerFactory.getLogger(DateUtils.class);

	public static SimpleDateFormat FORMAT_YD= new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat FORMAT_YHH= new SimpleDateFormat("yyyy-MM-dd HH");
	public static SimpleDateFormat FORMAT_YM= new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat FORMAT_YS= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	private static Map<String,SimpleDateFormat> formats=new HashMap<String,SimpleDateFormat>();
	static{
		formats.put("10", new SimpleDateFormat("yyyy-MM-dd"));
		formats.put("13", new SimpleDateFormat("yyyy-MM-dd HH"));
		formats.put("16", new SimpleDateFormat("yyyy-MM-dd HH:mm"));
		formats.put("19", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		//formats.put("23", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:fff"));
	}


	public static String date2str(Date date,SimpleDateFormat fmt){
	    return fmt.format(date);
    }
	
	public static Date str2date(String str){
		String formatType=str.length()+"";
		SimpleDateFormat format=formats.get(formatType);
		if(format!=null){
			try {
				return format.parse(str);
			} catch (ParseException e) {
				logger.error(StringUtils.strs("时间数据 [ ",str," ] 解析异常"),e);
				return null;
			}
		}else{
			logger.error(StringUtils.strs("时间数据 [ ",str," ] 没有响应的解析格式"));
			return null;
		}
	}
	
	public static String format(Date date, SimpleDateFormat x){
		return formats.get(x).format(date);
	}
}
