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
                
        var suma_total_mesas=sumaColumna(event,"cmpResumenMesa","totalMesas");
        formatCurrencyFieldText(document.getElementById("totalMesas"));
        $("#totalMesaLabel").html($("#totalMesas").val());
        
        var suma_total_dom=sumaColumna(event,"cmpResumenDomicilio","totalDom");
        formatCurrencyFieldText(document.getElementById("totalDom"));
        $("#totalDomicilioLabel").html($("#totalDom").val());
        
        var suma_total_llevar=sumaColumna(event,"cmpResumenLlevar","totalLlevar");
        formatCurrencyFieldText(document.getElementById("totalLlevar"));
        $("#totalLlevarLabel").html($("#totalLlevar").val());
        
        $("#totalTotal").val((suma_total_dom+suma_total_llevar+suma_total_mesas));
        formatCurrencyFieldText(document.getElementById("totalTotal"));
        $("#totalTotalLabel").html($("#totalTotal").val());
    });
});
