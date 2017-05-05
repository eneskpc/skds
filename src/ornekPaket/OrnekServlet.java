package ornekPaket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Request;

public class OrnekServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ArrayList<Request> talepler = new ArrayList<>();
		
		talepler.add(new Request("Hello"));
		talepler.add(new Request("Naber"));
		talepler.add(new Request("Java"));
		talepler.add(new Request("Php"));
		talepler.add(new Request("Laravel"));
		
		req.setAttribute("talepler", talepler);
		//test
		//dispatcher nesnesini servlet context objesinden oluþturuyoruz.
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/test.jsp");
		dispatcher.forward(req, resp);
		//daha sonra request nesnesine eklediðimiz attribute ile birlikte req ve resp i jsp ye yönlendiriyoruz.
	}

}
