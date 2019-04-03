package com.hinova.dao;

import java.util.List;
import java.util.Map;

import com.hinova.entity.Device;
import com.hinova.entity.FaceHistory;

public interface EquipDao {
	public List<Device> list(Map<String, Object> map);
	public Integer getTotal(Map<String, Object> map);
	public Integer add(Device device);
	public Integer edit(Device device);
	public Device findbyName(String name);
	public Device findbyId(Integer id);
	public Integer delete_device(Integer id);
}
