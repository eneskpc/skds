package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Company extends User {

	private String name;
	private String detail;
	private String contactName;
	private String contactPhone;
	private String imageUrl;
	private boolean approved;
	private int companyId;

	public Company(String detail, String contactName, String contactPhone, String imageUrl, boolean approved) {
		this.detail = detail;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.imageUrl = imageUrl;
		this.approved = approved;
	}

	public Company() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public boolean saveCompany() {
		if (this.saveUser()) {
			String sql2 = "INSERT INTO company(name,detail,contactName,contactPhone,imageUrl,approved,User_id) VALUES(?,?,?,?,?,'0',?)";
			PreparedStatement ps2;
			try {
				ps2 = MySQL.getConnection().prepareStatement(sql2);
				ps2.setString(1, this.getName());
				ps2.setString(2, this.getDetail());
				ps2.setString(3, this.getContactName());
				ps2.setString(4, this.getContactPhone());
				ps2.setString(5, this.getImageUrl());
				ps2.setInt(6, this.getId());
				if (ps2.executeUpdate() > 0)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static Company getCompany(int userId) throws SQLException {
		String sql = "SELECT user.id AS userId, user.email, user.password, company.id AS companyId, "
				+ "company.name, company.detail, company.contactName, company.contactPhone, "
				+ "company.imageUrl, company.approved FROM company INNER JOIN user "
				+ "On user.id=company.user_id WHERE user.id = ?";
		PreparedStatement pStatement = MySQL.getConnection().prepareStatement(sql);
		pStatement.setInt(1, userId);

		ResultSet result = pStatement.executeQuery();

		Company company = null;

		while (result.next()) {
			company = new Company();
			company.setId(result.getInt("userId"));
			company.setEmail(result.getString("email"));
			company.setPassword(result.getString("password"));
			company.setCompanyId(result.getInt("companyId"));
			company.setName(result.getString("name"));
			company.setDetail(result.getString("detail"));
			company.setContactName(result.getString("contactName"));
			company.setContactPhone(result.getString("contactPhone"));
			company.setImageUrl(result.getString("imageUrl"));
			company.setApproved(result.getBoolean("approved"));
		}

		return company;
	}

	public static ArrayList<Company> getCompanyList() throws SQLException {
		String sql = "SELECT user.id AS userId, company.name FROM company INNER JOIN user On user.id=company.user_id WHERE company.approved = true";
		Statement statement = MySQL.getConnection().createStatement();

		ResultSet result = statement.executeQuery(sql);

		ArrayList<Company> list = new ArrayList<>();

		while (result.next()) {
			Company company = new Company();
			company.setId(result.getInt("userId"));
			company.setName(result.getString("name"));
			list.add(company);
		}

		return list;
	}

	public static ArrayList<Company> getRandomCompany(int count) throws SQLException {
		String sql = "SELECT company.User_id AS userId, company.imageUrl, company.name "
				+ "FROM company WHERE approved=1 ORDER BY RAND() LIMIT "+count;
		
		Statement statement = MySQL.getConnection().createStatement();

		ResultSet result = statement.executeQuery(sql);

		ArrayList<Company> list = new ArrayList<>();

		while (result.next()) {
			Company company = new Company();
			company.setId(result.getInt("userId"));
			company.setName(result.getString("name"));
			company.setImageUrl(result.getString("imageUrl"));
			list.add(company);
		}

		return list;

	}

}
