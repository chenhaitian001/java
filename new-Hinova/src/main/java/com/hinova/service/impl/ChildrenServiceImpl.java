package com.hinova.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hinova.dao.ChildrenDao;
import com.hinova.dao.ClassDao;
import com.hinova.dao.GradeDao;
import com.hinova.dao.PatriarchDao;
import com.hinova.entity.Clazz;
import com.hinova.entity.Grade;
import com.hinova.entity.OutPutStudent;
import com.hinova.entity.Patriarch;
import com.hinova.entity.Student;
import com.hinova.service.ChildrenService;


@Service("childrenService")
public class ChildrenServiceImpl implements ChildrenService {
	@Resource ChildrenDao childrenDao;
	 @Resource PatriarchDao patriarchDao;
	 @Resource ClassDao classDao;
	 @Resource GradeDao gradeDao;
	 
	@Override
	public List<Student> listStudent(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return childrenDao.list(map);
	}
	@Override
	public List<Student> listLeaveStudent(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return childrenDao.listLeave(map);
	}
	@Override
	public Integer getStudentTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return childrenDao.getTotal(map);
	}

	@Override
	public Integer addStudent(Student student) {
		// TODO Auto-generated method stub
		try {
			return childrenDao.add(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer updateStudent(Student student) {
		// TODO Auto-generated method stub
		try {
			return childrenDao.edit(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student findByStudentName(String name) {
		// TODO Auto-generated method stub
		return childrenDao.findByName(name);
	}

	@Override
	public Student findByStudentId(String id) {
		// TODO Auto-generated method stub
		return childrenDao.findById(id);
	}

	@Override
	public Integer delete_student(String id) {
		// TODO Auto-generated method stub
		return childrenDao.delete_student(id);
	}

	@Override
	public List<Patriarch> listPatriarch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return patriarchDao.list_Patriarch(map);
	}

	@Override
	public Integer getPatriarchTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return patriarchDao.getPatriarchTotal(map);
	}

	@Override
	public Integer addPatriarch(Patriarch patriarch) {
		// TODO Auto-generated method stub
			return patriarchDao.addPatriarch(patriarch);
	}

	@Override
	public Integer updatePatriarch(Patriarch patriarch) {
		// TODO Auto-generated method stub
		return patriarchDao.editPatriarch(patriarch);
	}

	@Override
	public Patriarch findByPatriarchName(String name) {
		// TODO Auto-generated method stub
		return patriarchDao.findByPatriarchName(name);
	}

	@Override
	public Patriarch findByPatriarchId(String id) {
		// TODO Auto-generated method stub
		return patriarchDao.findByPatriarchId(id);
	}

	@Override
	public Integer delete_Patriarch(String id) {
		// TODO Auto-generated method stub
		return patriarchDao.delete_Patriarch(id);
	}

	@Override
	public List<OutPutStudent> exportStudent(Map<String, Object> map) {
		
		return childrenDao.listExport(map);
	}
	@Override
	public void fileList(List veList) {
		for(int i=0;i<veList.size();i++){
			List<Map<String, String>> veeList = (List<Map<String, String>>) veList.get(i);
			Student st = new Student();
			String gname = ""; //年级名
			for (Map<String,String> map:veeList) {
				
	                for (String s:map.keySet()) 
	                {
	                	s.replaceAll("\\s*", ""); 
	                	if(s.equals("name")){
	                		st.setName(map.get(s));
	                	}
	                	if(s.equals("sex")){
	                		st.setSex((map.get(s).equals("男"))?"1":"0");
	                	}
	                	if(s.equals("organizeId")){
	                		st.setOrganizeId(map.get(s));
	                	}
	                	if(s.equals("birthday")){
	                		st.setBirthday(map.get(s));
	                	}
	                	if(s.equals("goSchoolTime")){
	                		st.setGoSchoolTime(map.get(s));
	                	}
	                	if(s.equals("gradeId")){
	                		Grade grade =gradeDao.findByName(map.get(s));
	                		if(grade!=null){
	                			gname = map.get(s);
	                			st.setGradeId(grade.getGid());
	                		}
	                	}
	                	if(s.equals("clazzId")){
	                	  Map<String, Object> mapClazz = new HashMap<String, Object>();
	                	  mapClazz.put("cname", map.get(s));
	                	  mapClazz.put("gname", gname);
	                	  Clazz  clazz=	classDao.findByNameOnGradeName(mapClazz);
	                	  
	                	  if(clazz!=null){
	                		  st.setClazzId(clazz.getCid());
	                		}
	                		
	                	}
	                	if(s.equals("keeper")){
	                		st.setKeeper(map.get(s));
	                	}
	                	if(s.equals("keeperPhone")){
	                		st.setKeeperPhone(map.get(s));
	                	}
	                	if(s.equals("provinces")){
	                		st.setProvinces(map.get(s));
	                	}
	                	if(s.equals("cities")){
	                		st.setCities(map.get(s));
	                	}
	                	if(s.equals("counties")){
	                		st.setCounties(map.get(s));
	                	}
	                	if(s.equals("address")){
	                		st.setAddress(map.get(s));
	                	}
	                	if(s.equals("englishName")){
	                		st.setEnglishName(map.get(s));
	                	}
	                	if(s.equals("volk")){
	                		st.setVolk(map.get(s));
	                	}
	                	if(s.equals("nationality")){
	                		st.setNationality(map.get(s));
	                	}	               
	                	if(s.equals("cardType")){
	                		st.setCardType(map.get(s));
	                	}
	                	if(s.equals("residenceTyp")){
	                		st.setResidenceTyp(map.get(s));
	                	}
	                	if(s.equals("idCard")){
	                		st.setIdCard(map.get(s));
	                	}
	                	if(s.equals("physicalExamination")){
	                		st.setPhysicalExamination((map.get(s).equals("有"))?"1":"0");
	                	}
	                	if(s.equals("physicalBook")){
	                		st.setPhysicalBook((map.get(s).equals("有"))?"1":"0");
	                	}
	                	if(s.equals("healthyBook")){
	                		st.setHealthyBook((map.get(s).equals("有"))?"1":"0");
	                	}
	                	if(s.equals("physicalHistory")){
	                		st.setPhysicalHistory(map.get(s));
	                	}
	                	if(s.equals("allergy")){
	                		st.setAllergy((map.get(s).equals("有"))?"1":"0");
	                	}
	                	if(s.equals("defensiveMark")){
	                		st.setDefensiveMark((map.get(s).equals("有"))?"1":"0");
	                	}
	                	if(s.equals("specialMatter")){
	                		st.setSpecialMatter(map.get(s));
	                	}
	                	
	                }
	                
	         }
			
			try {
				childrenDao.add(st);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void upClazz(String[] id) {
		for(String a :id){
			//查询学生对应的班级 年级
			Student lst = childrenDao.listGradeClazzbyStudent(a);
			if(lst!=null){
				int clazz_id = Integer.parseInt(lst.getClazzId());
				String gradeId = lst.getGradeId();
				//根据年级查出所有班级
				List<Clazz> clazzList = classDao.listClazzByGradeId(gradeId);
				int ses = 0;
				int maxclass = Integer.parseInt(clazzList.get(clazzList.size()-1).getCid().toString());
				if(clazz_id==maxclass){
					//升级到毕业班级
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("isDelete", "1");
					map.put("id", a);
					childrenDao.upClazz(map);
					//升级到毕业班级
					return;
				}
				for(int j=0;j<=clazzList.size();j++){
					ses = Integer.parseInt(clazzList.get(j).getCid().toString());
					if(clazz_id<ses){
						break;
					}
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ClazzId", ses);
				map.put("id", a);
				//普通升班
				childrenDao.upClazz(map);
			}
		}
		
	}
}
