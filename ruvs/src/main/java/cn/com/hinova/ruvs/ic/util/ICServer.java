package cn.com.hinova.ruvs.ic.util;

import cn.com.hinova.ruvs.analyze.bean.FaceHistory;
import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.config.bean.*;
import cn.com.hinova.ruvs.ic.ICCache;
import cn.com.hinova.ruvs.ic.service.ICService;
import com.hanvon.faceid.sdk.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.net.InetAddress;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ICServer implements  ISocketServerThreadTask {


    static Logger logger=LoggerFactory.getLogger(ICServer.class);

    static TcpListenerPlus tcpServer=null;


    public  void l() throws Exception {

        logger.info(">>开始启动监听，IP：{}；端口：{}",Config.serverIp,Config.serverPort);


        tcpServer = new TcpListenerPlus(Config.serverPort, 0, InetAddress.getByName(Config.serverIp));

        // 设置通信线程任务委托
        tcpServer.ThreadTaskDelegate = this;

        // 设置通信密码
        tcpServer.setSecretKey(null);

        // 开启侦听服务
//        List<String> TrustedIPAddresses = new LinkedList<>();
//        TrustedIPAddresses.add("");
        tcpServer.StartListenThread(null, 0, 0);

        logger.info("<<启动监听完成");
    }

    private static Map<String,String> ipsn=new HashMap<String,String>();

    @Override
    public void OnServerTaskRequest(NetworkStreamPlus stream) throws Exception {
        while(true)
        {
            try {
                // 读取数据
                String resuString = stream.Read(Config.encoding);

                String ipport=MessageFormat.format("{0}_{1}",stream.Client.getInetAddress().getHostName(),stream.Client.getPort()+"");


//                Device device=ICCache.snDevice.get(ip);
//                if(device==null){
//                    continue;
//                }
                RUtil.p(resuString);

                if(resuString.startsWith("PostRecord"))
                {
                    logger.debug("设备提交记录申请");
                    // 准备上传考勤记录
                    String sn=resuString.replace("PostRecord(sn=\"","").replace("\")","");
                    ipsn.put(ipport,sn);
                    // 服务器回应，不上传照片
                    logger.debug("回复设备需要上传照片");
                    stream.Write("Return(result=\"success\" postphoto=\"true\")", Config.encoding);
                }
                else if(resuString.startsWith("PostEmployee"))
                {   // 准备上传人员信息

                    // 服务器回应
                    logger.debug("收到上传人员信息请求");
                    stream.Write("Return(result=\"success\")", Config.encoding);
                }
                else if(resuString.startsWith("Record"))
                {   // 读取考勤记录


                    String sn=ipsn.get(ipport);
                    Device device = ICCache.snDevice.get(sn);


                    logger.debug("接收考勤记录");
                    String line=resuString.replace("Record(","").replace(")","").replace("\n"," ").replace("\r"," ");
                    Map<String,String> map=ICUtil.line2map(line,new String[]{});

                    logger.debug("保存照片");
                    String filePath=ICUtil.savePhoto(map.get("photo"),Cache.basePath+"/upload");


                    String faceCode=map.get("id");

                    final Patriarch patriarch=ICCache.faceCodePatriarch.get(faceCode);
                    if(patriarch==null){
                        logger.debug("工号{}的家长信息不存在",faceCode);
                        continue;
                    }

                    FaceHistory record=new FaceHistory();

                    record.setId(UUID.randomUUID().toString());
                    record.setDevcieSN(device.getSn());
                    record.setDeviceLocation(device.getLocation());

                    record.setPatriarchId(patriarch.getId());
                    record.setPatriarchName(patriarch.getName());
                    record.setPatriarchPhone(patriarch.getPhone());
                    record.setPatriarchRelation(patriarch.getRelation());
                    record.setPatriarchPhoto("");

                    final Student student=patriarch.getStudent();
                    if(student!=null) {
                        record.setStudentId(student.getId());
                        record.setStudentName(student.getName());

                        final Clazz clazz = student.getClazz();
                        if (clazz != null) {
                            if(clazz.getGrade()!=null) {
                                record.setStudentGradeName(clazz.getGrade().getName());
                            }
                            record.setStudentClazzName(clazz.getName());



                            Set<Teacher> teachers=clazz.getTeacherSet();

                            for (Teacher teacher:teachers) {

                                record.setTeacherName(teacher.getName());
                                record.setTeacherPhone(teacher.getPhone());


                                //>> 保存发送微信消息到队列

                                StringBuilder jmsMsg=new StringBuilder(new Date().getTime()+"").append(",");
                                jmsMsg.append(teacher.getPhone()).append(",");
                                jmsMsg.append(student.getName()).append(",");
                                jmsMsg.append(patriarch.getRelation()).append(",");
                                jmsMsg.append(patriarch.getName());

                                ICCache.wxmsgQueue.put(jmsMsg.toString());
                            }

                        }
                    }

                    Calendar c = Calendar.getInstance();
                    record.setYear(c.get(Calendar.YEAR));
                    record.setMonth(c.get(Calendar.MONTH + 1));
                    record.setDay(c.get(Calendar.DAY_OF_MONTH));
                    record.setWeek(c.get(Calendar.WEEK_OF_YEAR));
                    record.setFaceTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("time")));
                    record.setFacePhoto("upload/"+filePath);

                    WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
                    if (ac==null){
                        return;
                    }
                    ICService idcbService = ac.getBean(ICService.class);
                    idcbService.saveHistory(record);
                    //保存打卡历史数据完成

                    // 服务器回应
                    stream.Write("Return(result=\"success\")", Config.encoding);
                }
                else if(resuString.startsWith("Employee"))
                {   // 读取人员信息

                    // 服务器回应
                    logger.debug("接收人员信息");
                    stream.Write("Return(result=\"success\")", Config.encoding);
                }
                else if(resuString.startsWith("Quit"))
                {   // 结束会话
                    logger.debug("会话结束");
                    break;
                }
            }
            catch (Exception ex)
            {
                break;  // 连接断开
            }
        }
    }


}
