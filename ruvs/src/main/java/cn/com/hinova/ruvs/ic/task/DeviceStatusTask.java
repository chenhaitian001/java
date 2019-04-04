package cn.com.hinova.ruvs.ic.task;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.config.bean.Device;
import cn.com.hinova.ruvs.config.bean.Patriarch;
import cn.com.hinova.ruvs.ic.ICCache;
import cn.com.hinova.ruvs.ic.service.ICService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备状态监控
 */
public class DeviceStatusTask implements Job {

    Logger logger=LoggerFactory.getLogger(DeviceStatusTask.class);

    public static long time=0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info(">> 设备状态");




        //将超时的设备移除缓存，sn，timestamp
        for(Map.Entry<String,Long> entry:ICCache.deviceTime.entrySet()){
            if(System.currentTimeMillis()-entry.getValue()>Config.deviceOuttime){
                ICCache.deviceTime.remove(entry.getKey());
            }
        }



        WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
        if (ac==null){
            return;
        }
        ICService idcbService = (ICService) ac.getBean(ICService.class);

//        if(ICCache.deviceTime.size()>0){

            //更新设备状态
            idcbService.setDeviceOnline(ICCache.deviceTime.keySet());
//        }


        //时间超过5分钟，重新加载设备信息,加载家长信息
        if(time==0||(System.currentTimeMillis()-time>30000)){
            List<Device>  devices=idcbService.findDevices();

            Map<String,Device> snDeviceTmp=new HashMap<String,Device>();

            for (Device device:devices) {
                snDeviceTmp.put(device.getSn(),device);
            }
            ICCache.snDevice=snDeviceTmp;



            List<Patriarch>  patriarchs=idcbService.getAllPatriarch();

            Map<String,Patriarch> patriarchsTmp=new HashMap<String,Patriarch>();

            for (Patriarch patriarch:patriarchs) {
                patriarchsTmp.put(patriarch.getFaceCode(),patriarch);


            }
            ICCache.faceCodePatriarch=patriarchsTmp;





            time=System.currentTimeMillis();

        }



        logger.info("<< 设备状态");

    }
}
