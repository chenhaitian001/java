package cn.com.hinova.ruvs.ic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RUtil {




    public static String mapFormat(Map<? extends Object,? extends Object> map){

        StringBuilder sbuider=new StringBuilder();

        for (Map.Entry entry:map.entrySet()) {
            if(sbuider.length()!=0){
                sbuider.append(";");
            }
            sbuider.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sbuider.toString();
    }



    public static  void p(Object...objs){

        StringBuilder sbuilder=new StringBuilder();

        for (Object obj:objs) {
            if(sbuilder.length()!=0){
                sbuilder.append(" ");
            }
            sbuilder.append(obj==null?"null":obj.toString());
        }
    }

    public static SimpleDateFormat dataFromat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parserDate(String time){

        try {
            return dataFromat.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }


}
