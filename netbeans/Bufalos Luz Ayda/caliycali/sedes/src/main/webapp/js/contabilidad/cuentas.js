$(document).ready(function(){
    var valorCampoIdCuenta = "";
    $(document).on("click","#btnBuscarCuenta",function(event){
        event.preventDefault();
        var url = $("#cuentasPuc").attr("action");
        var parametros = "idCuenta="+$("#codCta").val();
        loader("cargador","barra.gif");
        var html = peticionAjax(url,"post",parametros);
        loader("cargador","");
        $("#contenedorCuenta").html(html); 
    });
    
     
    $("#codCta").autocomplete({
        source: $("#contextpath").val()+"/cuentas/ajax/autocompletar.htm",
        select: function(event,ui){
            valorCampoIdCuenta = ui.item.idCuenta;
        }
        , close: function(event,ui){
            $("#codCta").val(valorCampoIdCuenta);
            var url = $("#cuentasPuc").attr("action");
            var parametros = "idCuenta="+$("#codCta").val();
            loader("cargador","barra.gif");
            var html = peticionAjax(url,"post",parametros);
            loader("cargador","");
            $("#contenedorCuenta").html(html);
            
        }
    });
    
    $(document).on("click","#btnActualizarCuenta",function(event){
        event.preventDefault();
        var url = $("#contextpath").val()+"/cuentas/ajax/actualizar.htm";
        var parametros = $("#cuentasPuc").serialize();
        var respuesta = peticionAjax(url,"post",parametros);
        $("#codCta").val("");
        $("#contenedorCuenta").html("");
        
    });
    
    $("#btnGuardaCuenta").on("click",function(event){
        event.preventDefault();
        var parametros = $("#cuentasPuc").serialize();
        var respuesta = peticionAjax(url,"post",parametros);
        if(respuesta==="ok"){
            lightboxMensaje("Guardado");
        }else{
            lightboxMensaje("Error al guardar");
        }
    });
});

