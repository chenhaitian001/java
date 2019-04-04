package cn.com.hinova.ruvs.sys.service.impl;

import java.util.List;

import cn.com.hinova.ruvs.common.base.SuperService;
import cn.com.hinova.ruvs.common.base.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hinova.ruvs.sys.service.IResourceService;
import cn.com.hinova.ruvs.sys.service.IRoleService;
import cn.com.hinova.ruvs.sys.bean.Resource;
import cn.com.hinova.ruvs.sys.bean.Role;

@Service("roleService")
public class RoleServiceImpl extends SuperService implements IRoleService {


	@javax.annotation.Resource
	private IResourceService resourceService;

	@Autowired
	private ICommonService baseService;


	public void saveResources(String roleId, String[] resourceIds) throws Exception {

		Role role = (Role) baseService.findById(new Role(roleId));
		if(role!=null){
			role.getResources().clear();
			if (role != null&&resourceIds!=null&&resourceIds.length>0) {
				List<Resource> resourceList = (List<Resource>) baseService.findByIds(Resource.class,resourceIds);
				for (Resource resource : resourceList) {
					role.getResources().add(resource);
				}
			}
			baseService.add(role);
		}
	}


	

}
