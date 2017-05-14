package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
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
				try {
					try {
						if (c.saveCustomer()) {
							response.getWriter().append("<div class='alert alert-success'>Kayıt İşlemi Başarılı</div>");
						} else {
							response.getWriter().append("<div class='alert alert-danger'>Kayıt İşlemi Başarısız</div>");
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					response.getWriter()
							.append("<div class='alert alert-danger'>"
									+ "Kullanıcı kayıt işleminde bilinmeyen bir hata oluştu. "
									+ "Bu <b>hata</b>, veritabanının olağandışı durum biriminde yakalandı. "
									+ "Bu birimin bildirdiği hata ise :<br/>" + "<b>" + e.getMessage() + "</b>"
									+ " Üyelik işlemini tekrar deneyin veya site yönetimi le iletişime geçin.<br /></div>");
				}
			} else
				response.getWriter().append("<div class='alert alert-danger'>Şifreleriniz Uyuşmuyor.</div>");
		} else if (request.getParameter("userType").equals("company")) {
		} else {
			response.getWriter().append("<div class='alert alert-danger'>Bilinmeyen kullanıcı tipi! : "
					+ request.getParameter("userType") + "</div>");
		}
	}

}
