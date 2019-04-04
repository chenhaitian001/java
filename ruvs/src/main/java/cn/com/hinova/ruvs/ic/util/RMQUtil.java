package cn.com.hinova.ruvs.ic.util;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.ic.ICCache;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

public class RMQUtil {

    private static final String USERNAME=ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址
    private static final int SENDNUM=10; // 发送的消息数量


    // 时间，老师手机号，学生姓名，关系，家长姓名
    public static void sendMessage(String msg) {


        try {

            // 连接工厂，JMS 用它创建连接
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, ActiveMQConnection.DEFAULT_PASSWORD,
                    Config.JMS_URL);
            // 构造从工厂得到连接对象
            Connection connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接 一个发送或接收消息的线程
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值slimsmart.queue是一个queue
            Destination destination = session.createQueue(Config.JMS_QUEUE);
            // 得到消息生成者【发送者】
            MessageProducer producer = session.createProducer(destination);
            // 设置持久化，
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            // 构造消息，并发送

            TextMessage message = session.createTextMessage(msg);
            // 发送消息到目的地方
            producer.send(message);
            //提交
            session.commit();
            session.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally{

        }

    }

    public static void main(String[] args) {
        StringBuilder jmsMsg=new StringBuilder(new Date().getTime()+"").append(",");
        jmsMsg.append("一个胖子").append(",");
        jmsMsg.append("张晓华").append(",");
        jmsMsg.append("父亲").append(",");
        try {
            RMQUtil.sendMessage(jmsMsg.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class MQThread extends Thread{

        @Override
        public void run() {

            while (true){
                String msg= null;
                try {
                    msg = ICCache.wxmsgQueue.take();
                    RMQUtil.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
