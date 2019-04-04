package test1;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.NumberToTextConverter;





public class excel {
    
    public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
        
        InputStream inputStream = new FileInputStream("F:\\studn.xls");
        //InputStream inp = new FileInputStream("C:/Users/H__D/Desktop/workbook.xls");
        try {
			//readExcel(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
        	String one = formatter.formatCellValue(row.getCell(0));
        	String two = formatter.formatCellValue(row.getCell(1));
        	HashMap<String,String> stu = new HashMap<String,String>();
        	List strlist = new ArrayList();
        	stu.put("name",one);
        	stu.put("class_id",two);
        	
        	
        //	System.out.println(a+"和"+b);
        	
        	
        	
           /* for (Cell cell : row) {
            	int num =row.getRowNum();
            	if(num>=2){
            		
            		 CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                     //通过获取单元格值并应用任何数据格式（Date，0.00，1.23e9，$ 1.23等），获取单元格中显示的文本
                     String text = formatter.formatCellValue(cell);
                     System.out.println(num+"和"+text);
                     
            		
            	}
               
            }*/
        }
        
    }
    
  
    
}