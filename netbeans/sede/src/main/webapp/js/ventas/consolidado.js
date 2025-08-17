$(document).ready(function () {

    $(document).on("click", "button#reporteVentasTotales", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formConsolidado");
        if (estadoFormulario === "") {
            var form = $('#formConsolidado').clone();
            $(form).attr("action", $("#formConsolidado").attr("action"));
            form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "pdf"));

            const link = document.createElement('a');
            link.href = $("#formConsolidado").attr("action") + "?" + $(form).serialize(); // Replace with your PDF path
            link.download = 'reporte_consolidado.pdf';   // Replace with desired filename
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);

        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });


    $(document).on("click", "button#reporteVentasTotalesxls", function (event) {
        event.preventDefault();
        var estadoFormulario = validarFormulario("#formConsolidado");
        if (estadoFormulario === "") {
            var form = $('#formConsolidado').clone();
            $(form).attr("action", $("#formConsolidado").attr("action"));
            $(form).attr("download", "reporte_porcentaje_ventas.xlsx");
            form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "excel"));
            form.appendTo('body').submit().remove();
        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
});

