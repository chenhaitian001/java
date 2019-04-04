package link.x86.wx.init;


import link.x86.util.TimerUtils;
import link.x86.winxin.bean.UserInfo;
import link.x86.wx.db.DBUtils;
import link.x86.wx.init.task.TokenJob;
import link.x86.wx.msg.ReadWXInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * 所有的初始化
 */
public class InitServletListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(InitServletListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        TimerUtils.startJobs();
        logger.info("项目初始化开始");
        logger.info("启动获取token定时任务");
        TimerUtils.addJob("realoadTokenN", "reloadTokenNG", "reloadTokenT", "reloadTokenTG", TokenJob.class, Config.CRONT_RELOADTOKEN);

        logger.info("启动建监听JMS消息");
        try {
            ReadWXInfo.initListener();
        } catch (Exception e) {
            e.printStackTrace();
        }


        new Thread(){
            @Override
            public void run() {
                logger.info("初始化，获取所有订阅用户信息");


                List<UserInfo> userInfosForDB=DBUtils.userInfoList();

                for (UserInfo userinfo:userInfosForDB) {
                    Cache.addUserInfo(userinfo);
                }
                List<UserInfo> userInfos=Cache.weiXin.getUserInfos();
                for (UserInfo userInfo:userInfos) {
                    if(!Cache.openidUserInfo.containsKey(userInfo.getOpenid())){
                        Cache.addUserInfo(userInfo);
                        DBUtils.saveWeixinInfo(userInfo.getOpenid(),userInfo.getNickname());
                    }

                }
                logger.info("数据库中订阅用户数{}个，获取微信订阅用户数{}个",userInfosForDB.size(),userInfos.size());

            }
        }.start();


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        TimerUtils.shutdownJobs();
    }
}
