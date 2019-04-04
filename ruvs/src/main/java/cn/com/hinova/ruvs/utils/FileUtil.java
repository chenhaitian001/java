package cn.com.hinova.ruvs.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.jn.ssr.superrescue.annotation.Translate;

//import com.jn.ssr.superrescue.cache.DictCache;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    private static Logger log = LogManager.getLogger(FileUtil.class);

    public static final int BIG_DATA_EXPORT_MIN = 50000;

    public static final int BIG_DATA_EXPORT_MAX = 2000000;
    private final static String excel2003L = ".xls"; // 2003- 版本的excel  
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

    //excel处理注解set集合
    public static HashSet<String> transClassSet = new HashSet();

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, title, sheetName);
    }

    /**
     * 导出函数
     *
     * @param list      导出集合
     * @param title     标题
     * @param sheetName sheet名
     * @param pojoClass 映射实体
     * @param fileName  文件名
     * @param response  httpresponce
     *                  size如果过大 需采用poi SXSSF
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) {
        //判断该类是否已经处理过excel注解
        long startTime = System.currentTimeMillis();
        if (!transClassSet.contains(String.valueOf(pojoClass))) {
            initProperties(pojoClass);
            transClassSet.add(String.valueOf(pojoClass));
        }
        defaultExport(list, pojoClass, fileName, response, title, sheetName);
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, String title, String sheetName) {
        Workbook workbook = null;
        ExportParams exportParams = new ExportParams(title, sheetName);
        if (list != null && list.size() > BIG_DATA_EXPORT_MAX) {
            sizeBeyondError(response);
            return;
        } else if (list != null && list.size() > BIG_DATA_EXPORT_MIN) {/*
            log.info("文件过大采用大文件导出：" + list.size());
            for (int i = 0; i < (list.size() / BIG_DATA_EXPORT_MIN + 1) && list.size() > 0; i++) {
                log.info("当前切片：" + i * BIG_DATA_EXPORT_MIN + "-" + (i + 1) * BIG_DATA_EXPORT_MIN);
                List<?> update = list.stream().skip(i * BIG_DATA_EXPORT_MIN).limit(BIG_DATA_EXPORT_MIN).collect(Collectors.toList());
                exportParams.setCreateHeadRows(true);
                exportParams.setMaxNum(BIG_DATA_EXPORT_MIN * 2 + 2);
                workbook = ExcelExportUtil.exportBigExcel(exportParams, pojoClass, update);
            }
            ExcelExportUtil.closeExportBigExcel();
        */} else {
            workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), pojoClass, list);
        }
        if (workbook == null) return;
        downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // workbooks.forEach(e -> e.write(response.getOutputStream()));

            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            try {
                response.getWriter().println("{\"code\":597,\"message\":\"export error!\",\"data\":\"\"}");
                response.getWriter().flush();
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                closeIo(response);
            }
        }
    }


    /**
     * 文件过大，不允许导出
     *
     * @param response
     */
    private static void sizeBeyondError(HttpServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            response.getWriter().println("{\"code\":599,\"message\":\"文件过大!\",\"data\":\"\"}");
            response.getWriter().flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            closeIo(response);
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse
            response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, "HSSF");
        if (workbook != null) ;
        downLoadExcel(fileName, response, workbook);
    }

    public static <
            T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
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
            e.printStackTrace();
            System.out.println("模版为空");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static List readExcel(File file) throws IOException, SQLException, InvalidFormatException{
    	InputStream inputStream = new FileInputStream("F:\\studn.xls");
        
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();
        List<HashMap<String, String>> strlist = new ArrayList<HashMap<String, String>>();
        for (Row row : sheet) {
        	int num =row.getRowNum();
        	if(num>=2){
        		String one = formatter.formatCellValue(row.getCell(0));
            	String two = formatter.formatCellValue(row.getCell(1));
            	HashMap<String,String> stu = new HashMap<String,String>();
            	stu.put("name",one);
            	stu.put("class_id",two);
            	strlist.add(stu);
        	}
        }
		return strlist;
	}
    public static int excelForNull(int num,Row row,Cell cell) {
		for (int j = 0; j < row.getLastCellNum(); j++) {//判断非“常规”的格式，被POI认为是实际行，实际是空行
			cell = row.getCell(j);					
			if (FileUtil.isEmpty(cell)) {
				num++;
			}else{
				String str=cell.toString();
				if(FileUtil.isEmpty(str)){
					num++;
				}
			}
		  }
		return num;
	}
 // 判断是否为空。
 	public static boolean isEmpty(Object Value) {
 		if (Value == null || Value.equals(""))
 			return true;
 		else
 			return false;
 	}
    public static <
            T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
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
            e.printStackTrace();
            System.out.println("文件为空");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 代理初始化该类的注解
     *
     * @param cl
     */
  public synchronized static void initProperties(Class cl) {}

    /**
     * 关闭writer
     *
     * @param response
     */
    private static void closeIo(HttpServletResponse response) {
        try {
            if (response.getWriter() != null) {
                response.getWriter().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
 
		
			//获取变量
		  
			sheet = work.getSheetAt(0);  
			if (sheet == null) {  
				return list;
			}  
			rowone = sheet.getRow(1);
			if (rowone == null || rowone.getFirstCellNum() == 1) {  
				return list;
			}
			for (int y = rowone.getFirstCellNum(); y < rowone.getLastCellNum(); y++) { 
				ve.add(getCellValue(rowone.getCell(y)));
			}
			
			//封装map //从第二行开始
			for (int j = sheet.getFirstRowNum()+2; j < sheet.getLastRowNum() + 1; j++) { 
 
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


}