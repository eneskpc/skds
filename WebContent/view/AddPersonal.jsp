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
            <input type="email" name="email" class="form-control"
                   placeholder="Email formatına uygun giriş yapınız. Örnek : aaa@bb.cc" required/>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" id="add-personal" class="btn btn-info">Personel Olarak Ata</button>
</div>

<script>
    $(document).find("#company form").validate({
        lang: 'tr',
        rules: {
            companyName: {
                required: true,
                maxlength: 50
            },
            authNameSurname: {
                required: true,
                minlength: 5,
                maxlength: 25
            },
            authEmail: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 10
            },
            paswordAgain: {
                required: true,
                equalTo: "[name=password]"
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
</script>