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

public class GetStaffRequestList extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		try {
			if(session != null) {
				User user = (User) session.getAttribute("LoggedUser");
				ArrayList<Request> requestList = Request.getStaffRequestList(user.getId());
				Gson gson = new GsonBuilder().create();
				resp.getWriter().write(gson.toJson(requestList));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
}
