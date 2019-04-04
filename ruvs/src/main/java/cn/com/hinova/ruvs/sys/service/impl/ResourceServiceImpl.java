package cn.com.hinova.ruvs.sys.service.impl;


import java.util.ArrayList;
import java.util.List;

import cn.com.hinova.ruvs.common.base.SuperService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import cn.com.hinova.ruvs.sys.service.IResourceService;

@Service("resourceService")
public class ResourceServiceImpl  extends SuperService implements IResourceService {



	@Override
	public List<cn.com.hinova.ruvs.sys.bean.Resource> getResourcesByUser(String userId, String parentId, int type){

		List<Object> param=new ArrayList<Object>();
		StringBuilder sql=new StringBuilder("select resource from User user ");
		sql.append("left join user.role role right join  role.resources resource ");
		sql.append("where user.isDelete=0 ");

		if(userId!=null){
			sql.append(" and ").append("user.id=? ");
			param.add(userId);

		}

		if(parentId!=null){
			sql.append(" and resource.parent.id =? ");
			param.add(parentId);
		}
		if(type>0){
			sql.append(" and resource.type =? ");
			param.add(type);
		}
		sql.append(" group by resource.id order by resource.parent.id asc , resource.sort asc ");
		Query query=this.getSession().createQuery(sql.toString());
		for (int i = 0; i < param.size(); i++) {

			query.setParameter(i, param.get(i));
		}

		return query.list();
	}
}
