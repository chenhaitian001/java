package com.hinova.dao;

import java.util.List;
import java.util.Map;

import com.hinova.entity.OutPutStudent;
import com.hinova.entity.Student;

public interface ChildrenDao {
	public List<Student> list(Map<String, Object> map);
	public List<Student> listLeave(Map<String, Object> map);
	public Integer getTotal(Map<String, Object> map);
	public Integer add(Student student);
	public Integer edit(Student student);
	public Student findByName(String name);
	public Student findById(String id);
	public Integer delete_student(String id);
	
	public List<Student> listStudent();
	public List<OutPutStudent> listExport(Map<String, Object> map);
	
	public Student listGradeClazzbyStudent(String id);
	
	public Integer upClazz(Map<String, Object> map);
}
