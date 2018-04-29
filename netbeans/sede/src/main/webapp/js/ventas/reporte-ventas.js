$(document).ready(function(){
    $("#fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $("#fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    
    $("#consultarVentas").click(function (event){
        event.preventDefault();
        var tipoPeticion="POST";
        var parametros = $("#formVentas").serialize();
        
        var urlVentasMesa = $("#contVentasMesa").attr("data-url");
        var htmlMesas = peticionAjaxAsync(urlVentasMesa,tipoPeticion,parametros);
        $("#contenidomesa").html(htmlMesas);
        
        var urlVentasDomicilio = $("#contVentasDomicilio").attr("data-url");
        var htmlDomicilio = peticionAjaxAsync(urlVentasDomicilio,tipoPeticion,parametros);
        $("#contenidodomicilio").html(htmlDomicilio);
        
        var urlVentasLlevar = $("#contVentasLlevar").attr("data-url");
        var htmlLlevar = peticionAjaxAsync(urlVentasLlevar,tipoPeticion,parametros);
        $("#contenidollevar").html(htmlLlevar);
        
        var urlVentasTotal = $("#contVentasTotal").attr("data-url");
        var htmlTotal = peticionAjaxAsync(urlVentasTotal,tipoPeticion,parametros);
        $("#contenidototal").html(htmlTotal);
                
        sumaColumna(event,"cmpResumenMesa","totalMesas");
        formatCurrencyFieldText(document.getElementById("totalMesas"));
        $("#totalMesaLabel").html($("#totalMesas").val());
        
        sumaColumna(event,"cmpResumenDomicilio","totalDom");
        formatCurrencyFieldText(document.getElementById("totalDom"));
        $("#totalDomicilioLabel").html($("#totalDom").val());
        
        sumaColumna(event,"cmpResumenLlevar","totalLlevar");
        formatCurrencyFieldText(document.getElementById("totalLlevar"));
        $("#totalLlevarLabel").html($("#totalLlevar").val());
        
        sumaColumna(event,"cmpResumenTotal","totalTotal");
        formatCurrencyFieldText(document.getElementById("totalTotal"));
        $("#totalTotalLabel").html($("#totalTotal").val());
    });
});
