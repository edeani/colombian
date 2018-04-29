$(document).ready(function(){
    $("#fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $("#fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(document).on("click","#consultarReporteConsolidadoSede",function(){
        $("#formReporteComprobante").submit();
    });
});


