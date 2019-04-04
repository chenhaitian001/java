package cn.com.hinova.ruvs.config.action;

import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.config.bean.Teacher;
import cn.com.hinova.ruvs.config.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 老师
 */
@Controller
@RequestMapping("/config/teacher")
public class TeacherAction extends SuperAction<Teacher> {


    @Autowired
    private ITeacherService teacherService;

    @RequestMapping("clazzCombo.do")
    @ResponseBody
    public Object getClazzCombo(HttpServletRequest req){

        String teacherId=req.getParameter("id");


        return teacherService.getClazzCombo(teacherId);
    }

}
