$(document).ready(function(){
    $("#fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $("#fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    
    $("#consultarDomicilios").click(function (event){
        event.preventDefault();
        var tipoPeticion="POST";
        var parametros = $("#formDomicilios").serialize();
        
        var urlDomiciliosDia = $("#contDomicilios").attr("data-url");
        var htmlMesas = peticionAjaxAsync(urlDomiciliosDia,tipoPeticion,parametros);
        $("#contenidodomicilios").html(htmlMesas);
        
        var suma_total_domicilios=sumaColumna(event,"cmpResumenDomicilios","totalDomicilios");
        formatCurrencyFieldText(document.getElementById("totalDomicilios"));
        $("#totalDomiciliosLabel").html($("#totalDomicilios").val());
        
        var suma_cant_domicilios=sumaColumna(event,"cmpResumenDomiciliosCant","totalCantDomicilios");
        $("#totalCantDomiciliosLabel").html($("#totalCantDomicilios").val());

    });
});
