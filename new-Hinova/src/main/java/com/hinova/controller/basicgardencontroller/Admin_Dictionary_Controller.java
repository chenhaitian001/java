package com.hinova.controller.basicgardencontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hinova.entity.Clazz;
import com.hinova.entity.Grade;
import com.hinova.entity.Role;
import com.hinova.entity.School;
import com.hinova.entity.Student;
import com.hinova.service.PublicService;

@Controller
@RequestMapping("/dictionary")
public class Admin_Dictionary_Controller {
	@Resource
	private PublicService publicService;
	@RequestMapping("/grade")
    @ResponseBody
    public List<Grade> getGrade(){
		
		List<Grade> list= publicService.listGrade();
		
		
		return list;
	}
	@RequestMapping("/clazz")
    @ResponseBody
    public List<Clazz> getClazz(@RequestParam(value = "gradeId", required = false) String gradeId,HttpServletRequest requset){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gradeId", gradeId);
		List<Clazz> list= publicService.listClazz(map);
		
		
		return list;
	}
	@RequestMapping("/student")
    @ResponseBody
    public List<Student> getStudent(){
		
		List<Student> list= publicService.listStudent();
		
		
		return list;
	}
	@RequestMapping("/role")
    @ResponseBody
    public List<Role> getRole(){
		
		List<Role> list= publicService.listRole();
		
		
		return list;
	}
	@RequestMapping("/school")
    @ResponseBody
    public List<School> getSchool(){
		
		List<School> list= publicService.listSchool();
		
		
		return list;
	}
}
