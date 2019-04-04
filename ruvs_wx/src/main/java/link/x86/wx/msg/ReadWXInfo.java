package link.x86.wx.msg;

import link.x86.winxin.bean.UserInfo;
import link.x86.winxin.business.Info;
import link.x86.wx.db.DBUtils;
import link.x86.wx.db.IFHistory;
import link.x86.wx.init.Cache;
import link.x86.wx.init.Config;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import javax.jms.*;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

/**
 * 接收JMS消息
 */
public class ReadWXInfo {

    private static Logger logger=LoggerFactory.getLogger(ReadWXInfo.class);

    public static void initListener() throws Exception{
        // 连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Config.JMS_USER, Config.JMS_PASSWD,
                Config.JMS_URL);
        // 构造从工厂得到连接对象
        Connection connection = connectionFactory.createConnection();
        // 启动
        connection.start();
        // 获取操作连接 一个发送或接收消息的线程
        //第一个参数为true  标识开启事务，需要手工commit
        final Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 获取session注意参数值slimsmart.queue是一个服务器的queue，星号*匹配任意字符
        Destination destination = session.createQueue(Config.JMS_QUEUENAME);
        // 消费者，消息接收者
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener( new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMsg = (TextMessage)message;

                try {
                    String msg=textMsg.getText();
                    logger.debug("收到消息{}",msg);

                    //消息格式定义，时间，微信号，学生，关系
                    String[] sarr=msg.split(",");
                    Long timestamp=Long.parseLong(sarr[0]);
                    String phone=sarr[1];
                    String studentName=sarr[2];
                    String gx=sarr[3];
                    String jiazhang=sarr[4];


                    IFHistory ifHistory= new IFHistory();
                    ifHistory.setCardTime(timestamp);
                    ifHistory.setTeacherPhone(phone);
                    ifHistory.setStudentName(studentName);
                    ifHistory.setGx(gx);
                    ifHistory.setJiazhang(jiazhang);

                    Calendar c=Calendar.getInstance();
                    c.setTimeInMillis(timestamp);
                    int hour=c.get(Calendar.HOUR_OF_DAY);
                    if(hour<10) {
                        ifHistory.setJieSong("song");
                    }else{
                        ifHistory.setJieSong("jie");
                    }


                    if(new Date().getTime()-timestamp<60000){
                        UserInfo userInfo=Cache.phoneUserInfo.get(phone);
                       if(userInfo!=null){
                           ifHistory.setOpenid(userInfo.getOpenid());
                           try {
                               String response= Cache.weiXin.sendTemplateMsg(Info.info1(userInfo.getOpenid(),studentName,gx,jiazhang));
                               JSONObject jsStr = JSONObject.parseObject(response); 
                               String errcode =jsStr.get("errcode").toString();
                               if(errcode.equals("0")){
                            	   ifHistory.setSendInfo("YES");
                                   logger.info("发送消息成功");
                               }else{
                            	   logger.info("发送消息成功+");
                                   String response1= Cache.weiXin.sendTemplateMsg(Info.info1(userInfo.getOpenid(),studentName,gx,jiazhang));
                                   ifHistory.setSendInfo("YES");
                                   logger.info("发送消息成功");
                               }
                           } catch (UnsupportedEncodingException e) {
                               e.printStackTrace();
                               logger.info("发送消息失败");
                               ifHistory.setSendInfo("ERROR");
                           }
                       }else{
                           logger.info("手机号【{}】没有进行微信订阅",phone);
                           ifHistory.setSendInfo("NO-BD-PHONE");
                       }
                    }else{

                        logger.debug("消息超时 {}", textMsg.getText());
                        ifHistory.setSendInfo("TIMEOUT");
                    }

                    DBUtils.saveIFHistory(ifHistory);

                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        try {
            initListener();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
