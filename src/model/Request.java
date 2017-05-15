package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Request {

	private int id;
	private String title;
	private String detail;
	private Date date;
	private User company;
	private User customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public static ArrayList<Request> getRequestList() throws SQLException {
		ArrayList<Request> list = new ArrayList<>();

		String sql = "SELECT request.id AS requestId, request.title, request.date, request.detail, "
				+ "request.Company_id AS companyId, request.Customer_id AS customerId, "
				+ "customer.name AS customerName, company.name AS companyName, company.imageUrl "
				+ "FROM request INNER JOIN customer ON request.Customer_id = customer.User_id "
				+ "INNER JOIN company ON request.Company_id = request.Company_id";

		Statement statement = MySQL.getConnection().createStatement();

		ResultSet result = statement.executeQuery(sql);

		while (result.next()) {
			Request request = new Request();
			request.setId(result.getInt("requestId"));
			request.setTitle(result.getString("title"));
			request.setDate(result.getDate("date"));
			request.setDetail(result.getString("detail"));
			Company company = new Company();
			company.setId(result.getInt("companyId"));
			company.setName(result.getString("companyName"));
			company.setImageUrl(result.getString("imageUrl"));
			Customer customer = new Customer();
			customer.setId(result.getInt("customerId"));
			customer.setName(result.getString("customerName"));

			request.setCompany(company);
			request.setCustomer(customer);

			list.add(request);
		}

		return list;

	}

	/**
	 * Talebi ekler ve veritabanında etkilenen satır sayısını döndürür.
	 * 
	 * @param request
	 * @return etkilenenSatırSayısı
	 * @throws SQLException
	 */
	public boolean createRequest() throws SQLException {
		String sql = "INSERT INTO request (title, detail, Company_id, Customer_id) VALUES (?,?,?,?)";
		PreparedStatement pStatement = MySQL.getConnection().prepareStatement(sql);
		pStatement.setString(1, this.title);
		pStatement.setString(2, this.detail);
		pStatement.setInt(3, this.company.getId());
		pStatement.setInt(4, this.customer.getId());

		if(pStatement.executeUpdate() == 1)
			return true;
		return false;
	}

	
}
