<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><i class="fa fa-times"></i>
    </button>
    <h4 class="modal-title">Kullanıcı Giriş</h4>
</div>
<div class="modal-body">
    <form class="form" action="">
        <div class="form-group">
            <input type="text" name="email" class="form-control" placeholder="Email Adresi"/>
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" placeholder="Parola"/>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default">Şifrenizi mi unuttunuz?</button>
    <button type="button" class="btn btn-primary pull-left register">Kayıt Ol</button>
    <button type="button" id="login" class="btn btn-info">Giriş Yap</button>
</div>