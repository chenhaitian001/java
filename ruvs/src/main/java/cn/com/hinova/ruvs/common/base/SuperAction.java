package cn.com.hinova.ruvs.common.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.hinova.ruvs.common.Config;
import cn.com.hinova.ruvs.common.fream.SpecialDateEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import cn.com.hinova.ruvs.common.annotation.CFUniqueAnnotation;
import cn.com.hinova.ruvs.common.bean.Paging;
import cn.com.hinova.ruvs.common.bean.Result;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.utils.NumberUtils;
import cn.com.hinova.ruvs.utils.UserUtils;

public class SuperAction<T extends SuperModel> extends MultiActionController {



	Class clazz=null;

	public SuperAction(){
        Type genType=this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        clazz = (Class<T>) params[0];
	}

	@Autowired
	protected ICommonService baseService;


	@RequestMapping("save.do")
	@ResponseBody
	public Result save(T t) throws Exception {
		try {
			
			Map<String,String> errors=new HashMap<String,String>();
			
			Field[] fields=t.getClass().getDeclaredFields();
			for (Field field : fields) {
				CFUniqueAnnotation cfa=field.getAnnotation(CFUniqueAnnotation.class);
				field.setAccessible(true);
				Object v=field.get(t);
				if(cfa!=null&&v!=null&&v.toString().length()>0){
					Map<String,String[]> param=new HashMap<String,String[]>();
					param.put("_:"+field.getType().getSimpleName()+":"+field.getName(), new String[]{v.toString()});
					if(t.getId()!=null&&t.getId().length()>0){
						param.put("!_:String:id", new String[]{t.getId()});
					}
					Cache.paramMapLocal.set(param);
					List<SuperModel> lst= (List<SuperModel>) this.baseService.find(clazz,null, null);
					if(lst.size()>0){
						errors.put(field.getName(), "内容重复");
					}
				}
			}
			
			if(errors.size()>0){
				return new Result(400, "操作失败","", errors);
			}

			if(!UserUtils.getUser().getLoginName().equalsIgnoreCase(Config.superAdminLoginName)) {
				t.setEditUser(UserUtils.getUser());
			}

			if (t.getId() != null && t.getId().trim() != "") {
				baseService.update(t);
			} else {

				if(!UserUtils.getUser().getLoginName().equalsIgnoreCase(Config.superAdminLoginName)) {
					t.setCreateUser(UserUtils.getUser());
				}
				baseService.add(t);
			}
			return new Result(200, "操作成功",null, null);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@RequestMapping("findById.do")
	@ResponseBody
	public T getById(T t) throws Exception {
		return (T) baseService.findById(t);
	}

	@RequestMapping("findPaging.do")
	@ResponseBody
	public Paging getPaging(@ModelAttribute("paging") Paging paging, HttpServletRequest req) throws Exception {

		paging.setRows(NumberUtils.toInt(req.getParameter("rows"), 10));
		Cache.paramMapLocal.set(req.getParameterMap());

		return baseService.findPaging(clazz,paging);
	}

	@RequestMapping("deleteByIds.do")
	@ResponseBody
	public Result deleteByIds(HttpServletRequest req) throws Exception {
		String ids[] = req.getParameterValues("id");
		try {
			baseService.deletes(clazz,ids);
			return new Result();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new Result(2120, "操作失败", ex.getMessage(),null);
		}
	}
	
	@RequestMapping("isNoExist.do")
	@ResponseBody
	public String isNonentity(HttpServletRequest req){
		Cache.paramMapLocal.set(req.getParameterMap());
		List<? extends Object> list=this.baseService.find(clazz,null,null);
		if(list==null||list.size()==0){
			return "true";
		}else{
			return "false";
		}
	}

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new SpecialDateEditor());
	}
}
