package cn.com.hinova.ruvs.config.service;

import cn.com.hinova.ruvs.config.bean.Patriarch;

import java.util.List;

public interface IPatriarchService {


    public List<Patriarch>  allFaces();
    public List<String> allFaceCodes();

    public int synPatriarchByDeviceIp(String ip);


    public int sysPatriarchToOtherDevice(String[] faceCodes,String targetIp);
}
