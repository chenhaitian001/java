package cn.com.hinova.ruvs.config.action;

import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.config.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学生
 */
@Controller

@RequestMapping("/config/student")
public class StudentAction extends SuperAction<Student> {
}
