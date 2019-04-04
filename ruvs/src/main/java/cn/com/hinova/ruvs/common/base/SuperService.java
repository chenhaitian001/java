package cn.com.hinova.ruvs.common.base;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;

@SuppressWarnings("JpaQlInspection")
public class SuperService extends HibernateDaoSupport  {



	@Resource(name = "sessionFactory")
	public void setDatasource(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}




}
