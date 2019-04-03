package com.hinova.dao;

import java.util.List;
import java.util.Map;

import com.hinova.entity.Teacher;

public interface WorkerDao {
	public List<Teacher> list(Map<String, Object> map);
	public Integer getTotal(Map<String, Object> map);
	public Integer add(Teacher teacher);
	public Integer edit(Teacher teacher);
	public Teacher findByName(String name);
	public Teacher findById(Integer id);
	public Integer delete_teacher(Integer id);
}
