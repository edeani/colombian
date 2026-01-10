$(document).ready(function () {

    var valorCampoIdCuenta = "";
    var valorCampoConceptoCuenta = "";
    var noDetalleMessage = "No se encontraron registros para la cuenta.";

    $("#fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $("#fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });

    $("#idCuenta").autocomplete({
        source: $("#contextpath").val() + "/" + $("#idpath").val() + "/cuentas/ajax/autocompletar.htm",
        select: function (event, ui) {
            valorCampoIdCuenta = ui.item.idCuenta;
            valorCampoConceptoCuenta = ui.item.nombreCuenta;
        }
        , close: function (event, ui) {
            $("#idCuenta").val(valorCampoIdCuenta);
            $("#concepto").val(valorCampoConceptoCuenta);
            console.log("Concept: " + valorCampoConceptoCuenta);
        }
    });

    $(document).on("click", "#reporteDetalleCuentas", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formDetalleCuentas");
        if (estadoFormulario === "") {
            var parametros = $("#formDetalleCuentas").serialize();
            var url = $("#formDetalleCuentas").attr("data-verification");
            var respuesta = peticionAjax(url, "get", parametros);
            if (respuesta === "S") {
                var form = $('#formDetalleCuentas').clone();
                $(form).attr("action", $("#formDetalleCuentas").attr("action"));
                form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "pdf"));

                const link = document.createElement('a');
                link.href = $("#formDetalleCuentas").attr("action") + "?" + $(form).serialize(); // Replace with your PDF path
                link.download = 'reporte_detalle_cuentas.pdf';   // Replace with desired filename
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            } else {
                lightboxMensaje(noDetalleMessage);
            }

        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });


    $(document).on("click", "#reporteDetalleCuentasxls", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formDetalleCuentas");
        if (estadoFormulario === "") {
            var parametros = $("#formDetalleCuentas").serialize();
            var url = $("#formDetalleCuentas").attr("data-verification");
            var respuesta = peticionAjax(url, "post", parametros);
            if (respuesta === "S") {
                var form = $('#formDetalleCuentas').clone();
                $(form).attr("action", $("#formDetalleCuentas").attr("action"));
                $(form).attr("download", "reporte_detalle_cuentas.xlsx");
                form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "excel"));
                form.appendTo('body').submit().remove();
            } else {
                lightboxMensaje(noDetalleMessage);
            }

        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });


});


