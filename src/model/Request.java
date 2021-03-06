package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Request {

	private int id;
	private String title;
	private String detail;
	private String date;
	private Company company;
	private Customer customer;
	private Staff staff;
	private int responseCount;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date2) {
		this.date = date2;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getResponseCount() {
		return responseCount;
	}

	public void setResponseCount(int responseCount) {
		this.responseCount = responseCount;
	}

	/**
	 * CustomerId var ise company'nin taleplerini getirir. customerId Negatif
	 * veya 0 için tüm talepleri getirir.
	 * 
	 * @param customerId
	 * @return ArrayList<Request>
	 * @throws SQLException
	 */

	public static Request getRequest(int rID) {
		try {
			String sql2 = "SELECT request.id AS requestId, request.title, request.date, request.detail,"
					+ "request.Company_id AS companyId, request.Customer_id AS customerId, "
					+ "customer.name AS customerName, company.name AS companyName, company.imageUrl "
					+ "FROM request INNER JOIN customer ON request.Customer_id = customer.User_id "
					+ "INNER JOIN company ON request.Company_id = company.User_id WHERE request.id = ?";
			PreparedStatement ps2 = MySQL.getConnection().prepareStatement(sql2);
			ps2.setInt(1, rID);
			ResultSet rs = ps2.executeQuery();

			Request r = null;
			if (rs.next()) {
				r = new Request();
				r.setCompany(Company.getCompany(rs.getInt("companyId")));
				r.setCustomer(Customer.getCustomer(rs.getInt("customerId")));
				r.setDate(new SimpleDateFormat("dd.MM.yyyy").format(rs.getDate("date")));
				r.setDetail(rs.getString("detail"));
				r.setId(rID);
				r.setTitle(rs.getString("title"));
			}
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Request> getRequestList(int customerId) throws SQLException {
		ArrayList<Request> list = new ArrayList<>();

		String sql = "SELECT request.id AS requestId, request.title, request.date, request.detail,"
				+ "request.Company_id AS companyId, request.Customer_id AS customerId, "
				+ "customer.name AS customerName, company.name AS companyName, company.imageUrl "
				+ "FROM request INNER JOIN customer ON request.Customer_id = customer.User_id "
				+ "INNER JOIN company ON request.Company_id = company.User_id";

		if (customerId > 0) {
			sql += " WHERE request.Customer_id = " + customerId;
		}

		Statement statement = MySQL.getConnection().createStatement();

		ResultSet result = statement.executeQuery(sql);

		while (result.next()) {
			Request request = new Request();
			request.setId(result.getInt("requestId"));
			request.setTitle(result.getString("title"));
			request.setDate(new SimpleDateFormat("dd.MM.yyyy").format(result.getDate("date")));
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
	 * Talebi veritabanına kaydederse true kaydetmezse false döndürür.
	 * 
	 * @return stateTrueOrFalse
	 * @throws SQLException
	 */
	public boolean createRequest() throws SQLException {
		String sql = "INSERT INTO request (title, detail, Company_id, Customer_id) VALUES (?,?,?,?)";
		PreparedStatement pStatement = MySQL.getConnection().prepareStatement(sql);
		pStatement.setString(1, this.title);
		pStatement.setString(2, this.detail);
		pStatement.setInt(3, this.company.getId());
		pStatement.setInt(4, this.customer.getId());

		if (pStatement.executeUpdate() > 0)
			return true;
		return false;
	}

	public static ArrayList<Request> getCompanyRequestList(int companyId) throws SQLException {
		String sql = "SELECT request.id , request.title, request.date, staff.name AS staffName"
				+ " FROM request LEFT JOIN staff ON staff.User_id = request.Staff_id WHERE request.Company_id ="
				+ companyId;
		ArrayList<Request> requestList = new ArrayList<>();

		Statement statement = MySQL.getConnection().createStatement();

		ResultSet result = statement.executeQuery(sql);

		while (result.next()) {
			Request request = new Request();
			request.setId(result.getInt("id"));
			request.setTitle(result.getString("title"));
			Staff staff = new Staff();
			String staffName = result.getString("staffName");
			if(staffName == null)
				staff.setName("Atanmamış");
			else
				staff.setName(staffName);
			request.setStaff(staff);
			request.setDate(new SimpleDateFormat("dd.MM.yyyy").format(result.getDate("date")));
			ArrayList<Response> responseList = Response.getResponses(request.getId());
			if (responseList != null)
				request.setResponseCount(responseList.size());
			requestList.add(request);
		}

		return requestList;

	}
	
	
	public static ArrayList<Request> getStaffRequestList(int staffId) throws SQLException {
		String sql = "SELECT request.id , request.title, request.date, customer.name"
				+ " from request INNER JOIN customer ON customer.User_id = request.Customer_id where request.Staff_id ="+staffId;
		ArrayList<Request> requestList = new ArrayList<>();

		Statement statement = MySQL.getConnection().createStatement();

		ResultSet result = statement.executeQuery(sql);

		while (result.next()) {
			Request request = new Request();
			request.setId(result.getInt("id"));
			request.setTitle(result.getString("title"));
			Customer customer = new Customer();
			customer.setName(result.getString("name"));
			request.setCustomer(customer);
			request.setDate(new SimpleDateFormat("dd.MM.yyyy").format(result.getDate("date")));
			ArrayList<Response> responseList = Response.getResponses(request.getId());
			if (responseList != null)
				request.setResponseCount(responseList.size());
			requestList.add(request);
		}

		return requestList;

	}
	
	public static boolean assignStaff(int staffId, int reqId){
		try {
			String sql = "UPDATE request SET Staff_id = ? WHERE id = ?";
			PreparedStatement pStatement = MySQL.getConnection().prepareStatement(sql);
			pStatement.setInt(1, staffId);
			pStatement.setInt(2, reqId);

			if (pStatement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
