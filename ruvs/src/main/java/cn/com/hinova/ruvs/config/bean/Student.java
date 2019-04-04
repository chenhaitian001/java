package cn.com.hinova.ruvs.config.bean;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.com.hinova.ruvs.common.base.SuperModel;

public class Student extends SuperModel {

    //姓名
    private String name;


    //班级
    @JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
    private Clazz clazz;

    //家长
    private Set<Patriarch> patriarchSet;

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
    @JsonIgnore
    public Set<Patriarch> getPatriarchSet() {
        return patriarchSet;
    }

    public void setPatriarchSet(Set<Patriarch> patriarchSet) {
        this.patriarchSet = patriarchSet;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
