package link.x86.wx.web;

import com.alibaba.fastjson.JSONObject;
import link.x86.winxin.WeiXin;
import link.x86.winxin.bean.AccessToken;
import link.x86.winxin.bean.UserInfo;
import link.x86.wx.db.DBUtils;
import link.x86.wx.db.IFHistory;
import link.x86.wx.init.Cache;
import link.x86.wx.map.service.FindRelationService;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 微信认证的接口
 */
public class IFHistoryServlet extends HttpServlet {

	 @Autowired
	 private FindRelationService findRelationService;
    private static Logger logger = LoggerFactory.getLogger(IFHistoryServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        SimpleDateFormat fmt=new SimpleDateFormat("yyyy/MM/dd");


        req.setAttribute("currentDate",fmt.format(new Date()));

        if(req.getSession().getAttribute("openid")==null) {
            String code = req.getParameter("code");

            logger.debug("Code信息为{}",code);

            if (code == null) {
                resp.getWriter().print("操作有误");
                return;
            }

            AccessToken accessToken = Cache.weiXin.getAccessToken(code);

            String openid=accessToken.getOpenid();
            logger.debug("教师来源信息查询"+openid);
            req.getSession().setAttribute("openid",openid );
            //判断是否为教师
            Boolean ifauthor= findRelationService.findAuthor(openid);
            if(ifauthor){
            	logger.debug(openid+"为教师");	
            	req.getRequestDispatcher("ifhistory.jsp").forward(req,resp);
            }else{
            	logger.debug(openid+"非教师");	
        		resp.sendRedirect("403.jsp");
        		return;
    		}
        }else{
        	String oppenid = req.getSession().getAttribute("openid").toString();
        	Boolean ifauthor= findRelationService.findAuthor(oppenid);
            if(ifauthor){
            	logger.debug(oppenid+"*为教师");	
            	req.getRequestDispatcher("ifhistory.jsp").forward(req,resp);
            }else{
            	logger.debug(oppenid+"*非教师");	
        		resp.sendRedirect("403.jsp");
        		return;
    		}
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	
        String openid= (String) req.getSession().getAttribute("openid");
        logger.debug(openid+"post查询来源打卡记录");	
        List<IFHistory> list=DBUtils.getIFHistory(openid,req.getParameter("date"),req.getParameter("jiesong"));
        String responseJson=JSONObject.toJSONString(list);

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(responseJson);
    }
    @Override
	public void init(ServletConfig config) throws ServletException
	{
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

}
