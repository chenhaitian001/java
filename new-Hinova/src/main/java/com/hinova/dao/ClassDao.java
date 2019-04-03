package com.hinova.dao;

import java.util.List;
import java.util.Map;

import com.hinova.entity.Clazz;
import com.hinova.entity.Grade;

public interface ClassDao {
	public List<Clazz> list(Map<String, Object> map);
	public Integer getTotal(Map<String, Object> map);
	public Integer add(Clazz clazz);
	public Integer edit(Clazz clazz);
	public Clazz findByName(String name);
	public Clazz findById(Integer id);
	public Integer delete_class(Integer id);
	
	public List<Clazz> listClazz(Map<String,Object> map);
	//结合年级查找班级
	public Clazz findByNameOnGradeName(Map<String, Object> map);
	
	public List<Clazz> listClazzByGradeId(String gradeid);
}
