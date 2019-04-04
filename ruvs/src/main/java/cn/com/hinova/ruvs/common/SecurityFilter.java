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

public class SecurityFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hreq=(HttpServletRequest) req;
		HttpServletResponse hresp= (HttpServletResponse) resp;

		String url=hreq.getRequestURI().replace(hreq.getContextPath()+"/", "");
		if(url.endsWith("detail_by_month_v2.do")||url.endsWith("detail_by_month_v2_export_pdf.do")){

		}else if(url.endsWith("config/searchmap/search_TX_map.do")){
			return;
		}else if(url.endsWith("login.do")||url.endsWith("subsys/login.jsp")){

		}else if(hreq.getSession().getAttribute("user")==null){
			hresp.sendRedirect(hreq.getContextPath()+"/login.jsp");
			return;
		}else{
			@SuppressWarnings("unchecked")
			Set<String> urls=(Set<String>) hreq.getSession().getAttribute("urls");
			@SuppressWarnings("unchecked")
			Set<String> allUrls=(Set<String>) hreq.getSession().getAttribute("allUrls");
			if(urls==null||(allUrls!=null&&allUrls.contains(url)&&!urls.contains(url))){
				if(hreq.getHeader("X-Requested-With")==null){
					//普通请求
					hresp.sendRedirect("403.jsp");
					return;
				}else{
					//ajax请求
					hresp.getWriter().print(MapUtils.toMap("code",403,"msg","没有权限!"));
					return;
				}
			}
		}

		UserUtils.userLocal.set((User)hreq.getSession().getAttribute("user"));
		
		chain.doFilter(hreq, hresp);
		Cache.paramMapLocal.remove();
		UserUtils.userLocal.remove();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
