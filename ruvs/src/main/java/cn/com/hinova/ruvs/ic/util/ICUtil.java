package cn.com.hinova.ruvs.ic.util;

import cn.com.hinova.ruvs.utils.DateUtils;
import cn.com.hinova.utils.DateUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ICUtil {

    public static Map<String,String> line2map(String line, String[] fieldKeys){

        for (String key:fieldKeys) {
            if(line.indexOf(key)==-1){
                return null;
            }
        }

        String[] linearr=line.split("\" +");

        Map<String,String> map=new HashMap<String,String>();
        for (String field:linearr) {
            String[] fieldarr=field.split("=");
            map.put(fieldarr[0],fieldarr[1].replace("\"",""));
        }
        return map;

    }



    public static byte[] parserPhoto(String photoString){

        return Base64.decodeBase64(photoString);
    }


    public static String savePhoto(byte[] photo,String dirPath){
        FileOutputStream fout=null;

        String photomd5=DigestUtils.md5Hex(photo);

        String dirName=DateUtils.date2str(new Date(), DateUtils.FORMAT_YD);


        String fileName=photomd5+".jpg";

        String filePath=dirName+"/"+fileName;

        File file=new File(dirPath+"/"+filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        try{
            fout=new FileOutputStream(file);
            fout.write(photo);
            fout.flush();
            fout.close();
            return filePath;
        }catch (Exception ex){

        }finally {
            try {
                fout.close();
            } catch (IOException e) {
            }
        }
        return null;
    }


    public static String savePhoto(String photoString,String dirPath){


        return savePhoto(parserPhoto(photoString),dirPath);
    }
}
