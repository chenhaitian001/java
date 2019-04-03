package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;

 
public class base64Util {
	public static void main(String[] args)
    {   
		File file=new File("d:\\222.jpg");
        String strImg = GetImageStr(file);//图片转成base64字符串
        System.out.print(strImg);
        //File file2=new File("d:\\222.jpg");//这是输出的图片和路径
        //GenerateImage(strImg,file2);//base64字符串转成图片
    }
	
	
	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: 
	 * @CreateTime: 
	 * @return
	 */
	public static String getImageBase64String(String imgFile) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(imgFile);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	    // 加密
	    return  Base64.encodeBase64String(data);
	}
	
	
	
	//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    public static String GetImageStr(File file)
    {
        //String imgFile = "d:\\111.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try 
        {
            in = new FileInputStream(file);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
       // BASE64Encoder encoder = new BASE64Encoder();
       // return encoder.encode(data);//返回Base64编码过的字节数组字符串
        return Base64.encodeBase64String(data);//返回Base64编码过的字节数组字符串
    }
    
    
    //对字节数组字符串进行Base64解码并生成图片
    public static boolean GenerateImage(String imgStr,File file)
    {
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try 
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            //String imgFilePath = "d:\\222.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(file);    
            out.write(b);
            out.flush();
            out.close();
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

}
