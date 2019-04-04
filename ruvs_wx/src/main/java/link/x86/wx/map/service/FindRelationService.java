package link.x86.wx.map.service;

import java.util.List;

import link.x86.wx.map.entity.EmployeeCheckinData;


public interface FindRelationService {
	public List findNumber(String no);
	public void saveEmployeeCheckinData(EmployeeCheckinData data);
	public List<EmployeeCheckinData> findEmployeeCheckinDataList(String loginid, Integer year, Integer month);
	public List findWork(String no);
	public List findWorkbyLogin(String loginid);
	public String findLogin(String openid);
	public String findrelation(String openid);
	public Boolean findAuthor(String opendi);
	public int findCountWorks(String loginid, Integer year, Integer month);
	public int checkTime(String openid);
	public void saveResource(String sql);
	public List ifStudentCode(String sql);
	public String amRelation(String no);
	public String pmRelation(String no);
	public String amRelationbyLogin(String no);
	public String pmRelationbyLogin(String no);
}
