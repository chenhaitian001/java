package com.hinova.controller.childmanagercontroller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hinova.entity.OutPutStudent;
import com.hinova.entity.PageBean;
import com.hinova.entity.Patriarch;
import com.hinova.entity.Student;
import com.hinova.entity.User;
import com.hinova.service.ChildrenService;
import com.hinova.util.FileUtil;
import com.hinova.util.ResponseUtil;



@Controller
@RequestMapping("/chileren")
public class StudentManagerController {
	 private static Logger logger = LoggerFactory.getLogger(StudentManagerController.class);

	@Resource
	private ChildrenService childrenService;
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("date1", date1);
		map.put("date2", date2);
		
		List<Student> list = childrenService.listStudent(map);
		Integer total = childrenService.getStudentTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/leavelist")
	public String listLeave(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("date1", date1);
		map.put("date2", date2);
		
		List<Student> list = childrenService.listLeaveStudent(map);
		Integer total = childrenService.getStudentTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/student/add")
	public ModelAndView student_add_orupdate() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/chileren/student/new_add.do");
		
		mav.setViewName("/admin/page/user/student_add_or_update");
		return mav;
	}
	@RequestMapping("/student/new_add")
	public String add(Student student, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		student.setCreateUser(currentUser.getName());
		int resultTotal =0;
		try {
			resultTotal = childrenService.addStudent(student);
		} catch (Exception e) {	
			logger.info("添加学生失败");
		}
		
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
			result.put("msg", "添加成功");
		} else {
			result.put("success", false);
			result.put("msg", "添加失败");
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	@RequestMapping("/student/edit")
	public ModelAndView edit_student(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Student student=  childrenService.findByStudentId(id);
	//	User user = userService.findById(Integer.parseInt(id));
		
		mav.addObject("student", student);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/chileren/student/new_update?id="+id);
		
		mav.setViewName("/admin/page/user/student_update");
		return mav;
	}
	@RequestMapping("/student/new_update")
	public String newupdate(Student student, @RequestParam(value="id",required=false)String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		student.setEditUser(currentUser.getName());//编辑人
		int resultTotal =0;
		try {
			resultTotal = childrenService.updateStudent(student);
		} catch (Exception e) {
			logger.info("编辑失败");
		}
		
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
			result.put("msg", "编辑成功");
		} else {
			result.put("success", false);
			result.put("msg", "编辑失败");
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	@RequestMapping("/student/delete")
	public String deleteStudent(Student student, @RequestParam(value="ids",required=false)String ids,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			try {
				childrenService.delete_student(idsStr[i]);
				result.put("success", true);
				result.put("msg", "删除成功");
			} catch (Exception e) {
				logger.info("删除学生失败");
				result.put("success", false);
				result.put("msg", "删除失败，学生被占用");
			}
			
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	/**
	 * 批量升班
	 * @param student
	 * @param ids
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/student/upClazz")
	public String upClazz(Student student, @RequestParam(value="ids",required=false)String ids,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			try {
				childrenService.upClazz(idsStr);
				result.put("success", true);
				result.put("msg", "删除成功");
			} catch (Exception e) {
				logger.info("升班失败");
				result.put("success", false);
				result.put("msg", "升班失败，请重新操作");
			}
			
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	@RequestMapping("/student/downMobel")
	public void downMobel(HttpServletRequest request, HttpServletResponse response){
		String fileName = "TemPlet.xls";
		String fileFullName = request.getServletContext().getRealPath("/upload/"+fileName);
		String contentType = "application/vnd.ms-excel";
		File file = null;
		BufferedInputStream stream = null;
		FileInputStream fis = null;
		
		if(fileFullName == null || "".equals(fileFullName)) return;
		
		try {
			file = new File(fileFullName);
			fis = new FileInputStream(file);			
			stream = new BufferedInputStream(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        try {
        	
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso-8859-1"));
            copy(stream, response.getOutputStream());
        }catch(Exception e){
        	e.printStackTrace();
        }
        finally {
            if (stream != null) {
            	try{
            		stream.close();
            	}catch(Exception ex){
            		ex.printStackTrace();
            	}
            }
            
            if (fis != null) {
            	try{
            		fis.close();
            	}catch(Exception ex){
            		ex.printStackTrace();
            	}
            }
        }
	}
	/**
	 * 导入学生
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/student/input")
	public String  InputEX(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject returnJSONObject = null;
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String uploadFileName = file.getOriginalFilename();
		try {
			if(file != null){
				String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."));
				//校验格式
				if(StringUtils.equalsIgnoreCase(suffix, ".xls") || StringUtils.equalsIgnoreCase(suffix, ".xlsx")){
					List listob  = FileUtil.getBankListByExcel(file.getInputStream(), file.getOriginalFilename());
					childrenService.fileList(listob);
					returnJSONObject =  new JSONObject(); 
					returnJSONObject.put("code", "0");
				}else{
					returnJSONObject =  new JSONObject();
					returnJSONObject.put("code", "1");
					returnJSONObject.put("failreason", "文件类型不支持！");
				}
				
				
			}
			String retJsonStr = returnJSONObject.toString();
			out.print(retJsonStr);
		} catch (Exception e) {
			returnJSONObject =  new JSONObject();
			returnJSONObject.put("code", "1");
			returnJSONObject.put("failreason", e.getMessage());
			out.print(returnJSONObject.toString());
		}
        return null;
	}
	/**
	 * 导出
	 * @param req
	 * @param response
	 */
	@RequestMapping("/student/export")
	public void export(HttpServletRequest req,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
        List<OutPutStudent> listtt= childrenService.exportStudent(map);
        
        FileUtil.exportExcel(listtt, "学生名单", "Welcom", OutPutStudent.class, "学生名单.xls", true, response);
     //   FileUtil.exportExcel(listtt,"花名册","可爱的小朋友",Student.class,"花名册.xls",response);
    }
	/**
	 * 流拷贝
	 * @param input
	 * @param output
	 * @return
	 * @throws IOException
	 */
	private static int copy(InputStream input, OutputStream output)throws IOException {
		byte[] buffer = new byte[1024 * 5];
		int count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		output.flush();
		return count;
	}
	@RequestMapping("/patriarch_list")
	public String patriarch_list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "date1", required = false) String date1, 
			@RequestParam(value = "date2", required = false) String date2, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("date1", date1);
		map.put("date2", date2);
		
		List<Patriarch> list = childrenService.listPatriarch(map);
		Integer total = childrenService.getPatriarchTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	@RequestMapping("/patriarch/add")
	public ModelAndView patriarch_add_orupdate() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/chileren/patriarch/new_add.do");
		
		mav.setViewName("/admin/page/user/patriarch_add_or_update");
		return mav;
	}
	@RequestMapping("/patriarch/new_add")
	public String add(Patriarch patriarch, HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		patriarch.setCreateUser(currentUser.getName());
		int resultTotal =0;
		try {
			resultTotal = childrenService.addPatriarch(patriarch);
		} catch (Exception e) {
			logger.info("添加年级失败");
		}
		
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
			result.put("msg", "添加成功");
		} else {
			result.put("success", false);
			result.put("msg", "添加失败");
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	@RequestMapping("/patriarch/edit")
	public ModelAndView edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Patriarch patriarch=  childrenService.findByPatriarchId(id);
	//	User user = userService.findById(Integer.parseInt(id));
		Student student = patriarch.getStudent();
		mav.addObject("student",student);
		mav.addObject("patriarch", patriarch);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/chileren/patriarch/new_update?id="+id);
		
		mav.setViewName("/admin/page/user/patriarch_add_or_update");
		return mav;
	}
	@RequestMapping("/patriarch/new_update")
	public String newupdate(Patriarch patriarch, @RequestParam(value="id",required=false)String id,HttpServletResponse response, HttpServletRequest request) throws Exception {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		patriarch.setEditUser(currentUser.getName());//编辑人
		patriarch.setId(id);
		int resultTotal =0;
		try {
			resultTotal = childrenService.updatePatriarch(patriarch);
		} catch (Exception e) {
			logger.info("编辑家长失败");
		}
		
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
			result.put("msg", "编辑成功");
		} else {
			result.put("success", false);
			result.put("msg", "编辑失败");
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
	@RequestMapping("/patriarch/delete")
	public String deletePatriarch(Patriarch patriarch, @RequestParam(value="ids",required=false)String ids,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			try {
				childrenService.delete_Patriarch(idsStr[i]);
				result.put("success", true);
				result.put("msg", "删除成功");
			} catch (Exception e) {
				logger.info("删除年级失败");
				result.put("success", false);
				result.put("msg", "删除失败，家长被占用");
			}
			
		}
		ResponseUtil.write(response, result.toString());
		return null;
	}
}
