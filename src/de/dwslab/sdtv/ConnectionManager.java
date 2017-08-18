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
        		connection = DriverManager.getConnection("jdbc:h2:file:/home/soheil/SDTypeH2/sd-type-validate/sdtv", "sa", "");
			//connection = DriverManager.getConnection("jdbc:h2:file:///C:/Users/rosha/OneDrive/Documents/db", "sa", "");
		} catch (SQLException e) {
			System.out.println("Error: could not initialize database");
		}
        return connection;
	}
}
