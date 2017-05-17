package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Response {
	private int id;
	private Response previous;
	private String message;
	private Request request;
	private User user;

	public Response() {
		// // It is for create inheritance
	}

	public Response(int id, Response previous, String message, Request request, User user) {
		super();
		this.id = id;
		this.previous = previous;
		this.message = message;
		this.request = request;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Response getPrevious() {
		return previous;
	}

	public void setPrevious(Response previous) {
		this.previous = previous;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static Response getResponse(int rID) {
		try {
			String sql2 = "SELECT response.id AS responseId,response.message,response.previous,response.isRead,request.id AS requestId,"
					+ "user.id AS userId,user.email AS userEmail,user.type as userType"
					+ " FROM response INNER JOIN request ON request.id = response.Request_id"
					+ " INNER JOIN user ON user.id = response.User_id WHERE response.id=?";
			PreparedStatement ps2 = MySQL.getConnection().prepareStatement(sql2);
			ps2.setInt(1, rID);
			ResultSet rs = ps2.executeQuery();

			Response r = null;
			if (rs.next()) {
				r = new Response();
				r.setId(rs.getInt("responseId"));
				r.setMessage(rs.getString("message"));
				r.setPrevious(Response.getResponse(rs.getInt("previous")));
				r.setRequest(Request.getRequest(rs.getInt("requestId")));
				if (rs.getInt("userType") == 3)
					r.setUser(Company.getCompany(rs.getInt("userId")));
				else if (rs.getInt("userType") == 1)
					r.setUser(Customer.getCustomer(rs.getInt("userId")));
			}
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Response> getResponses(int reqId) {
		try {
			String sql2 = "SELECT response.id AS responseId,response.message,response.previous,response.isRead,request.id AS requestId,user.id AS userId,user.email AS userEmail,user.type as userType FROM response INNER JOIN request ON request.id = response.Request_id INNER JOIN user ON user.id = response.User_id WHERE request.id=?";
			PreparedStatement ps2 = MySQL.getConnection().prepareStatement(sql2);
			ps2.setInt(1, reqId);
			ResultSet rs = ps2.executeQuery();
			
			System.out.println(reqId);

			ArrayList<Response> arrayR = new ArrayList<Response>();

			while (rs.next()) {
				Response r = new Response();
				r.setId(rs.getInt("responseId"));
				r.setMessage(rs.getString("message"));
				r.setPrevious(Response.getResponse(rs.getInt("previous")));
				r.setRequest(Request.getRequest(rs.getInt("requestId")));
				if (rs.getInt("userType") == 3)
					r.setUser(Company.getCompany(rs.getInt("userId")));
				else if (rs.getInt("userType") == 1)
					r.setUser(Customer.getCustomer(rs.getInt("userId")));
				arrayR.add(r);
			}
			return arrayR;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean setResponse() {
		try {
			String sql2 = "INSERT INTO response(message,previous,Request_id,User_id,isRead) VALUES(?,0,?,?,0)";
			PreparedStatement ps2 = MySQL.getConnection().prepareStatement(sql2);
			ps2.setString(1, this.getMessage());
			ps2.setInt(2, this.getRequest().getId());
			ps2.setInt(3, this.getUser().getId());

			if (ps2.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
