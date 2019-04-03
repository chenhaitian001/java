package com.hinova.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hinova.dao.TreeDao;
import com.hinova.entity.Tree;
import com.hinova.service.TreeService;



@Service("treeService")
public class TreeServiceImpl implements TreeService {
	
	@Resource
	private TreeDao  treeDao;
	
	public Tree findById(Integer id) {
		return treeDao.findById(id);
	}
	
	public List<Tree> getTreesByFatherOrIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return treeDao.getTreesByFatherOrIds(map);
	}
 
	


	 
}
