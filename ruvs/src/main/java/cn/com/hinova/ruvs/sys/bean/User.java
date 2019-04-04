package cn.com.hinova.ruvs.sys.bean;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.com.hinova.ruvs.common.annotation.CFUniqueAnnotation;
import cn.com.hinova.ruvs.common.base.SuperModel;

public class User extends SuperModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@CFUniqueAnnotation
	private String loginName;
	private String password;
	private String showName;
	@CFUniqueAnnotation
	private String phone;
	@CFUniqueAnnotation
	private String email;
	private String address;
	private int type;// 0普通用户，1管理员，100,超级管理员

	@JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {
	}
	
	public String getName(){
		return showName;
	}

	public User(String id) {
		this.setId(id);
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
