/**
 * 
 */
package link.x86.wx.map.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

/**
 * @author xuwei
 * 
 */
public class GetDateUtil {
    /**
     * 求出指定日期的所在月的第一天
     */
    public static Date getFirstDayInMonth(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateTime);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 求出指定日期的所在月的最后一天
     */
    public static Date getLastDayInMonth(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateTime);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }

    /**
     * 得到这个日期是星期几，星期返回值是1，星期天是7,以此类推
     */
    public static int getWeek(Date dateTime) {
        if (null == dateTime) {
            return -1;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateTime);
        return changeWeek(cal.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * 把国际惯例的星期日为第一天，改为星期一位第一天
     */
    private static int changeWeek(int a) {
        if (a != 1) {
            return a - 1;
        } else {
            return 7;
        }
    }

    /**
     * 转换日期格式yyyy-MM-dd 转换成yyyyMMdd
     */
    public static String changeDateToYYYYMMDD(String date) {
        SimpleDateFormat line = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date time = line.parse(date);
            return sdf.format(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
    public static String  getStr(String str) {
        if (str.length() <= 2) {
            return str;
        }
        return str.substring(0, 2) + ":" + getStr(str.substring(2));
    }
    public static String isWeekend(String bDate) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
        Date bdate = format1.parse(bDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return "OK";
        } else{
            return "NO";
        }
 
    }
    public static int month(){
    	Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
    	int month = c.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
    	return month;
    }
    public static int year(){
    	Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
    	int year = c.get(Calendar.YEAR);    //获取年
    	return year;
    }
    //获取某个月的天数
    public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
    public static String HHmmss(){
    	SimpleDateFormat format = new SimpleDateFormat("HHmmss");
    	Date date = new Date();
    	String HHmmss = format.format(date);
    	
		return HHmmss;
    	
    }
    
    public static Boolean ifEmpty(List list){
    	if(list!=null && !list.isEmpty()){
    		if(!StringUtils.isEmpty(list)&&list.get(0)!=null){
        		return true;
        	}else{
        		return false;
        	}
    		
    	}else{
    		
    		return false;
    	}
    }
}

