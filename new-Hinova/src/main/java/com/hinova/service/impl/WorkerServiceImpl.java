package com.hinova.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hinova.dao.WorkerDao;
import com.hinova.entity.Teacher;
import com.hinova.service.WorkerService;
@Service("workerService")
public class WorkerServiceImpl implements WorkerService {
	@Resource
	private WorkerDao workerdao;
	@Override
	public List<Teacher> listTeacher(Map<String, Object> map) {
		
		return workerdao.list(map);
	}

	@Override
	public Integer getTeacherTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		return workerdao.getTotal(map);
	}

	@Override
	public Integer addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		
		return workerdao.add(teacher);
	}

	@Override
	public Integer updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return workerdao.edit(teacher);
	}

	@Override
	public Teacher findByTeacherName(String name) {
		
		return workerdao.findByName(name);
		
	}

	@Override
	public Teacher findByTeacherId(Integer id) {
		// TODO Auto-generated method stub
		return workerdao.findById(id);
	}

	@Override
	public Integer delete_Teacher(Integer id) {
		// TODO Auto-generated method stub
		return workerdao.delete_teacher(id);
	}

}
