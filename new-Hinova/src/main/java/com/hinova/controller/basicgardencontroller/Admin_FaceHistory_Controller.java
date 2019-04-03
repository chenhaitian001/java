package com.hinova.controller.basicgardencontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hinova.entity.FaceHistory;
import com.hinova.entity.PageBean;
import com.hinova.service.FaceHistoryService;
import com.hinova.util.ResponseUtil;

@Controller
@RequestMapping("/device/facehistory")
public class Admin_FaceHistory_Controller {
	@Resource
	private FaceHistoryService faceHistoryService;
	
	@RequestMapping("/firstpage")
	public ModelAndView firstPage(){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login/firstpage");
		return mv;
	}
	/**
	 * 访客记录
	 * @param page
	 * @param rows
	 * @param date1
	 * @param date2
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list_face")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "timeBtn1", required = false) String timeBtn1, 
			@RequestParam(value = "timeBtn2", required = false) String timeBtn2, 
			@RequestParam(value = "comoncode", required = false) String comoncode, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("date1", timeBtn1);
		map.put("date2", timeBtn2);
		map.put("code", comoncode);
	//	List<Clazz> list1 = faceHistoryService.listClass(map);
	//	list1.get(0).g
	//	List<Grade> list2 = faceHistoryService.listGrade(map);
		
		List<FaceHistory> list = faceHistoryService.listRecord(map);
		Integer total = faceHistoryService.getRecordTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		System.out.println(gson.toJson(map));
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
}
