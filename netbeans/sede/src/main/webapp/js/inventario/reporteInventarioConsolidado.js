$(document).ready(function() {

    $("#fechaInicial").datepicker({dateFormat: "yy-mm-dd"});
    $("#fechaFinal").datepicker({dateFormat: "yy-mm-dd"});


    $(document).on("click","#consultarInventarioConsolidado",function(event) {
        event.preventDefault();
        var url = $("#formInventarioConsolidado").attr("action");
        var msjInvCliente = validarFormulario("#formInventarioConsolidado");
        if (msjInvCliente === "") {
            $("#listaInventario").html(peticionAjax(url, "POST", $("#formInventarioConsolidado").serialize()));
        } else {
            lightboxMensaje(msjInvCliente);
        }
        
    });

    
});

