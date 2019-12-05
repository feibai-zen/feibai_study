package com.feibai.study.demos.jdbc.dao;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static String driver;
	private static String url;
	private static String name;
	private static String password;

	static {
		Properties prop = new Properties();
		try {
			Reader in = new FileReader("src" + File.separator + "config.properties");
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		name = prop.getProperty("name");
		password = prop.getProperty("password");
	}

	public static Connection open() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, name, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void close(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
