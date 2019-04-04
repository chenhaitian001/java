package cn.com.hinova.ruvs.config.bean;

import cn.com.hinova.ruvs.common.base.SuperModel;

/**
 * 年级
 */
public class Grade extends SuperModel {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
