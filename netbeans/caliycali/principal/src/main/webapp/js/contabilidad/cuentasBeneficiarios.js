$(document).ready(function() {
    var nombreBeneficiario = "";
    var idBeneficiario = "";
    var nombreProveedor = "";
    var idProveedor = "";
    var valorCampoIdCuenta = "";
    var valorCampoIdFactura = "";
    var valorCampoNombreCuenta = "";
    var valorFechaFactura = "";
    var valorFactura = "";

    $(".fechaInicial").datepicker({
        dateFormat: "yy-mm-dd",
        onClose: function(fechaSeleccionada) {
            var fechaActual = $(this).val();
            fechaProximoCampo(this);
            /**Actualizo la fecha en los campos**/
            var filasTblDatos = $("#tblDatos").find("tbody tr");
            var numeroFilas = filasTblDatos.length;
            for(i=0;i<numeroFilas;i++){
                $("#fechaPago"+i).val(fechaActual);
            }
        }
    });

    $(document).on("keyup", ".claseCuenta", function(event) {
        if (event.keyCode === $.ui.keyCode.TAB && $(this).autocomplete("instance").menu.active) {
            event.preventDefault();
        } else {
            $(this).autocomplete({
                source: "/colombianCaliyCali/cuentas/ajax/autocompletar.htm",
                select: function(event, ui) {
                    valorCampoIdCuenta = ui.item.idCuenta;
                    valorCampoNombreCuenta = ui.item.nombreCuenta;
                }
                , close: function(event, ui) {
                    if (valorCampoIdCuenta.length >= 6) {
                        $(this).val(valorCampoIdCuenta);
                        var numberRow = $(this).data("numero");
                        var refCmpDetalle = "#detalle" + numberRow;
                        var refCmpFechaPago = "#fechaPago" + numberRow;
                        var refCmpNombreCuenta = "#concepto" + numberRow;
                        $(refCmpFechaPago).val($("#fechaPago").val());
                        $(refCmpNombreCuenta).val(valorCampoNombreCuenta);
                        $(refCmpDetalle).attr("disabled", false);
                        //$(refCmpIdCuenta).removeClass("campError");
                        //$(refCmpDetalle).addClass("campError");
                        $(refCmpDetalle).focus();
                    } else {
                        $(this).val("");
                        $(this).focus();
                    }
                }
            });
        }
    });
    $(document).on("keyup", ".claseCompra", function(event) {
        if (event.keyCode === $.ui.keyCode.TAB && $(this).autocomplete("instance").menu.active) {
            event.preventDefault();
        } else {
            $(this).autocomplete({
                source: "/colombianCaliyCali/factura/ajax/proveedor/autocompletar.htm?idproveedor=" + $("#idProveedor").val(),
                select: function(event, ui) {
                    valorCampoIdFactura = ui.item.value;
                    valorFechaFactura = ui.item.fechaFactura;
                    valorFactura = ui.item.valorTotal;
                }
                , close: function(event, ui) {
                    var facturaDigitada = $(this).val();
                    $(this).val(valorCampoIdFactura);
                    if(valorCampoIdFactura!==""){
                        var numberRow = $(this).data("numero");
                        var refCmpDetalle = "#detalle" + numberRow;
                        var refCmpFechaPago = "#fechaPago" + numberRow;
                        var refCmpFechaFactura = "#fechaFactura" + numberRow;
                        var refCmpSaldo = "#saldo" + numberRow;
                        $(refCmpFechaPago).val($("#fechaPago").val());
                        $(refCmpFechaFactura).val(valorFechaFactura);
                        $(refCmpSaldo).val(valorFactura);
                        $(refCmpSaldo).attr("data-valorfactura",valorFactura);
                        $(refCmpDetalle).attr("disabled", false);
                        //$(refCmpIdCuenta).removeClass("campError");
                        //$(refCmpDetalle).addClass("campError");
                        $(refCmpDetalle).focus();
                        valorCampoIdFactura="";
                    }else{
                        lightboxMensaje("La factura "+ facturaDigitada +" no existe o ya se encuentra cancelada")
                    }
                }
            });
        }
    });
    $(document).on("change", "#nombreBeneficiario", function() {
        nombreBeneficiario = $(this).text();
        idBeneficiario = $(this).val();
        //Completo los valores del beneficiario
        //$("#nombreBeneficiario").val(nombreBeneficiario);
        $("#idBeneficiario").val(idBeneficiario);
        //Obtengo la nueva secuencia
        var url = $("#formPago").attr("data-url");
        var secuenciaPago = peticionAjax(url, "post", "");
        $("#secuencia").val(secuenciaPago);
        $('#secuencia').attr('readonly', true);
        $("#cmpSecuencia").show();
        //coloco la fecha
        var urlFecha = "/colombianCaliyCali/pagos/ajax/fecha.htm";
        var fecha = peticionAjax(urlFecha, "post", "");
        //preparo para mostrar campo fecha
        $("#fechaPago").val(fecha);
        $('#fechaPago').attr('readonly', true);
        $("#fechaPago0").val(fecha);
        //Preparo campo de idpagotercero
        $("#idpagotercero0").val($("#secuencia").val());
        //muestro campos de fecha y el link de agregar fila
        $("#cmpFecha").show();
        $("#lnkRows").show();
        //desahabilito campos de la primera fila
        $("#detalle0").attr("disabled", true);
        $("#total0").attr("disabled", true);
        $("#concepto0").attr("readonly", true);
        $("#tblDatos").show();
        //Focus sobre el campo que necesito
        //$("#numerocuenta0").addClass("campError");
        $("#identificadorSede0").html($("#idSede").html());
    });
    $(document).on("change", "#nombreProveedor", function() {
        nombreProveedor = $(this).text();
        idProveedor = $(this).val();
        //Completo los valores del beneficiario
        //$("#nombreBeneficiario").val(nombreBeneficiario);
        $("#idProveedor").val(idProveedor);
        //Obtengo la nueva secuencia
        var url = $("#formProveedor").attr("data-url");
        var secuenciaPago = peticionAjax(url, "post", "");
        $("#secuencia").val(secuenciaPago);
        $('#secuencia').attr('readonly', true);
        $("#cmpSecuencia").show();
        //coloco la fecha
        var urlFecha = "/colombianCaliyCali/pagos/ajax/fecha.htm";
        var fecha = peticionAjax(urlFecha, "post", "");
        //preparo para mostrar campo fecha
        $("#fechaPago").val(fecha);
        $('#fechaPago').attr('readonly', true);
        $("#fechaPago0").val(fecha);
        //Preparo campo de idpagotercero
        $("#idpagoproveedor0").val($("#secuencia").val());
        //muestro campos de fecha y el link de agregar fila
        $("#cmpFecha").show();
        $("#lnkRows").show();
        //Traigo las pendientes del proveedor
        var urlPendientes = "/colombianCaliyCali/compras/ajax/avencer.htm";
        var htmlComprasPendientes = peticionAjax(urlPendientes,"post","idProveedor="+idProveedor);
        $("#divContenedorTabla2").html(htmlComprasPendientes);
        //Muestro capa de los pendientes
        $("#divContenedorTabla2").show();
        //desahabilito campos de la primera fila
        $("#detalle0").attr("disabled", true);
        $("#total0").attr("disabled", true);
        $("#concepto0").attr("readonly", true);
        $("#tblDatos").show();
        //Focus en el primer Campo
        $("#numeroCompra0").focus();
    });
    $(document).on("change", ".claseSelectSede", function() {
        var rowNum = $(this).data("numero");
        $("#inputIdentificadorSede" + rowNum).val($(this).val());
        $("#numerocuenta" + rowNum).focus();
    });
    $(document).on("keypress", ".claseCompra", function(evento) {
        var code = evento.keyCode || evento.which;
        if (code === 13) {
            var numero = $(this).data("numero");
            $("#detalle" + numero).attr("disabled", false);
            $("#detalle" + numero).focus();
        }
    });
    $(document).on("click", "#generar", function(e) {
        e.preventDefault();
        loader("cargador", "barra.gif");
        var continuar = validarCamposTabla("tblDatos");
        if (continuar) {
            //Preparar campos decimales
            prepararCamposDecimales();
            var url = $("#pagosTercerosDto").attr("action");
            var parametros = $("#pagosTercerosDto").serialize();
            var html = peticionAjax(url, "post", parametros);
            //Genero el PDF
            var body = document.body;
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = $("#pagosTercerosDto").data("urlcomprobante");
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
    /**
     * Guardar comprobantes de proveedor
     */
    $(document).on("click", "#generarComprobanteProveedor", function(event) {
        event.preventDefault();
        loader("cargador", "barra.gif");
        var continuar = validarCamposTabla("tblDatos");
        if (continuar) {
            prepararCamposDecimalesClase("claseTotalProveedor");
            var url = $("#pagosProveedorDto").attr("action");
            var parametros = $("#pagosProveedorDto").serialize();
            var html = peticionAjax(url, "post", parametros);
            //Genero el PDF
            var body = document.body;
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = $("#pagosProveedorDto").data("urlcomprobante");
            form.name = 'jsform';
            form.id = 'jsform';
            form.target = "_blank";
            var input = document.createElement('input');
            input.type = 'hidden';
            input.name = "idpagoproveedor";
            input.id = "idpagoproveedor";
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
    $(document).on("click", ".clsEliminarFila", function() {
        var celda = $(this).parent();
        var fila = $(celda).parent();
        $(fila).remove();
        var filasTr = $('#tblDatos tbody tr');
        var indice = 0;
        console.log(filasTr.length);
        $(filasTr).each(function(index) {
            var cells = $(this).children().find("select,input");
            //CeldaSede.
            var celdaSedeSelect = cells[0];
            celdaSedeSelect.id = "identificadorSede" + indice;
            $(celdaSedeSelect).attr("data-numero", indice);
            var celdaSedeInput = cells[1];
            celdaSedeInput.name = "detallePagosTerceros[" + indice + "].idSede";
            celdaSedeInput.id = "identificadorSede" + indice;
            var cmpIdPagoTercero = cells[2];
            cmpIdPagoTercero.name = "detallePagosTerceros[" + indice + "].idpagotercero";
            cmpIdPagoTercero.id = "idpagotercero" + indice;
            var cmpIdCuenta = cells[3];
            cmpIdCuenta.name = "detallePagosTerceros[" + (indice) + "].idCuenta";
            cmpIdCuenta.id = "numerocuenta" + indice;
            $(cmpIdCuenta).attr("data-numero", indice);
            //Campo Concepto
            var cmpConcepto = cells[4];
            cmpConcepto.name = "detallePagosTerceros[" + (indice) + "].conceptoCuenta";
            cmpConcepto.id = "concepto" + indice;
            //Campo Detalle
            var cmpDetalle = cells[5];
            cmpDetalle.name = "detallePagosTerceros[" + (indice) + "].detalle";
            cmpDetalle.id = "detalle" + indice;
            //Campo Total
            var cmpTotal = cells[6];
            cmpTotal.name = "detallePagosTerceros[" + (indice) + "].total";
            cmpTotal.id = "total" + indice;
            //Campo de Fecha
            var cmpFecha = cells[7];
            cmpFecha.name = "detallePagosTerceros[" + (indice) + "].fecha";
            cmpFecha.id = "fecha" + indice;
            //Orden de la factura
            var cmpNumero = cells[9];
            cmpNumero.name = "detallePagosTerceros[" + (indice) + "].numero";
            cmpNumero.id = "numero" + indice;
            $(cmpNumero).val(indice + 1);
            indice++;
        });
    });
    $(document).on("click", ".clsEliminarFilaProveedor", function() {
        var celda = $(this).parent();
        var fila = $(celda).parent();
        
        var numeroFilaElminada = $(this).data("numero");
        var numeroComprasPendientes = $('#tblDatos2 tbody tr').length;
        //Muestro si va a ser la primera compra en las endientes
        var contadorCompra = numeroComprasPendientesaAgregar();
        if(contadorCompra===0){
            $("#tblDatos2").show();
        }
        //Oculto si elimino la ultima compra en el comprobante
        var nc = "" +$("#numeroCompra"+numeroFilaElminada).val();
        for(i=0;i<numeroComprasPendientes;i++){
            var fc = "" +$("#filaComprobante"+i).data("identificadorcompra");
            if(nc === fc){
                $("#filaComprobante"+i).show();
                i=numeroComprasPendientes;
            }
        }
        
        $(fila).remove();
        var filasTr = $('#tblDatos tbody tr');
        var numeroComprasAgregadas = filasTr.length;
        
        if(numeroComprasAgregadas === 0){
            $("#divContenedorTabla").hide();
        }
        
        var indice = 0;
        $(filasTr).each(function(index) {
            var cells = $(this).children().find("select,input");
            //Columna 1
            var celdaidpagoproveedor = cells[0];
            celdaidpagoproveedor.id = "idpagoproveedor" + indice;
            celdaidpagoproveedor.name = "detallePagosProveedor[" + indice + "].idpagoproveedor";
            $(celdaidpagoproveedor).attr("data-numero", indice);
            var celdanumerocuenta = cells[1];
            celdanumerocuenta.id = "numerocuenta" + indice;
            celdanumerocuenta.name = "detallePagosProveedor[" + indice + "].idCuenta";
            $(celdanumerocuenta).attr("data-numero", indice);
            var celdanumeroCompra = cells[2];
            celdanumeroCompra.id = "numeroCompra" + indice;
            celdanumeroCompra.name = "detallePagosProveedor[" + indice + "].numeroCompra";
            $(celdanumerocuenta).attr("data-numero", indice);
            //Columna 2
            var celdafechaFactura = cells[3];
            celdafechaFactura.id = "fechaFactura" + indice;
            //Columna 3
            var celdadetalle = cells[4];
            celdadetalle.id = "detalle" + indice;
            celdadetalle.name = "detallePagosProveedor[" + indice + "].detalle";
            //Columna 4
            var celdafechaVencimiento = cells[5];
            celdafechaVencimiento.id = "fechaVencimiento" + indice;
            celdafechaVencimiento.name = "detallePagosProveedor[" + indice + "].fechaVencimiento";
            //Columna 5
            var celdasaldo = cells[6];
            celdasaldo.id = "saldo" + indice;
            celdasaldo.name = "detallePagosProveedor[" + indice + "].saldo";
            var celdasaldotemporal = cells[7];
            celdasaldotemporal.id = "saldoTemporal" + indice;
            //Columna 6
            var celdatotal = cells[8];
            celdatotal.id = "total" + indice;
            celdatotal.name = "detallePagosProveedor[" + indice + "].total";
            $(celdatotal).attr("data-numero", indice);
            var celdafechaPago = cells[9];
            celdafechaPago.id = "fechaPago" + indice;
            celdafechaPago.name = "detallePagosProveedor[" + indice + "].fecha";
            //Columna 7
            var celdabtnEliminar = cells[10];
            $(celdabtnEliminar).attr("data-numero",indice);
            var celdanumero = cells[11];
            celdanumero.id = "numero" + indice;
            celdanumero.name = "detallePagosProveedor[" + indice + "].numero";
            $(celdanumero).val(indice);
            indice++;
        });
    });

    $(document).on("keyup", ".claseTotal", function(event) {
        var total = 0;
        var tempTotal = "";
        $(".claseTotal").each(function(index) {
            tempTotal = $(this).val();
            //si viene valor en blanco coloco cero
            if (tempTotal === "") {
                tempTotal = "0";
            }
            while (tempTotal.indexOf(",") !== -1) {
                tempTotal = tempTotal.replace(",", "");
            }
            total = total + parseInt(tempTotal);
        });
        $("#totalPago").val(total);
    });
});
$(document).on("keyup", ".claseTotalProveedor", function(e) {
    var total = 0;
    var tempTotal = "";
    $(".claseTotalProveedor").each(function(index) {
        tempTotal = $(this).val();
        //si viene valor en blanco coloco cero
        if (tempTotal === "") {
            tempTotal = "0";
        }
        while (tempTotal.indexOf(",") !== -1) {
            tempTotal = tempTotal.replace(",", "");
        }
        total = total + parseInt(tempTotal);
    });



    var field = this;
    //Saco la cuenta que falta por pagar
    var rowNum = $(field).data("numero");
    var cantidadSaldo = 0;
    var totalFacturaFila = $(field).val();
    totalFacturaFila = totalFacturaFila.toString().replace(/\$|\,/g, '');
    if (isNaN(totalFacturaFila))
        totalFacturaFila = "0";
    /*if (cantidadSaldo === "") {
        cantidadSaldo = 0;
    }*/
    var temporal = $("#saldoTemporal"+rowNum).val();
    cantidadSaldo = parseInt(temporal); 
    cantidadSaldo = cantidadSaldo - parseInt(totalFacturaFila);
    //coloco la puntiacion del numero
    var num = field.value;
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num === (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
    field.value = num;
    $("#saldo" + rowNum).val(cantidadSaldo);
    $("#totalPago").val(total);
});

$(document).on("click",".clsAgregarFilaProveedor",function(e){
    
    //var cantidadTotalCompras = $("#contenidoComprasPendientes tr").length;
    var contadorCompras =0;
    
    
    var filaCompra = $(this).data("numero");
    $("#filaComprobante"+filaCompra).hide();
    contadorCompras = numeroComprasPendientesaAgregar();
    if(contadorCompras===0){
        $("#tblDatos2").hide();
    }
    addRowPagosProveedor("contenidoComprobante");
    var numeroFilasDetalle = $("#tblDatos tbody tr").length;
    
    $("#numeroCompra"+(numeroFilasDetalle-1)).val($("#idCompraPendiente"+filaCompra).val());
    $("#fechaFactura"+(numeroFilasDetalle-1)).val($("#fechaCompraPendiente"+filaCompra).val());
    $("#fechaVencimiento"+(numeroFilasDetalle-1)).val($("#fechaVencimientoPendiente"+filaCompra).val());
    $("#saldo"+(numeroFilasDetalle-1)).val($("#saldoPendiente"+filaCompra).val());
    $("#saldoTemporal"+(numeroFilasDetalle-1)).val($("#saldoPendiente"+filaCompra).val());
    
    $("#divContenedorTabla").show();
});
/*Manejo de tabla para Beneficiarios*/
function addRowPagos(tableID) {
    var numeral = "#";
    var npago = $("#secuencia").val();
    var htmlCmpRefSedes = $("#idSede").html();
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    //revisamos los campos
    var inputsTr = $(numeral + tableID + " tbody tr");
    //var filallena = validarCampos(inputsTr[rowCount-1]);
    var row = table.insertRow(rowCount);
    //var row = $("#listaParametrosFooter tbody");

    //var cellNoFooter = row.insertCell(0);
    //$(cellNoFooter).html(rowCount);

    /**
     * Primera columna: sede
     */
    var cellIdSede = row.insertCell(0);
    //Agrego Select de sedes
    var cmpSedeSelect = document.createElement("select");
    cmpSedeSelect.id = "identificadorSede" + (rowCount);
    cmpSedeSelect.name = "detallePagosTerceros[" + (rowCount) + "].nombreSede";
    $(cmpSedeSelect).addClass("claseSelectSede");
    $(cmpSedeSelect).html(htmlCmpRefSedes);
    //$(cmpSedeSelect).data("numero", "" + rowCount);
    $(cmpSedeSelect).attr("data-numero", "" + rowCount);
    cellIdSede.appendChild(cmpSedeSelect);
    var cmpSedeInput = document.createElement("input");
    cmpSedeInput.name = "detallePagosTerceros[" + (rowCount) + "].idSede";
    cmpSedeInput.type = "hidden";
    cmpSedeInput.id = "inputIdentificadorSede" + (rowCount);
    cellIdSede.appendChild(cmpSedeInput);
    //Segunda Columna
    //Agrego campo de cuentas
    var cellIdCuenta = row.insertCell(1);
    var cmpIdPagoTercero = document.createElement("input");
    cmpIdPagoTercero.type = "hidden";
    cmpIdPagoTercero.name = "detallePagosTerceros[" + rowCount + "].idpagotercero";
    cmpIdPagoTercero.id = "idpagotercero" + rowCount;
    $(cmpIdPagoTercero).val($("#secuencia").val());
    cellIdCuenta.appendChild(cmpIdPagoTercero);
    var cmpIdCuenta = document.createElement("input");
    cmpIdCuenta.name = "detallePagosTerceros[" + (rowCount) + "].idCuenta";
    cmpIdCuenta.id = "numerocuenta" + rowCount;
    //$(cmpIdCuenta).addClass("ui-autocomplete-input claseCuenta claseValidarNum");
    $(cmpIdCuenta).addClass("ui-autocomplete-input claseValidarNum claseCuenta");
    //$(cmpIdCuenta).data("numero", "" + rowCount);
    $(cmpIdCuenta).attr("data-numero", "" + rowCount);
    $(cmpIdCuenta).attr("autocomplete", "off");
    $(cmpIdCuenta).val("");
    cellIdCuenta.appendChild(cmpIdCuenta);
    //Tercera Columna
    var cellConcepto = row.insertCell(2);
    //Campo Concepto
    var cmpConcepto = document.createElement("input");
    cmpConcepto.name = "detallePagosTerceros[" + (rowCount) + "].conceptoCuenta";
    cmpConcepto.id = "concepto" + rowCount;
    $(cmpConcepto).addClass("claseConceptoCuenta");
    $(cmpConcepto).attr("readonly", "readonly");
    $(cmpConcepto).val("");
    cellConcepto.appendChild(cmpConcepto);
    //Cuarta columna
    var cellDetalle = row.insertCell(3);
    //Campo Detalle
    var cmpDetalle = document.createElement("input");
    cmpDetalle.name = "detallePagosTerceros[" + (rowCount) + "].detalle";
    cmpDetalle.id = "detalle" + rowCount;
    $(cmpDetalle).addClass("clasedescripcion claseproximocampo");
    $(cmpDetalle).attr("maxlength", "500");
    $(cmpDetalle).val("");
    $(cmpDetalle).attr("disabled", true);
    cellDetalle.appendChild(cmpDetalle);
    //Quinta columna
    var cellTotal = row.insertCell(4);
    //Campo Total
    var cmpTotal = document.createElement("input");
    cmpTotal.name = "detallePagosTerceros[" + (rowCount) + "].total";
    cmpTotal.id = "total" + rowCount;
    $(cmpTotal).addClass("claseValidarNum claseFormatDec claseTotalProveedor");
    $(cmpTotal).val("");
    $(cmpTotal).attr("disabled", true);
    //Campo de Fecha
    var cmpFecha = document.createElement("input");
    cmpFecha.type = "hidden";
    cmpFecha.name = "detallePagosTerceros[" + (rowCount) + "].fecha";
    cmpFecha.id = "fechaPago" + rowCount;
    $(cmpFecha).val($("#fechaPago").val());
    cellTotal.appendChild(cmpTotal);
    cellTotal.appendChild(cmpFecha);
    //Columna del boton
    var cellEliminar = row.insertCell(5);
    //Boton de eliminar
    var btnEliminar = document.createElement("input");
    btnEliminar.type = "button";
    btnEliminar.value = "-";
    $(btnEliminar).addClass("clsEliminarFila");
    cellEliminar.appendChild(btnEliminar);
    //Orden en la factura
    var cmpNumero = document.createElement("input");
    cmpNumero.type = "hidden";
    cmpNumero.name = "detallePagosTerceros[" + (rowCount) + "].numero";
    cmpNumero.id = "numero" + rowCount;
    $(cmpNumero).val(rowCount + 1);
    cellEliminar.appendChild(cmpNumero);
}
/*Manejo de tablas para Proveedores*/
function addRowPagosProveedor(tableID) {
    var numeral = "#";
    var npago = $("#secuencia").val();
    var htmlCmpRefSedes = $("#idSede").html();
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;

    //var filallena = validarCampos(inputsTr[rowCount-1]);
    var row = table.insertRow(rowCount);

    /**
     * Primera columna: No. de Factura
     */
    var cellNumeroCompra = row.insertCell(0);
    var cmpIdPagoProveedor = document.createElement("input");
    cmpIdPagoProveedor.type = "hidden";
    cmpIdPagoProveedor.id = "idpagoproveedor" + rowCount;
    cmpIdPagoProveedor.name = "detallePagosProveedor[" + rowCount + "].idpagoproveedor";
    $(cmpIdPagoProveedor).val($("#secuencia").val());
    var cmpNumeroCuenta = document.createElement("input");
    cmpNumeroCuenta.type = "hidden";
    cmpNumeroCuenta.id = "numerocuenta" + rowCount;
    cmpNumeroCuenta.name = "detallePagosProveedor[" + rowCount + "].idCuenta";
    $(cmpNumeroCuenta).val($("#cuentaProveedores").val());
    $(cmpNumeroCuenta).attr("data-numero", "" + rowCount);
    var cmpNumeroCompra = document.createElement("input");
    cmpNumeroCompra.id = "numeroCompra" + rowCount;
    cmpNumeroCompra.name = "detallePagosProveedor[" + rowCount + "].numeroCompra";
    $(cmpNumeroCompra).addClass("ui-autocomplete-input claseValidarNum claseCompra");
    $(cmpNumeroCompra).attr("autocomplete", "off");
    $(cmpNumeroCompra).attr("readonly", true);
    $(cmpNumeroCompra).attr("data-numero", "" + rowCount);

    //Agrego la primera columna
    cellNumeroCompra.appendChild(cmpIdPagoProveedor);
    cellNumeroCompra.appendChild(cmpNumeroCuenta);
    cellNumeroCompra.appendChild(cmpNumeroCompra);

    /**
     * Segunda Columna: Fecha de Factura
     */
    var cellFechaFactura = row.insertCell(1);
    var cmpFechaFactura = document.createElement("input");
    cmpFechaFactura.id = "fechaFactura" + rowCount;
    $(cmpFechaFactura).addClass("clasefechafactura");
    $(cmpFechaFactura).attr("disabled", "true");
    cellFechaFactura.appendChild(cmpFechaFactura);

    /**
     * Tercera Columna: Detalle
     */
    var cellDetalle = row.insertCell(2);
    var cmpDetalle = document.createElement("input");
    cmpDetalle.id = "detalle" + rowCount;
    cmpDetalle.name = "detallePagosProveedor[" + rowCount + "].detalle";
    $(cmpDetalle).attr("maxlength", "500");
    $(cmpDetalle).addClass("clasedescripcionproveedor");
    //$(cmpDetalle).attr("disabled", "true");
    cellDetalle.appendChild(cmpDetalle);

    /**
     * Cuarta Columna: Detalle
     */
    var cellFechaVencimiento = row.insertCell(3);
    var cmpFechaVencimiento = document.createElement("input");
    cmpFechaVencimiento.id = "fechaVencimiento" + rowCount;
    cmpFechaVencimiento.name = "detallePagosProveedor[" + rowCount + "].fechaVencimiento";
    $(cmpFechaVencimiento).attr("maxlength", "500");
    $(cmpFechaVencimiento).addClass("claseproximocampo");
    $(cmpFechaVencimiento).attr("readonly", true);
    cellFechaVencimiento.appendChild(cmpFechaVencimiento);
    /*$(cmpFechaVencimiento).datepicker({dateFormat: "yy-mm-dd"
        , onClose: function(fechaSeleccionada) {
            fechaProximoCampo(this);
        }
    });*/

    /**
     * Quinta Columna: Saldo
     */
    var cellSaldo = row.insertCell(4);
    var cmpSaldo = document.createElement("input");
    cmpSaldo.id = "saldo" + rowCount;
    cmpSaldo.name = "detallePagosProveedor[" + rowCount + "].saldo";
    $(cmpSaldo).attr("readonly", "readonly");
    $(cmpSaldo).addClass("claseproximocampo claseValidarNum claseFormatDec");
    var cmpSaldoTemporal = document.createElement("input");
    cmpSaldoTemporal.type="hidden";
    cmpSaldoTemporal.id = "saldoTemporal" + rowCount;
    cellSaldo.appendChild(cmpSaldo);
    cellSaldo.appendChild(cmpSaldoTemporal);
    /**
     * Sexta Columna: Total
     */
    var cellTotal = row.insertCell(5);
    var cmpTotal = document.createElement("input");
    cmpTotal.id = "total" + rowCount;
    cmpTotal.name = "detallePagosProveedor[" + rowCount + "].total";
    //$(cmpTotal).attr("disabled", "true");
    $(cmpTotal).addClass("claseValidarNum claseTotalProveedor");
    $(cmpTotal).attr("data-numero", rowCount);
    var cmpFechaPago = document.createElement("input");
    cmpFechaPago.type = "hidden";
    cmpFechaPago.id = "fechaPago" + rowCount;
    cmpFechaPago.name = "detallePagosProveedor[" + rowCount + "].fecha";
    $(cmpFechaPago).val($("#fechaPago").val());

    cellTotal.appendChild(cmpTotal);
    cellTotal.appendChild(cmpFechaPago);

    /**
     * Septima Columna: Boton Eliminar
     */
    var cellEliminar = row.insertCell(6);
    //Boton de eliminar
    var btnEliminar = document.createElement("input");
    btnEliminar.type = "button";
    btnEliminar.value = "-";
    $(btnEliminar).attr("data-numero",rowCount);
    $(btnEliminar).addClass("clsEliminarFilaProveedor");
    var cmpNumero = document.createElement("input");
    cmpNumero.id = "numero" + rowCount;
    cmpNumero.name = "detallePagosProveedor[" + rowCount + "].numero";
    cmpNumero.type = "hidden";
    $(cmpNumero).val(rowCount);

    cellEliminar.appendChild(btnEliminar);
    cellEliminar.appendChild(cmpNumero);

}

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

function numeroComprasPendientesaAgregar(){
    var cantidadTotalCompras = $("#contenidoComprasPendientes tr").length;
    var contadorCompras =0;
    for(j=0;j<cantidadTotalCompras;j++){
        if(!$("#filaComprobante"+j).is(":hidden")){
            contadorCompras++;
        }
    }
    return contadorCompras;
}