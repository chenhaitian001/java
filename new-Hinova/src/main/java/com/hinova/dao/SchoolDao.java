package com.hinova.dao;


import java.util.List;
import java.util.Map;

import com.hinova.entity.School;



public interface SchoolDao {
	
	public Integer add(School school);
	
	public Integer update(School school);
	
	public List<School> list(Map<String, Object> map);
	
	public Integer getTotal(Map<String, Object> map);
	
	public School findByName(String name);
	
	public School findById(Integer id);
	
	public Integer delete(Integer id);
	
	public List<School> listSchool();
}
