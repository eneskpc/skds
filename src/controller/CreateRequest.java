package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Request;
import model.User;

public class CreateRequest extends HttpServlet {

	private static final long serialVersionUID = 531455948948776642L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("LoggedUser");
		
		resp.setCharacterEncoding("UTF-8");;
		
		if(user != null) {
			Request request = new Request();
			request.setTitle(req.getParameter("rTitle"));
			request.setDetail(req.getParameter("rDetail"));
			Company comp = new Company();
			comp.setId(Integer.parseInt(req.getParameter("company")));
			request.setCompany(comp);
			request.setCustomer(user);
			try {
				request.createRequest();
				resp.getWriter().append("<div class='alert alert-success'>Ýþlem Baþarýlý</div>");
			} catch (SQLException e) {
				resp.getWriter().append("<div class='alert alert-success'>Ýþlem Baþarýsýz</div>");
				e.printStackTrace();
			}
		}
		
	}
	
}
