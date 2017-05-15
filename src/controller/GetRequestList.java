package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.GsonBuilder;

import model.Request;
import model.User;

public class GetRequestList extends HttpServlet {

	private static final long serialVersionUID = 2672378152867744328L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		ArrayList<Request> requestList = null;
		try {
			String userType = req.getParameter("userType");
			if(userType.equals("home")) {
				requestList = Request.getRequestList(-1);
			} else if(userType.equals("customer")) {
				if(session!=null) {
					User user = (User) req.getSession().getAttribute("LoggedUser");
					requestList = Request.getRequestList(user.getId());
				}
			}
			String json = new GsonBuilder().create().toJson(requestList);
			resp.getWriter().write(json);
			
		} catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
		}
		
	}

}
