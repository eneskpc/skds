package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
