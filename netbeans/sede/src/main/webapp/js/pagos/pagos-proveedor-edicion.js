$(document).ready(function(){
    $(document).on("click","#buscarPagoProveedor",function (event){
        event.preventDefault();
        var urlProv = $(this).attr("data-url");
        var html = peticionAjax(urlProv,"POST","idpago="+$("#secuencia").val()+"&tipo="+$("#tipo").val());
        
        $("#contenidoFormularioPago").html(html);
    });
    
    $(document).on("click","#limpiarBusqueda",function (event){
        $("#divContenedorTabla").html("");
        $("#secuencia").val("");
       
        $("#cmpBeneficiario").hide();
        $("#nombreProveedor").val("");
        
        $("#cmpFecha").hide();
        $("#fechaPago").val("");
        
        $("#totalPago").val("");
        $("#cmpTotal").hide();
        
        $("#limpiarBusqueda").hide();
        
        $("#buscarPagoProveedor").show();
    });
});


