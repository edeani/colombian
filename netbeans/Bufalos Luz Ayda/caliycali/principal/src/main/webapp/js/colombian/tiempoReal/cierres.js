$(document).ready(function () {
    $(".fecha").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(document).on("click", "input#consultarCierreDiario", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formCierreDiario");
        if (estadoFormulario==="") {
            loader("cargador", "barra.gif");
            var html = peticionAjax($("#formCierreDiario").attr("action"), "POST", "fecha=" + $("#fecha").val());
            loader("cargador", "");
            $("#cierreDiario").html(html);
        }else{
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
});


