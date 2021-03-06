package com.hinova.dao;


import java.util.List;
import java.util.Map;

import com.hinova.entity.Role;



public interface RoleDao {
	
	public Integer add(Role role);
	
	public Integer update(Role role);
	
	public List<Role> list(Map<String, Object> map);
	
	public Integer getTotal(Map<String, Object> map);
	
	public Role findByName(String name);
	
	public Role findById(Integer id);
	
	public Integer delete(Integer id);
	
	public List<Role> listRole();
	
}
