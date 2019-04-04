package cn.com.hinova.ruvs.common.listeners;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.common.cache.Cache;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class InitContextListener implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Cache.basePath=servletContextEvent.getServletContext().getRealPath("/");
        Config.loadCofnig();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
