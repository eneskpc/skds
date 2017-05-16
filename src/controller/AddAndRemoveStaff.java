package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Staff;
import model.User;

public class AddAndRemoveStaff extends HttpServlet {

	private static final long serialVersionUID = 1611970596814823708L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operation = req.getParameter("operation");
		if(operation.equals("remove")) {
			int staffId = Integer.parseInt(req.getParameter("staffId"));
			try {
				boolean state = Staff.removeStaff(staffId);
				if(state) {
					resp.getWriter().write("Kaldırma İşlemi Başarılı.");
				} else {
					resp.getWriter().write("Kaldırma Başarısız.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(operation.equals("add")) {
			User loggedUser = (User) req.getSession().getAttribute("LoggedUser");
			String email = req.getParameter("staffEmail");
			try {
				boolean state = Staff.addStaff(email, loggedUser.getId());
				System.out.println(state);
				System.out.println(email);
				if(state) {
					resp.getWriter().write(email+" personel olarak atandı!");
				} else {
					resp.getWriter().write("Bu email personel olarak atanamaz!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
