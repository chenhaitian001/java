package cn.com.hinova.ruvs.henuo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.NestableException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.hinova.ruvs.common.bean.Paging;
import cn.com.hinova.ruvs.henuo.entity.OutPutStudent;
import cn.com.hinova.ruvs.henuo.service.StudentService;
import cn.com.hinova.ruvs.utils.FileUtil;
import net.sf.json.JSONObject;


@Controller

@RequestMapping("config/input")
public class InputExcelController {
	
    //上传文件对象(和表单type=file的name值一致)
    private File upfile;
    
	public File getUpfile() {
		return upfile;
	}
	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}
    //上传类型
    private String uploadContentType;
	@Resource
	protected StudentService studentService;
	
	@RequestMapping("/inputExcel")
	public String input(){
		
		return   "config/input_excel";
	}
	/**
	 * 下载excel学生信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/downLoadEX")
	public void  downLoadEX(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/**
		 * 下载excel文件
		 * @param fileFullName  文件全名
		 * @param fileName      文件名
		 */
		String id1 = request.getParameter("p1");
		String id2 = request.getParameter("p2");
		String id3 = request.getParameter("p3");
		this.export(request,response);
		
		}
	/**
	 * 导入学生
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/Input")
	public String  InputEX(@RequestParam("upfile") MultipartFile upfile, HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject returnJSONObject = null;
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String uploadFileName = upfile.getOriginalFilename();
		try {
			if(upfile != null){
				String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."));
				//校验格式
				if(StringUtils.equalsIgnoreCase(suffix, ".xls") || StringUtils.equalsIgnoreCase(suffix, ".xlsx")){
					List listob  = FileUtil.getBankListByExcel(upfile.getInputStream(), upfile.getOriginalFilename());
					List fileList =studentService.fileList(listob);
					returnJSONObject =  new JSONObject(); 
					returnJSONObject.put("result", "success");
				}else{
					returnJSONObject =  new JSONObject();
					returnJSONObject.put("result", "fail");
					returnJSONObject.put("failreason", "文件类型不支持！");
				}
				
				
			}
			String retJsonStr = returnJSONObject.toString();
			out.print(retJsonStr);
		} catch (Exception e) {
			returnJSONObject =  new JSONObject();
			returnJSONObject.put("result", "fail");
			returnJSONObject.put("failreason", e.getMessage());
			out.print(returnJSONObject.toString());
		}
        return null;
	}
	/**
	 * 学生信息导入模板
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downLoadMODO")
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
	 * 导出
	 * @param req
	 * @param response
	 */
	public void export(HttpServletRequest req,HttpServletResponse response){
        String id1 = req.getParameter("p1");
		String id2 = req.getParameter("p2");
		String id3 = req.getParameter("p3");
        List<OutPutStudent> listtt= studentService.query(id1,id2,id3);
        FileUtil.exportExcel(listtt,"花名册","可爱的小朋友",OutPutStudent.class,"花名册.xls",response);
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
		public String getUploadContentType() {
			return uploadContentType;
		}
		public void setUploadContentType(String uploadContentType) {
			this.uploadContentType = uploadContentType;
		}
		
}