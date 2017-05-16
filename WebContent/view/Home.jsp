<%@page import="model.User"%>
<%@page import="model.Company"%>
<%@page import="model.Customer"%>
<%@page import="model.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%
	User loggedUser = (User) session.getAttribute("LoggedUser");
	System.out.println(loggedUser);
%>
<!doctype html>
<html lang="tr">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Hoşgeldiniz | Son Kullanıcı Destek Sistemi - Sorunlarınız
	Sorunlarımızdır</title>
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/css/style.css" />

</head>
<body ng-app="homeModule" ng-controller="homeController">
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
					
						<%if(loggedUser== null) { %>
							<li><a class="btn btn-primary navbar-btn login">Giriş
									Yap</a></li>
							<li><a class="btn btn-info navbar-btn register">Kayıt Ol</a></li>
							<%}else { %>
							<% if(loggedUser.getType() == 1) {%>
							<li><a href="#" class="create-request">Şikayette Bulunun !</a></li>
							
							<li class="dropdown"><a href="#"
								class="dropdown-toggle btn btn-primary navbar-btn"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><i class="fa fa-bell"></i><span
									class="badge">3</span></a>
								<ul class="dropdown-menu">
									<li class="notification-bar"><a href="">
											<div class="media">
												<div class="media-left">
													<img class="media-object" src="assets/images/indir.svg" />
												</div>
												<div class="media-body">Burada bildirim eylemi
													yazacaktır.</div>
											</div>
									</a></li>
									<li class="notification-bar"><a href="">
											<div class="media">
												<div class="media-left">
													<img class="media-object" src="assets/images/indir.svg" />
												</div>
												<div class="media-body">Burada bildirim eylemi
													yazacaktır.</div>
											</div>
									</a></li>
								</ul></li>
								
								<%} else if(loggedUser.getType() == 3) { %>
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
																elit. Morbi tempor erat ac arcu bibendum, sit amet
																euismod felis porta</p>
															<time datetime="2009-11-13T20:00">Yurtiçi Kargo •
																Mesut • 51 dk</time>
														</div>
													</div>
													<div class="col-md-2 col-xs-2 avatar">
														<img src="assets/images/indir.svg"
															class=" img-responsive ">
													</div>
												</div>
												<div class="row msg_container base_receive">
													<div class="col-md-2 col-xs-2 avatar">
														<img src="assets/images/indir.svg"
															class=" img-responsive ">
													</div>
													<div class="col-md-10 col-xs-10">
														<div class="messages msg_receive">
															<p>Lorem ipsum dolor sit amet, consectetur adipiscing
																elit. Morbi tempor erat ac arcu bibendum, sit amet
																euismod felis porta</p>
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
									<%} %>
							<li class="dropdown"><a href="#"
								class="dropdown-toggle btn btn-info navbar-btn"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><%=loggedUser.getEmail()%> <span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href=""><i class="fa fa-cog" aria-hidden="true"></i>
											Hesabım</a></li>
									<li><a href=""><i class="fa fa-sign-out"
											aria-hidden="true"></i> Çıkış</a></li>
								</ul></li>
								<%} %>


				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<div id="search-box" class="panel panel-primary">
					<div class="panel-body">
						<form class="form" action="">
							<div class="form-group">
								<input type="search" placeholder="Şikayet veya marka arayın..."
									ng-model="searchedText" class="form-control" />
							</div>
						</form>
					</div>
				</div>
				<div class="panel panel-primary"
					ng-repeat="request in requestList | filter:searchedText">
					<div class="panel-body">
						<div class="media">
							<div class="media-left">
								<img class="media-object" src="assets/images/indir.svg"
									alt="Şirket Adı">
							</div>
							<div class="media-body">
								<a class="media-heading" ng-bind="request.title"></a>
								<p ng-bind="request.detail"></p>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<i class="fa fa-user"></i>&nbsp;<span
							ng-bind="request.customer.name"></span>&nbsp;&nbsp; <i
							class="fa fa-clock-o"></i>&nbsp;<span ng-bind="request.date"></span>&nbsp;&nbsp;
						<i class="fa fa-caret-right"></i>&nbsp;<span
							ng-bind="request.company.name"></span>
					</div>
				</div>

			</div>
			<div class="col-md-3">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="panel-title">Bizimle Çalışan Bazı Markalar</div>
					</div>
					<ul class="list-group ">
						<li class="list-group-item company-item">
							<div class="media">
								<div class="media-left">
									<img class="media-object" src="assets/images/indir.svg"
										alt="Şirket Adı">
								</div>
								<div class="media-body">
									<a class="media-heading">Şirket Adı</a>
								</div>
							</div>
						</li>
						<li class="list-group-item company-item">
							<div class="media">
								<div class="media-left">
									<img class="media-object" src="assets/images/indir.svg"
										alt="Şirket Adı">
								</div>
								<div class="media-body">
									<a class="media-heading">Şirket Adı</a>
								</div>
							</div>
						</li>
						<li class="list-group-item company-item">
							<div class="media">
								<div class="media-left">
									<img class="media-object" src="assets/images/indir.svg"
										alt="Şirket Adı">
								</div>
								<div class="media-body">
									<a class="media-heading">Şirket Adı</a>
								</div>
							</div>
						</li>
					</ul>
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
	<script src="assets/js/angular.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/angularScripts.js"></script>
	<script src="assets/js/script.js"></script>
</body>
</html>