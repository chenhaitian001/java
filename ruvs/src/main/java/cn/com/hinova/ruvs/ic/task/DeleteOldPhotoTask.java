package cn.com.hinova.ruvs.ic.task;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.ic.util.FileUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 采集相关的定时任务
 * 每分钟执行一次，达到一定程度，执行相应任务
 */
public class DeleteOldPhotoTask implements Job {

    Logger logger=LoggerFactory.getLogger(DeleteOldPhotoTask.class);

    public static long time=0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info(">> 清除过时照片任务,清除{}天前的照片，开始",Config.photoSaveTimeForDay);
        String photoDir=Cache.basePath+"/upload";
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,0-Config.photoSaveTimeForDay);
        FileUtil.delteOldFiles(photoDir,c.getTimeInMillis());

        logger.info("<< 清除过时照片任务,清除{}天前的照片，结束",Config.photoSaveTimeForDay);
    }
}
