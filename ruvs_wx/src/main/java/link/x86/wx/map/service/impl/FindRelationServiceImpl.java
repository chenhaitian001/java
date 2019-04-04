package link.x86.wx.map.service.impl;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import link.x86.wx.map.dao.EntityDaoHibernate;
import link.x86.wx.map.entity.EmployeeCheckinData;
import link.x86.wx.map.service.FindRelationService;
import link.x86.wx.map.util.GetDateUtil;
@Service
public class FindRelationServiceImpl extends EntityDaoHibernate implements FindRelationService{
	@Override
	public List findNumber(String no) {
		String flindno= "SELECT loginid FROM t_resource WHERE openid ='"+no+"'";
		logger.debug("查询注册码："+flindno);
		List listno= this.findBySql(flindno, null);
		return listno;
	}

	@Override
	public void saveEmployeeCheckinData(EmployeeCheckinData data) {
		// TODO Auto-generated method stub
        this.save(data);
		
	}
	@Override
    public List<EmployeeCheckinData> findEmployeeCheckinDataList(String loginid, Integer year, Integer month) {
        // TODO Auto-generated method stub
		loginid = loginid.substring(0,loginid.length()-1);
     // TODO Auto-generated method stub
        StringBuffer hql = new StringBuffer(" select * from t_employee_checkin_data as dic ");
        hql.append(" where 1 = 1 ");
        if (year != null) {
            hql.append(" and dic.year like '" + year.toString() + "' ");
        }

        if (month != null) {
            hql.append(" and dic.month like '" + month.toString() + "' ");
        }
        if(!StringUtils.isEmpty(loginid)){
            hql.append(" and dic.loginid like '" + loginid + "%' ");
        }
        hql.append("  order by dic.year, dic.month,dic.checkinDate ");
        List<EmployeeCheckinData> list = this.getSqlList(hql.toString(), null,EmployeeCheckinData.class);
        if (null != list && list.size() > 0) {
            return list;
        }
        return Collections.emptyList();
	
	}
	
@Override
	public List findWork(String no) {
		no = this.findLogin(no);
		no = no.substring(0,no.length()-1);
		String sql = "SELECT max(da.checkinTime) as checktime,da.flag,res.loginid,res.openid,"
				+ "CASE res.relation "
			    +"WHEN '1' THEN '爸爸' "
			    +"WHEN '2' THEN '妈妈' "
			    +"WHEN '3' THEN '爷爷'"
			    +"WHEN '4' THEN '奶奶'"
			    +"WHEN '5' THEN '姥姥'"
			    +"WHEN '6' THEN '姥爷'"
			    +"ELSE '其他' END as relation,"
				+ "da.loginid from t_resource res LEFT JOIN t_employee_checkin_data da "
				+ "on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate() "
				+ "and da.loginid like '"+no+"%' GROUP BY da.flag" ;
		List<String> paraList = new ArrayList<String>();
	//	List<Map<String, String>> timeList = new ArrayList<Map<String, String>>();
		List ls= this.getQueryHashData(sql.toString(), paraList); 
		return ls;
	}
@Override
public List findWorkbyLogin(String no) {
	no = no.substring(0,no.length()-1);
	String sql = "SELECT max(da.checkinTime) as checktime,da.flag,res.loginid,res.openid,"
			+ "CASE res.relation "
		    +"WHEN '1' THEN '爸爸' "
		    +"WHEN '2' THEN '妈妈' "
		    +"WHEN '3' THEN '爷爷'"
		    +"WHEN '4' THEN '奶奶'"
		    +"WHEN '5' THEN '姥姥'"
		    +"WHEN '6' THEN '姥爷'"
		    +"ELSE '其他' END as relation,"
			+ "da.loginid from t_resource res LEFT JOIN t_employee_checkin_data da "
			+ "on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate() "
			+ "and da.loginid like '"+no+"%' GROUP BY da.flag" ;
	List<String> paraList = new ArrayList<String>();
//	List<Map<String, String>> timeList = new ArrayList<Map<String, String>>();
	List ls= this.getQueryHashData(sql.toString(), paraList); 
	return ls;
}
	//上午的接送关系
	public String amRelation(String no){
		no = this.findLogin(no);
		no = no.substring(0,no.length()-1);
		String sql = "SELECT 	CASE res.relation "
				+"	    WHEN '1' THEN '爸爸'    "
				+"	    WHEN '2' THEN '妈妈'    "
				+"	    WHEN '3' THEN '爷爷'    "
				+"	    WHEN '4' THEN '奶奶'    "
				+"	    WHEN '5' THEN '姥姥'    "
				+"	    WHEN '6' THEN '姥爷'    "
				+"	    ELSE '其他' END as relation from t_resource res LEFT JOIN t_employee_checkin_data da "
				+"		on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate() "
				+"		and da.loginid like '"+no+"%' and da.flag = '10' and da.checkinTime = (SELECT max(da.checkinTime)"
				+"		 from t_resource res LEFT JOIN t_employee_checkin_data da "
				+"		on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate()" 
				+"		and da.loginid like '"+no+"%' and da.flag = '10')";
		
		List list = this.findBySql(sql, null);
		if(GetDateUtil.ifEmpty(list)){
			return list.get(0).toString();
		}else{
			return "";
		}
		
	}
	//下午的接送关系
	public String pmRelation(String no){
		no = this.findLogin(no);
		no = no.substring(0,no.length()-1);
		List<String> paraList = new ArrayList<String>();
		String sql = "SELECT 	CASE res.relation "
		+"	    WHEN '1' THEN '爸爸'    "
		+"	    WHEN '2' THEN '妈妈'    "
		+"	    WHEN '3' THEN '爷爷'    "
		+"	    WHEN '4' THEN '奶奶'    "
		+"	    WHEN '5' THEN '姥姥'    "
		+"	    WHEN '6' THEN '姥爷'    "
		+"	    ELSE '其他' END as relation from t_resource res LEFT JOIN t_employee_checkin_data da "
		+"		on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate() "
		+"		and da.loginid like '"+no+"%' and da.flag = '20' and da.checkinTime = (SELECT max(da.checkinTime)"
		+"		 from t_resource res LEFT JOIN t_employee_checkin_data da "
		+"		on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate()" 
		+"		and da.loginid like '"+no+"%' and da.flag = '20')";
		
		List list = this.findBySql(sql, null);
		if(GetDateUtil.ifEmpty(list)){
			return list.get(0).toString();
		}else{
			return "";
		}
		
	}
	//上午的接送关系
		public String amRelationbyLogin(String no){
			no = no.substring(0,no.length()-1);
			String sql = "SELECT 	CASE res.relation "
					+"	    WHEN '1' THEN '爸爸'    "
					+"	    WHEN '2' THEN '妈妈'    "
					+"	    WHEN '3' THEN '爷爷'    "
					+"	    WHEN '4' THEN '奶奶'    "
					+"	    WHEN '5' THEN '姥姥'    "
					+"	    WHEN '6' THEN '姥爷'    "
					+"	    ELSE '其他' END as relation from t_resource res LEFT JOIN t_employee_checkin_data da "
					+"		on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate() "
					+"		and da.loginid like '"+no+"%' and da.flag = '10' and da.checkinTime = (SELECT max(da.checkinTime)"
					+"		 from t_resource res LEFT JOIN t_employee_checkin_data da "
					+"		on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate()" 
					+"		and da.loginid like '"+no+"%' and da.flag = '10')";
			
			List list = this.findBySql(sql, null);
			if(GetDateUtil.ifEmpty(list)){
				return list.get(0).toString();
			}else{
				return "";
			}
			
		}
		//下午的接送关系
		public String pmRelationbyLogin(String no){
			no = no.substring(0,no.length()-1);
			List<String> paraList = new ArrayList<String>();
			String sql = "SELECT 	CASE res.relation "
			+"	    WHEN '1' THEN '爸爸'    "
			+"	    WHEN '2' THEN '妈妈'    "
			+"	    WHEN '3' THEN '爷爷'    "
			+"	    WHEN '4' THEN '奶奶'    "
			+"	    WHEN '5' THEN '姥姥'    "
			+"	    WHEN '6' THEN '姥爷'    "
			+"	    ELSE '其他' END as relation from t_resource res LEFT JOIN t_employee_checkin_data da "
			+"		on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate() "
			+"		and da.loginid like '"+no+"%' and da.flag = '20' and da.checkinTime = (SELECT max(da.checkinTime)"
			+"		 from t_resource res LEFT JOIN t_employee_checkin_data da "
			+"		on res.loginid = da.loginid WHERE date(da.checkinDate) = curdate()" 
			+"		and da.loginid like '"+no+"%' and da.flag = '20')";
			
			List list = this.findBySql(sql, null);
			if(GetDateUtil.ifEmpty(list)){
				return list.get(0).toString();
			}else{
				return "";
			}
			
		}
	@Override
	public String findLogin(String openid) {
		String sql = "select loginid from t_resource where openid = '"+openid+"'";
		logger.debug("查询注册码sql"+sql);
		return this.findBySql(sql, null).get(0).toString();
	}
	@Override
	public String findrelation(String openid) {
		String sql = "select  CASE relation "
			    +"WHEN '1' THEN '爸爸' "
			    +"WHEN '2' THEN '妈妈' "
			    +"WHEN '3' THEN '爷爷'"
			    +"WHEN '4' THEN '奶奶'"
			    +"WHEN '5' THEN '姥姥'"
			    +"WHEN '6' THEN '姥爷'"
			    +"ELSE '其他' END as relation "
				+ "from t_resource where openid = '"+openid+"'";
		return this.findBySql(sql, null).get(0).toString();
	}

