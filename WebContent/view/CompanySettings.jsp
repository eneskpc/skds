<%@page import="model.Staff"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Company"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="tr">
<head>
<% 
Company company=null;
ArrayList<Staff> staffList = null;
User loggedUser = (User) session.getAttribute("LoggedUser");
if(loggedUser!=null) {
	if(loggedUser.getType() == 3) {
		company = Company.getCompany(loggedUser.getId());
		staffList = Staff.getStaffList(loggedUser.getId());
	} else {
		response.sendRedirect("/");
	}
} else {
	response.sendRedirect("/");
}
%>
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
				<a class="navbar-brand" href="/">SKDS</a>
				<p class="navbar-text">Sorunlarınız Sorunlarımızdır</p>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#"
						class="dropdown-toggle btn btn-primary navbar-btn"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">WebMaster'a Yaz</a>
						<ul class="dropdown-menu">
							<li class="webmaster-chat">
								<div class="panel panel-default">
									<div class="panel-body msg_container_base">
										<div class="row msg_container base_sent">
											<div class="col-md-10 col-xs-10">
												<div class="messages msg_sent">
													<p>Lorem ipsum dolor sit amet, consectetur adipiscing
														elit. Morbi tempor erat ac arcu bibendum, sit amet euismod
														felis porta</p>
													<time datetime="2009-11-13T20:00">Yurtiçi Kargo •
														Mesut • 51 dk</time>
												</div>
											</div>
											<div class="col-md-2 col-xs-2 avatar">
												<img src="assets/images/indir.svg" class=" img-responsive ">
											</div>
										</div>
										<div class="row msg_container base_receive">
											<div class="col-md-2 col-xs-2 avatar">
												<img src="assets/images/indir.svg" class=" img-responsive ">
											</div>
											<div class="col-md-10 col-xs-10">
												<div class="messages msg_receive">
													<p>Lorem ipsum dolor sit amet, consectetur adipiscing
														elit. Morbi tempor erat ac arcu bibendum, sit amet euismod
														felis porta</p>
													<time datetime="2009-11-13T20:00">Timothy • 51 dk</time>
												</div>
											</div>
										</div>
									</div>
									<div class="panel-footer">
										<div class="input-group">
											<input id="btn-input" type="text"
												class="form-control input-sm chat_input"
												placeholder="Buraya mesajını yaz..." /> <span
												class="input-group-btn">
												<button class="btn btn-primary btn-sm" id="btn-chat">Gönder</button>
											</span>
										</div>
									</div>
								</div>
							</li>
						</ul></li>
					<li class="dropdown"><a href="#"
						class="dropdown-toggle btn btn-info navbar-btn"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Hoşgeldin <%= company!= null ? company.getContactName():"" %> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/companyManager"><i class="fa fa-cog" aria-hidden="true"></i>
									Taleplerim</a></li>
							<li><a href=""><i class="fa fa-cog" aria-hidden="true"></i>
									Hesabım</a></li>
							<li><a href=""><i class="fa fa-sign-out"
									aria-hidden="true"></i> Çıkış</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row profile">
			<div class="col-md-3">
				<div class="profile-sidebar">
					<div class="profile-userpic">
						<img src="assets/images/indir.svg" class="img-responsive" alt="">
					</div>
					<div class="profile-usertitle">
						<div class="profile-usertitle-name"><%= company!= null ? company.getContactName() : "" %></div>
						<div class="profile-usertitle-job">Şirket Yetkilisi</div>
					</div>
					<div class="profile-usermenu">
						<ul class="nav">
							<li role="presentation" class="active"><a
								href="#companyInfo" aria-controls="companyInfo" role="tab"
								data-toggle="tab">Firma Bilgileri</a></li>
							<li role="presentation"><a href="#personalInfo"
								aria-controls="personalInfo" role="tab" data-toggle="tab">Personel
									Bilgileri</a></li>
							<li role="presentation"><a href="#userSettings"
								aria-controls="userSettings" role="tab" data-toggle="tab">Kullanıcı
									İşlemleri</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="profile-content">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="companyInfo">
							<h2>Firma Bilgileri</h2>
							<form action="" class="form">
								<div class="form-group">
									<label>Firma Adı</label> <input type="text" value="<%= company!= null ? company.getName() : "" %>"
										class="form-control"
										placeholder="Maksimum 50 karakter olacak şekilde girin" />
								</div>
								<div class="form-group">
									<label>Firma Açıklaması</label>
									<textarea class="form-control" placeholder="İsteğe bağlı"><%
									if(company!=null) {
										if(company.getDetail() != null) {
											out.print(company.getDetail());
										}
									}
									%></textarea>
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-info" value="Kaydet" />
								</div>
							</form>
						</div>
						<div role="tabpanel" class="tab-pane" id="personalInfo">
							<h2>Personel Bigileri</h2>
							<a href="#" class="btn btn-info pull-right add-personal"><i
								class="fa fa-user"></i> Personel Ekle</a>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Adı Soyadı</th>
										<th>Email Adresi</th>
										<th style="text-align: right">Silme İşlemi</th>
									</tr>
								</thead>
								<tbody>
								<%
								if(staffList != null)
									for(Staff staff : staffList) { %>
									<tr>
										<td><%=staff.getName() %></td>
										<td><%= staff.getEmail() %></td>
										<td align="right"><a href="" onclick="removeStaff(<%=staff.getId() %>)"
											class="btn btn-danger btn-xs"> <i class="fa fa-trash-o"></i>
										</a></td>
									</tr>
									<%} %>
								</tbody>
							</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="userSettings">
							<h2>Kullanıcı İşlemleri</h2>
							<form action="" class="form">
								<div class="form-group">
									<label>Email Adresi</label> <input type="text"
										class="form-control" value="<%= company!= null ? company.getEmail() : ""%>"
										placeholder="Maksimum 50 karakter olacak şekilde girin" />
								</div>
								<div class="form-group">
									<label>Parola</label> <input type="password"
										class="form-control"
										placeholder="Minimum 6, maksimum 10 karakter olmalıdır." />
								</div>
								<div class="form-group">
									<label>Parola Tekrar</label> <input type="password"
										class="form-control"
										placeholder="Yukarıdaki 'Parola' alanı ile aynı olmalıdır." />
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-info" value="Kaydet" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="container">
			&copy; 2017 All rights is <a href="http://nku.edu.tr">SKDS</a>.
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
	<script src="assets/js/tinymce/jquery.tinymce.min.js"></script>
	<script src="assets/js/script.js"></script>
</body>
</html>