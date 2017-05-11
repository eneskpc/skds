package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;

import model.Request;

public class GetRequestList extends HttpServlet {
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		try {
			ArrayList<Request> requestList = Request.getRequestList();
			String json = new GsonBuilder().create().toJson(requestList);
			resp.getWriter().write(json);
		} catch (SQLException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}

}
