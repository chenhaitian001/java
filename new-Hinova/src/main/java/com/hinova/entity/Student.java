package com.hinova.entity;

import java.util.List;


public class Student{

    //姓名
	private String id;
	private String name;
    private String sex ;
    private String organizeId ;//organize_id 组织机构
    private String  birthday ;//生日
    private String  goSchoolTime;//go_school_time 入园时间
    private String clazzId;//clazz_id 班级
    private String gradeId ;//grade_id 年级
    private String keeper ;// 主监护人
    private String keeperPhone;//keeper_phone
    private String address ;// 家庭住址
    private String provinces ;//省
    private String cities  ;// 市
    private String counties ;//县
    private String addressDetial ;//address_detial 详细地址
    private String describes ;//描述
    private String englishName;//english_name英文名
    private String volk ;//民族
    private String nationality ;//国籍
    private String residence ;//户口所在地
    private String cardType ;//card_type 证件类型
    private String residenceTyp ;//residence_typ 户口性质
    private String idCard ;//id_card身份证好
    private String physicalExamination;//physical_examination入园体检证明
    private String physicalBook  ;//physical_book 接种本
    private String healthyBook ;//healthy_book 医保手册
    private String physicalHistory ;//physical_history 病史 
    private String allergy  ;//过敏物
    private String defensive ; //有无漏接防御针
    
    //班级 一对多 学生
    private Clazz clazz;
    //家长 多对一 学生`address_detial`
    private List<Patriarch> patriarch;
    private String description;  //描述
    private String physicalHistoryMark;//病史 
    private String allergyMark;//过敏物
    private String defensiveMark;//有无漏接防御针
    
	private String specialMatter;//special_matter特殊告知事项
    
	private String editUser;
    private String createUser;
    private String createTime;
    private String editTime;
    public int isDelete = 0; // 0正常，1删除
    public String getPhysicalHistoryMark() {
		return physicalHistoryMark;
	}
	public void setPhysicalHistoryMark(String physicalHistoryMark) {
		this.physicalHistoryMark = physicalHistoryMark;
	}
	public String getAllergyMark() {
		return allergyMark;
	}
	public void setAllergyMark(String allergyMark) {
		this.allergyMark = allergyMark;
	}
	public String getDefensiveMark() {
		return defensiveMark;
	}
	public void setDefensiveMark(String defensiveMark) {
		this.defensiveMark = defensiveMark;
	}
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public List<Patriarch> getPatriarch() {
		return patriarch;
	}
	public void setPatriarch(List<Patriarch> patriarch) {
		this.patriarch = patriarch;
	}
	public String getOrganizeId() {
		return organizeId;
	}
	public void setOrganizeId(String organizeId) {
		this.organizeId = organizeId;
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
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getAddressDetial() {
		return addressDetial;
	}
	public void setAddressDetial(String addressDetial) {
		this.addressDetial = addressDetial;
	}
	
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getVolk() {
		return volk;
	}
	public void setVolk(String volk) {
		this.volk = volk;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getResidenceTyp() {
		return residenceTyp;
	}
	public void setResidenceTyp(String residenceTyp) {
		this.residenceTyp = residenceTyp;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhysicalExamination() {
		return physicalExamination;
	}
	public void setPhysicalExamination(String physicalExamination) {
		this.physicalExamination = physicalExamination;
	}
	public String getPhysicalBook() {
		return physicalBook;
	}
	public void setPhysicalBook(String physicalBook) {
		this.physicalBook = physicalBook;
	}
	public String getHealthyBook() {
		return healthyBook;
	}
	public void setHealthyBook(String healthyBook) {
		this.healthyBook = healthyBook;
	}
	public String getPhysicalHistory() {
		return physicalHistory;
	}
	public void setPhysicalHistory(String physicalHistory) {
		this.physicalHistory = physicalHistory;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public String getDefensive() {
		return defensive;
	}
	public void setDefensive(String defensive) {
		this.defensive = defensive;
	}
	public String getSpecialMatter() {
		return specialMatter;
	}
	public void setSpecialMatter(String specialMatter) {
		this.specialMatter = specialMatter;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
    
    


}
