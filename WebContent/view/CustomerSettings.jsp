<%@page import="model.Customer"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="tr">
<head>
<%
	Customer customer = null;
	User loggedUser = (User)session.getAttribute("LoggedUser");
	if(loggedUser== null) {
		response.sendRedirect("/");
	} else {
		if(loggedUser.getType() != 1) {
			response.sendRedirect("/");
		} else {
			customer = Customer.getCustomer(loggedUser.getId());
		}
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
					<li><a href="#" class="create-request">Şikayette Bulunun !</a></li>
					
					<li class="dropdown"><a href="#"
						class="dropdown-toggle btn btn-info navbar-btn"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Hoşgeldin, {User} <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/customerManager"><i class="fa fa-cog" aria-hidden="true"></i>
									Taleplerim</a></li>
							<li><a href=""><i class="fa fa-cog" aria-hidden="true"></i>
									Hesabım</a></li>
							<li><a href="/logout"><i class="fa fa-sign-out"
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
					<div class="profile-usertitle">
						<div class="profile-usertitle-name"><%= customer != null ? customer.getName() : "" %></div>
						<div class="profile-usertitle-job">Müşteri</div>
					</div>
					<div class="profile-usermenu">
						<ul class="nav">
							<li role="presentation" class="active"><a
								href="#personalInfo" aria-controls="personalInfo" role="tab"
								data-toggle="tab">Kişisel Bilgiler</a></li>
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
						<div role="tabpanel" class="tab-pane active" id="personalInfo">
							<h2>Kişisel Bilgiler</h2>
							<form action="" class="form">
								<div class="form-group">
									<label>Adı Soyadı</label> <input type="text"
										class="form-control" value="<%= customer != null ? customer.getName() : "" %>"
										placeholder="Maksimum 50 karakter olacak şekilde girin" />
								</div>
								<div class="form-group">
									<label>Cinsiyet</label> <select name="" id=""
										class="form-control">
										<option value="">Bay</option>
										<option value="">Bayan</option>
									</select>
								</div>
								<div class="form-group">
									<label>Doğum Yılı</label> <input type="number" value="<%= customer != null ? customer.getBirthYear() : "" %>"
										class="form-control" placeholder="4 Haneli sayı giriniz." />
								</div>
								<div class="form-group">
									<label>Yaşadığı Şehir</label> <select name="" id=""
										class="form-control">
										<option value="">İstanbul</option>
										<option value="">Tekirdağ</option>
									</select>
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-info" value="Kaydet" />
								</div>
							</form>
						</div>
						<div role="tabpanel" class="tab-pane" id="userSettings">
							<h2>Kullanıcı İşlemleri</h2>
							<form action="" class="form">
								<div class="form-group">
									<label>Email Adresi</label> <input type="text"
										class="form-control" value="<%= customer != null ? customer.getEmail() : "" %>"
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
	<script src="assets/js/script.js"></script>
</body>
</html>