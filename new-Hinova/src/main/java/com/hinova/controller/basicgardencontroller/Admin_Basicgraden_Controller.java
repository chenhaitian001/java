package com.hinova.controller.basicgardencontroller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hinova.entity.Clazz;
import com.hinova.entity.Device;
import com.hinova.entity.FaceHistory;
import com.hinova.entity.Grade;
import com.hinova.entity.PageBean;
import com.hinova.entity.User;
import com.hinova.service.FaceHistoryService;
import com.hinova.util.ResponseUtil;


@Controller
@RequestMapping("/basicgarden")
public class Admin_Basicgraden_Controller {
	 private static Logger logger = LoggerFactory.getLogger(Admin_Basicgraden_Controller.class);
	@Resource
	private FaceHistoryService faceHistoryService;
	/**
	 * 访客记录
	 * @param page
	 * @param rows
	 * @param date1
	 * @param date2
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list_face")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "timeBtn1", required = false) String timeBtn1, 
			@RequestParam(value = "timeBtn2", required = false) String timeBtn2, 
			@RequestParam(value = "comoncode", required = false) String comoncode, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("date1", timeBtn1);
		map.put("date2", timeBtn2);
		map.put("code", comoncode);
	//	List<Clazz> list1 = faceHistoryService.listClass(map);
	//	list1.get(0).g
	//	List<Grade> list2 = faceHistoryService.listGrade(map);
		
		List<FaceHistory> list = faceHistoryService.listRecord(map);
		Integer total = faceHistoryService.getRecordTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		System.out.println(gson.toJson(map));
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	/**
	 * 年级管理
	 * @param page
	 * @param rows
	 * @param date1
	 * @param date2
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list_grade")
	public String list1(@RequestParam(value = "page", required = false) String page,
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
		
		List<Grade> list = faceHistoryService.listGrade(map);
		Integer total = faceHistoryService.getGradeTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/grade/add")
	public ModelAndView grade_add_orupdate() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/basicgarden/grade/new_add.do");
		
		mav.setViewName("/admin/page/user/grade_add_or_update");
		return mav;
	}
	@RequestMapping("/grade/new_add")
	public String add(Grade grade, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		grade.setCreateUser(currentUser.getName());
		int resultTotal =0;
		try {
			resultTotal = faceHistoryService.addGrade(grade);
		} catch (Exception e) {
			logger.info("添加年级失败");
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
	@RequestMapping("/grade/edit")
	public ModelAndView edit(@RequestParam(value="gid",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Grade grade=  faceHistoryService.findByGradeId(Integer.parseInt(id));
	//	User user = userService.findById(Integer.parseInt(id));
		
		mav.addObject("grade", grade);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/basicgarden/grade/new_update?id="+id);
		
		mav.setViewName("/admin/page/user/grade_add_or_update");
		return mav;
	}
	@RequestMapping("/grade/new_update")
	public String newupdate(Grade grade, @RequestParam(value="id",required=false)String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		grade.setEditUser(currentUser.getName());//编辑人
		grade.setGid(id);
		int resultTotal =0;
		try {
			resultTotal = faceHistoryService.updateGrade(grade);
		} catch (Exception e) {
			logger.info("编辑年级失败");
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
	@RequestMapping("/grade/delete")
	public String deleteGrade(Grade grade, @RequestParam(value="ids",required=false)String ids,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			try {
				faceHistoryService.delete_grade(Integer.parseInt(idsStr[i]));
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
	/**
	 * 班级管理
	 * @param page
	 * @param rows
	 * @param date1
	 * @param date2
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list_class")
	public String list2(@RequestParam(value = "page", required = false) String page,
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
		
		List<Clazz> list = faceHistoryService.listClass(map);
		Integer total = faceHistoryService.getClassTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/class/add")
	public ModelAndView class_add_orupdate() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/basicgarden/class/new_add.do");
		
		mav.setViewName("/admin/page/user/class_add_or_update");
		return mav;
	}
	@RequestMapping("/class/new_add")
	public String classadd(Clazz clazz, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		clazz.setCreateUser(currentUser.getName());
		int resultTotal =0;
		try {
			resultTotal = faceHistoryService.addClass(clazz);
		} catch (Exception e) {
			logger.info("添加年级失败");
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
	@RequestMapping("/class/edit")
	public ModelAndView classedit(@RequestParam(value="cid",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Clazz clazz=  faceHistoryService.findByClassId(Integer.parseInt(id));
	//	User user = userService.findById(Integer.parseInt(id));
		Grade grade =clazz.getGrade();
		mav.addObject("grade",grade);
		mav.addObject("clazz", clazz);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/basicgarden/class/new_update?id="+id);
		
		mav.setViewName("/admin/page/user/class_add_or_update");
		return mav;
	}
	@RequestMapping("/class/new_update")
	public String classnewupdate(Clazz clazz, @RequestParam(value="id",required=false)String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		clazz.setEditUser(currentUser.getName());//编辑人
		clazz.setCid(id);
		int resultTotal =0;
		try {
			resultTotal = faceHistoryService.updateClass(clazz);
		} catch (Exception e) {
			logger.info("编辑年级失败");
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
	@RequestMapping("/class/delete")
	public String deleteClass(Clazz clazz, @RequestParam(value="ids",required=false)String ids,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			try {
				faceHistoryService.delete_class(Integer.parseInt(idsStr[i]));
				result.put("success", true);
				result.put("msg", "删除成功");
			} catch (Exception e) {
				logger.info("删除年级失败");
				result.put("success", false);
				result.put("msg", "删除失败，班级被占用");
			}
			
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	/**
	 * 考勤设备
	 * @param page
	 * @param rows
	 * @param date1
	 * @param date2
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list_equip")
	public String list3(@RequestParam(value = "page", required = false) String page,
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
		
		List<Device> list = faceHistoryService.listEquip(map);
		Integer total = faceHistoryService.getEquipTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/device/add")
	public ModelAndView device_add_orupdate() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/basicgarden/device/new_add.do");
		
		mav.setViewName("/admin/page/user/device_add_or_update");
		return mav;
	}
	@RequestMapping("/device/new_add")
	public String deviceadd(Device device, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		device.setCreateUser(currentUser.getName());
		int resultTotal =0;
		try {
			resultTotal = faceHistoryService.addDevice(device);
		} catch (Exception e) {
			logger.info("添加年级失败");
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
	@RequestMapping("/device/edit")
	public ModelAndView deviceedit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Device device=  faceHistoryService.findByDeviceId(Integer.parseInt(id));
	//	User user = userService.findById(Integer.parseInt(id));
		mav.addObject("device", device);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/basicgarden/device/new_update?id="+id);
		
		mav.setViewName("/admin/page/user/device_add_or_update");
		return mav;
	}
	@RequestMapping("/device/new_update")
	public String devicenewupdate(Device device, @RequestParam(value="id",required=false)String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		device.setEditUser(currentUser.getName());//编辑人
		device.setId(id);
		int resultTotal =0;
		try {
			resultTotal = faceHistoryService.updateDevice(device);
		} catch (Exception e) {
			logger.info("编辑年级失败");
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
	@RequestMapping("/device/delete")
	public String deletedevice(Device device, @RequestParam(value="ids",required=false)String ids,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			try {
				faceHistoryService.delete_device(Integer.parseInt(idsStr[i]));
				result.put("success", true);
				result.put("msg", "删除成功");
			} catch (Exception e) {
				logger.info("删除年级失败");
				result.put("success", false);
				result.put("msg", "删除失败，设备被占用");
			}
			
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	/**
	 * 监控大屏
	 * @param page
	 * @param rows
	 * @param date1
	 * @param date2
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list_monitor")
	public String list5(@RequestParam(value = "page", required = false) String page,
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
		
		List<FaceHistory> list = faceHistoryService.listRecord(map);
		Integer total = faceHistoryService.getRecordTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/monitor_last")
	@ResponseBody
	public List listLast(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
	//	PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("start", pageBean.getStart());
		//map.put("size", pageBean.getPageSize());
		map.put("date1", date1);
		map.put("date2", date2);
		
		List<FaceHistory> list = faceHistoryService.listRecord_last(map);
		/*Integer total = faceHistoryService.getRecordTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));*/
		return list;
	}
	
}
