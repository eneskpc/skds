package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Request;
import model.Response;
import model.Staff;
import model.User;

@WebServlet("/GetRequest")
public class GetRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetRequest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		if (Integer.parseInt(request.getParameter("id")) > 0) {
			Request r = Request.getRequest(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("requestInfo", r);
				System.out.println("İd : " + Integer.parseInt(request.getParameter("id")));
			ArrayList<Response> arrayR = Response.getResponses(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("arrayR", arrayR);
			
			HttpSession session = request.getSession();
			
			try {
				User user = (User)session.getAttribute("LoggedUser");
				if(user != null) {
					if(user.getType() == 3) {
						ArrayList<Staff> arraySt = Staff.getStaffList(user.getId());
						request.setAttribute("arraySt", arraySt);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/RequestDetail.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().append("Bu adres geçerli değil.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("personelId") != null) {
			int personalId = Integer.parseInt(request.getParameter("personelId"));
			int reqId = Integer.parseInt(request.getParameter("rId"));
			if (Request.assignStaff(personalId, reqId)) {
				request.setAttribute("rStatus",
						"<div class='alert alert-success'>Personel Değiştirildi!<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button></div>");
			} else {
				request.setAttribute("rStatus",
						"<div class='alert alert-danger'>Personel Değiştirilmedi. Sistem yöneticisi ile görüşün. Çözüm sürecine kadar talep ile siz ilgilenebilirsiniz.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button></div>");
			}
		}
		if (request.getParameter("comment") != null) {
			HttpSession session = request.getSession();
			Response r = new Response();
			r.setMessage(request.getParameter("comment"));
			r.setRequest(Request.getRequest(Integer.parseInt(request.getParameter("rId"))));
			r.setUser((User) session.getAttribute("LoggedUser"));
			if (r.setResponse()) {
				request.setAttribute("rStatus",
						"<div class='alert alert-success'>Yanıtınız Alındı!<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button></div>");
			} else {
				request.setAttribute("rStatus",
						"<div class='alert alert-danger'>Yanıtınız veritabanına kaydedilmedi. Sistem yöneticisi ile görüşün.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button></div>");
			}
			doGet(request, response);
		}
	}

}
