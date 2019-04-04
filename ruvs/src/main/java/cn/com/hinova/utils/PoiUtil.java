package cn.com.hinova.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class PoiUtil {

    public void wireteExcel(List<Map<String,Object>> list, String[] titles,OutputStream outStream){
        HSSFWorkbook workbook = new HSSFWorkbook();
        //新建工作表
        HSSFSheet sheet = workbook.createSheet();
        //创建行,行号作为参数传递给createRow()方法,第一行从0开始计算
        HSSFRow row = sheet.createRow(0);
        //创建单元格,row已经确定了行号,列号作为参数传递给createCell(),第一列从0开始计算
        HSSFCell cell = row.createCell(2);
        //设置单元格的值,即C1的值(第一行,第三列)
        cell.setCellValue("hello sheet");
        //输出到磁盘中
//        FileOutputStream fos = new FileOutputStream(new File("E:\\root\\sheet\\11.xls"));
        try {
            workbook.write(outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
//                    workbook.close();
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
