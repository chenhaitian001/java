package com.hinova.controller.basicgardencontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hinova.entity.PageBean;
import com.hinova.entity.Role;
import com.hinova.entity.School;
import com.hinova.entity.User;
import com.hinova.service.UserService;
import com.hinova.util.CryptographyUtil;
import com.hinova.util.ResponseUtil;



@Controller
@RequestMapping("/admin/user")
public class Admin_User_Controller {
	
	@Resource
	private UserService userService;
	
	
	@RequestMapping("/add")
	public String add(User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
		user.setPassword(CryptographyUtil.md5(user.getPassword(), "chenhaitian"));
		user.setCreateDateTime(new Date());
		
		int resultTotal = userService.add_User(user);
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
	
	
	
	/**
	 * 修改 密码 在不退出登陆的情况下。可以多次修改密码。
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword, HttpServletResponse response) throws Exception {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		user.setPassword(CryptographyUtil.md5(newPassword, "chenhaitian"));
		int resultTotal = userService.update_User(user);
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
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q, 
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		//map.put("q", "1");
		map.put("date1", date1);
		map.put("date2", date2);
		
		List<User> list = userService.list_User(map);
		Integer total = userService.getUserTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	/**
	 * /admin/user/update
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
		user.setPassword(CryptographyUtil.md5(user.getPassword(), "chenhaitian"));
		
		int resultTotal = userService.update_User(user);
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

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		
		for (int i = 0; i < idsStr.length; i++) {
			userService.delete_User(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return null;
	}
	
	//角色
	@RequestMapping("/role/list")
	public String list_role(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q, 
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
	//	map.put("q", "1");
		map.put("date1", date1);
		map.put("date2", date2);
		
		List<Role> list = userService.list_Role(map);
		Integer total = userService.getTotal_Role(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/role/add")
	public String add_role(Role role, HttpServletResponse response, HttpServletRequest request) throws Exception {
		role.setCreateDateTime(new Date());
		
		int resultTotal = userService.add_Role(role);
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
	@RequestMapping("/role/update")
	public String update_role(Role role, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		
		int resultTotal = userService.update_Role(role);
		JSONObject result = new JSONObject();
		
		if (resultTotal > 0) {
			result.put("success", true);
			result.put("msg", "修改成功");
		} else {
			result.put("success", false);
			result.put("msg", "添加失败");
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	@RequestMapping("/role/delete")
	public String delete_role(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		
		for (int i = 0; i < idsStr.length; i++) {
			userService.delete_Role(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result.toString());
		return null;
	}
	//园所
		@RequestMapping("/school/list")
		public String list_school(@RequestParam(value = "page", required = false) String page,
				@RequestParam(value = "limit", required = false) String rows,
				@RequestParam(value = "q", required = false) String q, 
				@RequestParam(value = "date1", required = false) String date1, 
				@RequestParam(value = "date2", required = false) String date2, 
				HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		//	map.put("q", "1");
			map.put("date1", date1);
			map.put("date2", date2);
			
			List<School> list = userService.list_School(map);
			Integer total = userService.getSchoolTotal(map);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
			
			map.clear();
			map.put("data", list);
			map.put("count", total);
			map.put("code", 0);
			map.put("msg", "");
			ResponseUtil.write(response, gson.toJson(map));
			return null;
		}
		@RequestMapping("/school/add")
		public String add_school(School school, HttpServletResponse response, HttpServletRequest request) throws Exception {
			User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			school.setCreateUser(currentUser.getName());
			int resultTotal = userService.add_School(school);
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
		@RequestMapping("/school/update")
		public String update_school(School school, HttpServletResponse response, HttpServletRequest request) throws Exception {
			User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			school.setEditUser(currentUser.getName());
			
			int resultTotal = userService.update_School(school);
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
		@RequestMapping("/school/delete")
		public String delete_school(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
				throws Exception {
			String[] idsStr = ids.split(",");
			JSONObject result = new JSONObject();
			
			for (int i = 0; i < idsStr.length; i++) {
				userService.delete_School(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			ResponseUtil.write(response, result.toString());
			return null;
		}
}
