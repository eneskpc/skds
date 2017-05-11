package model;

import java.util.ArrayList;

public class Request {
	
	private String detail;
	private Company company;

	public Request(String detail) {
		super();
		this.detail = detail;
	}

	public String getDetail() {
		return detail;
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

	public static ArrayList<Request> getRequestList() {
		ArrayList<Request> list = new ArrayList<>();
		
		return null;
		
	}
	
	
	

}
