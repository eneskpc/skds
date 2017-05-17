package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customer extends User {

	private String name;
	private String gender;
	private int homeCity;
	private int birthYear;

	public Customer() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(int homeCity) {
		this.homeCity = homeCity;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	public static ArrayList<Customer> getCustomers() {
		try {
			String sql2 = "SELECT user.id as userID,user.email as userEmail,user.password as userPass,customer.*  FROM user INNER JOIN customer ON customer.User_id = user.id";
			PreparedStatement ps2 = MySQL.getConnection().prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			
			ArrayList<Customer> customers = new ArrayList<Customer>();
			
			while(rs.next()){
				Customer c = new Customer();
				c.setId(rs.getInt("userID"));
				c.setEmail(rs.getString("userEmail"));
				c.setPassword(rs.getString("userPass"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setBirthYear(rs.getInt("birthYear"));
				c.setHomeCity(rs.getInt("homeCity"));
				customers.add(c);
			}
			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Customer> getCustomers(String filter) {
		try {
			String sql2 = "SELECT user.id as userID,user.email as userEmail,user.password as userPass,customer.*  FROM user INNER JOIN customer ON customer.User_id = user.id WHERE user.email LIKE '%?%' OR customer.name LIKE '%?%'";
			PreparedStatement ps2 = MySQL.getConnection().prepareStatement(sql2);
			ps2.setString(1, filter);
			ps2.setString(2, filter);
			ResultSet rs = ps2.executeQuery();
			
			ArrayList<Customer> customers = new ArrayList<Customer>();
			
			while(rs.next()){
				Customer c = new Customer();
				c.setId(rs.getInt("userID"));
				c.setEmail(rs.getString("userEmail"));
				c.setPassword(rs.getString("userPass"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setBirthYear(rs.getInt("birthYear"));
				c.setHomeCity(rs.getInt("homeCity"));
				customers.add(c);
			}
			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Customer getCustomer(int cID) {
		try {
			String sql2 = "SELECT user.id as userID,user.email as userEmail,user.password as userPass,customer.*  FROM user INNER JOIN customer ON customer.User_id = user.id WHERE user.id = ?";
			PreparedStatement ps2 = MySQL.getConnection().prepareStatement(sql2);
			ps2.setInt(1, cID);
			ResultSet rs = ps2.executeQuery();
			Customer c = null;
			if(rs.next()){
				c = new Customer();
				c.setId(rs.getInt("userID"));
				c.setEmail(rs.getString("userEmail"));
				c.setPassword(rs.getString("userPass"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setBirthYear(rs.getInt("birthYear"));
				c.setHomeCity(rs.getInt("homeCity"));
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean saveCustomer() {
		if (this.saveUser()) {
			String sql2 = "INSERT INTO customer(name,gender,birthYear,homeCity,User_id) VALUES(?,?,?,?,?)";
			PreparedStatement ps2;
			try {
				ps2 = MySQL.getConnection().prepareStatement(sql2);
				ps2.setString(1, this.getName());
				ps2.setString(2, this.getGender());
				ps2.setInt(3, this.getBirthYear());
				ps2.setInt(4, this.getHomeCity());
				ps2.setInt(5, this.getId());
				if (ps2.executeUpdate() > 0)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
