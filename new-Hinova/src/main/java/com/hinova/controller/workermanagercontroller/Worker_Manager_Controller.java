package com.hinova.controller.workermanagercontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hinova.entity.Clazz;
import com.hinova.entity.Grade;
import com.hinova.entity.PageBean;
import com.hinova.entity.Teacher;
import com.hinova.entity.User;
import com.hinova.service.WorkerService;
import com.hinova.util.ResponseUtil;

@Controller
@RequestMapping("/worker")
public class Worker_Manager_Controller {
	 private static Logger logger = LoggerFactory.getLogger(Worker_Manager_Controller.class);

	@Resource
	private WorkerService workerService;
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("date1", date1);
		map.put("date2", date2);
		
		List<Teacher> list = workerService.listTeacher(map);
		Integer total = workerService.getTeacherTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/teacher/add")
	public ModelAndView teacher_add_orupdate() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/worker/teacher/new_add.do");
		
		mav.setViewName("/admin/page/user/teacher_add_or_update");
		return mav;
	}
	@RequestMapping("/teacher/new_add")
	public String add(Teacher teacher, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		teacher.setCreateUser(currentUser.getName());
		int resultTotal =0;
		try {
			resultTotal = workerService.addTeacher(teacher);
		} catch (Exception e) {
			logger.info("添加教师失败");
		}
		
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
			result.put("msg", "添加成功");
		} else {
			result.put("success", false);
			result.put("msg", "添加失败");
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	@RequestMapping("/teacher/edit")
	public ModelAndView edit_teacher(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Teacher teacher=  workerService.findByTeacherId(Integer.parseInt(id));
	//	User user = userService.findById(Integer.parseInt(id));
		Grade grade = teacher.getGrade();
		Clazz clazz = teacher.getClazz();
		mav.addObject("teacher", teacher);
		mav.addObject("grade", grade);
		mav.addObject("clazz", clazz);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/worker/teacher/new_update?id="+id);
		
		mav.setViewName("/admin/page/user/teacher_update");
		return mav;
	}
	@RequestMapping("/teacher/new_update")
	public String newupdate(Teacher teacher, @RequestParam(value="id",required=false)String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		teacher.setEditUser(currentUser.getName());//编辑人
		teacher.setId(Integer.parseInt(id));;
		int resultTotal =0;
		try {
			resultTotal = workerService.updateTeacher(teacher);
		} catch (Exception e) {
			logger.info("编辑教师失败");
		}
		
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
			result.put("msg", "编辑成功");
		} else {
			result.put("success", false);
			result.put("msg", "编辑失败");
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	@RequestMapping("/teacher/delete")
	public String deleteTeacher(Teacher teacher, @RequestParam(value="ids",required=false)String ids,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			try {
				workerService.delete_Teacher(Integer.parseInt(idsStr[i]));
				result.put("success", true);
				result.put("msg", "删除成功");
			} catch (Exception e) {
				logger.info("删除年级失败");
				result.put("success", false);
				result.put("msg", "删除失败，年级被占用");
			}
			
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
}
