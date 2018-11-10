$(document).ready(function(){
    $("#fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $("#fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    
    $("#consultarCuadre").click(function (event){
        event.preventDefault();
        var tipoPeticion="POST";
        var parametros = $("#formCuadre").serialize();
        
        var urlCuadre = $("#contCuadre").attr("data-url");
        var htmlCuadre = peticionAjaxAsync(urlCuadre,tipoPeticion,parametros);
        $("#contenidocuadre").html(htmlCuadre);
        
        var pesos = "$";
        var suma_total_gastos=sumaColumna(event,"cmpResumenvalorGastos","totalGastos");
        formatCurrencyFieldText(document.getElementById("totalGastos"));
        $("#totalGastosLabel").html(pesos+$("#totalGastos").val());
        
        var suma_total_consignaciones=sumaColumna(event,"cmpResumenvalorConsignaciones","totalConsignaciones");
        formatCurrencyFieldText(document.getElementById("totalConsignaciones"));
        $("#totalConsignacionesLabel").html(pesos+$("#totalConsignaciones").val());
        
        var suma_total_ventas=sumaColumna(event,"cmpResumenvalorVentas","totalVentas");
        formatCurrencyFieldText(document.getElementById("totalVentas"));
        $("#totalVentasLabel").html(pesos+$("#totalVentas").val());
        
        var suma_total_pagos_tarjeta=sumaColumna(event,"cmpResumenvalorPagosTarjeta","totalPagosTarjeta");
        formatCurrencyFieldText(document.getElementById("totalPagosTarjeta"));
        $("#totalPagosTarjetaLabel").html(pesos+$("#totalPagosTarjeta").val());
        
        var suma_total_descuentos=sumaColumna(event,"cmpResumenvalorDescuentos","totalDescuentos");
        formatCurrencyFieldText(document.getElementById("totalDescuentos"));
        $("#totalDescuentosLabel").html(pesos+$("#totalDescuentos").val());
        
        /*var suma_total_mesas=sumaColumna(event,"cmpResumenCuadre","totalCuadre");
        formatCurrencyFieldText(document.getElementById("totalDomicilios"));
        $("#totalDomiciliosLabel").html($("#totalDomicilios").val());
        
        $("#totalCantDomiciliosLabel").html($("#totalDom").val());*/

    });
});
