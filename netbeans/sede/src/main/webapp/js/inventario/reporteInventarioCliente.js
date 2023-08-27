$(document).ready(function() {

    $("#fechaInicial").datepicker({dateFormat: "yy-mm-dd"});
    $("#fechaFinal").datepicker({dateFormat: "yy-mm-dd"});

    /*$(".botonReporte").live('click',function(){
     var sede = $(this).val();
     var capa = "datos"+sede; 
     var fecha = $("#fecha").val();
     cargarAjaxReporte(capa, sede, fecha);
     });
     */
    $(document).on("click","#consultarInventarioCilente",function(event) {
        event.preventDefault();
        var url = $("#formInventarioCliente").attr("action");
        var msjInvCliente = validarFormulario("#formInventarioCliente");
        if (msjInvCliente === "") {
            $("#listaInventario").html(peticionAjax(url, "POST", $("#formInventarioCliente").serialize()));
        } else {
            lightboxMensaje(msjInvCliente);
        }
        
    });

    
});

