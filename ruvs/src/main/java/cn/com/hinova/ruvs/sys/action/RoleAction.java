package cn.com.hinova.ruvs.sys.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import cn.com.hinova.ruvs.common.base.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.common.bean.Result;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.common.easyui.Tree;
import cn.com.hinova.ruvs.sys.service.IResourceService;
import cn.com.hinova.ruvs.sys.service.IRoleService;
import cn.com.hinova.ruvs.sys.bean.Resource;
import cn.com.hinova.ruvs.sys.bean.Role;

@Controller
@RequestMapping("/sys/role")
public class RoleAction extends SuperAction<Role> {


	@javax.annotation.Resource
	private IRoleService roleService;

	@Autowired
	private ICommonService baseService;
	
	@javax.annotation.Resource
	private IResourceService resourceService;
	
	@RequestMapping("saveResources.do")
	@ResponseBody
	public Result saveResources(HttpServletRequest req) throws Exception{
		
		String roleId=req.getParameter("roleId");
		String[] resourceIds=req.getParameterValues("resourceIds");
		roleService.saveResources(roleId, resourceIds);
		return new Result(200, "操作成功", "",null);
	}
	

	@RequestMapping("resource_tree.do")
	@ResponseBody
	public List<Tree> findResources(HttpServletRequest req) throws Exception{
		
		Role role= (Role) baseService.findById(new Role(req.getParameter("roleId")));
		
		Set<String> selectedResource=new HashSet<String>();
		for (Resource resource : role.getResources()) {
			selectedResource.add(resource.getId());
		}
		
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("_:String:parent.id", new String[]{""});
		Cache.paramMapLocal.set(map);
		
		List<Tree> treeList=new ArrayList<Tree>();
		List<Resource> resourceList= (List<Resource>) baseService.find(Resource.class,"sort", "desc");
		recursion(treeList,resourceList,selectedResource,5);
		
		return treeList;
	}
	
	
	private void  recursion(Collection<Tree> trees,Collection<Resource> resources,Collection<String> selectedIds,int ttl){
		if(ttl--==0){
			return ;
		}
		for (Resource resource : resources) {
			Tree tree=new Tree();
			tree.setId((String)resource.getId());
			tree.setText(resource.getName());
			if(selectedIds.contains(resource.getId())&&resource.getChilds().size()==0){
				tree.setChecked("true");
			}
			trees.add(tree);
			if(resource.getChilds().size()>0){
				tree.setChildren(new ArrayList<Tree>());
				recursion(tree.getChildren(), resource.getChilds(),selectedIds,ttl);
			}
		}
	}
	
	
}
