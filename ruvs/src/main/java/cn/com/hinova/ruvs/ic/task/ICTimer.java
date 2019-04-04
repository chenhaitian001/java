package cn.com.hinova.ruvs.ic.task;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.config.bean.Device;
import cn.com.hinova.ruvs.ic.ICCache;
import cn.com.hinova.ruvs.ic.util.ICDeviceUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class ICTimer {

    static Logger logger=LoggerFactory.getLogger(ICTimer.class);


    private static Scheduler scheduler;




    public static void timer()  {




        try {
            logger.info(">> 定时任务启动开始");
            // Initiate a Schedule Factory
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            // Retrieve a scheduler from schedule factory
             scheduler = schedulerFactory.getScheduler();



           startTask("deviceStatusTask",Config.deviceCront,DeviceStatusTask.class,scheduler);
           //清理照片
          // startTask("deleteOldPhotoTask",Config.deleteOldPhotoCront,DeleteOldPhotoTask.class,scheduler);
           //更新设备时间
          // startTask("setDeviceTimeTask",Config.setDeviceTimeCront,DeviceTimeTask.class,scheduler);

            // start the scheduler
            scheduler.start();



            logger.info("<< 定时任务启动结束");


        }catch(Exception ex){
            ex.printStackTrace();
            logger.error("定时任务启动异常");
        }
    }



    public static void startTask(String taskName,String taskCront,Class<? extends Job> taskClazz,Scheduler scheduler){


        // Initiate SimpleTrigger with its name and group name
        CronTrigger tmpTrigger = new CronTrigger(taskName+"Trigger", taskName+"TriggerGroup");
        try {
            // setup CronExpression
            CronExpression cexp = new CronExpression(taskCront);
            // Assign the CronExpression to CronTrigger
            tmpTrigger.setCronExpression(cexp);



        // Initiate JobDetail with job name, job group, and executable job class
        JobDetail tmpJob = new JobDetail(taskName+"Job", taskName+"JobsGroup", taskClazz);
        scheduler.scheduleJob(tmpJob, tmpTrigger);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
