package cn.com.hinova.ruvs.henuo.dao;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;



import org.springframework.util.Assert;
@SuppressWarnings("unchecked")
public class EntityDaoHibernate extends HibernateDaoSupport implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
	}
	@Resource
	   public void setSessionFactory0(SessionFactory sessionFactory){  
	      super.setSessionFactory(sessionFactory);  
	   }
	public List findBySql(final String sql, final Object... values) {
		Assert.hasText(sql);
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
				//		if(!PubMethod.isEmpty(values[i])){
							query.setParameter(i, values[i]);
				//		}
					}
				}
				return query.list();
			}
		});
		return list;
	}
	public List getSqlList(String queryName, List paraList, Class c1) {

		PreparedStatement stm = null;
		ResultSet rs = null;
		List list = new LinkedList();
		Connection conn = null;
		try {
			conn = this.getSession().connection();
			stm = conn.prepareStatement(queryName);
			if (paraList != null) {
				for (int i = 0; i < paraList.size(); i++) {
					Object obj = paraList.get(i);
					stm.setObject(i + 1, obj);

				}
			}

			rs = stm.executeQuery();

			ResultSetMetaData metadata = rs.getMetaData();
			
			while (rs.next()) {
				
				if( isBaseClass(c1))
				{
					
					String data = rs.getString(1);
					if(rs.getObject(1)!=null&&
							rs.getObject(1).getClass().getSimpleName().equalsIgnoreCase("timestamp"))
						{
							Timestamp timeStamp=rs.getTimestamp(1);
							if(timeStamp!=null){
							
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//定义格式，不显
								data=df.format(timeStamp);
							}
							
						}
						if(rs.getObject(1)!=null&&
								rs.getObject(1).getClass().getSimpleName().equalsIgnoreCase("date"))
						{
								java.sql.Date timeStamp=rs.getDate(1);
								if(timeStamp!=null){
									
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显
									data=df.format(timeStamp);
								}
								
						}
						
					Object re=createPrimitiveObj(data,c1);
					list.add(re);
				}
				else
				{
					Object obj = c1.newInstance();
					for (int i = 1; i <= metadata.getColumnCount(); i++) {
						String columnName = metadata.getColumnName(i);
	
						int type = metadata.getColumnType(i);
						
						String value = rs.getString(i);
						if(rs.getObject(i)!=null&&
							rs.getObject(i).getClass().getSimpleName().equalsIgnoreCase("timestamp"))
						{
							
							Timestamp timeStamp=rs.getTimestamp(i);
							if(timeStamp!=null){
								
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//定义格式，不显
								value=df.format(timeStamp);
							}
							
						}
						if(rs.getObject(i)!=null&&
								rs.getObject(i).getClass().getSimpleName().equalsIgnoreCase("date"))
						{
								java.sql.Date timeStamp=rs.getDate(i);
								if(timeStamp!=null){
									
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显
									value=df.format(timeStamp);
								}
							
						}
						
						
						setObjectColumn(obj, columnName, value, type);
	
					}
					list.add(obj);
				}
				
			
			}
			return list;
		} catch (Exception e) {

			e.printStackTrace();
			

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if(stm!=null)
				{
					stm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		return list;

	}
	public List getQueryHashData(String sql, List paraList) {
		// Connection conn = this.getSession().connection();

		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		List list = new LinkedList();
		try {

			//conn = this.getSession().connection();
			conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			stm = conn.prepareStatement(sql);
			if (paraList != null) {
				for (int i = 0; i < paraList.size(); i++) {
					Object obj = paraList.get(i);
					if(obj instanceof Date){
						stm.setTimestamp(i+1,new Timestamp(((Date)obj).getTime()) ); 
					}else{
						stm.setObject(i + 1, obj);
					}
				}
			}

			rs = stm.executeQuery();

			ResultSetMetaData rd = rs.getMetaData();
			while (rs.next() == true) {
				//Vector v = new Vector();
				Map<String,String> returnMap = new HashMap<String,String>();
				for (int j = 1; j <= rd.getColumnCount(); j++) {
					String data = rs.getString(j);
					returnMap.put(rd.getColumnName(j).toLowerCase(), data);
				}
				list.add(returnMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if(stm!=null)
				{
					stm.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
				
			}

		}
		return list;
	}
	private boolean isBaseClass(Class c)
	{
		if(c.getName().equals("java.math.BigDecimal"))
		{
			return true;
		}
		if(c.getName().equals("java.lang.Integer"))
		{
			return true;
		}
		if(c.getName().equals("java.util.Date"))
		{
			return true;
		}
		if(c.getName().equals("java.sql.Timestamp"))
		{
			return true;
		}
		if(c.getName().equals("int"))
		{
			return true;
		}
		if(c.getName().equals("java.lang.String"))
		{
			return true;
		}
		if(c.getName().equals("double"))
		{
			return true;
		}
		if(c.getName().equals("java.lang.Double"))
		{
			return true;
		}
		if(c.getName().equals("long"))
		{
			return true;
		}
		if(c.getName().equals("java.lang.Long"))
		{
			return true;
		}
		return false;
	}
	private Object createPrimitiveObj(String data,Class c) throws ParseException{
	
		if(c.getName().equals("java.lang.Integer"))
		{
			return java.lang.Integer.valueOf(data).intValue();
		}
		if(c.getName().equals("java.lang.Date"))
		{
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss.SSS");
			if (data.length() == 19) {
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			Date date = format.parse(data);
			return date;
		}
		if(c.getName().equals("java.lang.Timestamp"))
		{
			SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");
			if (data.length() == 19) {
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			java.sql.Timestamp time = new Timestamp(format.parse(
					data).getTime());
			return time;
		}
		if(c.getName().equals("java.lang.int"))
		{
			return java.lang.Integer.valueOf(data).intValue();
		}
		if(c.getName().equals("java.lang.String"))
		{
			return data;
		}
		if(c.getName().equals("java.lang.double"))
		{
			return  java.lang.Double.valueOf(data).doubleValue();
		}
		if(c.getName().equals("java.lang.Double"))
		{
			 return java.lang.Double.valueOf(data);
		}
		if(c.getName().equals("java.lang.long"))
		{
			return java.lang.Integer.valueOf(data).longValue();
		}
		if(c.getName().equals("java.lang.Long"))
		{
			return Long.parseLong(data);
		}
		return null;
	}
	private void setObjectColumn(Object obj, String columnName, String data, int type) throws ParseException, IllegalArgumentException,
	IllegalAccessException, InvocationTargetException {
	if (data == null) {
		return;
	}
	Method[] methods = obj.getClass().getMethods();
	for (int i = 0; i < methods.length; i++) {
	
		if (methods[i].getName().compareToIgnoreCase("set" + columnName) == 0) {
	
			Method method = methods[i];
			Class[] cs = method.getParameterTypes();
			if (cs.length == 1) {
	
				if (cs[0].getName().equals("java.lang.String")) {
	
					Object[] paras = new Object[1];
					paras[0] = data;
					method.invoke(obj, paras);
					return;
	
				}
				if (cs[0].getName().equals("java.lang.Object")) {
	
					Object[] paras = new Object[1];
					paras[0] = data;
					method.invoke(obj, paras);
					return;
	
				}
				if (cs[0].getName().equals("int")) {
					Object[] paras = new Object[1];
	
					paras[0] = java.lang.Integer.valueOf(data).intValue();
					method.invoke(obj, paras);
					return;
	
				}
				if (cs[0].getName().equals("java.lang.Integer")) {
					Object[] paras = new Object[1];
	
					paras[0] = java.lang.Integer.valueOf(data).intValue();
					method.invoke(obj, paras);
					return;
	
				}
				if (cs[0].getName().equals("long")) {
					Object[] paras = new Object[1];
					paras[0] = java.lang.Integer.valueOf(data).longValue();
					method.invoke(obj, paras);
					return;
				}
				if (cs[0].getName().equals("java.lang.Long")) {
					Object[] paras = new Object[1];
					paras[0] = Long.parseLong(data);
					method.invoke(obj, paras);
					return;
				}
				if (cs[0].getName().equals("double")) {
					Object[] paras = new Object[1];
					paras[0] = java.lang.Double.valueOf(data).doubleValue();
					method.invoke(obj, paras);
					return;
				}
				if (cs[0].getName().equals("java.lang.Double")) {
					Object[] paras = new Object[1];
					paras[0] = java.lang.Double.valueOf(data).doubleValue();
					method.invoke(obj, paras);
					return;
				}
				if (cs[0].getName().equals("java.math.BigDecimal")) {
					Object[] paras = new Object[1];
					paras[0] = new java.math.BigDecimal(data);
					method.invoke(obj, paras);
					return;
				}
	
				if (cs[0].getName().equals("java.sql.Timestamp")) {
					Object[] paras = new Object[1];
					
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss.SSS");
					if (data.length() == 19) {
							format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					}
					format.setLenient(false);
					java.sql.Timestamp time = new Timestamp(format.parse(data).getTime());
				
					paras[0] = time;
					method.invoke(obj, paras);
					return;
				}
	
				if (cs[0].getName().equals("java.util.Date")) {
					if (data == null)
						return;
					Object[] paras = new Object[1];
					
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss.SSS");
					if (data.length() == 19) {
							format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					}
					Date date = format.parse(data);
					paras[0] = new Date(date.getTime());
					method.invoke(obj, paras);
					return;
				}
			}
	
		}
	}

	}
	
	public int execUpdateThrowE(String updateSql) throws Exception {
		logger.info("update===========>"+updateSql);
		int returnbool = -1;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			returnbool = stmt.executeUpdate(updateSql);
			conn.commit();
			stmt.close();
		} catch (Exception e) {
			
			logger.error("执行更新语句出现异常：" + e.getMessage() + ",sql=" + updateSql);
			returnbool = -1;
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException SqlE) {
				SqlE.printStackTrace();
			}
			throw new Exception(e.getMessage());
		} finally {
			try {
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
			conn = null;
		}

		return returnbool;
	}
}