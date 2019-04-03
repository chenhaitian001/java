package com.hinova.controller.systemmanagercontroller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hinova.entity.Role;
import com.hinova.entity.School;
import com.hinova.entity.User;
import com.hinova.service.PublicService;
import com.hinova.service.UserService;




@Controller
@RequestMapping("/houtai/user")
public class HouTai_User_Controller {
	
	
	@Resource
	private UserService  userService;
	@Resource
	private PublicService  publicService;
	
	
	/**
	 * 
	 * @param t  是null 代表，是全部
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "用户管理");
		mav.addObject("title", "用户管理");
		mav.setViewName("/admin/page/user/user_manage");
		return mav;
	}
	@RequestMapping("/role/manage")
	public ModelAndView manage_role() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "角色管理");
		mav.addObject("title", "角色管理");
		mav.setViewName("/admin/page/user/role_manage");
		return mav;
	}
	@RequestMapping("/school/manage")
	public ModelAndView manage_school() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "园所管理");
		mav.addObject("title", "园所管理");
		mav.setViewName("/admin/page/user/school_manage");
		return mav;
	}
	
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/user/add.do");
		
		mav.setViewName("/admin/page/user/user_add_or_update");
		return mav;
	}
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		User user = userService.findByUserId(Integer.parseInt(id));
		
		mav.addObject("user", user);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/user/update.do?id="+id);
		
		mav.setViewName("/admin/page/user/user_add_or_update");
		return mav;
	}
	
	@RequestMapping("/role/setPersm")
	public ModelAndView setPersm(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		//如果id有值就是 更新 如果没有值  就是添加
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", id);
		mav.setViewName("admin/page/user/set_persm");
		return mav;
	}
	
	@RequestMapping("/setPassword")
	public ModelAndView setPassword(@RequestParam(value = "id", required = false) String id,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		//如果id有值就是 更新 如果没有值  就是添加
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId", id);
		mav.addObject("save_url", "/admin/user/update?id="+id);
		mav.setViewName("admin/page/user/set_password");
		return mav;
	}
	
	//角色
	@RequestMapping("/role/add")
	public ModelAndView add_role() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/user/role/add.do");
		
		mav.setViewName("/admin/page/user/role_add_or_update");
		return mav;
	}
	
	
	@RequestMapping("/role/edit")
	public ModelAndView edit_role(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Role role = userService.findById_Role(Integer.parseInt(id));
		
		mav.addObject("role", role);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/user/role/update.do?id="+id);
		
		mav.setViewName("/admin/page/user/role_add_or_update");
		return mav;
	}
	//园所
		@RequestMapping("/school/add")
		public ModelAndView add_school() throws Exception {
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("btn_text", "添加");
			mav.addObject("save_url", "/admin/user/school/add.do");
			
			mav.setViewName("/admin/page/user/school_add_or_update");
			return mav;
		}
		
		
		@RequestMapping("/school/edit")
		public ModelAndView edit_school(@RequestParam(value="id",required=false)String id
				,HttpServletResponse response
				,HttpServletRequest request) throws Exception {
			ModelAndView mav = new ModelAndView();
			
			School school = userService.findBySchoolId(Integer.parseInt(id));
			
			mav.addObject("school", school);
			mav.addObject("btn_text", "修改");
			mav.addObject("save_url", "/admin/user/school/update.do?id="+id);
			
			mav.setViewName("/admin/page/user/school_add_or_update");
			return mav;
		}
	
}


