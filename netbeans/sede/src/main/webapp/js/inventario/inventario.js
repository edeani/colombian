$(document).ready(function(){
    /*$('#tablaInventario').DataTable({
            lengthMenu: [[50, 80, 100], [50, 80, 100]],
            language: {
                search: "Buscar:",
                info: "Orden _START_ hasta _END_ de _TOTAL_",
                lengthMenu: "Mostrar _MENU_",
                loadingRecords: "Cargando...",
                processing: "Procesando...",
                zeroRecords: "No se encontraron elementos",
                paginate: {
                    first: "Primero",
                    previous: "Ant.",
                    next: "Sig.",
                    last: "&Uacute;ltimo"
                }
            }
        });*/
    //Asigno datepickers a los campos que necesito, en este caso por clase dentro del html
    $( ".fechaInicial" ).datepicker({
        dateFormat: "yy-mm-dd"
    } );
    $( ".fechaFinal" ).datepicker({
        dateFormat: "yy-mm-dd"
    } );
    
    $(".clsActualizarFila").live('click',function(){
        url = $("#contenidoInventario").data("actualizar");
        type="POST";
        var fila = $(this).parents().get(1);
        fila = fila.cells;
        var tramaInventario="";
        for(i=0;i<fila.length;i++){
            if(i<fila.length - 2){
                tramaInventario+= fila[i].children[0].value;
                if(i<fila.length - 3){
                    tramaInventario+="@";
                }
            }else{
                break;
            }
        }
        
        //Actualizo el registro en inventario
        var estado="";
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            data: "producto="+tramaInventario,
            async: false,
            success: function (result) {
                estado=result;
            } 
        });
        
        if(estado==""){
            $.colorbox({
                html:"<p id='mensaje'>El producto "+fila[0].children[0].value+" fue actualizado</p>",
                initialHeight:50,
                Height:50
            });
        }else{
            $.colorbox({
                html:"<p id='mensaje'>El producto no fue actualizado</p>",
                initialHeight:50,
                Height:50
            });
        }
        
    });

    $(".clsEliminarFila").live('click',function() {
        /*obtener el cuerpo de la tabla; contamos cuantas filas (tr) tiene
		si queda solamente una fila le preguntamos al usuario si desea eliminarla*/
        var objCuerpo=$(this).parents().get(2);
        if($(objCuerpo).find('tr').length==1){
            alert('No se puede eliminar la ultima fila');
            return;
				
        }
         
        var respuesta = confirm("Desea Eliminar el registro");
        /*obtenemos el padre (tr) del td que contiene a nuestro boton de eliminar
		que quede claro: estamos obteniendo dos padres
					
		el asunto de los padres e hijos funciona exactamente como en la vida real
		es una jergarquia. imagine un arbol genealogico y tendra todo claro ;)
				
			tr	--> padre del td que contiene el boton
				td	--> hijo de tr y padre del boton
					boton --> hijo directo de td (y nieto de tr? si!)
		*/
        if(respuesta){       
            var objFila=$(this).parents().get(1);
            var objIdProducto=objFila.cells;
                
            var idProducto =objIdProducto[0].children[0].value; 
            type = "POST";
            var tramaProducto="";
    
            var url = $("#divContenedorTabla").data("url");
            $.ajax({
                url: url,
                timeout: 20000,
                type: type,
                data: "idProducto="+idProducto,
                async: false,
                success: function (result) {
                    tramaProducto= result;
                } 
            });
            
            
            /*eliminamos el tr que contiene los datos del contacto (se elimina todo el
			contenido (en este caso los td, los text y logicamente, el boton */
            $(objFila).remove();
            //Actualizar Total
            var totalFila = $(this).parents().get(2);
            //Arreglo que contiene todos los tr de la tabla en el tbody
            var tr = $("#contenidoFactura").children();
            //Variable acumuladora
            var totalFactura=0;
            
            $.colorbox({
                html:"<p id='mensaje'>Producto eliminado</p>",
                initialHeight:50,
                Height:50
            });
        }
    });   
    
    $(".clsAgregarFila").live('click',function(e){
        
        e.preventDefault();
        var estadoFormulario="";
        
        //validar campos  vacios
        $("#idInventario").find(':input').each(function() {
            var elemento= this;
            if($(this).val() == ""){
                if($(this).is(".contentRequired")){
                    $(this).addClass("campError");
                    estadoFormulario = "Hay campos vac&iacute;os";
                }
            }else{
                if($(this).is(".contentRequired")){
                    $(this).removeClass("campError");
                }
            }
        }); 
        
        
        if(estadoFormulario==""){
            type = "POST";
            var response="";
            //guardar producto
            var url = $("#idInventario").attr("action");
            $.ajax({
                url: url,
                timeout: 20000,
                type: type,
                data: $("#idInventario").serialize(),
                async: false,
                success: function (result) {
                    response= result;
                } 
            });
            
                        
            //Si el producto ya  existe
            if(response == ""){
                //Limpio los campos del formulario
                $("#idInventario").find(':input').each(function() {
                    if(!$(this).is(".clsAgregarFila")){
                        $(this).val("");
                    }
                });
                //Actualizar lista
                var url = $("#contenidoInventario").data("url");
                $.ajax({
                    url: url,
                    timeout: 20000,
                    type: type,
                    data: $("#idInventario").serialize(),
                    async: false,
                    success: function (result) {
                        $("#contenidoInventario").html(result);
                    } 
                });
                
                $.colorbox({
                    html:"<p id='mensaje'>Producto guardado</p>",
                    initialHeight:50,
                    Height:50
                });
                
                
            } else{
                $.colorbox({
                    html:"<p id='mensaje'>"+response+"</p>",
                    initialHeight:50,
                    Height:50
                });
            }
        }else{
            $.colorbox({
                html:"<p id='mensaje'>"+estadoFormulario+"</p>",
                initialHeight:50,
                Height:50
            });
        }
         
    });
    
    $(".descripcionProducto").live('keyup',function(e){
        getContenidoCaracteres(this, $(this).val());
        //Luego un uppercase
        this.value=this.value.toUpperCase();
    });
    
});


