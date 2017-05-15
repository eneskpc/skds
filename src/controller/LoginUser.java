package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;


public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginUser() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getParameter("email").equals("") && !request.getParameter("password").equals("")) {
			User user = User.ValidationUser(request.getParameter("email"), request.getParameter("password"));
			if (user == null) {
				response.getWriter().append("false");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("LoggedUser", user);
				String userType = (user.getType() == 1 ? "customer"
						: (user.getType() == 2 ? "personal" : (user.getType() == 3 ? "company" : "webmaster")));
				response.getWriter().append(userType);
			}
		} else {
			response.getWriter().append("false");
		}
	}

}
