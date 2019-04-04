package cn.com.hinova.ruvs.henuo.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.common.base.SuperModel;
import cn.com.hinova.ruvs.common.bean.Paging;
import cn.com.hinova.ruvs.config.bean.Student;
import cn.com.hinova.ruvs.henuo.dao.EntityDaoHibernate;
import cn.com.hinova.ruvs.henuo.entity.OutPutStudent;
import cn.com.hinova.ruvs.henuo.service.StudentService;
import cn.com.hinova.ruvs.utils.FileUtil;
import cn.com.hinova.ruvs.utils.UserUtils;
import cn.com.hinova.ruvs.utils.WhereUtils;
import cn.com.hinova.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class StudendServiceImpl extends EntityDaoHibernate implements StudentService {

	
	/**
	 * 学生信息查询 过滤掉毕业生
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Paging queryStudent(String a, String b,String c,Paging paging){
		String bt = b.trim();
		String idstu = "";
		String hql = "";
		if(this.isEmpty(b)){
				 StringBuffer sql=new StringBuffer(" from Student where 1=1 and is_delete = '0'");
				 if(!this.isEmpty(a)){
					 sql.append(" and name like '"+a+"%' ");
				 }
				 if(!this.isEmpty(c)){
					 sql.append(" and clazz_id = '"+c+"' ");
				 }
				 sql.append(" order by edit_time desc ");
				 hql  = sql.toString();
		}else{
			//获取学生id
			StringBuffer sql=new StringBuffer("SELECT n.id,n.name,n.sex,n.clazz_id,n.create_user_id,n.edit_user_id,n.create_time,n.edit_time,n.description,n.is_delete,b.name from td_config_student n left JOIN td_config_patriarch b on n.id = b.student_id where 1=1 and n.is_delete = '0'");
			if(!this.isEmpty(a)){
				sql.append(" and n.name like '%"+a+"%' ");
			}
			if(!this.isEmpty(c)){
				 sql.append(" and n.clazz_id = '"+c+"' ");
			 }
			sql.append(" and b.name = '"+bt+"' ");
			List<String> paraList = new ArrayList<String>();
			List ls= this.getQueryHashData(sql.toString(), paraList);
			for(int i=0;i<ls.size();i++){
				Map paperMap = (Map) ls.get(i);
				idstu = paperMap.get("id").toString();
			}
			hql= "from Student where id = '"+idstu+"' and is_delete = '0' order by edit_time desc";
		}
		Query query=this.getSession().createQuery(hql.toString());
		try {
		List<SuperModel> list= query.setFirstResult(paging.getFirstIndex())//
				.setMaxResults(paging.getMaxIndex()).list();
		paging.setList(list);
			paging.setCount(findCount("td_config_student"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paging;
	}
	
	public long findCount(String  name) throws Exception {
		String hql = "from Student";
		Query query=this.getSession().createQuery(hql.toString());
		return query.list().size();
	}
	
	// 判断是否为空。
	public static boolean isEmpty(String Value) {
			return (Value == null || Value.trim().equals("") || Value.trim().equals("null"));
	}
	/**
	 * 导出学生信息，过滤掉毕业生
	 */
	@Override
	public List query(String a,String b,String c) {
		List ruleList = new ArrayList();
		List returnList = new ArrayList();
		StringBuffer sqlrule = new StringBuffer("SELECT a.name as name,c.name class FROM td_config_student a left JOIN td_config_clazz c on a.clazz_id = c.id left JOIN (SELECT DISTINCT(student_id) FROM td_config_patriarch) b on a.id = b.student_id where 1=1 and a.is_delete = '0' ");
		//学生
		if(!this.isEmpty(a)){
			sqlrule.append(" and a.name like '%"+a+"%' ");
		}
		//家长
		if(!this.isEmpty(b)){
			String bt = b.trim();
			sqlrule.append(" and b.name = '"+bt+"'  ");
		}
		//班级
		if(!this.isEmpty(c)){
			sqlrule.append(" and a.clazz_id = '"+c+"' ");
		}
		
		
		List<String> paraList = new ArrayList<String>();
		ruleList = this.getQueryHashData(sqlrule.toString(), paraList);
		for (int i = 0; i < ruleList.size(); i++) {
			HashMap ruleMap = (HashMap)ruleList.get(i);
			String name =  ruleMap.get("name").toString();
			String classs = ruleMap.get("class").toString();
			OutPutStudent person = new OutPutStudent(name,classs);
			returnList.add(person);
			
		}
		return returnList;
	}
	
	@Override
	public List fileList(List veList) {
		for(int i=0;i<veList.size();i++){
			List<Map<String, String>> veeList = (List<Map<String, String>>) veList.get(i);
			String class_name = null;
			String name = null;
			for (Map<String,String> map:veeList) {
	                for (String s:map.keySet()) 
	                {
	                	if(s.equals("name")){
	                		name =map.get(s);
	                	}
	                	if(s.equals("class_id")){
	                		
	                	}
	                	class_name =map.get(s);
	                }
	                String ifclass = "SELECT id from td_config_clazz where name ='"+class_name+"'";
	    			List list =this.findBySql(ifclass, null);
	    			if(list.size()>0){
	    				if(!FileUtil.isEmpty(class_name)){
	    					try {
	    					String class_id = list.get(0).toString();
	    					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    					String nowDate = df.format(new Date());
	    					String id = DateUtil.getYMDHMSS();
	    					String insertsql = "insert into td_config_student (id,name,clazz_id,create_time,edit_time,is_delete) VALUES ('"+id+"','"+name+"','"+class_id+"','"+nowDate+"','"+nowDate+"','0');";
	    					this.execUpdateThrowE(insertsql);
	    					} catch (Exception e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    				}
	    			}
	         }
		}
		return null;
	}

	@Override
	public void upClass(String[] id) {
		
		List list = new ArrayList();
		for(String a :id){
			list.add(a);
		}
		for(int i=0;i<list.size();i++){
			String student_id = list.get(i).toString();
			
			String clazzSql= "SELECT clazz_id,grade_id from td_config_student t LEFT JOIN td_config_clazz z on t.clazz_id = z.id where t.id = '"+student_id+"'";
			List<String> paraList = new ArrayList<String>();
			List mapList =this.getQueryHashData(clazzSql, paraList);
			if(!FileUtil.isEmpty(mapList)){
				HashMap ruleMap = (HashMap)mapList.get(0);
				int clazz_id = Integer.parseInt(ruleMap.get("clazz_id").toString());
				String grade_id =ruleMap.get("grade_id").toString();
				//查出所有班级
				String allClass = "SELECT id from td_config_clazz where grade_id = '"+grade_id+"'";
				List<String> para = new ArrayList<String>();
				List classList =this.findBySql(allClass, null);
				Collections.sort(classList);
				int ses = 0;
				int outclass = 00;
				int maxclass = Integer.parseInt(classList.get(classList.size()-1).toString());
				if(clazz_id==maxclass){
					//升级到毕业班级
					String graduateSql = "update  td_config_student set is_delete = '1' where id = '"+student_id+"'";
					try {
						this.execUpdateThrowE(graduateSql);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//升级到毕业班级
					return;
				}
				for(int j=0;j<=classList.size();j++){
					ses = Integer.parseInt(classList.get(j).toString());
					if(clazz_id<ses){
						break;
					}
				}
				//普通升班
				String updateClass = "update  td_config_student set clazz_id = '"+ses+"' where id = '"+student_id+"'";
				try {
					this.execUpdateThrowE(updateClass);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public Paging queryLeavest(String a, Paging paging) {

		String hql = "";
				 StringBuffer sql=new StringBuffer(" from Student where 1=1 and is_delete = '1'");
				 if(!this.isEmpty(a)){
					 sql.append(" and name like '"+a+"%' ");
				 }
				 sql.append(" order by edit_time desc ");
				 hql  = sql.toString();
		Query query=this.getSession().createQuery(hql.toString());
		try {
		List<SuperModel> list= query.setFirstResult(paging.getFirstIndex())//
				.setMaxResults(paging.getMaxIndex()).list();
		paging.setList(list);
			paging.setCount(findCount("td_config_student"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paging;
	
	}

	@Override
	public List findifrelation(String student_id,String relation) {
		String sql = "SELECT student_id from td_config_patriarch where student_id = '"+student_id+"' and relation = '"+relation+"'";
		List list = this.findBySql(sql, null);
		return list;
	}

	@Override
	public void saveRelation(String student_id,String relation) {
		String namesql = "select name from td_config_student where id='"+student_id+"'";
		List nameList = this.findBySql(namesql, null);
		String name =  nameList.get(0).toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String nowDate = df.format(new Date());
		String id = DateUtil.getYMDHMSS();
		String savasql = "INSERT into  td_config_patriarch (id,name,relation,student_id,is_delete,face_code,create_time,edit_time)"
				+ " VALUES ('"+id+"','"+name+relation+"','"+relation+"','"+student_id+"','"+0+"','"+id+"','"+nowDate+"','"+nowDate+"');";
		try {
			this.execUpdateThrowE(savasql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
