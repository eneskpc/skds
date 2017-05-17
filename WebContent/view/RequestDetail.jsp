<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Company"%>
<%@page import="model.Customer"%>
<%@page import="model.Staff"%>
<%@page import="model.Request"%>
<%@page import="model.Response"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%
	User loggedUser = (User) session.getAttribute("LoggedUser");
	Request rInfo = (Request) request.getAttribute("requestInfo");
	ArrayList<Response> arrayR = (ArrayList<Response>) request.getAttribute("arrayR");
	ArrayList<Staff> arraySt = (ArrayList<Staff>) request.getAttribute("arraySt");
	ArrayList<Company> companyList = Company.getRandomCompany(4);
%>
<!doctype html>
<html lang="tr">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Hoşgeldiniz | Son Kullanıcı Destek Sistemi - Sorunlarınız
	Sorunlarımızdır</title>
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">SKDS</a>
				<p class="navbar-text">Sorunlarınız Sorunlarımızdır</p>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">

					<%
						if (loggedUser == null) {
					%>
					<li><a class="btn btn-primary navbar-btn login">Giriş Yap</a></li>
					<li><a class="btn btn-info navbar-btn register">Kayıt Ol</a></li>
					<%
						} else {
					%>
					<%
						if (loggedUser.getType() == 1) {
					%>
					<li><a href="#" class="create-request">Şikayette Bulunun !</a></li>
					<%
						}
					%>
					<li class="dropdown"><a href="#"
						class="dropdown-toggle btn btn-info navbar-btn"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><%=loggedUser.getEmail()%> <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<%
								if (loggedUser.getType() == 1) {
							%>
							<li><a href="/customerManager"><i class="fa fa-cog"
									aria-hidden="true"></i> Taleplerim</a></li>
							<li><a href="/customerSettings"><i class="fa fa-cog"
									aria-hidden="true"></i> Hesabım</a></li>
							<%
								} else if (loggedUser.getType() == 2) {
							%>

							<li><a href="/personelManager"><i class="fa fa-cog"
									aria-hidden="true"></i> Taleplerim</a></li>
							<li><a href="/personalSettings"><i class="fa fa-cog"
									aria-hidden="true"></i> Hesabım</a></li>
							<%
								} else if (loggedUser.getType() == 3) {
							%>

							<li><a href="/companyManager"><i class="fa fa-cog"
									aria-hidden="true"></i> Taleplerim</a></li>
							<li><a href="/companySettings"><i class="fa fa-cog"
									aria-hidden="true"></i> Hesabım</a></li>
							<%
								}
							%>
							<li><a href="/logout"><i class="fa fa-sign-out"
									aria-hidden="true"></i> Çıkış</a></li>
						</ul></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<%
					if (rInfo != null) {
				%>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="media">
							<div class="media-left">
								<img class="media-object" src="assets/images/indir.svg"
									alt="Şirket Adı">
							</div>
							<div class="media-body">
								<a class="media-heading"><%=rInfo.getTitle()%></a>
								<p>
									<i class="fa fa-user"></i>&nbsp;<span><%=rInfo.getCustomer().getName()%></span>
									tarafından oluşturuldu.<br /> <i class="fa fa-clock-o"></i>&nbsp;<span><%=rInfo.getDate()%></span>
									tarihinde oluşturuldu.
								</p>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<%=rInfo.getDetail()%>
					</div>
					<div class="panel-footer">
						<%
							if (arrayR != null) {
						%>
						<i class="fa fa-comment-o"></i>&nbsp;<span>Toplamda <%=arrayR.size()%>
							adet cevap bulunmaktadır.
						</span>
						<%
							} else {
						%>
						<i class="fa fa-comment-o"></i>&nbsp;<span>Henüz cevap
							verilmedi.</span>
						<%
							}
						%>

					</div>
				</div>
				<%
					}
				%>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4 class="panel-title">Cevaplar</h4>
					</div>
					<div class="panel-body">
						<%
							if (loggedUser != null && rInfo != null && arraySt != null) {
								if (rInfo.getCustomer().getId() == loggedUser.getId()
										|| rInfo.getCompany().getId() == loggedUser.getId()
										|| rInfo.getStaff().getId() == loggedUser.getId()) {
									if (request.getAttribute("rStatus") != null)
										out.print(request.getAttribute("rStatus"));
									if (rInfo.getCompany().getId() == loggedUser.getId()) {
						%>
						<form class="form" method="post">
							<input type="hidden" name="rId" value="<%=rInfo.getId()%>" />
							<div class="form-group">
								<select name="personelId" class="form-control"
									onchange="this.form.submit()">
									<option value="0">Bir personel seçin veya taleplere
										siz cevap verin. İsterseniz daha sonra değiştirebilirsiniz.</option>
									<%
										if (arraySt != null)
														for (Staff s : arraySt) {
									%>
									<option value="<%=s.getId()%>"><%=s.getName()%></option>
									<%
										}
									%>
								</select>
							</div>
						</form>
						<%
							}
						%>
						<form class="form" method="post">
							<input type="hidden" name="rId" value="<%=rInfo.getId()%>" />
							<div class="form-group">
								<textarea class="form-control" name="comment"
									placeholder="Minimum 10 karakter ile cevap ekleyebilirsiniz."></textarea>
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-info pull-right"
									value="Cevabı Gönder">
							</div>
						</form>
						<%
							}
							}
						%>
						<div class="clearfix"></div>
						<%
							if (arrayR != null) {
								for (Response r : arrayR) {
						%>
						<div class="media">
							<div class="media-left">
								<img class="media-object" src="assets/images/indir.svg"
									alt="<%=r.getUser().getEmail()%>" />
							</div>
							<div class="media-body">
								<a class="media-heading"><%=r.getUser().getEmail()%></a>
								<p><%=r.getMessage()%></p>
							</div>
						</div>
						<%
							}
							} else {
						%><br>
						<div class="alert alert-info">Bir cevap verildiğinde, bu
							alanda görebileceksiniz.</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">Bizimle Çalışan Bazı Markalar</div>
					</div>
					<ul class="list-group ">
						<%
							for (Company company : companyList) {
						%>
						<li class="list-group-item company-item">
							<div class="media">
								<div class="media-left">
									<img class="media-object" src="assets/images/indir.svg"
										alt="Şirket Adı">
								</div>
								<div class="media-body">
									<a class="media-heading"><%=company.getName()%></a>
								</div>
							</div>
						</li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="container">
			&copy; 2017 All rights is <a href="http://nku.edu.tr">NKU</a>.
		</div>
	</div>
	<div class="modal fade" id="mainModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content"></div>
		</div>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/script.js"></script>
</body>
</html>