	@Override
	public Boolean findAuthor(String openid) {
		String sql = "SELECT phone FROM td_config_teacher where phone = (SELECT phone FROM lf_weixin_phone WHERE openid = '"+openid+"')";
		logger.debug(sql);
		List author = this.findBySql(sql, null);
		if(GetDateUtil.ifEmpty(author)){
			return true;
		}
		   return false;
	}

	@Override
	public int findCountWorks(String loginid, Integer year, Integer month) {
		    loginid = loginid.substring(0,loginid.length()-1);
	     // TODO Auto-generated method stub
	        StringBuffer hql = new StringBuffer(" SELECT COUNT(*) from ");
	        hql.append(" (select * from t_employee_checkin_data where 1=1");
	        if (year != null) {
	            hql.append(" and year like '" + year.toString() + "' ");
	        }else{
	        	hql.append(" and year like '" + GetDateUtil.year() + "' ");
	        }

	        if (month != null) {
	            hql.append(" and month like '" + month.toString() + "' ");
	        }else{
	        	hql.append(" and month like '" + GetDateUtil.month() + "' ");
	        }
	        if(!StringUtils.isEmpty(loginid)){
	            hql.append(" and loginid like '" + loginid + "%' ");
	        }
	        hql.append("  GROUP BY checkinDate) t ");
		
		//String sql = "SELECT COUNT(*) from (select * from t_employee_checkin_data where loginid like '"+loginid+"%' and year like '"+year+"%' and month like '"+month+"%' GROUP BY checkinDate) t";
		int contt=Integer.parseInt(this.findBySql(hql.toString(), null).get(0).toString());
		return contt;
	}

	@Override
	public int checkTime(String openid) {
		String loginid = this.findLogin(openid);
		logger.debug(loginid);
		loginid = loginid.substring(0, loginid.length()-1);
		String sql = "SELECT max(da.checkinTime) as checkintime from t_employee_checkin_data da  WHERE date(da.checkinDate) = curdate() and loginid like '"+loginid+"%'";
		logger.debug(sql);
		List list=this.findBySql(sql, null);
		if(GetDateUtil.ifEmpty(list)){
			logger.info("打卡当前时间"+GetDateUtil.HHmmss()+"打卡时间"+list.get(0).toString());
			int time_difference = Integer.parseInt(GetDateUtil.HHmmss())-Integer.parseInt(list.get(0).toString());
			if(time_difference>180){
				return 1;
			}else{
				logger.info("打卡时间间隔太短-当前时间"+GetDateUtil.HHmmss()+"打卡时间"+list.get(0).toString());
				return 0;
			}
		}else{
			return 1;
		}
	}

	@Override
	public void saveResource(String sql) {
		try {
			this.execUpdateThrowE(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List ifStudentCode(String sql) {
		List list = this.findBySql(sql, null);
		return list;
	}
}
