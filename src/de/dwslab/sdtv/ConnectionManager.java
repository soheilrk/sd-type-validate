package de.dwslab.sdtv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for connection management
 * @author Heiko
 *
 */
public class ConnectionManager {
	private static Connection connection = null;
	
	public static synchronized Connection getConnection() {
		if(connection!=null)
			return connection;
		try {
		//	Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: could not load H2 driver");
		}
        try {
        	//connection = DriverManager.getConnection("jdbc:mysql://localhost/sdtv", "root", "1234");
        		String path = System.getProperty("user.dir");
        		System.out.println(path);
        		connection = DriverManager.getConnection("jdbc:h2:file:path", "sa", "");
			//connection = DriverManager.getConnection("jdbc:h2:file:///C:/Users/rosha/OneDrive/Documents/db", "sa", "");
			//connection = DriverManager.getConnection("jdbc:h2:file:path", "sa", "");

		} catch (SQLException e) {
			System.out.println("Error: could not initialize database");
		}
        return connection;
	}
}
