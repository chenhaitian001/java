package test1;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.io.File;
import java.util.Properties;

public class ExportDB {

    public static void main(String[] args) {
        // 读取配置文件
        File file=new File("src/main/resources/hibernate.cfg.xml");
        System.out.println("file.exists()+\"  \"+file.getAbsoluteFile().getAbsolutePath() = " + file.exists()+"  "+file.getAbsoluteFile().getAbsolutePath());
        Configuration cfg = new Configuration().configure();
        // 创建SchemaExport对象
        SchemaExport export = new SchemaExport(cfg);
        // 创建数据库表
        export.create(true, true);
    }
}
