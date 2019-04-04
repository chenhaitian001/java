package cn.com.hinova.ruvs.ic.listener;

import cn.com.hinova.ruvs.ic.task.ICTimer;
import cn.com.hinova.ruvs.ic.util.ICServer;
import cn.com.hinova.ruvs.ic.util.ICServerThread;
import cn.com.hinova.ruvs.ic.util.ICServerUdp;
import cn.com.hinova.ruvs.ic.util.RMQUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ICListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //启动定时任务
        ICTimer.timer();
        new ICServerThread().start();

        new RMQUtil.MQThread().start();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
