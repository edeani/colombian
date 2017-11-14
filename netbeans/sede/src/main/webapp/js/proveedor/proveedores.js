$(document).ready(function(){
    $(document).on('change','#selectProveedor',function(){
        
        var url = $("#divproveedores").data("url");
        var idproveedor = $("#selectProveedor").val();

        if(idproveedor!==""){
            loader("cargador", "barra.gif");
            var parametros = "idproveedor="+idproveedor;
            var tipo = "POST";
            var resultado = peticionAjax(url,tipo,parametros);
            $("#proveedores").html(resultado);
            $("#divContenedorTabla").show();
            loader("cargador", "");
        }
    });
    
    $(document).on('click','#actualizarProveedor',function(e){
        e.preventDefault();
        var url = $("#proveedores").data("actualizar");
        var estadoFormulario ="";
        var formulario = document.getElementById("formProveedores");
        
        estadoFormulario = validarFormulario(formulario);
        
        loader("cargador", "barra.gif");
        if(estadoFormulario===""){
            var parametros = $("#formProveedores").serialize();
            var tipo = "POST";
            var html = peticionAjax(url,tipo,parametros);
            if(html==="ok"){
                $("#proveedores").html("");
                $("#divContenedorTabla").hide()
                lightboxMensaje("Proveedor Actualizado");
               
            }else{
                lightboxMensaje("Error en el proceso");
            }
            
        }else{
            lightboxMensaje("Hay campos requeridos por llenar");
        }
        loader("cargador", "");
         
    });
    
    $(document).on('click','#eliminarProveedor',function(e){
        e.preventDefault();
        var url = $("#proveedores").data("eliminar");
        var idproveedor = $("#idproveedor").val();
        
        var rdialog=confirm("Desea Eliminar el proveedor ... ");
        if(rdialog===true){        
            loader("cargador", "barra.gif");
            var parametros = "idproveedor="+idproveedor;
            var tipo = "POST";
            var respuesta = peticionAjax(url, tipo, parametros);
            if(respuesta==="ok"){
                cargarProveedoresSelect();
                $("#divContenedorTabla").hide();
                $("#proveedores").html("");
                lightboxMensaje("Proveedor Eliminado");
            }else{
                lightboxMensaje("Error en el proceso");
            }
            loader("cargador", "");
        }
    });
    
    $(document).on('click','#nuevoProveedor',function(e){
        e.preventDefault();
        var url = $("#proveedores").data("nuevo");
            var parametros = "";
            var tipo = "POST";
            var respuesta = peticionAjax(url, tipo, parametros);
            $("#proveedores").html(respuesta);
            $("#divContenedorTabla").show();
    });
    $(document).on('click','#agregarProveedor',function(e){
        e.preventDefault();
        var url = $("#proveedores").data("guardar");
        var estadoFormulario ="";
        var formulario = document.getElementById("formProveedores");
        
        estadoFormulario = validarFormulario(formulario);
        
        loader("cargador", "barra.gif");
        if(estadoFormulario===""){
            var parametros = $("#formProveedores").serialize();
            var tipo = "POST";
            var html = peticionAjax(url,tipo,parametros);
            if(html==="ok"){
                cargarProveedoresSelect();
                $("#divContenedorTabla").hide();
                $("#proveedores").html("");
                lightboxMensaje("Proveedor Creado");
            }else{
                lightboxMensaje("Error en el proceso");
            }
            
        }else{
            lightboxMensaje("Hay campos requeridos por llenar");
        }
        loader("cargador", "");
         
    });
    
    function cargarProveedoresSelect(){
        var tipo = "POST";
        $("#selectProveedor").html("");
        var urlSelect =  $("#selectProveedor").data("cargarselect");
        var htmlselect = peticionAjax(urlSelect, tipo, "");
        $("#selectProveedor").append("<option value=''>Seleccione Proveedor</option>");
        $("#selectProveedor").append(htmlselect);
    }
});


