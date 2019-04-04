package cn.com.hinova.ruvs.henuo.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import cn.com.hinova.ruvs.common.bean.Paging;

public interface StudentService {
	
	public Paging queryStudent(String a,String b,String c,Paging paging);
	
	public Paging queryLeavest(String a,Paging paging);
	
	public List query(String a,String b,String c);
	
	public List fileList(List a);
	
	public void upClass(String id[]);
	
	public List findifrelation(String student_id,String relation);
	
	public void saveRelation(String student_id,String relation);

}
