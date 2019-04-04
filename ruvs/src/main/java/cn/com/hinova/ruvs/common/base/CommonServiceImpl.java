package cn.com.hinova.ruvs.common.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.hinova.ruvs.common.bean.Paging;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.utils.StringUtils;
import cn.com.hinova.ruvs.utils.SuperAdminPasswordUtils;
import cn.com.hinova.ruvs.utils.WhereUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@SuppressWarnings("JpaQlInspection")
@Service("baseService")
public class CommonServiceImpl extends HibernateDaoSupport implements ICommonService {



	@Resource(name = "sessionFactory")
	public void setDatasource(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}


	public void add(SuperModel superModel) throws Exception {

		this.getHibernateTemplate().save(superModel);
		this.getHibernateTemplate().flush();
	}

	public void update(SuperModel superModel) throws Exception {

		SuperModel tmpt=findById(superModel);

	    List<String> fieldNames=new ArrayList<String>();

        for (Field field: superModel.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object v=field.get(superModel);
            if (v==null ||(v instanceof Number &&v.equals(0))){
                fieldNames.add(field.getName());
            }
            if(v instanceof SuperModel){
            	if(((SuperModel) v).getId()!=null){
            		v=this.findById((SuperModel)v);
            		field.set(tmpt,v);
				}
			}

        }


        BeanUtils.copyProperties(superModel,tmpt,fieldNames.toArray(new String[]{}));


		this.getHibernateTemplate().update(tmpt);
		this.getHibernateTemplate().flush();
	}

	@Override
	public void delete(SuperModel superModel) throws Exception {

		this.getHibernateTemplate().delete(this.findById(superModel));
		this.getHibernateTemplate().flush();
	}

	@Override
	public void deletes(Class clazz,Serializable[] ids) throws Exception {

		this.getSession().createQuery(StringUtils.strs("delete ",clazz.getName()," where id in (:ids) "))//
				.setParameterList("ids", ids).executeUpdate();
		this.getHibernateTemplate().flush();
	}


	@SuppressWarnings({ "unchecked" })
	public Paging findPaging(Class clazz,Paging paging) throws Exception {

		StringBuilder sql=new StringBuilder("from ").append(clazz.getName()).append(" where isDelete=0 ");

		List<Object> params=new ArrayList<Object>();
		WhereUtils.where(sql, params);
		if(paging.getSort()!=null&&paging.getOrder()!=null){
			sql.append(" order by ").append(paging.getSort()).append(" ").append(paging.getOrder());
		}
		Query query=this.getSession().createQuery(sql.toString());
		WhereUtils.param(query, params);

		List<SuperModel> list= query.setFirstResult(paging.getFirstIndex())//
				.setMaxResults(paging.getMaxIndex()).list();
		paging.setList(list);
		paging.setCount(findCount(clazz));
		return paging;
	}



	public SuperModel findById(SuperModel superModel)  throws Exception {
		try{
			return (SuperModel) this.getHibernateTemplate().get(superModel.getClass(), superModel.getId());
		}catch(Exception ex){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<cn.com.hinova.ruvs.sys.bean.Resource> findByIds(Class clazz, Serializable[] ids)  throws Exception {
		return this.getSession().createQuery("from " + clazz.getName() + " where id in (:ids)")//
				.setParameterList("ids", ids).list();
	}

	public long findCount(Class clazz) throws Exception {

		StringBuilder sql=new StringBuilder("select count(*) from ").append(clazz.getName()).append(" where isDelete=0 ");

		List<Object> params=new ArrayList<Object>();
		WhereUtils.where(sql, params);
		Query query=this.getSession().createQuery(sql.toString());
		WhereUtils.param(query, params);
		return (Long) query.list().get(0);
	}

	@SuppressWarnings("unchecked")
	public List<SuperModel> find(Class clazz, String sort, String order){

		StringBuilder sql=new StringBuilder("from ").append(clazz.getName()).append(" where isDelete=0 ");

		List<Object> params=new ArrayList<Object>();
		WhereUtils.where(sql, params);

		if(sort==null||order==null){
			sort="id";
			order="desc";
		}

		if(sort!=null&&order!=null){
			sql.append(" order by ").append(sort).append(" ").append(order);
		}
		Query query=this.getSession().createQuery(sql.toString());
		WhereUtils.param(query, params);

		return query.list();
	}


	public List<Map<String,Object>> getMapList(String sql,List<Object> params){

		Query query = this.getSession().createQuery(sql.toString());
		WhereUtils.param(query, params);

		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

	}


}
