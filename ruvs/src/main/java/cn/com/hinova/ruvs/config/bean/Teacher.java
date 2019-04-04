package cn.com.hinova.ruvs.config.bean;

import cn.com.hinova.ruvs.common.base.SuperModel;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Set;

public class Teacher extends SuperModel {

    //姓名
    private String name;

    //手机号
    private String phone;

    //邮箱
    private String email;

    //地址
    private String address;

    //管理的班级
    @JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
    private Clazz clazz;


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

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
