package link.x86.wx.init.task;

import link.x86.wx.init.Cache;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取token的定时任务
 */
public class TokenJob implements Job {

    private static Logger logger=LoggerFactory.getLogger(TokenJob.class);


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.debug("获取token开始");
        Cache.weiXin.loadToken();
        logger.debug("获取token结束");

    }
}
