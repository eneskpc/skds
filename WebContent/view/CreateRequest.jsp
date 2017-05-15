<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<i class="fa fa-times"></i>
	</button>
	<h4 class="modal-title">
		Bir Talep Oluştur <small>Şikayetini Dile Getir</small>
	</h4>
</div>
<div class="modal-body">
	<form class="form" id="createRequestForm" action="">
		<div class="form-group">
			<label for="">Talep Başlığı</label> <input type="text" name="rTitle"
				class="form-control" placeholder="Maksimum 50 Karakter Giriniz" />
		</div>
		<div class="form-group">
			<label>Şirket</label> <input type="text" name="company" id="company"
				class="form-control" placeholder="Şirket İsmini Giriniz" />
		</div>
		<div class="form-group">
			<label>Talep İçeriği</label>
			<textarea name="rDetail" id="" cols="30" rows="10"
				class="form-control"
				placeholder="Minimum 20 karakter olacak şekilde, sorununuzu detaylandırın."></textarea>
		</div>
	</form>
</div>
<div class="modal-footer">
	<button type="button" id="create-request" class="btn btn-info">Oluştur</button>
</div>


<script>
	$.ajax({
		url : "/app/getCompanyList",
		method : 'POST'
	}).done(function(response) {
		console.log(response);
	});


	$(document)
			.find('button#create-request')
			.click(
					function() {
							$.ajax({
								url : "/app/createRequest",
								method : 'POST',
								data : $(document).find("#createRequestForm").serialize()
							}).done(function(response) {
								$(document).find('#mainModal .modal-content').html("<div class='modal-body'>"
										+'<button type="button" class="close" data-dismiss="modal"'+
										'aria-label="Close"><i class="fa fa-times"></i></button>'+response+"</div>");
							});
					});
</script>
