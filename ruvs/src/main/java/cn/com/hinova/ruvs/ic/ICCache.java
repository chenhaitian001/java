package cn.com.hinova.ruvs.ic;

import cn.com.hinova.ruvs.config.bean.Device;
import cn.com.hinova.ruvs.config.bean.Patriarch;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ICCache {



    //设备时间-用于判断设备离线
    public static Map<String,Long> deviceTime=new HashMap<String,Long>();

    //SN对应的设备
    public static Map<String,Device> snDevice=new HashMap<String,Device>();

    //人脸识别号，对应的家长信息
    public static Map<String,Patriarch> faceCodePatriarch=new HashMap<String,Patriarch>();


    public static LinkedBlockingQueue<String> wxmsgQueue=new LinkedBlockingQueue<String>();



}
