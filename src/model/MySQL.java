package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	
	private static MySQL mysql = new MySQL();
	
	Connection conn = null;
	final String USER = "username";
	final String PASS = "password";
	final String DB_URL = "jdbc:mysql://localhost/EMP";

	private MySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MySQL getConnection() {
		return mysql;
	}
	
}
