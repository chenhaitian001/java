package link.x86.wx.map.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import link.x86.util.RelationUtil;
import link.x86.wx.map.entity.Result;
import link.x86.wx.map.service.FindRelationService;
import link.x86.wx.map.util.GetDateUtil;

//@Controller 控制器注解.
@Controller
@RequestMapping(value="/",produces = "application/json;charset=utf-8")
public class LoginAuthenticationController {
	protected static final String AJAX = "ajaxDone";
	private static Logger logger = LoggerFactory.getLogger(LoginAuthenticationController.class);
	
	@Resource
	private FindRelationService findRelationService;
	
  //@requestMapping
  @Transactional
  @RequestMapping("checkLogin")
  @ResponseBody
  public void check(@RequestParam(value = "loginId", required = false) String loginId,
		  @RequestParam(value = "sex", required = false) String sex,
		  @RequestParam(value = "select_year2", required = false) String select_year2,
		  @RequestParam(value = "select_month2", required = false) String select_month2,
		  @RequestParam(value = "select_day2", required = false) String select_day2,
		  @RequestParam(value = "Code", required = false) String Code,
		  @RequestParam(value = "relation", required = false) String relation,
		  @RequestParam(value = "Phoneno", required = false) String Phoneno,
		  @RequestParam(value = "oppenid", required = false) String oppenid,
		  HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException { 
	  	  //保存注册信息
	  	//关系
	  	 relation= Code.substring(Code.length()-1);
	  	//性别
	  	 sex =	 RelationUtil.getSex(sex);
	  
	  	 
	  	 String sql = "INSERT INTO t_resource (loginid,openid,phone,sex,relation)"
	  	 		+ " (SELECT '"+Code+"','"+oppenid+"','"+Phoneno+"','"+sex+"','"+relation+"' from DUAL WHERE"
	  	 		+ " NOT EXISTS (SELECT 1 from t_resource where loginid = '"+Code+"' or openid = '"+oppenid+"' ) )";
	  	  
	  	  findRelationService.saveResource(sql);
	  	  
	  	  request.setAttribute("oppenid", oppenid);
	  	  request.setAttribute("loginIdd", Code);
	  	List findworkList=findRelationService.findWork(oppenid);
	  	request.setAttribute("amTime", "待签到");
	  	request.setAttribute("pmTime", "待签退");
		if(GetDateUtil.ifEmpty(findworkList)){
    		for(int i=0;i<findworkList.size();i++){
	    		String time = "";
	    		String timelong= "";
	    		String flag = "";
	    		String relations = "";
	    		String amTime = "";
	    		String pmTime = "";
	    			HashMap paperMap = (HashMap) findworkList.get(i);
	    			timelong = paperMap.get("checktime").toString();
	    			time = GetDateUtil.getStr(timelong);
	    			flag =  paperMap.get("flag").toString();
	    			relations =  paperMap.get("relation").toString();
	    			if(flag.equals("10")){
	    				request.setAttribute("amTime", time);
	    				request.setAttribute("amRelation", relations);
	    			}else if(flag.equals("20")){
	    				request.setAttribute("pmTime", time);
	    				request.setAttribute("pmRelation", relations);
	    			}
	    	}
    	}else{
    		request.setAttribute("ifshow", false);
    	}
	  	  request.getRequestDispatcher("punch.jsp").forward(request,response);
  }
  @RequestMapping("checkCode")
  @ResponseBody
  public Result checkCode(@RequestParam(value = "Code", required = false) String Code,
		  @RequestParam(value = "phonecode", required = false) String phonecode,
		  @RequestParam(value = "studentName", required = false) String studentName
		  ){
	  //判断手机号是否被占用 被谁占用
	  String ifPhone  = "SELECT phone FROM t_resource WHERE phone = '"+phonecode+"'";
	  List phoneList =findRelationService.ifStudentCode(ifPhone);
	  if(GetDateUtil.ifEmpty(phoneList)){
		  return new Result(250, "操作失败", "",null);
	  }
	  //判断是否存在该学生
	  String trimName = studentName.trim();
	  String studentid = Code.substring(0, Code.length()-1);
	  String ifStudent = "select id from td_config_student WHERE name = '"+trimName+"' ";
	  List ifStudentList =findRelationService.ifStudentCode(ifStudent);
	  if(!GetDateUtil.ifEmpty(ifStudentList)){
		  return new Result(300, "操作失败", "",null);
	  }
	  //注册码是否存在
	  
	  String sql = "select id from td_config_student WHERE id = '"+studentid+"'";
	  List list =findRelationService.ifStudentCode(sql);
	  if(GetDateUtil.ifEmpty(list)){
		  return new Result();
	  }else{
		  return new Result(2120, "操作失败", "",null);
	  }
  }
}