package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Request;
import model.Response;

@WebServlet("/GetRequest")
public class GetRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetRequest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (Integer.parseInt(request.getParameter("id"))>0) {
			Request r = Request.getRequest(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("requestInfo", r);
			
			ArrayList<Response> arrayR = Response.getResponses(Integer.parseInt(request.getParameter("id")));
			System.out.println(arrayR);
			request.setAttribute("arrayR", arrayR);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/RequestDetail.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().append("Bu adres geçerli değil.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
