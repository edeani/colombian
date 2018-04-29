$(document).ready(function(){
    $(".fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(".fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    
    $(document).on("click", "input#consultarMesas", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formMesas");
        if (estadoFormulario==="") {
            loader("cargador", "barra.gif");
            var html = peticionAjax($("#formMesas").attr("action"), "POST", "fechaInicial=" + $("#fechaInicial").val()+"&fechaFinal="+$("#fechaFinal").val()
                    +"&sede="+$("#sedeSession").find("option:selected").text());
            loader("cargador", "");
            $("#listaMesas").html(html);
        }else{
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
});