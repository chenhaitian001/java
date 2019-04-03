package com.hinova.dao;

import java.util.List;
import java.util.Map;

import com.hinova.entity.Grade;

public interface GradeDao {
	public List<Grade> list(Map<String, Object> map);
	public Integer getTotal(Map<String, Object> map);
	public Integer add(Grade grade);
	public Integer edit(Grade grade);
	public Grade findByName(String name);
	public Grade findById(Integer id);
	public Integer delete_grade(Integer id);
	
	//查询年级
	public List<Grade> listgrade();
}
