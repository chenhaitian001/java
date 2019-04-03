package com.hinova.service;

import java.util.List;
import java.util.Map;



import com.hinova.entity.Clazz;
import com.hinova.entity.Device;
import com.hinova.entity.FaceHistory;
import com.hinova.entity.Grade;


public interface FaceHistoryService {
	public List<Grade> listGrade(Map<String, Object> map) ;
	public Integer getGradeTotal(Map<String, Object> map);
	public Integer addGrade(Grade grade);
	public Integer updateGrade(Grade grade);
	public Grade findByGradeName(String name);
	public Grade findByGradeId(Integer id); 
	public Integer delete_grade(Integer id);
	
	public List<Clazz> listClass(Map<String, Object> map);
	public Integer getClassTotal(Map<String, Object> map);
	public Integer addClass(Clazz clazz);
	public Integer updateClass(Clazz clazz);
	public Clazz findByClassName(String name);
	public Clazz findByClassId(Integer id); 
	public Integer delete_class(Integer id);
	
	public List<FaceHistory> listRecord(Map<String, Object> map);
	public Integer getRecordTotal(Map<String, Object> map) ;
	public List<FaceHistory> listRecord_last(Map<String, Object> map);
	
	public List<Device> listEquip(Map<String, Object> map);
	public Integer getEquipTotal(Map<String, Object> map);
	public Integer addDevice(Device device);
	public Integer updateDevice(Device device);
	public Device findByDeviceName(String name);
	public Device findByDeviceId(Integer id); 
	public Integer delete_device(Integer id);
	
}
