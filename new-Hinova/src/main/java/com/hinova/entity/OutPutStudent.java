package com.hinova.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class OutPutStudent {
	@Excel(name = "学生编号", orderNum = "0")
    private Integer id;
	@Excel(name = "姓名", orderNum = "1")
	private String name;
	@Excel(name = "性别",replace = {"男_1", "女_0"}, orderNum = "2")
	private String sex ;
	@Excel(name = "班级", orderNum = "3")
	private String clazzId;
	@Excel(name = "年级", orderNum = "4")
	private String gradeId;
	@Excel(name = "生日", orderNum = "5")
	private String  birthday ;//生日
	@Excel(name = "入园时间", orderNum = "6")
    private String  goSchoolTime;//go_school_time 入园时间
	@Excel(name = "民族", orderNum = "7")
    private String volk ;//民族
	@Excel(name = "主监护人", orderNum = "8")
    private String keeper ;// 主监护人
	@Excel(name = "手机号", orderNum = "9")
    private String keeperPhone;//keeper_phone
	@Excel(name = "省", orderNum = "10")
    private String provinces ;//省
	@Excel(name = "市", orderNum = "11")
    private String cities  ;// 市
	@Excel(name = "县", orderNum = "12")
    private String counties ;//县
	@Excel(name = "详细地址", orderNum = "13")
    private String address ;//address 详细地址
	@Excel(name = "组织机构", orderNum = "14")
	private String organizeId ;//organize_id 组织机构
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGoSchoolTime() {
		return goSchoolTime;
	}
	public void setGoSchoolTime(String goSchoolTime) {
		this.goSchoolTime = goSchoolTime;
	}
	public String getVolk() {
		return volk;
	}
	public void setVolk(String volk) {
		this.volk = volk;
	}
	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	public String getKeeperPhone() {
		return keeperPhone;
	}
	public void setKeeperPhone(String keeperPhone) {
		this.keeperPhone = keeperPhone;
	}
	public String getProvinces() {
		return provinces;
	}
	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}
	public String getCities() {
		return cities;
	}
	public void setCities(String cities) {
		this.cities = cities;
	}
	public String getCounties() {
		return counties;
	}
	public void setCounties(String counties) {
		this.counties = counties;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrganizeId() {
		return organizeId;
	}
	public void setOrganizeId(String organizeId) {
		this.organizeId = organizeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazzId() {
		return clazzId;
	}
	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	
}
