package cn.com.hinova.ruvs.config.action;

import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.config.bean.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * 年级
 */

@Controller
@RequestMapping("/config/grade")
public class GradeAction extends SuperAction<Grade> {
}
