package cn.com.hinova.ruvs.config.service.impl;

import cn.com.hinova.ruvs.common.base.ICommonService;
import cn.com.hinova.ruvs.common.base.SuperService;
import cn.com.hinova.ruvs.config.bean.Patriarch;
import cn.com.hinova.ruvs.config.service.IPatriarchService;
import cn.com.hinova.ruvs.ic.util.ICDeviceUtils;
import cn.com.hinova.ruvs.ic.util.ICUtil;
import org.apache.commons.collections.iterators.ArrayListIterator;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SuppressWarnings("JpaQlInspection")
@Service
public class PatriarchServiceImpl extends SuperService implements IPatriarchService {


    @Autowired
    private ICommonService commonService;

    @Override
    public List<Patriarch> allFaces() {


        return this.getSession().createQuery("from Patriarch").list();
    }


    @Override
    public List<String> allFaceCodes() {


        return this.getSession().createQuery("select faceCode from Patriarch").list();
    }

    @Override
    public int synPatriarchByDeviceIp(String ip) {

        List<String> allFaceCode=this.allFaceCodes();

        List<String> deviceFaceCode=ICDeviceUtils.getEmployeeId(ip,9922);

        List<String> newFaceCode=new ArrayList<String>();


        for (String faceCode: deviceFaceCode) {
            if(!allFaceCode.contains(faceCode)){
                newFaceCode.add(faceCode);
            }
        }



        for (String faceCode:newFaceCode) {

            Map<String,String> map= ICDeviceUtils.getEmployee(ip,9922,faceCode);

            String faceId=map.get("id");
            String name=map.get("name");

            Patriarch patriarch=new Patriarch();
            patriarch.setFaceCode(faceId);
            patriarch.setName(name);
            patriarch.setPhone("");
            patriarch.setResuData(map.get("resu_data"));


            try {
                this.commonService.add(patriarch);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        List<Patriarch> list=allFaces();
        for (Patriarch patriarch:list) {

            Map<String,String> map= ICDeviceUtils.getEmployee(ip,9922,patriarch.getFaceCode());


            patriarch.setResuData(map.get("resu_data"));


        }


        return 0;
    }

    @Override
    public int sysPatriarchToOtherDevice(String[] faceCodes, String targetIp) {



        String hql="from Patriarch ";

        if(faceCodes!=null){
            hql+="where faceCode in (:faceCodes)";
        }

        Query query;
        query = this.getSession().createQuery(hql);


        if(faceCodes!=null){
            query.setParameterList("faceCodes",faceCodes);
        }
        List<Patriarch> patriarches=query.list();

        for (Patriarch patriarche:patriarches) {

            ICDeviceUtils.setEmployee(targetIp,9922,patriarche.getResuData().replace("Return","SetEmployee"));

        }


        return faceCodes!=null?faceCodes.length:0;
    }
}
