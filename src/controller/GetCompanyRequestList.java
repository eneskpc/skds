package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Request;
import model.User;

public class GetCompanyRequestList extends HttpServlet {
	
	private static final long serialVersionUID = -5189958106236288981L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		
		try {
			if(session != null) {
				User user = (User) session.getAttribute("LoggedUser");
				System.out.println(user.getId());
				ArrayList<Request> requestList = Request.getCompanyRequestList(user.getId());
				Gson gson = new GsonBuilder().create();
				resp.getWriter().write(gson.toJson(requestList));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		

}
