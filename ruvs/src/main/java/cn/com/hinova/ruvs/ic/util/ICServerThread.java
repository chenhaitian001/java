package cn.com.hinova.ruvs.ic.util;

import cn.com.hinova.ruvs.common.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ICServerThread extends Thread {

    Logger logger=LoggerFactory.getLogger(ICServerThread.class);

    @Override
    public void run() {
        super.run();

        try {



            new ICServer().l();
            new ICServerUdp().l();
        } catch (Exception e) {
            logger.error("设备监听启动失败，IP：{}；端口：{}",Config.serverIp,Config.serverPort);
            e.printStackTrace();

        }
    }
}
