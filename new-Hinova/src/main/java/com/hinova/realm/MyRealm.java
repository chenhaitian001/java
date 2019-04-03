package com.hinova.realm;


import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hinova.entity.User;
import com.hinova.service.UserService;
import com.hinova.util.CryptographyUtil;




/**
 * 自定义Realm
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm{
	
	@Resource
	private UserService  userService;
	
	/**
	 * 为当前的登录的用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
	
	
	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String login_type = (String) SecurityUtils.getSubject().getSession().getAttribute("login_type");
		switch (login_type) {
		case "wx_login":
			String openid =(String) token.getPrincipal();//我上面使用了openid
			if(openid!=null){
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(openid, CryptographyUtil.md5(openid, "chenhaitian"), "xxx");
				return authcInfo;
			}else{
			}
			break;
		case "user_login":
			String name=(String) token.getPrincipal();//我上面使用了name
			User user=userService.findByUserName(name);
			if(user!=null){
				//SecurityUtils.getSubject().getSession().setAttribute("currentuser", user); //把当前用户信息存到session中
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getName(), user.getPassword(), "xxx");
				return authcInfo;
			}else{
			}
			break;
		}
		return null;
	}

}
