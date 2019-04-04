package cn.com.hinova.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	
	
	private static String driver,url,userName,password;

	static{
		InputStream inStream=null;
		
		try {
			inStream=DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties p=new Properties();
			p.load(inStream);
			driver=p.getProperty("jdbc.driverClassName");
			url=p.getProperty("jdbc.url");
			userName=p.getProperty("jdbc.username");
			password=p.getProperty("jdbc.password");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				inStream.close();
			} catch (IOException e) {
			}
		}
	}

	public static Connection createConnection() {

		try {
			Class.forName(driver);

			return DriverManager.getConnection(url, userName, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void close(Object... objs) {
		for (Object obj : objs) {
			if (obj instanceof AutoCloseable) {
				AutoCloseable autoClose = (AutoCloseable) obj;
				try {
					autoClose.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
