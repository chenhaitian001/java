package cn.com.hinova.ruvs.analyze.service;

import cn.com.hinova.ruvs.analyze.bean.FaceHistory;

import java.util.List;

public interface IBigScreenService {


    public List<FaceHistory> getFaceHistory(int size) ;
    public List<FaceHistory> getNewFaceHistory(String lastTime);
}
