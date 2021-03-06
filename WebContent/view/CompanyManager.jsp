<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="tr">
<head>
<%
	User loggedUser = (User)session.getAttribute("LoggedUser");
	if(loggedUser== null) {
		response.sendRedirect("/");
	} else {
		if(loggedUser.getType() != 3) {
			response.sendRedirect("/");
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
<body ng-app="companyModule" ng-controller="companyController">
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
						aria-expanded="false">Hoşgeldin, {User} <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/companyManager"><i class="fa fa-cog" aria-hidden="true"></i>
									Taleplerim</a></li>
							<li><a href="/companySettings"><i class="fa fa-cog" aria-hidden="true"></i>
									Hesabım</a></li>
							<li><a href="/logout"><i class="fa fa-sign-out"
									aria-hidden="true"></i> Çıkış</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div id="search-box" class="panel panel-primary">
					<div class="panel-body">
						<form class="form" action="">
							<div class="form-group">
								<input type="search" placeholder="Talep arayın..."
									class="form-control" />
							</div>
						</form>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Talep Numarası</th>
									<th>Talep Başlığı</th>
									<th>İlgili Personel</th>
									<th>Talep Geliş Tarihi</th>
									<th>Durum</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in data">
									<td ng-bind="item.id">1</td>
									<td><a class="media-heading"  href="/requestDetail?id={{item.id}}" ng-bind="item.title"></a></td>
									<td ng-bind="item.staff.name"></td>
									<td ng-bind="item.date"></td>
									<td ng-if="item.responseCount == 0" class="text-danger">Yeni</td>
									<td ng-if="item.responseCount != 0" class="text-success">Cevaplandı</td>
								</tr>
							</tbody>
						</table>
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
	<script src="/assets/js/angular.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/script.js"></script>
	<script src="/assets/js/angularScripts.js"></script>
	<script src="assets/js/bootstrap-chat.js"></script>
</body>
</html>