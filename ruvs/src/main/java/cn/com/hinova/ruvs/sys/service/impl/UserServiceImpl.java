package cn.com.hinova.ruvs.sys.service.impl;

import cn.com.hinova.ruvs.common.base.SuperService;
import org.springframework.stereotype.Service;

import cn.com.hinova.ruvs.sys.service.IUserService;
import cn.com.hinova.ruvs.sys.bean.User;

@SuppressWarnings("ALL")
@Service("userService")
public class UserServiceImpl  extends SuperService implements IUserService {



	@Override
	public void updatePassword(User user, String newPassword) throws Exception {



		String sql="update User set password=? where id=? ";
		this.getSession().createQuery(sql)
				.setParameter(0, newPassword)
				.setParameter(1, user.getId()).executeUpdate();

	}

}
