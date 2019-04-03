package com.hinova.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.hinova.dao.ChildrenDao;
import com.hinova.dao.ClassDao;
import com.hinova.dao.GradeDao;
import com.hinova.dao.RoleDao;
import com.hinova.dao.SchoolDao;
import com.hinova.entity.Clazz;
import com.hinova.entity.Grade;
import com.hinova.entity.Role;
import com.hinova.entity.School;
import com.hinova.entity.Student;
import com.hinova.entity.Tree;
import com.hinova.service.PublicService;
import com.hinova.service.TreeService;
import com.hinova.service.UserService;
import com.hinova.util.MyUtil;





@Service("publicService")
public class PublicServiceImpl implements PublicService {
	
	@Resource
	private TreeService treeService;
	@Resource
	private UserService userService;
	@Resource
	private GradeDao gradeDao;
	@Resource
	private ClassDao clazzDao;
	@Resource
	private ChildrenDao childrenDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private SchoolDao schoolDao;
	
	public void addLeftMenu(ModelAndView mav) {
		
		mav.addObject("leftPage", "/admin/common/left_menu.jsp");
		
		Role currentUser = (Role) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		currentUser = userService.findById_Role(currentUser.getId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		String menuIds = currentUser.getMenuIds();
		if(menuIds==null){
			menuIds = "";
		}
		List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(menuIds);  
		map.put("father", -1);
		map.put("ids", ids);
		
		if(ids.size()>0){
		}else{
			mav.addObject("treeList", null);
		}
		
		try {
			List<Tree> treeList = getTreesByParentId(map);
			mav.addObject("treeList", treeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 锟矫菜碉拷
	 */
	public List<Tree> getTreesByParentId(Map<String,Object> map) throws Exception {
		//String parentId,String ids  = map
		List<Tree> list = treeService.getTreesByFatherOrIds(map);
		for(Tree tree : list){
			//锟斤拷锟�锟角革拷选锟斤拷  锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟叫讹拷   
			//tree.setChecked(true);
			if("open".equals(tree.getState())){
				continue;
			}else{
				map.put("father", tree.getId()+"");//锟斤拷father  锟斤拷锟斤拷ids锟斤拷锟斤拷锟�
				tree.setChildren(getTreesByParentId(map));
			}
		}
		return list;
	}


	@Override
	public List<Grade> listGrade() {
		
		return gradeDao.listgrade();
	}


	@Override
	public List<Clazz> listClazz(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return clazzDao.listClazz(map);
	}


	@Override
	public List<Student> listStudent() {
		// TODO Auto-generated method stub
		return childrenDao.listStudent();
	}
	@Override
	public List<Role> listRole() {
		// TODO Auto-generated method stub
		return roleDao.listRole();
	}
	@Override
	public List<School> listSchool() {
		// TODO Auto-generated method stub
		return schoolDao.listSchool();
	}
	
}
