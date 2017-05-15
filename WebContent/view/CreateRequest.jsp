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
			<label>Şirket</label> <input type="text" name="company-text"
				class="form-control" placeholder="Şirket İsmini Giriniz" autocomplete="off" />
				<input type="hidden" name="company" />
				<ul class="nav nav-stacked autoComplete-field"></ul>
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
	var companyList;
	$.ajax({
		url : "/app/getCompanyList",
		method : 'POST'
	}).done(function(response) {
		companyList = jQuery.parseJSON(response);
	});
	
	var companyText = $(document).find('#mainModal input[name=company-text]');
	$(document).find('#mainModal input[name=company-text]').on('input', function() {
		if (companyList != null) {
			companyText.parent().find('ul.autoComplete-field').html("");
			if(companyText.val() != ""){
				companyList.forEach(function(item, index) {
					if(item.name.toLowerCase().search(companyText.val()) > -1){
						var cImageUrl = "/assets/images/resimyok.png";
						if(item.imageUrl != null) cImageUrl = item.imageUrl;
						companyText.parent().find('ul.autoComplete-field').html(
						'<li cid="'+item.id+'"><a class="media">'+
						  '<div class="media-left">'+
						      '<img class="media-object" src="'+cImageUrl+'">'+
						  '</div>'+
						  '<div class="media-body">'+
						    '<h4 class="media-heading">'+item.name+'</h4>'+
						  '</div>'+
						'</a></li>');
					}
				});
			}
		}
	});
	
	$(document).on('click','#mainModal ul.autoComplete-field li a',function(){
		companyText.css('display','none');
		companyText.val("");
		companyText.parent().find('ul.autoComplete-field').css('display','none');
		companyText.parent().find('ul.autoComplete-field').html("");
		$(document).find('#mainModal input[name=company]').val($(this).parent().attr('cid'));
		$(document).find('#mainModal input[name=company-text]').parent('.form-group').append(
				'<div class="alert companySelected alert-info">'+$(this).find('h4.media-heading').html()+
				'<button type="button" class="close" data-dismiss="alert" aria-label="Close">'+
				  '<span aria-hidden="true">&times;</span>'+
				'</button></div>');
	});
	$(document).on('click','.companySelected button.close',function(){
		companyText.css('display','block');
		companyText.focus();
		companyText.parent().find('ul.autoComplete-field').css('display','block');
	});

	$(document)
			.find('button#create-request')
			.click(
					function() {
						$
								.ajax(
										{
											url : "/app/createRequest",
											method : 'POST',
											data : $(document).find(
													"#createRequestForm")
													.serialize()
										})
								.done(
										function(response) {
											$(document)
													.find(
															'#mainModal .modal-content')
													.html(
															"<div class='modal-body'>"
																	+ '<button type="button" class="close" data-dismiss="modal"'+
										'aria-label="Close"><i class="fa fa-times"></i></button>'
																	+ response
																	+ "</div>");
										});
					});
</script>
