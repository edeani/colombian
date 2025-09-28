$(document).ready(function () {

    $(document).on("click", "button#facturacionInventarioXLS", function (event) {
        event.preventDefault();
        
        var idFormulario = "#formInventariofacturacion";
        
        var form = $(idFormulario).clone();
        $(form).attr("action", $(this).attr("data-url"));
        //$(form).attr("method","POST");
        $(form).attr("download", "reporte_inventario_facturacion.xlsx");
        form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "excel"));
        form.appendTo('body').submit().remove();
    });
    
    $(document).on("click", "button#facturacionInventarioSedeXLS", function (event) {
        event.preventDefault();
        
        var idFormulario = "#formInventarioFacturacionSede";
        var idSubsede = $("#idSubsede").val();
        if (idSubsede !== "") {
            var form = $(idFormulario).clone();
            $(form).attr("action", $(this).attr("data-url"));
            //$(form).attr("method","POST");
            $(form).attr("download", "reporte_inventario_facturacion.xlsx");
            form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "excel"));
            form.append($("<input></input>").attr('type', 'hidden').attr('name', 'idSubsede').attr('value', idSubsede));
            form.appendTo('body').submit().remove();
        }else{
             lightboxMensaje("Hay campos vac&iacute;os");
        }
    });

});

