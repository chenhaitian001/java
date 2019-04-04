package cn.com.hinova.ruvs.common;


import cn.com.hinova.ruvs.utils.NumberUtils;

import javax.management.remote.JMXServiceURL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Config {



	public static Set<String> whereSymbolSet =new HashSet<String>();
	
	static{
		whereSymbolSet.add("<");
		whereSymbolSet.add(">");
		whereSymbolSet.add(">_");
		whereSymbolSet.add("<_");
		whereSymbolSet.add("_");
		whereSymbolSet.add("!_");
		whereSymbolSet.add("in");
		whereSymbolSet.add("like");
		whereSymbolSet.add("is not");
		whereSymbolSet.add("is");
		whereSymbolSet.add("not in");
		whereSymbolSet.add("=");
	}


    // 灵犀数据
	private static List<String> starWord=new ArrayList<String>();

	public static String getRandomStarWord(){
        if (starWord.size()>0){
           return starWord.get(new Random().nextInt(starWord.size()));
        }else{
            return "开心最重要";
        }
    }

	public static void loadCofnig(){
		InputStream inputStream=Config.class.getClassLoader().getResourceAsStream("bw.txt");
		try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() > 1) {
                    starWord.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}





	public static String siteName;

	public static String superAdminLoginName;
	public static String superAdminShowName;


    public static String encoding="GBK";
    public static String serverIp=null;
    public static int serverPort=9900;

    public static int serverUdpPort=9904;

    public static String deviceCront=null;
    public static String deleteOldPhotoCront=null;
    public static String setDeviceTimeCront=null;


    public static int deviceOuttime=0;

    public static int dataReloadTime=0;


    public static int photoSaveTimeForDay=356;


    public static String JMS_URL;
    public static String JMS_QUEUE;



    static {

        InputStream inputStream=Config.class.getClassLoader().getResourceAsStream("config.txt");

        Properties p=new Properties();
        try {
            InputStreamReader ir=new InputStreamReader(inputStream,"UTF8");
            p.load(ir);

            siteName=p.getProperty("site.name","");
            superAdminLoginName=p.getProperty("admin.superadmin.loginname","");
            superAdminShowName=p.getProperty("admin.superadmin.showname","");

            encoding=p.getProperty("io.encoding");
            serverIp=p.getProperty("server.ip");
            serverPort=Integer.parseInt(p.getProperty("server.port"));

            serverUdpPort=Integer.parseInt(p.getProperty("server.udp.port"));
            deviceOuttime=Integer.parseInt(p.getProperty("device.outtime"))*1000;
            dataReloadTime=Integer.parseInt(p.getProperty("data.config.reload.outtime"))*1000;

            deviceCront=p.getProperty("tesk.cront.deviceStatus");
            deleteOldPhotoCront=p.getProperty("tesk.cront.deleteOldPhotoFile");
            setDeviceTimeCront=p.getProperty("tesk.cront.setDeviceTime");


            photoSaveTimeForDay=NumberUtils.toInt(p.getProperty("photo.saveTimeForDay"),365);


            JMS_URL=p.getProperty("jms.url");
            JMS_QUEUE=p.getProperty("jms.queue");


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
