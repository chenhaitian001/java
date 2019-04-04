package link.x86.wx.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import link.x86.winxin.bean.AccessToken;
import link.x86.wx.init.Cache;
import link.x86.wx.map.service.FindRelationService;
import link.x86.wx.map.util.GetDateUtil;

public class MAPresearchServlet extends HttpServlet{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(MAPresearchServlet.class);
	 @Autowired
	 private FindRelationService findRelationService;
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	HttpServletRequest hreq=(HttpServletRequest) req;
			HttpServletResponse hresp= (HttpServletResponse) resp;
	    	resp.setContentType("text/html; charset=utf-8");
	    	/*String oppenid = "oT6Bi57D1aUNA8Ugftbv24dkC_2U";
	    	logger.debug("进入地图定位");
            logger.debug("进入地图定位"+oppenid);
	        req.setAttribute("oppenid", oppenid);
	    	List findnoList=findRelationService.findNumber(oppenid);
	            if(GetDateUtil.ifEmpty(findnoList)){
			        String loginId = findRelationService.findLogin(oppenid);
			    	req.setAttribute("loginIdd", loginId);
			    	//获取家长
			    	String relationname = findRelationService.findrelation(oppenid);
			    	req.setAttribute("relation", relationname);
			    	
			    	List findworkList=findRelationService.findWork(oppenid);
			    	req.setAttribute("amTime", "待签到");
		    		req.setAttribute("pmTime", "待签退");
		    		if(GetDateUtil.ifEmpty(findworkList)){
			    		for(int i=0;i<findworkList.size();i++){
				    		String time = "";
				    		String timelong= "";
				    		String flag = "";
				    		String relation = "";
				    		String amTime = "";
				    		String pmTime = "";
				    			HashMap paperMap = (HashMap) findworkList.get(i);
				    			timelong = paperMap.get("checktime").toString();
				    			time = GetDateUtil.getStr(timelong);
				    			flag =  paperMap.get("flag").toString();
				    			relation =  paperMap.get("relation").toString();
				    			if(flag.equals("10")){
				    				//上午的关系
				    				relation = findRelationService.amRelation(oppenid);
				    				req.setAttribute("amTime", time);
				    				req.setAttribute("amRelation", relation);
				    			}else if(flag.equals("20")){
				    				//下午的关系
				    				relation = findRelationService.pmRelation(oppenid);
				    				req.setAttribute("pmTime", time);
				    				req.setAttribute("pmRelation", relation);
				    			}
				    	}
			    	}else{
			    		req.setAttribute("ifshow", false);
			    	}
	    	
	            }
	            req.getRequestDispatcher("punch.jsp").forward(req,resp);*/
	    	///////////////////////////////////////////////////////////////////////
	    	//无openid获取
	        if(req.getSession().getAttribute("openid")==null) {
	            String code = req.getParameter("code");
	            logger.debug("无openid");
	            logger.debug("Code信息为{}",code);
	            if (code == null) {
	                resp.getWriter().print("操作有误");
	            }
	            AccessToken accessToken = Cache.weiXin.getAccessToken(code);
	            String oppenid=accessToken.getOpenid();
	            logger.debug("进入地图定位");
	            logger.debug("进入地图定位"+oppenid);
	            req.setAttribute("oppenid", oppenid);
	            logger.debug(oppenid+"验证是否注册");
	            List findnoList=findRelationService.findNumber(oppenid);
	            if(GetDateUtil.ifEmpty(findnoList)){
			        String loginId = findRelationService.findLogin(oppenid);
			        logger.debug("注册码"+loginId);
			    	req.setAttribute("loginIdd", loginId);
			    	//获取家长
			    	//String relationname = findRelationService.findrelation(oppenid);
			    	//req.setAttribute("relation", relationname);
			    	
			    	List findworkList=findRelationService.findWork(oppenid);
			    	req.setAttribute("amTime", "待签到");
		    		req.setAttribute("pmTime", "待签退");
			    	if(GetDateUtil.ifEmpty(findworkList)){
			    		for(int i=0;i<findworkList.size();i++){
				    		String time = "";
				    		String timelong= "";
				    		String flag = "";
				    		String relation = "";
				    		String amTime = "";
				    		String pmTime = "";
				    			HashMap paperMap = (HashMap) findworkList.get(i);
				    			timelong = paperMap.get("checktime").toString();
				    			time = GetDateUtil.getStr(timelong);
				    			flag =  paperMap.get("flag").toString();
				    			relation =  paperMap.get("relation").toString();
				    			if(flag.equals("10")){
				    				//上午的关系
				    				relation = findRelationService.amRelation(oppenid);
				    				req.setAttribute("amTime", time);
				    				req.setAttribute("amRelation", relation);
				    			}else if(flag.equals("20")){
				    				//下午的关系
				    				relation = findRelationService.pmRelation(oppenid);
				    				req.setAttribute("pmTime", time);
				    				req.setAttribute("pmRelation", relation);
				    			}
				    	}
			    	}else{
			    		req.setAttribute("ifshow", false);
			    	}
			    	req.getRequestDispatcher("punch.jsp").forward(req,resp);
	            }else{
	            	req.getRequestDispatcher("newLogin.jsp").forward(req,resp);  
	    			return;
	            	
	            }
	        }else{
	        	String oppenid=req.getSession().getAttribute("openid").toString();
	        	logger.debug("进入地图定位+");
	            logger.debug("进入地图定位+"+oppenid);
		        req.setAttribute("oppenid", oppenid);
		        logger.debug(oppenid+"是否注册");
	            List findnoList=findRelationService.findNumber(oppenid);
	            if(GetDateUtil.ifEmpty(findnoList)){
	            	logger.debug("进入地图定位");
		            logger.debug("进入地图定位"+oppenid);
			        req.setAttribute("oppenid", oppenid);
			        String loginId = findRelationService.findLogin(oppenid);
			        logger.debug("注册码"+loginId);
			    	req.setAttribute("loginIdd", loginId);
			    	//获取家长
			    	//String relationname = findRelationService.findrelation(oppenid);
			    	//req.setAttribute("relation", relationname);
			    	
			    	List findworkList=findRelationService.findWork(oppenid);
			    	req.setAttribute("amTime", "待签到");
		    		req.setAttribute("pmTime", "待签退");
			    	if(!findworkList.isEmpty()){
			    		for(int i=0;i<findworkList.size();i++){
				    		String time = "";
				    		String timelong= "";
				    		String flag = "";
				    		String relation = "";
				    		String amTime = "";
				    		String pmTime = "";
				    			HashMap paperMap = (HashMap) findworkList.get(i);
				    			timelong = paperMap.get("checktime").toString();
				    			time = GetDateUtil.getStr(timelong);
				    			flag =  paperMap.get("flag").toString();
				    			relation =  paperMap.get("relation").toString();
				    			req.setAttribute("relation", relation);
				    			if(flag.equals("10")){
				    				//上午的关系
				    				relation = findRelationService.amRelation(oppenid);
				    				req.setAttribute("amTime", time);
				    				req.setAttribute("amRelation", relation);
				    			}else if(flag.equals("20")){
				    				//下午的关系
				    				relation = findRelationService.pmRelation(oppenid);
				    				req.setAttribute("pmTime", time);
				    				req.setAttribute("pmRelation", relation);
				    			}
				    	}
			    	}else{
			    		req.setAttribute("ifshow", false);
			    	}
			    	req.getRequestDispatcher("punch.jsp").forward(req,resp);
	            }else{
	            	req.getRequestDispatcher("newLogin.jsp").forward(req,resp);  
	    			return;
	            }
	        }
	             
	    }
	    
	    @Override
		public void init(ServletConfig config) throws ServletException
		{
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		}

	 }
