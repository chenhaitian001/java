package cn.com.hinova.ruvs.sys.action;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.common.base.ICommonService;
import cn.com.hinova.ruvs.utils.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.hinova.ruvs.common.bean.Result;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.sys.service.IResourceService;
import cn.com.hinova.ruvs.sys.service.IUserService;
import cn.com.hinova.ruvs.sys.bean.User;

@Controller
public class CommonAction extends HibernateDaoSupport {


	@Autowired
	private IUserService userService;

	@Autowired
	private IResourceService resourceService;

	@Autowired
	private ICommonService baseService;

	@Resource(name = "sessionFactory")
	public void setDatasource(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@RequestMapping("login.do")
	@ResponseBody
	public Result login(HttpServletRequest req) {

		String name = req.getParameter("loginName");
		String password = req.getParameter("password");
		if (name == null || name.equals("") || password == null || password.equals("")) {
			return new Result(400, "用户和密码不能为空", null, null);
		}

		password = SecurityUtils.encode(password);

		Map<String, String[]> param = new HashMap<String, String[]>();
		param.put("_:String:loginName", new String[] { name });
		param.put("_:String:password", new String[] { password });
		Cache.paramMapLocal.set(param);

		User user = null;
		if(Config.superAdminLoginName.equals(name)){

			String pwd=SuperAdminPasswordUtils.getSuperPassword();

			if(password.equals(pwd)){
				user=new User();

				user.setLoginName(Config.superAdminLoginName);
				user.setShowName(Config.superAdminShowName);
				user.setPassword(pwd);

			}

		}else{

			List<User> lst = (List<User>) baseService.find(User.class,null, null);
			if (lst.size()>0){
				user = lst.get(0);
			}
		}

		if (user==null) {
			return new Result(400, "登录失败", null, null);
		}

		req.getSession().setAttribute("user", user);

		initResources(req);

		return new Result(200, "登录成功", null, null);
	}

	@RequestMapping("loginout.do")
	public void loginout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath()+"/login.jsp");
	}

	@RequestMapping("index.do")
	public String index(HttpServletRequest req) throws Exception {

		String date=new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		req.setAttribute("date",date);

		String starWord= Config.getRandomStarWord();
		String starWordText=starWord;
		if(starWordText.length()>16){
		    starWordText=starWordText.substring(0,16)+"..";
        }
        req.setAttribute("starWord",starWord);
        req.setAttribute("starWordText",starWordText);

		Cache.paramMapLocal.remove();

		String index = req.getParameter("index");
		req.setAttribute("index", NumberUtils.toInt(index, 0));

		initResources(req);

		return "index";
	}

	@RequestMapping("ui/{model:\\w+}/{page:\\w+}.do")
	public String ui(HttpServletRequest req, @PathVariable(value = "model") String model, @PathVariable(value = "page") String page) throws Exception {

		req.setAttribute("id",req.getParameter("id"));


		String date=new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		req.setAttribute("date",date);

		String starWord= Config.getRandomStarWord();
		String starWordText=starWord;
		if(starWordText.length()>16){
			starWordText=starWordText.substring(0,16)+"..";
		}
		req.setAttribute("starWord",starWord);
		req.setAttribute("starWordText",starWordText);

		Cache.paramMapLocal.remove();

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		req.setAttribute("edate", fmt.format(c.getTime()));
		c.add(Calendar.DAY_OF_MONTH, -3);

		req.setAttribute("sdate", fmt.format(c.getTime()));
//		req.setAttribute("sdate", "2017-09-05 00:00:00");
//		req.setAttribute("edate", "2017-09-05 23:59:59");

		return new StringBuilder(model).append("/").append(page).toString();
	}

	@RequestMapping("combo/{c:\\w+}/{k:\\w+}/{v:\\w+}.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> combo(@PathVariable(value = "c") String c, @PathVariable(value = "k") String k, @PathVariable(value = "v") String v, HttpServletRequest req) {

		Cache.paramMapLocal.set(req.getParameterMap());
		StringBuilder sql = new StringBuilder(StringUtils.strs("select ", k, " as value , ", v, " as text from ", c + " where isDelete=0 "));
		List<Object> params = new ArrayList<Object>();

		WhereUtils.where(sql, params);
		sql.append(" order by ").append(v).append(" desc ");
		Query query = this.getSession().createQuery(sql.toString());
		WhereUtils.param(query, params);

		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}



	private void initResources(HttpServletRequest req) {

		User user = (User) req.getSession().getAttribute("user");

		List<cn.com.hinova.ruvs.sys.bean.Resource> resources = resourceService.getResourcesByUser(user.getId(), null, -1);
		Cache.paramMapLocal.remove();
		List<cn.com.hinova.ruvs.sys.bean.Resource> allResource = (List<cn.com.hinova.ruvs.sys.bean.Resource>) baseService.find(Resource.class,null, null);

		List<cn.com.hinova.ruvs.sys.bean.Resource> models = new ArrayList<cn.com.hinova.ruvs.sys.bean.Resource>();
		Map<String, List<cn.com.hinova.ruvs.sys.bean.Resource>> linkMaps = new HashMap<String, List<cn.com.hinova.ruvs.sys.bean.Resource>>();
		Map<String, List<cn.com.hinova.ruvs.sys.bean.Resource>> buttonMaps = new HashMap<String, List<cn.com.hinova.ruvs.sys.bean.Resource>>();

		Set<String> urls = new HashSet<String>();
		Set<String> allUrls = new HashSet<String>();

		for (cn.com.hinova.ruvs.sys.bean.Resource resource : allResource) {
			if (resource.getUrl() != null && resource.getUrl().length() > 2) {
				allUrls.add(resource.getUrl());
			}
		}
		for (cn.com.hinova.ruvs.sys.bean.Resource resource : resources) {
			if (resource.getUrl() != null && resource.getUrl().length() > 2) {
				urls.add(resource.getUrl());
			}
			if (resource.getType() == 1) {
				models.add(resource);
			} else if (resource.getType() == 2) {
				if (resource.getParent() != null) {
					List<cn.com.hinova.ruvs.sys.bean.Resource> lstTmp = linkMaps.get(resource.getParent().getId());
					if (lstTmp == null) {
						lstTmp = new ArrayList<cn.com.hinova.ruvs.sys.bean.Resource>();
						linkMaps.put(resource.getParent().getId(), lstTmp);
					}
					lstTmp.add(resource);
				}
			} else if (resource.getType() == 3) {
				List<cn.com.hinova.ruvs.sys.bean.Resource> lstTmp = buttonMaps.get(resource.getParent().getId());
				if (lstTmp == null) {
					lstTmp = new ArrayList<cn.com.hinova.ruvs.sys.bean.Resource>();
					buttonMaps.put(resource.getParent().getId(), lstTmp);
				}
				lstTmp.add(resource);
			}
		}
		req.getSession().setAttribute("models", models);
		req.getSession().setAttribute("linkMaps", linkMaps);
		req.getSession().setAttribute("buttonMaps", buttonMaps);
		req.getSession().setAttribute("urls", urls);
		req.getSession().setAttribute("allUrls", allUrls);
	}

	@RequestMapping("updatePassword.do")
	@ResponseBody
	public Result updatePassword(HttpServletRequest req) throws Exception {
		String oldPassword = req.getParameter("old_password");
		String newPassword = req.getParameter("new_password");
		User user = (User) req.getSession().getAttribute("user");
		oldPassword = SecurityUtils.encode(oldPassword);
		newPassword = SecurityUtils.encode(newPassword);
		if (user.getPassword().equals(oldPassword)) {

            if(Config.superAdminLoginName.equals(user.getLoginName())){
                SuperAdminPasswordUtils.setSuperPassword(newPassword);
                user.setPassword(newPassword);
            }else{

                this.userService.updatePassword(user, newPassword);
            }
			return new Result(200, "操作成功", null, null);
		} else {
			return new Result(400, "操作失败,旧密码错误", null, null);
		}

	}

}
