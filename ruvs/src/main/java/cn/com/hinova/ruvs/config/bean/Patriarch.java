package cn.com.hinova.ruvs.config.bean;

import cn.com.hinova.ruvs.common.base.SuperModel;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Set;

/**
 * 家长信息
 */
public class Patriarch extends SuperModel {

    //人脸识别编号
    private String faceCode;

    //姓名
    private String name;

    //手机
    private String phone;

    //邮箱
    private String email;

    //地址
    private String address;

    //关系，爷爷，奶奶，爸爸，妈妈，叔叔，婶婶，姥姥，老爷
    private String relation;
    private int studentId;
    public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	//考勤机上的原始数据
    private String resuData;

    public String getResuData() {
        return resuData;
    }

    public void setResuData(String resuData) {
        this.resuData = resuData;
    }

    //宝宝
    private Student student;

    public String getFaceCode() {
        return faceCode;
    }

    public void setFaceCode(String faceCode) {
        this.faceCode = faceCode;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
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
}

