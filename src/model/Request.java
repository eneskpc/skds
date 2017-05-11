package model;

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
	private Company company;
	private Customer customer;


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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
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
		
		while(result.next()) {
			Request request = new Request();
			request.setId(result.getInt("resultId"));
			request.setTitle(result.getString("title"));
			request.setDate(result.getDate("date"));
			request.setDetail("detail");
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

}
