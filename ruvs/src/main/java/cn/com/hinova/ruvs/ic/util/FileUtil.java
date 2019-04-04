package cn.com.hinova.ruvs.ic.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

public class FileUtil {


    /**
     * 目录中的，小于某一个时间的，删除
     * @param filePath
     * @param lastTime
     */
    public static void delteOldFiles(String filePath, final long lastTime){


        File fileDir =new File(filePath);
        if(fileDir.exists()&&fileDir.isDirectory()){
            File[] files=fileDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {

                    if(pathname.lastModified()<lastTime){
                        return true;
                    }else {
                        return false;
                    }
                }
            });

            for (File ftmp:files) {
                deleteFile(ftmp);
            }
        }

    }


    public static void deleteFile(File file){


        if(file.exists()){
            if(file.isDirectory()) {
                for (File ftmp : file.listFiles()) {
                    deleteFile(ftmp);
                }
            }

            file.delete();
        }


    }
}
