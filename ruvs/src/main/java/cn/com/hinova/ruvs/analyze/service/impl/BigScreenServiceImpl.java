package cn.com.hinova.ruvs.analyze.service.impl;

import cn.com.hinova.ruvs.analyze.bean.FaceHistory;
import cn.com.hinova.ruvs.analyze.service.IBigScreenService;
import cn.com.hinova.ruvs.common.base.SuperService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@Service("bigScreenService")
public class BigScreenServiceImpl extends SuperService implements IBigScreenService {
    @Override
    public List<FaceHistory> getFaceHistory(int size) {
        return this.getSessionFactory().getCurrentSession()
                .createQuery("from FaceHistory order by faceTime desc ").setMaxResults(size).list();
    }


    public List<FaceHistory> getNewFaceHistory(String lastTime) {

        SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        try {
            date=fmt.parse(lastTime);
        } catch (ParseException e) {

        }

        return this.getSessionFactory().getCurrentSession()
                .createQuery("from FaceHistory where faceTime>:lastTime order by faceTime desc ").setParameter("lastTime",date).list();
    }
}
