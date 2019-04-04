package cn.com.hinova.ruvs.config.bean;

import cn.com.hinova.ruvs.common.base.SuperModel;

/**
 * 设备
 */
public class Device extends SuperModel {


    //安装位置
    private String location;

    //地址
    private String ip;


    //设备序列号
    private String sn;

    //0离线，1在线
    private int status;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
