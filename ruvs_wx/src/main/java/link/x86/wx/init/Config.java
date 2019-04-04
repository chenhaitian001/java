package link.x86.wx.init;

import link.x86.util.PropertiesUtil;

/**
 * 配置文件
 */
public class Config {

    public static final String CONFIG_FILE_NAME = "app.properties";

    public static String appid;
    public static String appsecret;
    public static String token;
    public static String msgTemplateId;


    public static String CRONT_RELOADTOKEN = "0 0 * * * ?";

    public static String JMS_USER;
    public static String JMS_PASSWD;
    public static String JMS_URL;
    public static String JMS_QUEUENAME;


    public static String DB_DRIVER;
    public static String DB_URL;
    public static String DB_USER;
    public static String DB_PASSWD;



    public static void load() {


        appid = PropertiesUtil.get(CONFIG_FILE_NAME, "wx.appid");
        appsecret = PropertiesUtil.get(CONFIG_FILE_NAME, "wx.appsecret");
        CRONT_RELOADTOKEN = PropertiesUtil.get(CONFIG_FILE_NAME, "cront.reloadtoken");
        msgTemplateId=PropertiesUtil.get(CONFIG_FILE_NAME,"wx.msgTemplateId");

        token=PropertiesUtil.get(CONFIG_FILE_NAME,"wx.token");



        JMS_USER = PropertiesUtil.get(CONFIG_FILE_NAME, "jms.user");
        JMS_PASSWD = PropertiesUtil.get(CONFIG_FILE_NAME, "jms.passwd");
        JMS_URL = PropertiesUtil.get(CONFIG_FILE_NAME, "jms.url");
        JMS_QUEUENAME = PropertiesUtil.get(CONFIG_FILE_NAME, "jms.queuename");


        DB_DRIVER = PropertiesUtil.get(CONFIG_FILE_NAME, "db.driver");
        DB_URL = PropertiesUtil.get(CONFIG_FILE_NAME, "db.url");
        DB_USER = PropertiesUtil.get(CONFIG_FILE_NAME, "db.user");
        DB_PASSWD = PropertiesUtil.get(CONFIG_FILE_NAME, "db.passwd");


    }

    static {
        load();
    }
}
