package com.hinova.util;
 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
 
/**
 * @Auther: lijiawei
 * @Date: 2018/7/23 12:08
 * @Description: Excle 文件导入导出Util(easypoi)
 */
public class FileUtil {
	private final static String excel2003L = ".xls"; // 2003- 版本的excel  
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
	/**  
	 * 描述：获取IO流中的数据，组装成List<List<Object>>对象  
	 *   
	 * @param in,fileName  
	 * @return  
	 * @throws IOException  
	 */  
	@SuppressWarnings("unused")
	public static List getBankListByExcel(InputStream in, String fileName) throws Exception {  
		List<List<HashMap<String, String>>> list = new LinkedList<List<HashMap<String, String>>>();
		List ve = new ArrayList();
		
		HashMap<String,String> map = new HashMap<String,String>();
		// 创建Excel工作薄  
		Workbook work = getWorkbook(in, fileName);  
		if (null == work) {  
			throw new Exception("创建Excel工作薄为空！");  
		}  
		Sheet sheet = null;  
		Row row = null;  
		Row rowone = null;  
		Cell cell = null;  
 
		
			
		  	//获取第一页
			sheet = work.getSheetAt(0);  
			if (sheet == null) {  
				return list;
			}  
			
			//获取行
			rowone = sheet.getRow(2);
			if (rowone == null || rowone.getFirstCellNum() == 1) {  
				return list;
			}
			for (int y = rowone.getFirstCellNum(); y < rowone.getLastCellNum(); y++) { 
				ve.add(getCellValue(rowone.getCell(y)));
			}
			
			//封装map //从第三行开始
			for (int j = sheet.getFirstRowNum()+3; j < sheet.getLastRowNum() + 1; j++) { 
 
				row = sheet.getRow(j);  
				//getFirstRowNum/getFirstCellNum-->从0开始取
				//去除第一行row.getFirstCellNum() == j == 0
				if (row == null || row.getFirstCellNum() == j) {  
					continue;  
				}  
 
				// 遍历所有的列  
				List li = new ArrayList();  
				
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) { 
					//第一行为变量
					HashMap<String,String> veMap= new HashMap<String,String>();
					cell = row.getCell(y); 
					veMap.put(ve.get(y).toString(), getCellValue(cell).toString());
					
					
					li.add(veMap);  
				}  
				list.add(li);  
			}  
		  
		return list;  
	}
	/**  
	 * 描述：根据文件后缀，自适应上传文件的版本  
	 *   
	 * @param inStr,fileName  
	 * @return  
	 * @throws Exception  
	 */  
	public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {  
		Workbook wb = null;  
		String fileType = fileName.substring(fileName.lastIndexOf("."));  
		if (excel2003L.equals(fileType)) {  
			wb = new HSSFWorkbook(inStr); // 2003-  
		} else if (excel2007U.equals(fileType)) {  
			wb = new XSSFWorkbook(inStr); // 2007+  
		} else {  
			throw new Exception("解析的文件格式有误！");  
		}  
		return wb;  
	}
	/**  
	 * 描述：对表格中数值进行格式化  
	 *   
	 * @param cell  
	 * @return  
	 */  
	public static Object getCellValue(Cell cell) {  
		Object value = null;  
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符  
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化  
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字  
		
		//System.out.println("cell="+cell);
		
		if(cell==null){
			return "";
		}
		switch (cell.getCellType()) {  
		case Cell.CELL_TYPE_STRING:  
			value = cell.getRichStringCellValue().getString();  
			break;  
		case Cell.CELL_TYPE_NUMERIC:  
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {  
				value = df.format(cell.getNumericCellValue());  
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {  
				value = sdf.format(cell.getDateCellValue());  
			} else {  
				value = df2.format(cell.getNumericCellValue());  
			}  
			break;  
		case Cell.CELL_TYPE_BOOLEAN:  
			value = cell.getBooleanCellValue();  
			break;  
		case Cell.CELL_TYPE_BLANK:  
			value = "";  
			break;  
		default:  
			break;  
		}  
		return value;  
	}  
   /**
    * 功能描述：复杂导出Excel，包括文件名以及表名。创建表头
    *
    * @author 陈海天
    * @date 2018/7/23 13:07
    * @param list 导出的实体类
    * @param title 表头名称
    * @param sheetName sheet表名
    * @param pojoClass 映射的实体类
    * @param isCreateHeader 是否创建表头
    * @param fileName
    * @param response
    * @return 
   */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }
 
 
    /**
     * 功能描述：复杂导出Excel，包括文件名以及表名,不创建表头
     *
     * @author 陈海天
     * @date 2018/7/23 13:07
     * @param list 导出的实体类
     * @param title 表头名称
     * @param sheetName sheet表名
     * @param pojoClass 映射的实体类
     * @param fileName
     * @param response
     * @return
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }
 
    /**
     * 功能描述：Map 集合导出
     *
     * @author 陈海天
     * @date 2018/7/23 16:14
     * @param list 实体集合
     * @param fileName 导出的文件名称
     * @param response
     * @return
    */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }
 
    /**
     * 功能描述：默认导出方法
     *
     * @author 陈海天
     * @date 2018/7/23 15:33
     * @param list 导出的实体集合
     * @param fileName 导出的文件名
     * @param pojoClass pojo实体
     * @param exportParams ExportParams封装实体
     * @param response
     * @return
     */
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }
 
    /**
     * 功能描述：Excel导出
     *
     * @author 陈海天
     * @date 2018/7/23 15:35
     * @param fileName 文件名称
     * @param response
     * @param workbook Excel对象
     * @return
    */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new  RuntimeException(e);
        }
    }
 
    /**
     * 功能描述：默认导出方法
     *
     * @author 陈海天
     * @date 2018/7/23 15:33
     * @param list 导出的实体集合
     * @param fileName 导出的文件名
     * @param response
     * @return
    */
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) ;
        downLoadExcel(fileName, response, workbook);
    }
    
    
    /**
     * 功能描述：根据文件路径来导入Excel
     *
     * @author 陈海天
     * @date 2018/7/23 14:17 
     * @param filePath 文件路径
     * @param titleRows 表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass Excel实体类
     * @return
    */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        //判断文件是否存在
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
 
        }
        return list;
    }
 
    /**
     * 功能描述：根据接收的Excel文件来导入Excel,并封装成实体类
     *
     * @author 陈海天
     * @date 2018/7/23 14:17
     * @param file 上传的文件
     * @param titleRows 表标题的行数
     * @param headerRows 表头行数
     * @param pojoClass Excel实体类
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
 
        }
        return list;
    }
 
 
}
