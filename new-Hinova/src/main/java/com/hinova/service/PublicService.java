package com.hinova.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.hinova.entity.Clazz;
import com.hinova.entity.Grade;
import com.hinova.entity.Role;
import com.hinova.entity.School;
import com.hinova.entity.Student;




public interface PublicService {
	
	
	public void addLeftMenu(ModelAndView mav);
	
	public List<Grade> listGrade() ;
	public List<Student> listStudent() ;
	public List<Clazz> listClazz(Map<String,Object> map) ;
	public List<Role> listRole() ;
	public List<School> listSchool() ;
}
