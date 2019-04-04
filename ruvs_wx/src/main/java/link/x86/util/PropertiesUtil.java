package link.x86.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * properties配置读取工具包
 */
public class PropertiesUtil {


    private static Map<String,Properties> ps=new HashMap<String,Properties>();

    public static String get(String filename,String pk){

        Properties p=ps.get(filename);
        if(p==null){
            InputStream inputStream=null;
            p=new Properties();
            try {
                inputStream=PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
                p.load(inputStream);
                ps.put(filename,p);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(p!=null){
            return p.getProperty(pk);
        }else{
            return null;
        }


    }


}
