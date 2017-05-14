<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<i class="fa fa-times"></i>
	</button>
	<h4 class="modal-title">Üye Kayıt Formu</h4>
</div>
<div class="modal-body">
	<ul id="member-type" class="nav nav-tabs nav-justified" role="tablist">
		<li role="presentation" class="active"><a href="#customer"
			aria-controls="customer" role="tab" data-toggle="tab"> <i
				class="fa fa-user-o" aria-hidden="true"></i> Müşteri Üyeliği
		</a></li>
		<li role="presentation"><a href="#company"
			aria-controls="company" role="tab" data-toggle="tab"> <i
				class="fa fa-building-o" aria-hidden="true"></i> Firma Üyeliği
		</a></li>
	</ul>
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="customer">
			<br />
			<form class="form">
				<input type="hidden" name="userType" value="customer" />
				<div class="form-group">
					<label>Adınız Soyadınız* :</label> <input type="text"
						name="nameSurname" class="form-control"
						placeholder="Minimum 5, Maksimum 25 karakter girebilirsiniz."
						required />
				</div>
				<div class="form-group">
					<label>Email Adresiniz* :</label> <input type="email" name="email"
						class="form-control"
						placeholder="Email formatına uygun giriş yapınız. Örnek : aaa@bb.cc"
						required />
				</div>
				<div class="form-group">
					<label>Parola* :</label> <input type="password" name="password"
						class="form-control"
						placeholder="Minimum 6, Maksimum 10 karakter girebilirsiniz."
						required />
				</div>
				<div class="form-group">
					<label>Parola (Tekrar)* :</label> <input type="password"
						name="rePassword" class="form-control"
						placeholder="Yukarıdaki 'Parola' alanı ile eşleşmesi gerekmektedir."
						required />
				</div>
			</form>
		</div>
		<div role="tabpanel" class="tab-pane" id="company">
			<br />
			<form class="form">
			<input type="hidden" name="userType" value="company" />
				<div class="form-group">
					<label>Firma Adınız* :</label> <input type="text"
						name="companyName" class="form-control"
						placeholder="Maksimum 50 karakter girebilirsiniz." required />
				</div>
				<div class="form-group">
					<label>Yetkili Adı Soyadı* :</label> <input type="text"
						name="authNameSurname" class="form-control"
						placeholder="Minimum 5, Maksimum 25 karakter girebilirsiniz."
						required />
				</div>
				<div class="form-group">
					<label>Yetkili Email Adresi* :</label> <input type="email"
						name="authEmail" class="form-control"
						placeholder="Email formatına uygun giriş yapınız. Örnek : aaa@bb.cc"
						required />
				</div>
				<div class="form-group">
					<label>Parola* :</label> <input type="password" name="password"
						class="form-control"
						placeholder="Minimum 6, Maksimum 10 karakter girebilirsiniz."
						required />
				</div>
				<div class="form-group">
					<label>Parola (Tekrar)* :</label> <input type="password"
						name="passwordAgain" class="form-control"
						placeholder="Yukarıdaki 'Parola' alanı ile aynı olmalıdır."
						required />
				</div>
			</form>
		</div>
	</div>
</div>
<div class="modal-footer">
	<button type="button" id="register" class="btn btn-info">Kayıt
		Ol</button>
	<button type="button" class="btn btn-primary pull-left login">Bir
		üyeliğiniz var mı?</button>
</div>

<script>
	$(document)
			.find('button#register')
			.click(
					function() {
						if ($(document).find(".tab-pane.active").attr('id') == "company") {
							$.ajax({
								url : "/app/RegisterUser",
								method : 'POST',
								data : $(document).find("#company form").serialize()
							}).done(function(response) {
								$(document).find('#mainModal .modal-content').html("<div class='modal-body'>"
										+'<button type="button" class="close" data-dismiss="modal"'+
										'aria-label="Close"><i class="fa fa-times"></i></button>'+response+"</div>");
							});
						} else if ($(document).find(".tab-pane.active").attr(
								'id') == "customer") {
							$.ajax({
								url : "/app/RegisterUser",
								method : 'POST',
								data : $(document).find("#customer form").serialize()
							}).done(function(response) {
								$(document).find('#mainModal .modal-content').html("<div class='modal-body'>"
										+'<button type="button" class="close" data-dismiss="modal"'+
										'aria-label="Close"><i class="fa fa-times"></i></button>'+response+"</div>");
							});
						}
					});
</script>