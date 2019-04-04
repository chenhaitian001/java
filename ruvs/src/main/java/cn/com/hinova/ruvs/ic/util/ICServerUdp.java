package cn.com.hinova.ruvs.ic.util;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.ic.ICCache;
import com.hanvon.faceid.sdk.IDgramPacketHandler;
import com.hanvon.faceid.sdk.UdpClientPlus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ICServerUdp implements IDgramPacketHandler {

    static Logger logger=LoggerFactory.getLogger(Config.class);
    // 创建侦听服务器
    UdpClientPlus UdpServer = null;

    public void l(){

        logger.info(">> UDP监听开始，ip:{} ; port:{}",Config.serverIp,Config.serverUdpPort);

        try {
            UdpServer = new UdpClientPlus(Config.serverUdpPort, InetAddress.getByName(Config.serverIp));

            // 设置通信线程任务委托
            UdpServer.DgramPacketHandler = this;

            // 设置通信密码
            UdpServer.setSecretKey(null);

            // 设置通信字符集
            UdpServer.CharsetName = Config.encoding;

            // 开启侦听服务
//            List<String> TrustedIPAddresses = new LinkedList<>();
//            TrustedIPAddresses.add("192.168.2.133");
            UdpServer.StartListenThread(null, 0);

            logger.info("<< UDP监听结束，ip:{} ; port:{}",Config.serverIp,Config.serverUdpPort);

        } catch (Exception e) {
            logger.info("<<>> UDP监听失败，ip:{} ; port:{}",Config.serverIp,Config.serverUdpPort);
        }

    }

    @Override
    public void OnDgramPacketReceived(InetAddress inetAddress, int i, byte[] bytes) throws Exception {

    }

    @Override
    public void OnDgramPacketReceived(InetAddress address, int i, String content) throws Exception {

        String message = "来自：" + address.getHostName() + " 内容：" + content + "\r\n";

        String line=content.replace("DevStatus(","").replace(")","");
        Map<String,String> map=ICUtil.line2map(line,new String[]{"sn=","ip="});
        ICCache.deviceTime.put(map.get("sn"),System.currentTimeMillis());
    }
}
