$(document).ready(function() {
    var valorCampoIdCuenta = "";
    var valorCampoConceptoCuenta = "";
    
    $(".fechaInicial").datepicker({
        dateFormat: "yy-mm-dd",
        onClose: function(fechaSeleccionada) {
            var fechaActual = $(this).val();
            fechaProximoCampo(this);
            /**Actualizo la fecha en los campos**/
            var filasTblDatos = $("#tblPagosConsolidado").find("tbody tr");
            var numeroFilas = filasTblDatos.length;
            for(i=0;i<numeroFilas;i++){
                $("#fechaPago"+i).val(fechaActual);
            }
            fechaProximoCampo(this);
        }
    });
    
    $(document).on("change", "#nombreBeneficiario", function() {
        var url = $("#formPagoConsolidadoSede").data("url");
        var urlConsecutivo = $("#formPagoConsolidadoSede").data("urlconsecutivo");
        var consecutivo = peticionAjax(urlConsecutivo, "post", "");
        $("#idBeneficiario").val($("#nombreBeneficiario").val());
        $("#secuencia").val(consecutivo);
        $('#secuencia').attr('readonly', true);
        $("#cmpSecuencia").show();
        $("#cmpFecha").show();

        $("#cmpCuentaConsolidado").show();
        $("#cmpDetalle").show();
        $("#cmpTotalPagar").show();

        var html = peticionAjax(url, "post", "");
        $("#divContenedorTabla").html(html);

        var numeroFilas = $("#tblPagosConsolidado tbody").find("tr");
        for (i = 0; i < numeroFilas.length; i++) {
            $("#idPago" + i).val(consecutivo);
        }

        $("#tblPagosConsolidado").show();
    });

    $("#idCuentaConsolidado").autocomplete({
        source: "/colombianCaliyCali/cuentas/ajax/autocompletar.htm",
        select: function(event, ui) {
            valorCampoIdCuenta = ui.item.idCuenta;
            valorCampoConceptoCuenta = ui.item.nombreCuenta;
        }
        , close: function(event, ui) {
            $("#idCuentaConsolidado").val(valorCampoIdCuenta);
            var numeroFilas = $("#tblPagosConsolidado tbody").find("tr");
            for (i = 0; i < numeroFilas.length; i++) {
                $("#numerocuenta" + i).val(valorCampoIdCuenta);
                $("#concepto" + i).val(valorCampoConceptoCuenta);
            }
        }
    });

    $(document).on("keyup", "#detalleConsolidado", function(e) {
        var numeroFilas = $("#tblPagosConsolidado tbody").find("tr");
        for (i = 0; i < numeroFilas.length; i++) {
            $("#detalle" + i).val($(this).val());
        }
    });

    $(document).on("keyup", "#totalPagar", function(e) {
        var numeroFilas = $("#tblPagosConsolidado tbody").find("tr");
        var snumero = $(this).val();
        snumero = snumero.toString().replace(/\$|\,/g, '');
        for (i = 0; i < numeroFilas.length; i++) {
            var numero = parseInt(snumero);

            var porcentaje = parseFloat($("#porcentaje" + i).val());
            var t = numero * porcentaje;
            $("#total" + i).val(Math.round(t));
            var cmpTotal = document.getElementById("total" + i);
            formatoNumeroDecimal(cmpTotal);
        }

        sumaColumna(e, "claseTotalProveedor", "total");
        var cmpTotalAcum = document.getElementById("total");
        formatoNumeroDecimal(cmpTotalAcum);
    });

    $(document).on("click", "#generar", function(e) {
        e.preventDefault();
        loader("cargador", "barra.gif");
        var continuar = validarCamposTabla("tblPagosConsolidado");
        if (continuar) {
            //Preparar campos decimales
            prepararCamposDecimales();
            var url = $("#pagosConsolidadoSedeDto").attr("action");
            var parametros = $("#pagosConsolidadoSedeDto").serialize();
            var html = peticionAjax(url, "post", parametros);
            //Genero el PDF
            var body = document.body;
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = $("#pagosConsolidadoSedeDto").data("urlcomprobante");
            form.name = 'jsform';
            form.id = 'jsform';
            form.target = "_blank";
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = "idpagotercero";
            input.id = "idpagotercero";
            input.value = $("#secuencia").val();
            form.appendChild(input);
            body.appendChild(form);
            form.submit();
            var urlLocal = window.location.href;
            window.location.href = urlLocal;
        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
        loader("cargador", "");
    });
});
function fechaProximoCampo(campo) {

    var fields = $('body').find("button,input,textarea,select");
    var index = fields.index($(campo));
    /**
     * Si el siguiente campo esta deshabilitado
     */
    if ($(fields[index + 2]).is(":disabled")) {
        $(fields[index + 2]).attr("disabled", false);
    }

    if (index > -1 && (index + 2) < fields.length) {
        $(fields[index + 2]).focus();
    }
}

