package com.hinova.controller;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.hinova.entity.Role;
import com.hinova.entity.Tree;
import com.hinova.entity.User;
import com.hinova.service.TreeService;
import com.hinova.service.UserService;
import com.hinova.util.CryptographyUtil;
import com.hinova.util.MyUtil;
import com.hinova.util.ResponseUtil;
import com.utils.FaceApi2;
import com.utils.base64Util;





@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService  userService;
	@Resource
	private TreeService treeService;
	@RequestMapping("/admin")
	public String checklogin(User user,HttpServletResponse response,HttpServletRequest request
			,RedirectAttributes attr)throws Exception{
		JSONObject result = new  JSONObject();
		SecurityUtils.getSubject().getSession().setTimeout(3600000);
		//判断是否为系统登录
		SecurityUtils.getSubject().getSession().setAttribute("login_type", "user_login");
		
		//shiro通过SecurityUtils.getSubject()获得主体，主体可以理解为客户端实例，原理在后面讲
        Subject subject = SecurityUtils.getSubject();
        //验证登录判断用户名密码用subject.login(token); 自定义realm
		UsernamePasswordToken token=new UsernamePasswordToken(user.getName(), CryptographyUtil.md5(user.getPassword(), "chenhaitian"));
		try {
			subject.login(token);
			
			//将登录信息放入session中
			user = userService.findByUserName(user.getName());
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", user); 
			result.put("success", true);
			result.put("msg","登录成功");
			ResponseUtil.write(response, result.toString());
			return null;
			
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg","用户名密码错误");
			
			ResponseUtil.write(response, result.toString());
			return null;
		}
		
	}
	@RequestMapping("/face_login")
	public ModelAndView faceLogin(@RequestParam(value = "message", required = false) String message,HttpServletResponse response,HttpServletRequest request
			,RedirectAttributes attr)throws Exception{
		
		//System.out.println(message);
		File file=new File("d:\\new1.jpg");
		//转图片
		base64Util.GenerateImage(message, file);
		
		FaceApi2.search("", "a4bfa59d-5a04-49af-9819-c21760249aa9", message);
		
		ModelAndView mav = new ModelAndView();
		if(1==1){
			return mav;
		}
		JSONObject result = new  JSONObject();
		
		try{
			User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			currentUser = userService.findByUserId(currentUser.getId());
			//获取jues
			Map<String, Object> map = new HashMap<String, Object>();
			//通过roleids 获取 角色id
			String  roleids= currentUser.getRoleIds();
			Role roles = userService.findById_Role(Integer.parseInt(roleids));
		    String menuIds =roles.getMenuIds();
			if(menuIds==null){
				menuIds = "";
			}
			List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(menuIds);  
			map.put("father", -1);
			map.put("ids", ids);
			
			if(ids.size()>0){
			}else{
				mav.addObject("treeList", null);
			}
			mav.addObject("leftPage", "/admin/common/left_menu.jsp");
			try {
				List<Tree> treeList = getTreesByParentId(map);
				mav.addObject("treeList", treeList);
				Gson gson = new Gson();
				gson.toJson(treeList);
				//ResponseUtil.write(response, gson.toJson(treeList));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		  // mav.setViewName("/admin/main");
			 mav.setViewName("/admin/page/user/top");
			//mav.setViewName("/login/NewFile");
			return mav;
			
		}catch(Exception e){
			
		}
		return mav;
	}
	/**
	 * /user/login
	 * 用户登录
	 */
	@RequestMapping("/login")
	public ModelAndView login(User user,HttpServletResponse response,HttpServletRequest request
			,RedirectAttributes attr)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		JSONObject result = new  JSONObject();
		
		try{
			User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			currentUser = userService.findByUserId(currentUser.getId());
			//获取jues
			Map<String, Object> map = new HashMap<String, Object>();
			//通过roleids 获取 角色id
			String  roleids= currentUser.getRoleIds();
			Role roles = userService.findById_Role(Integer.parseInt(roleids));
		    String menuIds =roles.getMenuIds();
			if(menuIds==null){
				menuIds = "";
			}
			List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(menuIds);  
			map.put("father", -1);
			map.put("ids", ids);
			
			if(ids.size()>0){
			}else{
				mav.addObject("treeList", null);
			}
			mav.addObject("leftPage", "/admin/common/left_menu.jsp");
			try {
				List<Tree> treeList = getTreesByParentId(map);
				mav.addObject("treeList", treeList);
				Gson gson = new Gson();
				gson.toJson(treeList);
				//ResponseUtil.write(response, gson.toJson(treeList));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		  // mav.setViewName("/admin/main");
			 mav.setViewName("/admin/page/user/top");
			//mav.setViewName("/login/NewFile");
			return mav;
			
		}catch(Exception e){
			
		}
		return mav;
	}
	/**
	 * 
	 * @param user
	 * @param response
	 * @param request
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login_Tree")
	public String login_Tree(User user,HttpServletResponse response,HttpServletRequest request
			,RedirectAttributes attr)throws Exception{
		
		ModelAndView mav = new ModelAndView();
		JSONObject result = new  JSONObject();
		
		try{
			//User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			User	currentUser = userService.findByUserId(3);
			//获取jues
			Map<String, Object> map = new HashMap<String, Object>();
			String menuIds = currentUser.getRoleIds();
			if(menuIds==null){
				menuIds = "";
			}
			List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(menuIds);  
			map.put("father", -1);
			map.put("ids", ids);
			
			if(ids.size()>0){
			}else{
				mav.addObject("treeList", null);
			}
			try {
				List<Tree> treeList = getTreesByParentId(map);
				mav.addObject("treeList", treeList);
				Gson gson = new Gson();
				gson.toJson(treeList);
				ResponseUtil.write(response, gson.toJson(treeList));
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch(Exception e){
			
		}
		return null;
	}
	public List<Tree> getTreesByParentId(Map<String,Object> map) throws Exception {
		List<Tree> list = treeService.getTreesByFatherOrIds(map);
		for(Tree tree : list){
			if("open".equals(tree.getState())){
				continue;
			}else{
				map.put("father", tree.getId()+"");
				tree.setChildren(getTreesByParentId(map));
			}
		}
		return list;
	}
	@RequestMapping("/fail")
	public ModelAndView fail(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/login/login");
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro
		return "redirect:/login/login.jsp";
	}
}
