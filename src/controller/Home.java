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

@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Home() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Request> talepler = new ArrayList<>();
		
		talepler.add(new Request("Hello"));
		talepler.add(new Request("Naber"));
		talepler.add(new Request("Java"));
		talepler.add(new Request("Php"));
		talepler.add(new Request("Laravel"));
		
		request.setAttribute("talepler", talepler);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/Home.jsp");/**/
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
