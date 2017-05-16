package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Staff extends User {

	private String name;

	public Staff() {
		super();
	}

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}

	
	public static ArrayList<Staff> getStaffList(int companyId) throws SQLException {
		String sql = "SELECT staff.name, staff.Company_id AS companyId, staff.User_id AS userId,"
				+ " user.email FROM staff INNER JOIN user "
				+ "ON staff.User_id = user.id WHERE staff.Company_id = " + companyId;
		
		Statement statement = MySQL.getConnection().createStatement();
		
		ArrayList<Staff> staffList = new ArrayList<>();
		
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()) {
			Staff staff = new Staff();
			staff.setName(result.getString("name"));
			staff.setId(result.getInt("userId"));
			staff.setEmail(result.getString("email"));
			staffList.add(staff);
		}
		
		return staffList;
		
	}

	public static boolean removeStaff(int staffId) throws SQLException {
		String staffDeleteSql = "DELETE FROM staff WHERE staff.User_id ="+staffId;
		String userDeleteSql = "DELETE FROM user WHERE user.id = " + staffId;
		
		Statement statement = MySQL.getConnection().createStatement();
	
		if(statement.execute(staffDeleteSql)) 
			if(statement.execute(userDeleteSql))
				return true;
		return false;
	}

	public boolean createStaff(String email, int companyId) throws SQLException {
		String selectPersonalId = "SELECT user.id, customer.name FROM user INNER JOIN customer"
				+ " ON customer.User_id = user.id WHERE user.email = " + email;
		
		Statement statement = MySQL.getConnection().createStatement();
		
		ResultSet result = statement.executeQuery(selectPersonalId);
		
		while(result.next()) {
			int id = result.getInt("id");
			
		}
		return false;
		
	}
	
}