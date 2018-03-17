$(document).ready(function () {

    $("#sede").on("focus", function () {
        var url = $("#sedes").attr("data-url");
        $.ajax({
            url: url,
            timeout: 20000,
            type: "POST",
            data: "username=" + $("#username").val(),
            async: false,
            success: function (result) {
               var primeraOpcion = '<option value="">Seleccionar Sede</option>';
               $("#sedes").html(primeraOpcion+result);
            }
        });
    });
    
    $("#sede").on("change", function () {
        
    });
});


