package link.x86.wx.map.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 封装的返回数据工具
 * @author Administrator
 *
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
