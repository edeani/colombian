$(document).ready(function() {
    var valorCampoIdCuenta = "";
    var valorCampoNombreCuenta = "";
    $("#fecha").datepicker({
        dateFormat: "yy-mm-dd"
    });

    $(document).on("click", "#generarReporteConsolidadoSede", function(event) {
        event.preventDefault();
        $("#agregarFila").hide();
        var urlBuscar = $("#comprobanteCierreSedesDto").data("urlcomprobante");
        var parametros = $("#comprobanteCierreSedesDto").serialize();
        $("#divContenedorTabla").html("");
        var validacionFormulario = validarFormulario("#comprobanteCierreSedesDto");
        if (validacionFormulario === "") {
            loader("cargador", "barra.gif");
            var repuestaHtml = peticionAjax(urlBuscar, "post", parametros);
            $("#divContenedorTabla").html(repuestaHtml);
            loader("cargador", "");
        } else {
            lightboxMensaje(validacionFormulario);
        }

    });

    $(document).on("click", "#guardarComprobanteCierreSede", function(event) {
        event.preventDefault();
        loader("cargador", "barra.gif");
        var continuar = validarCamposTabla("tblDatos");
        if (continuar) {
            if ($("#totalDeber").val() !== $("#totalHaber").val()) {
                lightboxMensaje("Las sumas deben ser iguales");
            } else {
                prepararCamposDecimalesPoint();
                prepararCamposDecimales();
                $("#fila").html("");
                var url = $("#comprobanteCierreSedesDto").attr("action");
                var parametros = $("#comprobanteCierreSedesDto").serialize();
                var consecutivo = peticionAjax(url, "post", parametros);

                var urlComprobante = $("#comprobanteCierreSedesDto").data("urlcomprobantepdf");
                //Genero el PDF
                var body = document.body;
                var form = document.createElement('form');
                form.method = 'POST';
                form.action = urlComprobante;
                form.name = 'jsform';
                form.id = 'jsform';
                form.target = "_blank";
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = "idComprobanteCierre";
                input.id = "idComprobanteCierre";
                input.value = consecutivo;
                form.appendChild(input);
                body.appendChild(form);
                form.submit();
                var urlLocal = window.location.href;
                window.location.href = urlLocal;
            }
        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
        loader("cargador", "");
    });

    $(document).on("keyup", ".claseCuentaCierre", function(event) {
        if (event.keyCode === $.ui.keyCode.TAB && $(this).autocomplete("instance").menu.active) {
            event.preventDefault();
        } else {
            $(this).autocomplete({
                source: $("#contextpath").val()+"/cuentas/ajax/autocompletar.htm",
                select: function(event, ui) {
                    valorCampoIdCuenta = ui.item.idCuenta;
                    valorCampoNombreCuenta = ui.item.nombreCuenta;
                }
                , close: function(event, ui) {
                    if (valorCampoIdCuenta.length >= 6) {
                        $(this).val(valorCampoIdCuenta);
                    } else {
                        $(this).val("");
                        $(this).focus();
                    }
                }
            });
        }
    });

    $(document).on("click", "#agregarFilaCierreSede", function() {
        agregarFila('tblDatos', 'fila');
        actualizarHaberDeber();
    });
    $(document).on("click", ".claseEliminarFilaCierre", function(e) {
        var fila = $(this).parent().parent();
        eliminarFila('tblDatos', 'generica', fila);
        actualizarHaberDeber();
        //Sumo los valores
        sumaColumna(e, "claseTotalDeber", "totalDeber");
        sumaColumna(e, "claseTotalHaber", "totalHaber");
        //Le coloco formato al campo de total
        var field = document.getElementById("totalDeber");
        formatoNumeroDecimal(field);
        field = document.getElementById("totalHaber");
        formatoNumeroDecimal(field);
    });

    $(document).on("keyup", ".claseTotalDeber", function(e) {
        //borro lo que hay en el haber de la fila
        var filaTr = $(this).parent().parent();
        var numeroTr = $(filaTr).attr("data-numero");
        $("#haber" + numeroTr).val("0");
        $("#totalDetalle" + numeroTr).val($(this).val());

        //Sumo los valores
        sumaColumna(e, "claseTotalDeber", "totalDeber");
        sumaColumna(e, "claseTotalHaber", "totalHaber");
        //Le coloco formato al campo de total
        var field = document.getElementById("totalDeber");
        formatoNumeroDecimal(field);
        field = document.getElementById("totalHaber");
        formatoNumeroDecimal(field);
    });
    $(document).on("keyup", ".claseTotalHaber", function(e) {
        //borro lo que hay en el deber de la fila
        var filaTr = $(this).parent().parent();
        var numeroTr = $(filaTr).attr("data-numero");
        $("#deber" + numeroTr).val("0");
        $("#totalDetalle" + numeroTr).val($(this).val());
        //Sumo los valores
        sumaColumna(e, "claseTotalHaber", "totalHaber");
        sumaColumna(e, "claseTotalDeber", "totalDeber");
        //Le coloco formato al campo de total
        var field = document.getElementById("totalHaber");
        formatoNumeroDecimal(field);
        field = document.getElementById("totalDeber");
        formatoNumeroDecimal(field);
    });

});

function actualizarHaberDeber() {
    var camposDeber = $("#tblDatos tbody").find(".claseTotalDeber");
    var numeroCamposDeber = camposDeber.length;
    for (i = 0; i < numeroCamposDeber; i++) {
        camposDeber[i].id = "deber" + i;
    }

    var camposHaber = $("#tblDatos tbody").find(".claseTotalHaber");
    var numeroCamposHaber = camposHaber.length;
    for (i = 0; i < numeroCamposDeber; i++) {
        camposHaber[i].id = "haber" + i;
    }

    var camposTotal = $("#tblDatos tbody").find(".claseTotalDetalle");
    var numeroCamposTotal = camposTotal.length;
    for (i = 0; i < numeroCamposTotal; i++) {
        camposTotal[i].id = "totalDetalle" + i;
    }
}


