package com.hinova.service;

import java.util.List;
import java.util.Map;

import com.hinova.entity.Role;
import com.hinova.entity.School;
import com.hinova.entity.User;


public interface UserService {
	//角色
	public Integer add_Role(Role role);
	
	public Integer update_Role(Role Role);

	public List<Role> list_Role(Map<String, Object > map);
	
	public Integer getTotal_Role(Map<String, Object > map);

	public Role findByName_Role(String name);
	
	public Role findById_Role(Integer id); 
	
	public Integer delete_Role(Integer id);
	
	//用户
	//角色
	public Integer add_User(User user);
		
	public Integer update_User(User user);

	public List<User> list_User(Map<String, Object > map);
		
	public Integer getUserTotal(Map<String, Object > map);

	public User findByUserName(String name);
		
	public User findByUserId(Integer id); 
		
	public Integer delete_User(Integer id);	
	
	//用户
	//园所
	public Integer add_School(School school);
			
	public Integer update_School(School school);

	public List<School> list_School(Map<String, Object > map);
			
	public Integer getSchoolTotal(Map<String, Object > map);

	public School findBySchoolName(String name);
		
	public School findBySchoolId(Integer id); 
			
	public Integer delete_School(Integer id);	
}
