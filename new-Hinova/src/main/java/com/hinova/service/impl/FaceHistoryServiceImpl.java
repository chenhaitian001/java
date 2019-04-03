package com.hinova.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hinova.dao.ClassDao;
import com.hinova.dao.EquipDao;
import com.hinova.dao.FaceHistoryDao;
import com.hinova.dao.GradeDao;
import com.hinova.entity.Clazz;
import com.hinova.entity.Device;
import com.hinova.entity.FaceHistory;
import com.hinova.entity.Grade;
import com.hinova.service.FaceHistoryService;
@Service("faceHistoryService")
public class FaceHistoryServiceImpl implements FaceHistoryService {
	@Resource
	private FaceHistoryDao faceHistoryDao;
	@Resource
	private ClassDao classDao;
	@Resource
	private GradeDao gradeDao;
	@Resource
	private EquipDao equipDao;
	
	public List<Grade> listGrade(Map<String, Object> map) {
	
		return gradeDao.list(map);
	}
	public Integer getGradeTotal(Map<String, Object> map) {
		return gradeDao.getTotal(map);
	}
	public List<Clazz> listClass(Map<String, Object> map) {
		
		return classDao.list(map);
	}
	public Integer getClassTotal(Map<String, Object> map) {
		return classDao.getTotal(map);
	}
	public List<FaceHistory> listRecord(Map<String, Object> map) {
		
		return faceHistoryDao.list(map);
	}
	public Integer getRecordTotal(Map<String, Object> map) {
		return faceHistoryDao.getTotal(map);
	}
	public Integer getEquipTotal(Map<String, Object> map) {
		return equipDao.getTotal(map);
	}
	
	@Override
	public List<Device> listEquip(Map<String, Object> map) {
		try {
			return equipDao.list(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<FaceHistory> listRecord_last(Map<String, Object> map) {
		
		return faceHistoryDao.list_last(map);
	}
	//添加年级
	@Override
	public Integer addGrade(Grade grade) {
		
		return gradeDao.add(grade);
	}
	@Override
	public Integer updateGrade(Grade grade) {
		// TODO Auto-generated method stub
		return gradeDao.edit(grade);
	}
	@Override
	public Grade findByGradeName(String name) {
		// TODO Auto-generated method stub
		return gradeDao.findByName(name);
	}
	@Override
	public Grade findByGradeId(Integer id) {
		// TODO Auto-generated method stub
		return gradeDao.findById(id);
	}
	@Override
	public Integer delete_grade(Integer id) {
		// TODO Auto-generated method stub
		return gradeDao.delete_grade(id);
	}
	
	//添加班级
	@Override
	public Integer addClass(Clazz clazz) {
		
		return classDao.add(clazz);
	}
	@Override
	public Integer updateClass(Clazz clazz) {
		// TODO Auto-generated method stub
		return classDao.edit(clazz);
	}
	@Override
	public Clazz findByClassName(String name) {
		// TODO Auto-generated method stub
		return classDao.findByName(name);
	}
	@Override
	public Clazz findByClassId(Integer id) {
		// TODO Auto-generated method stub
		return classDao.findById(id);
	}
	@Override
	public Integer delete_class(Integer id) {
		// TODO Auto-generated method stub
		return classDao.delete_class(id);
	}
	@Override
	public Integer addDevice(Device device) {
		// TODO Auto-generated method stub
		return equipDao.add(device);
	}
	@Override
	public Integer updateDevice(Device device) {
		// TODO Auto-generated method stub
		return equipDao.edit(device);
	}
	@Override
	public Device findByDeviceName(String name) {
		// TODO Auto-generated method stub
		return equipDao.findbyName(name);
	}
	@Override
	public Device findByDeviceId(Integer id) {
		// TODO Auto-generated method stub
		return equipDao.findbyId(id);
	}
	@Override
	public Integer delete_device(Integer id) {
		// TODO Auto-generated method stub
		return equipDao.delete_device(id);
	}
}
