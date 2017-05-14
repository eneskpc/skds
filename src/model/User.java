package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

	private int id;
	private String email;
	private String password;
	private int type;

	public User() {

	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean saveUser() throws SQLException {
		String sql = "INSERT INTO user(email,password,type) VALUES(?,?,?)";
		PreparedStatement ps = MySQL.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, this.getEmail());
		ps.setString(2, this.getPassword());
		ps.setInt(3, this.getType());
		int lastInsertID = 0;
		if (ps.executeUpdate() > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				lastInsertID = rs.getInt(1);
			}
			this.setId(lastInsertID);
			return true;
		}
		return false;
	}
}
