package com.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {

	private static String URL;
	private static String USER;
	private static String PASSWORD;
	
	static {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			URL = props.getProperty("database.url");
			USER = props.getProperty("database.user");
			PASSWORD = props.getProperty("database.password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ConnectionManager() {}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
