package cn.com.hinova.ruvs.utils;

import java.io.*;

public class SuperAdminPasswordUtils {



    private static InputStream inStream=null;
    private static OutputStream outStream=null;

    private static String superPassword=null;

    public static String getSuperPassword(){

        if(superPassword==null) {
            try {
                inStream = new FileInputStream(SuperAdminPasswordUtils.class.getClassLoader().getResource("superinfo.txt").getPath());
                byte[] buffer = new byte[1024];
                int size = inStream.read(buffer);
                if(size>0) {
                    superPassword = new String(buffer, 0, size);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return superPassword;
    }


    public static void setSuperPassword(String superPassword){

        try {
            outStream = new FileOutputStream(SuperAdminPasswordUtils.class.getClassLoader().getResource("superinfo.txt").getPath());
            outStream.write(superPassword.getBytes());
            SuperAdminPasswordUtils.superPassword=superPassword;
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



}
