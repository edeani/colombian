$(document).ready(function () {
    $("#fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $("#fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    
    $("#consultarGastos").click(function(event){
        event.preventDefault();
        var url = $("#formGastos").attr("action");
        var html = peticionAjax(url,"POST",$("#formGastos").serialize());
        $("#reporteGastos").html(html);
        //$("#datosGastos").treetable({ expandable: true });
    });
    
    $("#idSede").change(function(){
        var sel = $("#idSede option:selected").text();
        $("#nombreSede").val(sel);
    });
});


