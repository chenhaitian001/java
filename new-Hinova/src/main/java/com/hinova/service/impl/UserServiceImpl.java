package com.hinova.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hinova.dao.RoleDao;
import com.hinova.dao.SchoolDao;
import com.hinova.dao.UserDao;
import com.hinova.entity.Role;
import com.hinova.entity.School;
import com.hinova.entity.User;
import com.hinova.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private RoleDao  roleDao;
	@Resource
	private UserDao  userDao;
	@Resource
	private SchoolDao  schoolDao;

	public Integer add_Role(Role role) {
		// TODO Auto-generated method stub
		return roleDao.add(role);
	}

	public Integer update_Role(Role role) {
		// TODO Auto-generated method stub
		return roleDao.update(role);
	}

	public List<Role> list_Role(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleDao.list(map);
	}

	public Integer getTotal_Role(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleDao.getTotal(map);
	}

	public Role findByName_Role(String name) {
		// TODO Auto-generated method stub
		try {
			return roleDao.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Role findById_Role(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.findById(id);
	}

	public Integer delete_Role(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.delete(id);
	}
	//角色
	
	public Integer add_User(User user) {
		// TODO Auto-generated method stub
		try {
			return userDao.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Integer update_User(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	public List<User> list_User(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.list(map);
	}

	public Integer getUserTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.getTotal(map);
	}

	public User findByUserName(String name) {
		// TODO Auto-generated method stub
		try {
			return userDao.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User findByUserId(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	public Integer delete_User(Integer id) {
		// TODO Auto-generated method stub
		return userDao.delete(id);
	}

	@Override
	public Integer add_School(School school) {
		// TODO Auto-generated method stub
		return schoolDao.add(school);
	}

	@Override
	public Integer update_School(School school) {
		// TODO Auto-generated method stub
		return schoolDao.update(school);
	}

	@Override
	public List<School> list_School(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return schoolDao.list(map);
	}

	@Override
	public Integer getSchoolTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return schoolDao.getTotal(map);
	}

	@Override
	public School findBySchoolName(String name) {
		// TODO Auto-generated method stub
		return schoolDao.findByName(name);
	}

	@Override
	public School findBySchoolId(Integer id) {
		// TODO Auto-generated method stub
		return schoolDao.findById(id);
	}

	@Override
	public Integer delete_School(Integer id) {
		// TODO Auto-generated method stub
		return schoolDao.delete(id);
	}

	
	

	
	
	
}
