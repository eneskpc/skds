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
	private int isCompanyRead;
	private int isStaffRead;
	// Company ekranı için cevaplandı mı bilgisinin alacağımız yer

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

	public void setDate(String date) {
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

	public int getIsCompanyRead() {
		return isCompanyRead;
	}

	public void setIsCompanyRead(int isCompanyRead) {
		this.isCompanyRead = isCompanyRead;
	}

	public int getIsStaffRead() {
		return isStaffRead;
	}

	public void setIsStaffRead(int isStaffRead) {
		this.isStaffRead = isStaffRead;
	}

	/**
	 * CustomerId var ise company'nin taleplerini getirir. customerId Negatif
	 * veya 0 için tüm talepleri getirir.
	 * 
	 * @param customerId
	 * @return ArrayList<Request>
	 * @throws SQLException
	 */
	public static ArrayList<Request> getRequestList(int customerId) throws SQLException {
		ArrayList<Request> list = new ArrayList<>();
		
		String sql = "SELECT request.id AS requestId, request.title, request.date, request.detail,"
				+ "request.Company_id AS companyId, request.Customer_id AS customerId, "
				+"customer.name AS customerName, company.name AS companyName, company.imageUrl "
				+"FROM request INNER JOIN customer ON request.Customer_id = customer.User_id "
				+"INNER JOIN company ON request.Company_id = company.User_id";

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

		if (pStatement.executeUpdate() == 1)
			return true;
		return false;
	}

	public static ArrayList<Request> getCompanyRequestList(int companyId) throws SQLException {
		String sql = "SELECT request.id , request.title, staff.name AS staffName, request.date, "
				+ "request.IsCompanyRead, count(DISTINCT response.Request_id) AS response "
				+ "FROM request INNER JOIN staff ON " + "staff.User_id = request.Staff_id INNER JOIN response ON"
				+ " response.Request_id = request.id WHERE request.Company_id = " + companyId;
		ArrayList<Request> requestList = new ArrayList<>();

		Statement statement = MySQL.getConnection().createStatement();

		ResultSet result = statement.executeQuery(sql);

		while (result.next()) {
			Request request = new Request();
			request.setId(result.getInt("id"));
			request.setTitle(result.getString("title"));
			Staff staff = new Staff();
			staff.setName(result.getString("staffName"));
			request.setStaff(staff);
			request.setDate(new SimpleDateFormat("dd.MM.yyyy").format(result.getDate("date")));
			request.setIsCompanyRead(result.getInt("isCompanyRead"));
			request.setResponseCount(result.getInt("response"));
		}

		return requestList;

	}
}
