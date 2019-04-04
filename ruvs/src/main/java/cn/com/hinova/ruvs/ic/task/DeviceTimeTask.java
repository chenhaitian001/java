package cn.com.hinova.ruvs.ic.task;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.config.bean.Device;
import cn.com.hinova.ruvs.config.bean.Patriarch;
import cn.com.hinova.ruvs.ic.ICCache;
import cn.com.hinova.ruvs.ic.service.ICService;
import cn.com.hinova.ruvs.ic.util.ICDeviceUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备状态监控
 */
public class DeviceTimeTask implements Job {

    Logger logger=LoggerFactory.getLogger(DeviceTimeTask.class);

    public static long time=0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info(">> 更新设备时间");


        WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
        if (ac==null){
            return;
        }
        ICService idcbService = (ICService) ac.getBean(ICService.class);

        List<Device> list=idcbService.findDevices();

        if (list!=null&&list.size()>0){
            for(Device device:list){
                ICDeviceUtils.setTime(device.getIp(),9922,new Date());
            }
        }


        logger.info("<< 更新设备时间");

    }
}
