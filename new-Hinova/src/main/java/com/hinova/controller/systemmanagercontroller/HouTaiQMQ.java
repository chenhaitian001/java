/*package com.hinova.controller.systemmanagercontroller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.hinova.entity.Clazz;
import com.hinova.entity.FaceHistory;
import com.hinova.entity.Patriarch;
import com.hinova.entity.Student;
import com.hinova.entity.Teacher;
import com.hinova.service.ChildrenService;
import com.hinova.util.RelationUtil;
import com.ic.ICCache;



@Controller
@RequestMapping("/Alaram")
public class HouTaiQMQ{
	
	 private static Logger logger = LoggerFactory.getLogger(HouTaiQMQ.class);
	 	@Resource
		protected ChildrenService childrenService;

	 	@RequestMapping("/search_TX_map")
	    protected void INPUT(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 			resp.setContentType("text/html; charset=utf-8");
	            String code =req.getParameter("secretkeycode").replaceAll("'", "");
	            //学生识别码
	            String student_id = code.substring(0,code.length()-1);
	            //亲属关系
	            String relation =  RelationUtil.getRelation(code.substring(code.length()-1));
	            //判断该学生是否有该亲属关系
	            List stList = childrenService.findifrelation(student_id,relation);
	            if(stList!=null && !stList.isEmpty()){
	            	
	            }else{
	            	//若无该关系家长 进行新增该关系家长的信息
		            childrenService.saveRelation(student_id,relation);
	            }
	            logger.info(code+relation);
		        //通过识别码  找到对应的家长
		        List<Patriarch> patriarchs=this.getSession().createQuery("from Patriarch where student_id = '"+student_id+"' and relation = '"+relation+"'").list();
                
		        logger.info("获取到了手机号，可以连接进行微信推送测试了");
                if(patriarchs.size()==0||patriarchs==null){
                    logger.debug("工号{}的家长信息不存在");
                    return;
                }
                
                Patriarch patriarch = patriarchs.get(0); 
                FaceHistory record=new FaceHistory();
                record.setId(UUID.randomUUID().toString());
                record.setDevcieSN("wx");
                record.setDeviceLocation("微信打卡");
                record.setPatriarchId(patriarch.getId().toString());
                record.setPatriarchName(patriarch.getName());
                record.setPatriarchPhone(patriarch.getPhone());
                record.setPatriarchRelation(patriarch.getRelation());
                record.setPatriarchPhoto("");

                final Student student=patriarch.getStudent();
                if(student!=null) {
                    record.setStudentId(student.getId().toString());
                    record.setStudentName(student.getName());

                    final Clazz clazz = student.getClazz();
                    if (clazz != null) {
                        if(clazz.getGrade()!=null) {
                            record.setStudentGradeName(clazz.getGrade().getGname());
                        }
                        record.setStudentClazzName(clazz.getCname());



                        List<Teacher> teachers=clazz.getTeacher();

                        for (Teacher teacher:teachers) {

                            record.setTeacherName(teacher.getName());
                            record.setTeacherPhone(teacher.getPhone());


                            //>> 保存发送微信消息到队列

                            StringBuilder jmsMsg=new StringBuilder(new Date().getTime()+"").append(",");
                            jmsMsg.append(teacher.getPhone()).append(",");
                            jmsMsg.append(student.getName()).append(",");
                            jmsMsg.append(patriarch.getRelation()).append(",");
                            jmsMsg.append(patriarch.getName());

                            try {
								ICCache.wxmsgQueue.put(jmsMsg.toString());
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        }

                    }
                }

                Calendar c = Calendar.getInstance();
                record.setYear(c.get(Calendar.YEAR));
                record.setMonth(c.get(Calendar.MONTH + 1));
                record.setDay(c.get(Calendar.DAY_OF_MONTH));
                record.setWeek(c.get(Calendar.WEEK_OF_YEAR));
                
                try {
					record.setFaceTime(getNowDate());
				} catch (ParseException e) {
					record.setFaceTime(new Date());
				}
                record.setFacePhoto("wx");

                WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
                if (ac==null){
                    return;
                }
                ICService idcbService = ac.getBean(ICService.class);
                idcbService.saveHistory(record);
                //保存打卡历史数据完成

                // 服务器回应
                
            
	 	}
	 	public static Date getNowDate() throws ParseException {
	 		   Date currentTime = new Date();
	 		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 		   String dateString = formatter.format(currentTime);
	 		   
	 		  String fmt = "yyyy-MM-dd HH:mm:ss";
	 		  SimpleDateFormat sdf = new SimpleDateFormat(fmt);
	 		  Date date = sdf.parse(dateString);
	 		  return date;
	 		  }
	 }

*/