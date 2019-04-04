package cn.com.hinova.ruvs.ic.service;

import cn.com.hinova.ruvs.analyze.bean.FaceHistory;
import cn.com.hinova.ruvs.config.bean.Device;
import cn.com.hinova.ruvs.config.bean.Patriarch;

import java.util.List;
import java.util.Set;

public interface ICService {

    //所有设备
    public List<Device> findDevices();

    //设置在线
    public void setDeviceOnline(Set<String> sns);


    public List<Patriarch> getAllPatriarch();


    public void saveHistory(FaceHistory fh);


}
