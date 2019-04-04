package cn.com.hinova.ruvs.config.bean;

import cn.com.hinova.ruvs.common.base.SuperModel;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Set;

/**
 * 班级
 */
public class Clazz extends SuperModel {

    //班级名
    private String name;

    //年级
    @JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
    private Grade grade;


    private Set<Teacher> teacherSet;

//    @JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
//    private Teacher teacher;
//
//
//    public Teacher getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }


    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
