package cn.com.hinova.ruvs.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.sys.bean.Resource;

@Controller
@RequestMapping("/sys/resource")
public class ResourceAction extends SuperAction<Resource> {


}
