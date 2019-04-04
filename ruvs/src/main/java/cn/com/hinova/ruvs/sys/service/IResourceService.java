package cn.com.hinova.ruvs.sys.service;

import java.util.List;

import cn.com.hinova.ruvs.sys.bean.Resource;


public interface   IResourceService   {

	public  List<Resource> getResourcesByUser(String userId,String parentId,int type);
	
}
