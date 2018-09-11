$(document).ready(function(){
    $(".fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(".fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    
    $(document).on("click", "input#consultarInventario", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formInventario");
        if (estadoFormulario==="") {
            loader("cargador", "barra.gif");
            var html = peticionAjax($("#formInventario").attr("action"), "POST", "fechaInicial=" + $("#fechaInicial").val()+"&fechaFinal="+$("#fechaFinal").val()
                    +"&sede="+$("#sedeSession").find("option:selected").text());
            loader("cargador", "");
            $("#listaInventario").html(html);
        }else{
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
});