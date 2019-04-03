package com.hinova.dao;

import java.util.List;
import java.util.Map;

import com.hinova.entity.Patriarch;

public interface PatriarchDao {
	public List<Patriarch> list_Patriarch(Map<String, Object> map);
	public Integer getPatriarchTotal(Map<String, Object> map);
	public Integer addPatriarch(Patriarch patriarch);
	public Integer editPatriarch(Patriarch patriarch);
	public Patriarch findByPatriarchName(String name);
	public Patriarch findByPatriarchId(String id);
	public Integer delete_Patriarch(String id);
}
