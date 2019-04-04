package cn.com.hinova.ruvs.ic.util;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.ic.ICCache;
import com.hanvon.faceid.sdk.FaceId;
import com.hanvon.faceid.sdk.FaceIdAnswer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ICDeviceUtils {



    private static String execCommand(String ip,int port,String command){
        FaceIdAnswer output=null;
        FaceId fi=null;
        try{

            output = new FaceIdAnswer();
            fi = new FaceId(ip,port);
            fi.Execute(command,output,Config.encoding);
            return output.answer;

        }catch (Exception ex){
            return null;

        }finally {
            try {
                fi.close();
            } catch (IOException e) {
            }
        }
    }
    //GetRecord 记录管理类命令 GetRecord(start_time="2008-08-01 0:0:0"
    //end_time="2014-08-31 23:59:59")
    public static List<Map<String,String>> getRecorve(String ip, int port){

        List<Map<String,String>> resuList=new ArrayList<Map<String,String>>();

       String resu=execCommand(ip,port,"GetRecord()");

       if(resu==null){
           return null;
       }

       String[] resuarr=resu.split("\n");
        for (String line:resuarr ) {


            Map<String,String> map=ICUtil.line2map(line,new String[]{"time=","id=","name="});

            if(map!=null) {

                resuList.add(map);
            }

        }
        return resuList;

    }

    //getEmployeeId 员工管理类命令
    public static List<String> getEmployeeId(String ip, int port){

        List<Map<String,String>> resuList=new ArrayList<Map<String,String>>();

       String resu=execCommand(ip,port,"GetEmployeeID()");

       if(resu==null){
           return null;
       }


        String line=resu.replace("Return(","").replace(")","");

       String[] linearr=line.split("\" +");

       List<String> list=new ArrayList<String>();

        for (String field:linearr) {
            String[] fieldarr=field.split("=");
            if(fieldarr[0].equalsIgnoreCase("id")){
                list.add(fieldarr[1].replace("\"",""));
            }

        }
       return list;

    }
    //获取用户信息
    public static Map<String,String> getEmployee(String ip, int port,String jobId){

        List<Map<String,String>> resuList=new ArrayList<Map<String,String>>();

       String resu=execCommand(ip,port,"GetEmployee(id=\""+jobId+"\")");




        if(resu==null){
            return null;
        }

        String line=resu.replace("Return(","").replace(")","").replace("\n"," ").replace("\r"," ");

        Map<String,String> map= ICUtil.line2map(line,new String[]{"id=","name="});
        map.remove("face_data");
        String sn=map.get("sn");
        resu=resu.replace("result=\"success\"","").replace("sn=\""+sn+"\"","");
        map.put("resu_data",resu);
        return map;
    }



    //获取设备信息
    public static Map<String,String > getDeviceInfo(String ip, int port){



        String resu=execCommand(ip,port,"GetDeviceInfo()");


        if(resu==null){
            return null;
        }

        String line=resu.replace("Return(","").replace(")","");



        Map<String ,String> map= ICUtil.line2map(line,new String[]{"result=","ip=","gateway="});

        return map;

    }


    public static  boolean setTime(String ip, int port,Date date){

        SimpleDateFormat datefmt=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timefmt=new SimpleDateFormat("HH:mm:ss");
        String sdate=datefmt.format(date);
        String stime=timefmt.format(date);


        String resu=execCommand(ip,port,"SetDateTime(date=\""+sdate+"\" time=\""+stime+"\")");


        if(resu==null){
            return false;
        }

        if(resu.indexOf("success")!=-1){
            return true;
        }else{
            return false;
        }

    }


    public static  boolean setEmployee(String ip, int port,String commond){


        String resu=execCommand(ip,port,commond);


        if(resu==null){
            return false;
        }

        if(resu.indexOf("success")!=-1){
            return true;
        }else{
            return false;
        }

    }





    public static void main(String[] args) {


        setTime("192.168.2.133",9922,new Date());


        RUtil.p("-----------获取打卡记录------------");
        List<Map<String,String>> resuList=getRecorve("192.168.2.133",9922);
        if(resuList!=null) {
            for (Map<String,String> map : resuList) {
                System.out.println(RUtil.mapFormat(map));
            }
        }

        RUtil.p("-----------获取设备信息------------");
        Map<String,String> map=getDeviceInfo("192.168.2.133",9922);
        if(map!=null) {
            System.out.println(RUtil.mapFormat(map));
        }

        RUtil.p("-----------获取所有ID------------");
        List<String> resuList1=getEmployeeId("192.168.2.133",9922);
        for (String id: resuList1) {
            System.out.println("id = " + id);
        }


        RUtil.p("-----------获取用户信息------------");
        Map<String,String> map3=getEmployee("192.168.2.133",9922,"2");
        if(map!=null) {
            System.out.println(RUtil.mapFormat(map3));
        }
    }
}
