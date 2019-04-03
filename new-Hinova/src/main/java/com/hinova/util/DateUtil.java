package com.hinova.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.commonj.ScheduledTimerListener;

import com.mysql.fabric.xmlrpc.base.Data;

/**
 * ���ڹ�����
 * @author Administrator
 */
public class DateUtil {

	public static void main(String s[]) throws Exception{
		System.out.println(dateToWeek("2018-01-01"));
	}
	
	
	/**
	 * yyyyMMdd hhmmssSSS
	 * ���ڶ���ת�ַ���
	 */
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	/**
	 * �ַ���ת���ڶ���
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	
	/**
	 * ����һ���·ݣ�2017-01
	 * ���   2017-01-31
	 */
	public static String getDays(String str){
		str = dateAddMonth(str, "yyyy-MM", 1);//2017-01---2017-02
		str = str+"-01";//2017-02-01
		str = dateAddDay(str, "yyyy-MM-dd", -1);////2017-01-31
		return str;
	}
		
		/**
		 * ��2��ʱ����
		 * @param end
		 * @param begin
		 * @return
		 */
		public static long date_between(Date end,Date begin ){
			/*SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   Date begin=dfs.parse("2004-01-02 11:30:24");
			   Date end = dfs.parse("2004-01-02 11:31:40");*/
			   long between=(end.getTime()-begin.getTime())/1000;//����1000��Ϊ��ת������
			   long min=between/60;
			   System.out.println("���"+min);
			   return min;
		}
		
		
		/**
		 *  �����ʽ������
		 * @param date1
		 * @param date2
		 * @return 1��2�� ����1   ��ȷ���0   С�ڷ���-1
		 */
		public static int compareTo(String date1,String date2){
			return date1.compareTo(date2);
		}
		
		
		

		/**
		 * �����ʽ ��1��  ����  ��-1��
		 * @param dateStr ʱ���ַ��� 2018-05-05 12:11
		 * @param dateFormat   ʱ���ʽ   yyyy-MM-dd HH:mmssSSS
		 * @param n  �Ӷ�����
		 */
		public static String dateAddDay(String dateStr,String dateFormat,  int n) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Calendar cd = Calendar.getInstance();
				cd.setTime(sdf.parse(dateStr));
				cd.add(Calendar.DATE, n);// ����һ��
				// cd.add(Calendar.MONTH, n);//����һ����
				return sdf.format(cd.getTime());

			} catch (Exception e) {
				return null;
			}
		}
		
		
		/**
		 * �����ʽ ��1��  ����  ��-1��
		 * @param dateStr ʱ���ַ��� 2018-05-05 12:11
		 * @param dateFormat   ʱ���ʽ   yyyy-MM-dd HH:mmssSSS
		 * @param n  �Ӷ�����
		 */
		public static String dateAddMonth(String dateStr,String dateFormat,  int n) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Calendar cd = Calendar.getInstance();
				cd.setTime(sdf.parse(dateStr));
				//cd.add(Calendar.DATE, n);// ����һ��
				cd.add(Calendar.MONTH, n);//����һ����
				return sdf.format(cd.getTime());

			} catch (Exception e) {
				return null;
			}
		}
		
		
		/**
		 * �����ʽ   ��1Сʱ  ����  ��-1Сʱ
		 * @param dateStr ʱ���ַ��� 2018-05-05 12:11
		 * @param dateFormat   ʱ���ʽ   yyyy-MM-dd HH:mmssSSS
		 * @param n  �Ӷ�����
		 */
		public static String dateAddHour(String dateStr,String dateFormat,  int n) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Calendar cd = Calendar.getInstance();
				cd.setTime(sdf.parse(dateStr));
				cd.add(Calendar.HOUR, n);//����һ����
				return sdf.format(cd.getTime());
			} catch (Exception e) {
				return null;
			}
		}
		
		
		/**
		 * @param datetime  2018-01-01
		 * @return  ����һ
		 */
	    public static String dateToWeek(String datetime) {
	        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	        String[] weekDays = { "������", "����һ", "���ڶ�", "������", "������", "������", "������" };
	        Calendar cal = Calendar.getInstance(); // ���һ������
	        Date datet = null;
	        try {
	            datet = f.parse(datetime);
	            cal.setTime(datet);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // ָʾһ�������е�ĳ�졣
	        if (w < 0)
	            w = 0;
	        return weekDays[w];
	    }
	    
		
}
