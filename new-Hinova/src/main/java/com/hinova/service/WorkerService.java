package com.hinova.service;

import java.util.List;
import java.util.Map;

import com.hinova.entity.Teacher;

public interface WorkerService {
	public List<Teacher> listTeacher(Map<String, Object> map) ;
	public Integer getTeacherTotal(Map<String, Object> map);
	public Integer addTeacher(Teacher teacher);
	public Integer updateTeacher(Teacher teacher);
	public Teacher findByTeacherName(String name);
	public Teacher findByTeacherId(Integer id); 
	public Integer delete_Teacher(Integer id);
}
