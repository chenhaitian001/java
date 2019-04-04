package cn.com.hinova.ruvs.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.common.bean.Result;
import cn.com.hinova.ruvs.sys.bean.User;
import cn.com.hinova.ruvs.utils.SecurityUtils;

@Controller
@RequestMapping("/sys/user")
public class UserAction extends SuperAction<User> {


	@RequestMapping("save.do")
	@ResponseBody
	@Override
	public Result save(User t) throws Exception {

		if(t.getPassword()!=null){
			t.setPassword(SecurityUtils.encode(t.getPassword()));
		}
		
		return super.save(t);
	}
	
	
	
	
	
	
}
