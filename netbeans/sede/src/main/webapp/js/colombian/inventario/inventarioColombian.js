$(document).ready(function () {
    $(".fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(".fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });

    $(document).on("click", "input#consultarInventario", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formInventario");
        if (estadoFormulario === "") {
            loader("cargador", "barra.gif");
            var html = peticionAjax($("#formInventario").attr("action"), "POST", "fechaInicial=" + $("#fechaInicial").val() + "&fechaFinal=" + $("#fechaFinal").val()
                    + "&sede=" + $("#sedeSession").find("option:selected").text());
            loader("cargador", "");
            $("#listaInventario").html(html);
        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });


    $(document).on("click", "input#consultarInventarioxls", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formInventario");
        if (estadoFormulario === "") {

            var form = $('#formInventario').clone();
             $(form).attr("action", $("#formInventario").attr("data-download"));
             $(form).attr("download", "reporte_inventario.xlsx");
             form.append($("<input></input>").attr('type', 'hidden').attr('name', 'sede').attr('value', $("#sedeSession").find("option:selected").text()));
             form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "excel"));
             
             form.appendTo('body').submit().remove();

            
         

        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
});