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
        
        var urlVentasMesa = $("#contDomicilios").attr("data-url");
        var htmlMesas = peticionAjaxAsync(urlVentasMesa,tipoPeticion,parametros);
        $("#contenidodomicilios").html(htmlMesas);
        
        var suma_total_mesas=sumaColumnaWithoutFormat(event,"cmpResumenDomicilios","totalDomicilios");
        formatCurrencyFieldText(document.getElementById("totalDomicilios"));
        $("#totalDomiciliosLabel").html($("#totalDomicilios").val());
        
        $("#totalCantDomiciliosLabel").html($("#totalDom").val());

    });
});
