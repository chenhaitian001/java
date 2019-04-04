package cn.com.hinova.ruvs.config.action;

import cn.com.hinova.ruvs.common.base.ICommonService;
import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.config.bean.Clazz;
import cn.com.hinova.ruvs.utils.StringUtils;
import cn.com.hinova.ruvs.utils.WhereUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 班级
 */
@Controller
@RequestMapping("/config/clazz")
public class ClazzAction extends SuperAction<Clazz> {



    @Autowired
    private ICommonService commonService;

    @RequestMapping("combo.do")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> combo() {



        return commonService.getMapList("select id as value,grade.name ||' '|| name as text from Clazz where isDelete=0 order by grade.name asc, name asc",new ArrayList<Object>());
    }
}
