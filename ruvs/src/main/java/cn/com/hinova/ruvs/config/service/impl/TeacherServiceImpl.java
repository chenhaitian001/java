package cn.com.hinova.ruvs.config.service.impl;

import cn.com.hinova.ruvs.common.base.SuperService;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.config.service.ITeacherService;
import cn.com.hinova.ruvs.utils.StringUtils;
import cn.com.hinova.ruvs.utils.WhereUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl extends SuperService implements ITeacherService {
    @Override
    public List<Map<String, String>> getClazzCombo(String teacherId) {


//        StringBuilder sql=new StringBuilder("select c.id as value,c.grade.name ||' '|| c.name as text from Clazz c left join c.teacherSet t where t is null ");
//
//        if (teacherId!=null&&teacherId.length()!=0){
//            sql.append(" or t.id=:id ");
//
//        }

        StringBuilder sql=new StringBuilder("select c.id as value,c.grade.name ||' '|| c.name as text from Clazz c  ");


        sql.append(" order by c.grade.name asc,c.name asc ");

        Query query = this.getSession().createQuery(sql.toString());

            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
}
