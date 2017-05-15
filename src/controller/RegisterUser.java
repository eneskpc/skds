package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Company;
import model.Customer;

public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		if (request.getParameter("userType").equals("customer")) {
			if (request.getParameter("password").equals(request.getParameter("rePassword"))) {
				Customer c = new Customer();
				c.setName(request.getParameter("nameSurname"));
				c.setEmail(request.getParameter("email"));
				c.setPassword(request.getParameter("password"));
				c.setType(1);
				if (c.saveCustomer()) {
					response.getWriter().append("<div class='alert alert-success'>Kayıt İşlemi Başarılı</div>");
				} else {
					response.getWriter().append("<div class='alert alert-danger'>Kayıt İşlemi Başarısız</div>");
				}
			} else
				response.getWriter().append("<div class='alert alert-danger'>Şifreleriniz Uyuşmuyor</div>");
		} else if (request.getParameter("userType").equals("company")) {
			if (request.getParameter("password").equals(request.getParameter("passwordAgain"))) {
				Company c = new Company();
				c.setName(request.getParameter("companyName"));
				c.setContactName(request.getParameter("authNameSurname"));
				c.setEmail(request.getParameter("authEmail"));
				c.setPassword(request.getParameter("password"));
				c.setType(3);
				if (c.saveCompany()) {
					response.getWriter().append("<div class='alert alert-success'>Kayıt İşlemi Başarılı</div>");
				} else {
					response.getWriter().append("<div class='alert alert-danger'>Kayıt İşlemi Başarısız</div>");
				}
			} else
				response.getWriter().append("<div class='alert alert-danger'>Şifreleriniz Uyuşmuyor</div>");
		} else {
			response.getWriter().append("<div class='alert alert-danger'>Bilinmeyen kullanıcı tipi! : "
					+ request.getParameter("userType") + "</div>");
		}
	}

}
