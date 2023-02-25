$(document).ready(function(){
    $(".unidadesCampo").live('keyup',function(e){
        var code = e.keyCode || e.which;
        //Reemplazo los valores que ingreso
        this.value = this.value.replace(/[^0-9]/g,''); 
        if ( code=='9') {
            var object = $(this);
            //focusNextInputField(1,$(this));
            return true;
        }else{
            
            fila = $(this).parents().get(1);
            cols = fila.cells;
            
            //Valores de cada input que estan en los td
            var unidadString =  cols[2].children[0].value;
            var valorUnidadString = cols[3].children[0].value;
            
            var vs = valorUnidadString;
            while(valorUnidadString.indexOf(",", 0) != -1){
                valorUnidadString = valorUnidadString.replace(",","");
            }
            //Elimino la parte decimal            
            var unidad = "0";
            
            //COndicion para que cuando haya al menos un número
            if(unidadString.length >=1){
                if(unidadString.charAt(0) == "0"){
                    unidadString = unidadString.substring(1);
                    this.value=unidadString;
                }   
                unidad = parseInt(unidadString);
            }else{//Si no hay nada coloco cero
                this.value="0";
            }
            var cadena = String.fromCharCode(code);
            //Calculo el total de ese producto en la fila
            if((parseInt(code) > 48 || parseInt(code) < 57) && (parseInt(code) > 96 || parseInt(code) < 106) ){
            //valorTotalString+=cadena;
            //$(this).value+=cadena;
            }else{
                cadena="";
            }
            cols[4].children[0].value = ""+ Math.round(valorUnidadString * unidad) ;
            formatCurrencyFieldText(cols[4].children[0]);
            //Actualizar el total de la factura
            //Obtengo a quien tiene las filas
            var totalFila = $(this).parents().get(2);
            calcularTotalFactura(totalFila);
            
            tecla = (document.all) ? e.keyCode : e.which;

            if (tecla==8) return true;
            if (tecla==0) return true;
            
            patron =/[1234567890]/;
            te = String.fromCharCode(tecla);
            return patron.test(te);
            
            if((parseInt(code) < 48 || parseInt(code) > 57) && (parseInt(code) < 96 || parseInt(code) > 106) ){
                return false;
            }else{
                return true;
            }
        }
    });
    $(document).on("keydown",".primerCampo2",function(e){
        var code = e.keyCode || e.which;
        if (  code=='9') {
            
            //Obtengo el input que recibió elevento
            var object = $(this);
            //Obtengo el tr que tiene el evento
            var fila = $(this).parents().get(1);
            url = $("#divContenedorTabla").data("url");    
            type = "POST";
            var tramaProducto="";
            $.ajax({
                url: url,
                timeout: 20000,
                type: type,
                data: "idProducto="+object.val(),
                async: false,
                success: function (result) {
                    tramaProducto= result;
                } 
            });
            
            var cols = fila.cells;

            var arrTrama = tramaProducto.split(",");
            if(arrTrama.length > 1){
                for(i=0;i<2;i++){
                    cols[i].children[0].value = arrTrama[i];
                    
                }
                
                //Coloco el promedio con el valor unitario
                cols[3].children[0].value = arrTrama[2];
                
                //coloco el focus en las unidades
                //lo que sucede eq que cuando obtengo el valor del keydown el nextab esta en elñ siguiente campo de ta 
                if(code=='13'){
                    focusNextInputField(2,$(this));
                }else{
                    focusNextInputField(0,$(this));
                }
            }else{
               
                $.colorbox({
                    html:"<p id='mensaje'>"+tramaProducto+"</p>",
                    initialHeight:50,
                    Height:50
                });
                
            }
        }else{
            if (code=='8') return true;
            if (code=='0') return true;
	    
            if((parseInt(code) < 48 || parseInt(code) > 57) && (parseInt(code) < 96 || parseInt(code) > 106) ){
                return false;
            }else{
                return true;
            }
        }
    });
    $(".totalCampo").live('keyup',function(e){
        var code = e.keyCode || e.which;
        //Reemplazo los valores que ingreso
        this.value = this.value.replace(/[^0-9]/g,'');
        
        //Obtengo el input que recibió elevento
        var object = $(this);
        //Obtengo el tr que tiene el evento
        var fila = $(this).parents().get(1);
        //Obtengo los td del tr
        var cols = fila.cells;
        if (code=='13' ) {
            var listUrl = $("#contenidoAveria").data("url");
            var strNueva_Fila='<tr>'+
            '<td><select class="clsAnchoTotal primerCampo"><option value="" >Seleccione</option>'+peticionAjaxProducto(listUrl, "POST", "")+'</select><input type="text" name="name" id="name" autocomplete="off" style="width: 93px;" class="primerCampo2"></td>'+
            '<td><input type="text" class="clsAnchoTotal" readonly="readonly"/></td>'+
            '<td><input type="text" class="clsAnchoTotal unidadesCampo"  /></td>'+
            '<td><input type="text" class="clsAnchoTotal" readonly="readonly"/></td>'+
            '<td><input type="text" readonly="readonly" class="clsAnchoTotal totalCampo" /></td>'+
            '<td align="right"><input type="button" value="-" class="clsEliminarFila"/></td>'+
            '</tr>';
				
            /*obtenemos el padre del boton presionado (en este caso queremos la tabla, por eso
		utilizamos get(3)
			table -> padre 3
				tfoot -> padre 2
					tr -> padre 1
						td -> padre 0
		nosotros queremos utilizar el padre 3 para agregarle en la etiqueta
		tbody una nueva fila*/
            var objTabla=$(this).parents().get(3);
				
            //agregamos la nueva fila a la tabla
            $(objTabla).find('tbody').append(strNueva_Fila);
				
            //si el cuerpo la tabla esta oculto (al agregar una nueva fila) lo mostramos
            if(!$(objTabla).find('tbody').is(':visible')){
                //le hacemos clic al titulo de la tabla, para mostrar el contenido
                $(objTabla).find('caption').click();
            }
            
            
            //avanzo los focus que necesito - El this debe ser un input
            focusNextInputField(2,$(this));
            
        }
        formatCurrencyFieldText(this);
    });
    
    function calcularTotalFactura(totalFila){
            
        //Arreglo que contiene todos los tr de la tabla en el tbody
        var tr = totalFila.children;
        //Variable acumuladora
        var totalFactura=0;
        //Ciclo para acumular cantidad
        var totalFactura = 0;
        for(i=0;i<tr.length ; i++){
                
            var valorFila = tr[i].children[4].children[0];
            if(valorFila.value != ''){
                var fila = valorFila.value;
                while(fila.indexOf(",", 0) != -1){
                    fila = fila.replace(",","");
                }
                totalFactura+=parseInt(fila);
            }
            
            
        }
            
        $("#totalAveria").val(totalFactura);
        formatCurrencyFieldText(document.getElementById("totalAveria"));
    }
    
    function focusNextInputField(campos,objeto) {

        var fields = objeto.parents('form:eq(0),body').find('button,input,textarea,select');
        var index = fields.index( objeto );
        if ( index > -1 && ( index + 1 ) < fields.length ) {
            fields.eq( index + campos ).focus();
        }
        
    
    };
    
    $(document).on("click","#averiar", function(e){
        //e.preventDefault();
        //variables en el llamado
        type:"POST";
    var fila = $("#contenidoAveria").children();
        var factura="";
        if($("#totalAveria").val()=="" || $("#totalAveria").val()=="0"){
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Averia Vac&iacute;a</p></body></html>",
                initialHeight:50,
                Height:50
            });    
        }else{
            for(i=0;i<fila.length;i++){
                var valor = fila[i].children[2].children[0].value
                if(valor != "" && valor!=undefined){
                    //valor = valor.split(".");
                    factura+=fila[i].children[0].children[0].value+","+fila[i].children[1].children[0].value+","
                    +quitarFormato(valor)+","+fila[i].children[3].children[0].value+","+quitarFormato(fila[i].children[4].children[0].value);
            
                    if(i != fila.length - 1){
                        factura+="@";
                    }
                }
            }
            $("#averia").val(factura);
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Guardando</p></body></html>",
                initialHeight:50,
                Height:50
            });
                
            $("#totalAveria").val(quitarFormato($("#totalAveria").val()));
            $("#detalleAveriaDTO").submit();
        }
       
    });
    
    //cuando se haga clic en cualquier clase .clsEliminarFila se dispara el evento
    $(document).on('click','.clsEliminarFila',function(){
        /*obtener el cuerpo de la tabla; contamos cuantas filas (tr) tiene
		si queda solamente una fila le preguntamos al usuario si desea eliminarla*/
        var objCuerpo=$(this).parents().get(2);
        if($(objCuerpo).find('tr').length==1){
            alert('No se puede eliminar la ultima fila');
            return;
				
        }
					
        /*obtenemos el padre (tr) del td que contiene a nuestro boton de eliminar
		que quede claro: estamos obteniendo dos padres
					
		el asunto de los padres e hijos funciona exactamente como en la vida real
		es una jergarquia. imagine un arbol genealogico y tendra todo claro ;)
				
			tr	--> padre del td que contiene el boton
				td	--> hijo de tr y padre del boton
					boton --> hijo directo de td (y nieto de tr? si!)
		*/
        var objFila=$(this).parents().get(1);
        /*eliminamos el tr que contiene los datos del contacto (se elimina todo el
			contenido (en este caso los td, los text y logicamente, el boton */
        $(objFila).remove();
        //Actualizar Total
        var totalFila = $(this).parents().get(2);
        //Arreglo que contiene todos los tr de la tabla en el tbody
        var tr = $("#contenidoAveria").children();
        //Variable acumuladora
        var totalFactura=0;
        //Ciclo para acumular cantidad
        for(i=0;i<tr.length ; i++){
            var valorFila = tr[i].children[4].children[0];
            if(valorFila.value != ''){
                var filaValor = valorFila.value;
                while(filaValor.indexOf(",", 0) != -1){
                    filaValor = filaValor.replace(",","");
                }
                totalFactura+=parseInt(filaValor);
            }
        }
            
        $("#totalAveria").val(totalFactura);
        formatCurrencyFieldText(document.getElementById("totalAveria"));
    });
    
    $(document).on('change',".primerCampo",function(e){
        fila = $(this).parents().get(1);
        cols = fila.cells;
        
        var select = $(this).find('option:selected');
        cols[0].children[1].value =  select.val();
        var elemento = $(this);
        
        //Consulta de datos
        //Obtengo el input que recibió elevento
        var object = $(this);
        //Obtengo el tr que tiene el evento
        var fila = $(this).parents().get(1);
        url = $("#divContenedorTabla").data("url");    
        type = "POST";
        var tramaProducto="";
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            data: "idProducto="+object.val(),
            async: false,
            success: function (result) {
                tramaProducto= result;
            } 
        });
        var listUrl = $("#contenidoAveria").data("url");
        var html = peticionAjaxProducto(listUrl, "POST", "");
        elemento.html("<select class='clsAnchoTotal primerCampo'><option value='"+select.val()+"' select>"+
                    + select.val() + "</option></select>");
        elemento.append(html);
        
        var cols = fila.cells;

        var arrTrama = tramaProducto.split(",");
        if(arrTrama.length > 1){
            for(i=0;i<2;i++){
                cols[i].children[0].value = arrTrama[i];
                
            }
            //Coloco el promedio con el valor unitario
            cols[3].children[0].value = arrTrama[2];
            //coloco el focus en las unidades
            //lo que sucede eq que cuando obtengo el valor del keydown el nextab esta en elñ siguiente campo de ta 
            focusNextInputField(2,$(this));
                
        }else{
               
            $.colorbox({
                html:"<p id='mensaje'>"+tramaProducto+"</p>",
                initialHeight:50,
                Height:50
            });
                
        }
        
    });
    function peticionAjaxProducto(url,type,parametros){
        var codigo="";
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            data: parametros,
            async: false,
            success: function (result) {
                codigo =  result;
                
                
                
            } 
        });
        return codigo;
    }
});
	




