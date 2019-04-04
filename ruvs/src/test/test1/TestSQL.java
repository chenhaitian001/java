package test1;

import sun.util.resources.CalendarData;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TestSQL {

    private static String sqltemp="INSERT INTO `ruvs_1.0`.`tf_analyze_face_history`(`id`,`device_location`,`device_sn`,`patriarch_id`,`patriarch_name`,`patriarch_phone`,`patriarch_relation`,`patriarch_photo`,`student_id`,`student_name`,`student_grade_name`,`student_clazz_name`,`teacher_name`,`teacher_phone`,`year`,`month`,`week`,`day`,`face_time`,`face_photo`)" +
            "VALUES('12','{1}','34-34-23-45-56','212331','{2}','13789548767','{3}','upload/photo/12323213.png','09876545678'," +
            "'{4}','{5}','{6}','{7}','12345678900',2018,5,13,1,'{8}','upload/photo/12345689.png');";

    private static String[][] msg=new String[][]{
            new String[]{"东大门","朱清扬","爷爷","朱梓萌","小班","三班","萌萌老师",""},
            new String[]{"东大门","张名扬","奶奶","刘雨萱","小班","三班","萌萌老师",""},
            new String[]{"东大门","周海峰","父亲","周子涵","小班","三班","萌萌老师",""},
            new String[]{"东大门","杨晓倩","母亲","周谦","小班","二班","兰兰老师",""},
            new String[]{"东大门","李丽丽","母亲","管雅雯","小班","二班","兰兰老师",""},
            new String[]{"东大门","张晓晓","母亲","殷雨馨","小班","二班","兰兰老师",""},
            new String[]{"东大门","于乐乐","母亲","杨军","小班","二班","兰兰老师",""},
            new String[]{"东大门","赵晶晶","母亲","王源依","小班","二班","兰兰老师",""},
            new String[]{"东大门","李晓云","母亲","杨子嘉","中班","一班","乐乐老师",""},
            new String[]{"东大门","张晓海","父亲","张轩","中班","一班","乐乐老师",""},
            new String[]{"东大门","崔晓珊","母亲","成培韬","中班","一班","乐乐老师",""},
            new String[]{"东大门","岳欣欣","母亲","林甜甜","中班","一班","乐乐老师",""},
            new String[]{"东大门","王朝阳","父亲","王韬涵","中班","一班","乐乐老师",""},
            new String[]{"东大门","施昱杰","父亲","施宇灿","大班","三班","丽丽老师",""},
            new String[]{"东大门","袁磊","父亲","袁浩轩","大班","三班","丽丽老师",""},
            new String[]{"东大门","姜页帆","母亲","张蒙蒙","大班","三班","丽丽老师",""},
            new String[]{"东大门","葛浩轩","父亲","葛栋梁","大班","三班","丽丽老师",""},
            new String[]{"东大门","杜文涛","父亲","杜雅婷","大班","三班","丽丽老师",""},
            new String[]{"东大门","郝媛媛","母亲","陈禹涵","大班","三班","丽丽老师",""},
            new String[]{"东大门","刘浩楠","父亲","刘心堂","大班","三班","丽丽老师",""},
            new String[]{"东大门","侯甜甜","父亲","侯雨萱","大班","一班","莲莲老师",""},
            new String[]{"东大门","戴雅杰","父亲","戴虎","大班","一班","莲莲老师",""},
            new String[]{"东大门","侯甜甜","母亲","杜晨汐","大班","一班","莲莲老师",""},
            new String[]{"东大门","谷怡辰","父亲","谷鸿睿","大班","一班","莲莲老师",""},
            new String[]{"东大门","王翔国","父亲","王子豪","大班","一班","莲莲老师",""},
            new String[]{"东大门","于芷涵","母亲","杨延泽","大班","一班","莲莲老师",""},
            new String[]{"东大门","张嘉欣","母亲","曹佳萱","大班","一班","莲莲老师",""},
            new String[]{"东大门","胡艺阳","父亲","胡逸轩","大班","一班","莲莲老师",""},
            new String[]{"东大门","华俊涛","父亲","华欣怡","大班","一班","莲莲老师",""},
            new String[]{"东大门","李佳慧","母亲","徐佳旭","大班","一班","莲莲老师",""}
    };






    //1 安装位置，家长姓名，称呼，学生名，年级，班级，老师名
    public static void main(String[] args) throws IOException {


        FileOutputStream fout=new FileOutputStream("/Users/ren/tmp/a.txt");
        String[] vs=null;
        int a=100;
        for (int i=0;i<3000;i++){
            vs=msg[new Random().nextInt(msg.length)];
            Calendar c=Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH,1-new Random().nextInt(300));
            vs[7]=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());

            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH)+1;
            int week=c.get(Calendar.WEEK_OF_YEAR);
            int day=c.get(Calendar.DAY_OF_MONTH);

            String sg=" INSERT INTO `ruvs_1.0`.`tf_analyze_face_history`(`id`,`device_location`,`device_sn`,`patriarch_id`,`patriarch_name`,`patriarch_phone`,`patriarch_relation`,`patriarch_photo`,`student_id`,`student_name`,`student_grade_name`,`student_clazz_name`,`teacher_name`,`teacher_phone`,`year`,`month`,`week`,`day`,`face_time`,`face_photo`)" +
                    " VALUES('12-"+a+++"','"+vs[0]+"','34-34-23-45-56','212331','"+vs[1]+"','13789548767','"+vs[2]+"','upload/photo/12323213.png','09876545678'," +
                    " '"+vs[3]+"','"+vs[4]+"','"+vs[5]+"','"+vs[6]+"','12345678900',"+year+","+month+","+week+","+day+",'"+vs[7]+"','upload/photo/12345689.png');\n";
          fout.write(sg.getBytes());
        }
      fout.close();
    }
}
