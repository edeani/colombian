$(document).on("ready", function () {
    $(document).on("click", "#btn-search", function () {
        $.ajax({
            url: $("#contextpath").val()+"/usuarios/ajax/buscar-x-mail.htm",
            data: "email=" + $("#email").val(),
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                console.log(response);
            }
        });
    });
});


