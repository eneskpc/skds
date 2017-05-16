package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Company;

public class GetCompanyList extends HttpServlet {

	private static final long serialVersionUID = 662279495144329082L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		try {
			resp.setContentType("text/html;charset=UTF-8");
			ArrayList<Company> companyList = Company.getCompanyList();
			Gson gson = new GsonBuilder().create();
			resp.getWriter().write(gson.toJson(companyList));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
