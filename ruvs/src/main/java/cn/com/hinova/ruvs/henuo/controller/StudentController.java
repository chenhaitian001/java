package cn.com.hinova.ruvs.henuo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.com.hinova.ruvs.common.bean.Paging;
import cn.com.hinova.ruvs.common.bean.Result;
import cn.com.hinova.ruvs.henuo.entity.Bar;
import cn.com.hinova.ruvs.henuo.entity.PageBean;
import cn.com.hinova.ruvs.henuo.service.StudentService;
import cn.com.hinova.ruvs.utils.ResponseUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;

@Controller

@RequestMapping("/config/newstudent")
public class StudentController {
	@Resource
	protected StudentService studentService;
	/**
	 * 学生信息查询
	 * @param req
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findPaging.do")
	@ResponseBody
	public Paging  getPaging(HttpServletRequest req,Paging paging) throws Exception {
		String id1 = req.getParameter("p1");
		String id2 = req.getParameter("p2");
		String id3 = req.getParameter("p3");
		return studentService.queryStudent(id1,id2,id3,paging);
	}
	/**
	 * 批量升班
	 * @param req
	 * @return
	 */
	@RequestMapping("upClass.do")
	@ResponseBody
	public Result upClass(HttpServletRequest req){
		String id[]  = req.getParameterValues("id");
		try {
			studentService.upClass(id);
			
			return new Result();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new Result(2120, "操作失败", ex.getMessage(),null);
		}
		
	}
	
	
	/**
	 * 查找离园学生
	 */
	@RequestMapping("findLeaveST.do")
	@ResponseBody
	public Paging findLeaveST(HttpServletRequest req,Paging paging) throws Exception {
		String id1 = req.getParameter("p1");
		return studentService.queryLeavest(id1,paging);
		
	}
	@RequestMapping("eacharts.do")
	public String Eacharts(HttpServletResponse response){
		Gson gson = new Gson();
		
		List list = new ArrayList();
		for(int i=1;i<5;i++){
			Bar  b = new Bar();
			b.setName("班级"+i);
			b.setNum(i);
			list.add(b);
		}
		JSONArray json = JSONArray.fromObject(list);
		try {
			ResponseUtil.write(response, gson.toJson(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
