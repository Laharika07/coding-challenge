package com.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws IOException, SQLException {
		
		if(connection != null) {
			
			return connection;
		}
		
		else {
			
			String conString = PropertyUtil.getPropertyString("src/main/resources/mysql.properties");
            connection = DriverManager.getConnection(conString);
			return connection;
		}
		
		
	}
	
	

}
