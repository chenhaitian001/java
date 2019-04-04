package cn.com.hinova.ruvs.ic.service.impl;

import cn.com.hinova.ruvs.analyze.bean.FaceHistory;
import cn.com.hinova.ruvs.common.base.CommonServiceImpl;
import cn.com.hinova.ruvs.common.base.SuperService;
import cn.com.hinova.ruvs.config.bean.*;
import cn.com.hinova.ruvs.ic.service.ICService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
@Service("icService")
@Transactional
public class ICServiceImpl extends SuperService implements ICService {



    @Override
    public List<Device> findDevices() {
        return this.getSession().createQuery("from Device").list();
    }


    @Override
    public void setDeviceOnline(Set<String> sns) {

        List<Device> devs= findDevices();
        for (Device dev : devs) {
            if (sns.contains(dev.getSn())) {
                dev.setStatus(1);
            } else {
                dev.setStatus(0);
            }
        }
    }

    @Override
    public List<Patriarch> getAllPatriarch() {


        List<Patriarch> patriarchs=this.getSession().createQuery("from Patriarch").list();

        for (Patriarch patriarch:patriarchs) {

            try{
                Student student=patriarch.getStudent();
                if(student!=null){
                    Clazz clazz=student.getClazz();
                    if(clazz!=null){

                        Set<Teacher> teachers=clazz.getTeacherSet();

                        for (Teacher teacher:teachers) {

                            if(teacher!=null){
                                teacher.getName();
                            }
                            Grade grade=clazz.getGrade();
                            if(grade!=null){
                                grade.getName();
                            }
                        }

                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return patriarchs;
    }

    @Override
    public void saveHistory(FaceHistory fh) {
        this.getSession().save(fh);
    }
}
