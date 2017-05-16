<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><i class="fa fa-times"></i>
    </button>
    <h4 class="modal-title">Personel Ekle</h4>
</div>
<div class="modal-body">
    <form class="form">
        <div class="form-group">
            <label>Email Adresi* :</label>
            <input type="email" name="email" class="form-control" id="staff-email"
                   placeholder="Email formatına uygun giriş yapınız. Örnek : aaa@bb.cc" required/>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" id="addPersonal" class="btn btn-info">Personel Olarak Ata</button>
</div>

<script>
    $(document).find('button#addPersonal').click(function() {
        alert("tıklandı");
    	/*$.ajax({
			url : "/app/addAndRemoveStaff",
			method : 'POST',
			data : {"staffEmail" : $("#staff-email").value(), "operation":"add"}
		}).done(function(response) {
			alert(response);
		});*/
	});
</script>