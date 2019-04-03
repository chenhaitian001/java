package com.hinova.entity;


import java.util.Date;

public class FaceHistory {

    private String id;
    //设备位置
    private String deviceLocation;
    //设备SN号
    private String devcieSN;

    //家长ID
    private String patriarchId;
    //家长名称
    private String patriarchName;
    //家长电话
    private String patriarchPhone;
    //学生对家长的称呼
    private String patriarchRelation;
    //家长照片
    private String patriarchPhoto;

    //学生ID
    private String studentId;
    //学生名字
    private String studentName;
    //学生年级名称
    private String studentGradeName;
    //学生班级名称
    private String studentClazzName;
    //班主任老师名称
    private String teacherName;
    //班主任电话
    private String teacherPhone;

    //年
    private int year;
    //月
    private int month;
    //周
    private int week;
    //天
    private int day;

    //人脸时间
    //@JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatDate.class)
    private Date faceTime;
    //人脸照片
    private String facePhoto;

    private int isDelete=0;

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public String getDevcieSN() {
        return devcieSN;
    }

    public void setDevcieSN(String devcieSN) {
        this.devcieSN = devcieSN;
    }

    public String getPatriarchId() {
        return patriarchId;
    }

    public void setPatriarchId(String patriarchId) {
        this.patriarchId = patriarchId;
    }

    public String getPatriarchName() {
        return patriarchName;
    }

    public void setPatriarchName(String patriarchName) {
        this.patriarchName = patriarchName;
    }

    public String getPatriarchPhone() {
        return patriarchPhone;
    }

    public void setPatriarchPhone(String patriarchPhone) {
        this.patriarchPhone = patriarchPhone;
    }

    public String getPatriarchRelation() {
        return patriarchRelation;
    }

    public void setPatriarchRelation(String patriarchRelation) {
        this.patriarchRelation = patriarchRelation;
    }

    public String getPatriarchPhoto() {
        return patriarchPhoto;
    }

    public void setPatriarchPhoto(String patriarchPhoto) {
        this.patriarchPhoto = patriarchPhoto;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGradeName() {
        return studentGradeName;
    }

    public void setStudentGradeName(String studentGradeName) {
        this.studentGradeName = studentGradeName;
    }

    public String getStudentClazzName() {
        return studentClazzName;
    }

    public void setStudentClazzName(String studentClazzName) {
        this.studentClazzName = studentClazzName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Date getFaceTime() {
        return faceTime;
    }

    public void setFaceTime(Date faceTime) {
        this.faceTime = faceTime;
    }

    public String getFacePhoto() {
        return facePhoto;
    }

    public void setFacePhoto(String facePhoto) {
        this.facePhoto = facePhoto;
    }
}
