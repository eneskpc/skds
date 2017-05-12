function footerMinLocation() {
    var dHeight = $(document).height();
    var bHeight = $("body").height();
    $('#footer').css("margin-top", (dHeight - bHeight) + "px");
}

$(document).ready(function () {
    footerMinLocation();
    $(window).resize(function () {
        footerMinLocation();
    });
    var modalLoading = '<div class="loading"><i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i></div>';
    $(document).on('click', '.login', function () {
        $('#mainModal .modal-content').html(modalLoading);
        if ($('#mainModal').css('display') != 'block')
            $('#mainModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
        $.ajax("/app/login").done(function (response) {
            $('#mainModal .modal-content').html(response);
        });
    });
    $(document).on('click', '.register', function () {
        $('#mainModal .modal-content').html(modalLoading);
        if ($('#mainModal').css('display') != 'block')
            $('#mainModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
        $.ajax("/app/register").done(function (response) {
            $('#mainModal .modal-content').html(response);
        });
    });

    $(document).on('click', '.create-request', function () {
        $('#mainModal .modal-content').html(modalLoading);
        if ($('#mainModal').css('display') != 'block')
            $('#mainModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
        $.ajax("/app/createRequest").done(function (response) {
            $('#mainModal .modal-content').html(response);
        });
    });

    $(document).on('click', '.add-personal', function () {
        $('#mainModal .modal-content').html(modalLoading);
        if ($('#mainModal').css('display') != 'block')
            $('#mainModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
        $.ajax("addPersonal.php").done(function (response) {
            $('#mainModal .modal-content').html(response);
        });
    });

    /*$(document).on('click', '#register', function () {
     var memberTypeForm = $(document).find('#mainModal .modal-content .tab-pane.active').attr('id');
     if (memberTypeForm == "company") {
     $(document).find("#company form").submit();
     } else if (memberTypeForm == "customer") {
     alert("naber");
     }
     });*/
});