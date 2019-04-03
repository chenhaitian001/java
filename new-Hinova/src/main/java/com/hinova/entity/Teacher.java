package com.hinova.entity;



public class Teacher{
	private Integer id;
	//姓名
    private String name;
    

	private String gradeid;
    private String clazzid;
    

	//手机号
    private String phone;

    //邮箱
    private String email;

    //地址
    private String address;
    
    private String provinces; // '省',
    private String cities;  //'市',
    private String counties; // '县',
    private String addressDetial ;// '详细地址',
    private String number ; //'工号',
    private String gender ; //'性别' ,
    private String agencyNumber; //'所属机构编号',
    private String worker;  //'职务',
    private String code; //  '考勤识别码',
    private String birthday; // '出生日期',
    private String nationality;//  '国籍',
    private String preschoolEducation; //  '是否学前教育毕业',
    private String cardType; // '证件类型',
    private String preschoolTeachers;  //'幼儿师范毕业',
    private String idCard; // '证件号',
    private String teacherPc ; // '是否有教师资格证',
    private String pHm ; // '港澳台侨',
    private String title ; // '职称',
    private String politicalLandscape; //  '政治面貌',
    private String schooling  ;// '学历',
    private String volk ; //'民族',
    private String teacherPcType;  // '教师资格证种类',
    private String residenceTyp  ;//'户口性质',
    private String issuing ;  //'发证机关',
    private String residence;  //'户口所在地',
    private String teacherPcNo; // '教师资格证号码',
    private String placeBirth;//  '出生地',
    private String compileType;//  '编制情况',
    private String workerType ; //'入职时间',
    private String ifJob;  //'是否在职',
    private String employingWay; // '用工方式',
    
	//管理的班级
    private Clazz clazz;
    private Grade grade;

	private String editUser;
    private String createUser;
    private String createTime;
    private String editTime;
    public int isDelete = 0; // 0正常，1删除
    public String description;
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAgencyNumber() {
		return agencyNumber;
	}

	public void setAgencyNumber(String agencyNumber) {
		this.agencyNumber = agencyNumber;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPreschoolEducation() {
		return preschoolEducation;
	}

	public void setPreschoolEducation(String preschoolEducation) {
		this.preschoolEducation = preschoolEducation;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getPreschoolTeachers() {
		return preschoolTeachers;
	}

	public void setPreschoolTeachers(String preschoolTeachers) {
		this.preschoolTeachers = preschoolTeachers;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getTeacherPc() {
		return teacherPc;
	}

	public void setTeacherPc(String teacherPc) {
		this.teacherPc = teacherPc;
	}

	public String getpHm() {
		return pHm;
	}

	public void setpHm(String pHm) {
		this.pHm = pHm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoliticalLandscape() {
		return politicalLandscape;
	}

	public void setPoliticalLandscape(String politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	public String getSchooling() {
		return schooling;
	}

	public void setSchooling(String schooling) {
		this.schooling = schooling;
	}

	public String getVolk() {
		return volk;
	}

	public void setVolk(String volk) {
		this.volk = volk;
	}

	public String getTeacherPcType() {
		return teacherPcType;
	}

	public void setTeacherPcType(String teacherPcType) {
		this.teacherPcType = teacherPcType;
	}

	public String getResidenceTyp() {
		return residenceTyp;
	}

	public void setResidenceTyp(String residenceTyp) {
		this.residenceTyp = residenceTyp;
	}

	public String getIssuing() {
		return issuing;
	}

	public void setIssuing(String issuing) {
		this.issuing = issuing;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getTeacherPcNo() {
		return teacherPcNo;
	}

	public void setTeacherPcNo(String teacherPcNo) {
		this.teacherPcNo = teacherPcNo;
	}

	public String getPlaceBirth() {
		return placeBirth;
	}

	public void setPlaceBirth(String placeBirth) {
		this.placeBirth = placeBirth;
	}

	public String getCompileType() {
		return compileType;
	}

	public void setCompileType(String compileType) {
		this.compileType = compileType;
	}

	public String getWorkerType() {
		return workerType;
	}

	public void setWorkerType(String workerType) {
		this.workerType = workerType;
	}

	public String getIfJob() {
		return ifJob;
	}

	public void setIfJob(String ifJob) {
		this.ifJob = ifJob;
	}

	public String getEmployingWay() {
		return employingWay;
	}

	public void setEmployingWay(String employingWay) {
		this.employingWay = employingWay;
	}
    public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
    public String getGradeid() {
		return gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	public String getClazzid() {
		return clazzid;
	}

	public void setClazzid(String clazzid) {
		this.clazzid = clazzid;
	}
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
