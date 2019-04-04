package cn.com.hinova.ruvs.utils;

import cn.com.hinova.ruvs.sys.bean.User;

public class UserUtils {


	public static ThreadLocal<User> userLocal=new ThreadLocal<User>();

	public static User getUser(){
		return userLocal.get();
	}
}
