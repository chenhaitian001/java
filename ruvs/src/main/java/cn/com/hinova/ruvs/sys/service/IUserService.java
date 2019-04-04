package cn.com.hinova.ruvs.sys.service;

import cn.com.hinova.ruvs.sys.bean.User;



public interface   IUserService  {

	public abstract void updatePassword(User user, String newPassword)  throws Exception;
	

}
