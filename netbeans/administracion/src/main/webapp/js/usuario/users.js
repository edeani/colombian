$(document).on("ready", function () {
    $(document).on("click", "#btn-search", function () {
        $.ajax({
            url: $("#contextpath").val() + "/usuarios/ajax/buscar-x-mail.htm",
            data: "email=" + $("#email").val(),
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                $("#datosUsuario").html(response);
            }
        });
    });

    $(document).on("click", "#save-user", function (event) {
        event.preventDefault();
        var resumenDireccion = "";
        var continuar = true;
        if ($("#componente").val() !== "" && $("#datoComponente").val() !== "" && $("#datoComponente1").val() !== "" && $("#datoComponente2").val() !== "") {
            resumenDireccion = "" + $("#componente").val() + " " + $("#datoComponente").val() + " #" + $("#datoComponente1").val() + "-" + $("#datoComponente2").val();
        } else {
            continuar = false;
            $("#direccion").val("");
        }
        if (resumenDireccion.length > 0) {
            var elementos = resumenDireccion.split(" ");
            if (elementos.length === 3) {
                $("#direccion").val(resumenDireccion);
            } else {
                $("#direccion").val("");
                continuar = false;
            }
        }
        if (continuar) {
            $.ajax({
                url: $("#contextpath").val() + "/usuarios/ajax/actualizar-usuario.htm",
                data: $("#usuarioDto").serialize(),
                type: 'POST',
                timeout: 20000,
                success: function (response) {
                    $("#datosUsuario").html(response);
                }
            });
        }else{
            console.log("Dirección incorrecta");
        }
    });
});


