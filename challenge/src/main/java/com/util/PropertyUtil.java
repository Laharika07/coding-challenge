package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	
	public static String getPropertyString(String fileName) throws IOException{
		
		 Properties p = new Properties();
	        FileInputStream f = new FileInputStream(fileName);
	        p.load(f);
	        f.close();

	        String hostname = p.getProperty("hostname");
	        String dbname = p.getProperty("dbname");
	        String username = p.getProperty("username");
	        String password = p.getProperty("password");
	        String port = p.getProperty("port");
	        
	        return "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;	
	}

}
