package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	/**
	 * Verilen id ile kayýtlý þirket bilgisini döndürür.
	 * Þirket yok ise null döndürür.
	 * 
	 * @param companyId
	 * @return Company
	 * @throws SQLException
	 */
	public static Company getCompany(int userId) throws SQLException {
		String sql = "SELECT user.id AS userId, user.email, user.password, company.id AS companyId, "
				+ "company.name, company.detail, company.contactName, company.contactPhone, "
				+ "company.imageUrl, company.approved FROM company INNER JOIN user "
				+ "On user.id=company.user_id WHERE user.id = ?";
		PreparedStatement pStatement = MySQL.getConnection().prepareStatement(sql);
		pStatement.setInt(1, userId);
		
		ResultSet result = pStatement.executeQuery();
		
		Company company = null;
		
		while(result.next()) {
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

	public static Company createCompany(Company company) throws SQLException {
		
		
		
		
		/*String sql = "INSERT INTO company(name,detail,contactName,contactPhone,imageUrl,approved,User_id)"
				+ "VALUES(?,?,?,?,?,?,?)";*/
		
		
		return company;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", detail=" + detail + ", contactName=" + contactName + ", contactPhone="
				+ contactPhone + ", imageUrl=" + imageUrl + ", approved=" + approved + ", companyId=" + companyId + "]";
	}
	
	
	
}
