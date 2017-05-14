package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	
	private static MySQL mysql = new MySQL();
	private Connection conn = null;

	private MySQL() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/skds?useLegacyDatetimeCode=false&serverTimezone=UTC"
					+ "&useSSL=true&user=root&password=&");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return mysql.conn;
	}
	
}
