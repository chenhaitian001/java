package com.hinova.service;

import java.util.List;
import java.util.Map;

import com.hinova.entity.OutPutStudent;
import com.hinova.entity.Patriarch;
import com.hinova.entity.Student;

public interface ChildrenService {
	
	public List<Student> listStudent(Map<String, Object> map) ;
	public List<Student> listLeaveStudent(Map<String, Object> map) ;

	public Integer getStudentTotal(Map<String, Object> map);
	public Integer addStudent(Student student);
	public Integer updateStudent(Student student);
	public Student findByStudentName(String name);
	public Student findByStudentId(String id); 
	public Integer delete_student(String id);
	public List<OutPutStudent> exportStudent(Map<String, Object> map) ;
	public void fileList(List veList);
	public void upClazz(String[] id);
	
	//真的家长
	public List<Patriarch> listPatriarch(Map<String, Object> map) ;
	public Integer getPatriarchTotal(Map<String, Object> map);
	public Integer addPatriarch(Patriarch Patriarch);
	public Integer updatePatriarch(Patriarch Patriarch);
	public Patriarch findByPatriarchName(String name);
	public Patriarch findByPatriarchId(String id); 
	public Integer delete_Patriarch(String id);

}
