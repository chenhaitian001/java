package ruvs_wx;

import java.util.Calendar;

public class test {
public static void main(String[] args) {
 Calendar c = Calendar.getInstance();
 System.out.println(c);
 
int year = c.get(Calendar.YEAR);
int month = c.get(Calendar.MONTH) + 1;
int date = c.get(Calendar.DAY_OF_MONTH);
int hour = c.get(Calendar.HOUR);
int minute = c.get(Calendar.MINUTE);
int second = c.get(Calendar.SECOND);
int week = c.get(Calendar.DAY_OF_WEEK);//1--7的值,对应：星期日，星期一，星期二，星期三....星期六

System.out.println(year + "年" + month + "月" + date + "日 " + hour + ":" + minute + ":" + second);
//System.out.println(getWeek(week));
}



//public static String getWeek(int week) {
//
//    String[] arr = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
//    return arr[week - 1];
//    }
}
 
 

