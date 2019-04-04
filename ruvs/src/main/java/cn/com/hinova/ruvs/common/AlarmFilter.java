package cn.com.hinova.ruvs.common;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.sys.bean.User;
import cn.com.hinova.ruvs.utils.MapUtils;
import cn.com.hinova.ruvs.utils.UserUtils;
import net.sf.json.JSONObject;



public class AlarmFilter implements Filter {
 
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
 
        String servletPath = request.getServletPath();
        //System.out.println("进入过滤");
        request.getRequestDispatcher(servletPath).forward(request, response);
        
 
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
