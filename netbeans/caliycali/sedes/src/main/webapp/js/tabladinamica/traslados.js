$(document).ready(function(){
    //Asigno datepickers a los campos que necesito, en este caso por clase dentro del html
    $( ".fechaInicial" ).datepicker({
        dateFormat: "yy-mm-dd"
    } );
    $( ".fechaFinal" ).datepicker({
        dateFormat: "yy-mm-dd"
    } ); 

    function modify(){
        $('#name').val($('.primerCampo').val());
        output();
    }

    function output(){
        $('p').text('value: ' + $('#name').val());
    }

    $('#name').on('click', function(){
        $(this).select()
    }).on('blur', function(){
        output();
    })

    modify();
    
    $(".unidadesCampo").live('keyup',function(e){
        var code = e.keyCode || e.which;
        //Reemplazo los valores que ingreso
        this.value = this.value.replace(/[^0-9]/g,''); 
        if ( code=='9') {
            var object = $(this);
            focusNextInputField(1,$(this));
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
    $(document).on("keypress",".totalCampo", function(e){
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
            
            var listUrl = $("#contenidoFactura").data("url");
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
    //formatCurrencyFieldText(this);
    });
    
    function calcularTotalFactura(totalFila){
            
        //Arreglo que contiene todos los tr de la tabla en el tbody
        var tr = totalFila.children;
        //Variable acumuladora
        var totalFactura=0;
        //Ciclo para acumular cantidad
        var totalFactura = 0;
        for(i=1;i<tr.length ; i++){
            var valorFila = tr[i].children[4].children[0];
            if(valorFila.value != ''){
                var fila = valorFila.value;
                while(fila.indexOf(",", 0) != -1){
                    fila = fila.replace(",","");
                }
                totalFactura+=parseInt(fila);
            }
            
            
        }
            
        $("#totalFactura").val(totalFactura);
        formatCurrencyFieldText(document.getElementById("totalFactura"));
    }
    
    function focusNextInputField(campos,objeto) {

        var fields = objeto.parents('form:eq(0),body').find('button,input,textarea,select');
        var index = fields.index( objeto );
        if ( index > -1 && ( index + 1 ) < fields.length ) {
            fields.eq( index + campos ).focus();
        }
        
    
    };
    
    $(document).on('click','#trasladar',function(event){
        event.preventDefault();
        var fila = $("#contenidoFactura").children();
        var factura="";
        var factura2="";
        var sedeOrigen = $("#sedeOrigen").val();
        var sedeDestino = $("#sedeDestino").val();
        if($("#sedeOrigen").val() == "" &&  $("#sedeDestino").val() == ""){
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Debe seleccionar la sede origen y de destino</p></body></html>",
                initialHeight:50,
                Height:50,
                close:'aceptar'
            }); 
        }else if($("#sedeOrigen").val() == $("#sedeDestino").val()){
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Las sedes deben ser diferentes</p></body></html>",
                initialHeight:50,
                Height:50,
                close:'aceptar'
            }); 
        }else if($("#sedeOrigen").val() == ""){
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Debe seleccionar Sede Origen</p></body></html>",
                initialHeight:50,
                Height:50,
                close:'aceptar'
            }); 
        }else if($("#sedeDestino").val() == ""){
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Debe seleccionar Sede Destino</p></body></html>",
                initialHeight:50,
                Height:50,
                close:'aceptar'
            }); 
        }else if($("#totalFactura").val()=="" || $("#totalFactura").val()=="0"){
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Factura Vacia</p></body></html>",
                initialHeight:50,
                Height:50,
                close:"aceptar"
            }); 
        }else{
            for(i=1;i<fila.length;i++){
                var unidades = quitarFormato(fila[i].children[2].children[0].value);
                var pesos = quitarFormato(fila[i].children[4].children[0].value);
                if(unidades != "" && unidades!=undefined){
                    //valor = valor.split(".");
                    factura+=fila[i].children[0].children[0].value+","+fila[i].children[1].children[0].value+","
                    +unidades+","+fila[i].children[3].children[0].value+","+pesos;
                    factura2+=fila[i].children[0].children[0].value+","+fila[i].children[1].children[0].value+",-"
                    +unidades+","+fila[i].children[3].children[0].value+","+"-"+pesos;
                    if(i != fila.length - 1){
                        factura+="@";
                        factura2+="@";
                    }
                }
            }
            
            factura2+="|"+factura;
            $("#totalFactura").val(quitarFormato($("#totalFactura").val()));
            $("#factura").val(factura2);
            url = $("#formFactura").data("url");
                    
            //var numeroFactura = $("#numeroFactura").val();
            var f = factura2;
            var html = "";
            var type = "POST";
                
            //pongo el loader
            loader("cargador", "calcular.gif");
            //Guardo el id de la sede
            var idsede = $("#sede").val();
            var totalFactura = $("#totalFactura").val();
            
           
            
            //Guardo el traslado
            var url  = $("#formFactura").data("url");
            var url2 = $("#detalleFacturaTrasladoDTO").attr("action");
            $("#factura").val(f);
            $("#sede").val(idsede);
            $("#totalFactura").val(totalFactura);
            $("#sedeOrigen").val(sedeOrigen);
            $("#sedeDestino").val(sedeDestino);
            //Trasladar factura
            var facturasT ="";
            $.ajax({
                url: url2,
                timeout: 20000,
                type: "POST",
                data: $("#detalleFacturaTrasladoDTO").serialize(),
                async: false,
                success: function (result) {
                    facturasT = result;
                } 
            });
            
            var arrFacturas = facturasT.split("@");
            var factOrigen = arrFacturas[0];
            var factDest = arrFacturas[1];
            var urlPdf="/chia/factura/facturaVentaPDF.htm";
            
            relocate(urlPdf,{'numeroFactura':factOrigen,'sede':sedeOrigen},"_blank");
            relocate(urlPdf,{'numeroFactura':factDest,'sede':sedeDestino},"_blank");
            //Restablezco valores
            //$("#numeroFactura").val(numeroFactura);
            /*$("#factura").val(f);
            $("#sede").val(idsede);
            $("#totalFactura").val(totalFactura);
            $("#sedeOrigen").val(sedeOrigen);
            $("#sedeDestino").val(sedeDestino);
            $("#detalleFacturaTrasladoDTO").submit();
            //var sec =parseInt($("#numeroFactura").val()) + 1;
            //$("#numeroFactura").val(sec);
            $("#factura").val("");*/
            
            $.ajax({
                url: url,
                timeout: 20000,
                type: "POST",
                async: false,
                success: function (result) {
                    $("#formFactura").html(result);
                } 
            });
            //Quito el loader
            loader("cargador", "");
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Guardado</p></body></html>",
                initialHeight:50,
                Height:50,
                close:"aceptar"
            });
            
            
            
            //Restablezco valores
            //$("#numeroFactura").val(numeroFactura);
            /*$("#factura").val(f);
            $("#sede").val(idsede);
            $("#totalFactura").val(totalFactura);
            $("#sedeOrigen").val(sedeOrigen);
            $("#sedeDestino").val(sedeDestino);
            $("#detalleFacturaTrasladoDTO").submit();
            //var sec =parseInt($("#numeroFactura").val()) + 1;
            //$("#numeroFactura").val(sec);
            $("#factura").val("");*/
            $("#totalFactura").val("");
           // 
        }
    });
    
    $(document).on('click','#actualizar',function(event){
        event.preventDefault();
        var fila = $("#contenidoFactura").children();
        var factura=""; 
        if($("#numeroFactura").val() == ""){
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Ingresar N&uacute;mero de factura</p></body></html>",
                initialHeight:50,
                Height:50,
                close:'aceptar'
            }); 
        }else{
            for(i=1;i<fila.length;i++){
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
            
            $("#totalFactura").val(quitarFormato($("#totalFactura").val()));
            $("#factura").val(factura);
            url = $("#formFactura").data("url");
            var html = "";
            var type = "POST";
            
            //pongo el loader
            loader("cargador", "calcular.gif");
            //Guardo los valores
            var idsede =$("#numeroSede").val();
            var numeroFactura = $("#numeroFactura").val();
            $.ajax({
                url: url,
                timeout: 20000,
                type: type,
                data: $("#detalleFacturaDTO").serialize(),
                async: false,
                success: function (result) {
                    $("#formFactura").html(result);
                } 
            });
            
            //Quito el loader
            loader("cargador", "");
            $("#sede").val(idsede);
            $("#numeroFactura").val(numeroFactura);
            $("#detalleFacturaDTO").submit();
            $.colorbox({
                html:"<!DOCTYPE html><html><body><p id='mensaje'>Guardado</p></body></html>",
                initialHeight:50,
                Height:50,
                close:"aceptar"
            });
            $("#numeroFactura").val("");
            $("#factura").val("");
        }
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
        var listUrl = $("#contenidoFactura").data("url");
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
    
    $(document).on('change','#estFac',function(){
        $("#estadoFactura").val($("#estFac").val());
    });
    function peticionAjaxProducto(url,type,parametros){
        var codigo="";
        $.ajax({
            url: url,
            timeout: 200000,
            type: type,
            data: parametros,
            async: false,
            success: function (result) {
                codigo =  result;

            } 
        });
        return codigo;
    }
    
    $(document).on('click','#btnBuscar',function(){
        var numeroFactura = $("#numeroFactura").val();
        if($("#numeroFactura").val() != ""){
            loader("cargador", "barra.gif");
            html = peticionAjaxProducto("/chia/factura/ajax/listaProductos.htm", "POST","numeroFactura="+numeroFactura);
            $("#contenidoFactura").html(html);
            loader("cargador", "");
            if($("#numeroSede").val() == ""){
                $("#labelSede").html("");
                $("#ids").val("");
                $("#ns").val("");
                $("#totalFactura").val("");
            }else{
                var htmlSelect="";
                if($("#estadoFactura").val() == "A"){
                    htmlSelect = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Estado Factura: <select id='estFac' > "+
                "<option value='A' selected>Activo</option>"+
                "<option value='I'>Inactivo</option>"+
                "</select>";
                }else{
                    htmlSelect = " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Estado Factura: <select id='estFac' name='estFac'> "+
                "<option value='A'>Activo</option>"+
                "<option value='I' selected>Inactivo</option>"+
                "</select>";
                }
                $("#ids").val($("#numeroSede").val());
                $("#ns").val($("#nombreSede").val());
                //Formato al número
                formatCurrencyFieldText(document.getElementById("totalFact"));
                //Asigno el valor
                $("#totalFactura").val($("#totalFact").val());
                
                $("#labelSede").html("Sede: "+$("#nombreSede").val()+htmlSelect);
                $("#campos").html("");
                
                //Seteo la sede en lista
                //$("#listaSedes option[value="+$("#numeroSede").val()+"]").attr("selected",true);
                $("#sede").val($("#numeroSede").val());
                
                seleccionarElementos();
            }
        }else{
            alert("Ingrese el n\xfamero de factura");
        }
    });
    
    function seleccionarElementos(){
        var fila = $("#contenidoFactura").children();
        for(i=1;i<fila.length;i++){
            var select = fila[i].children[0].children[0];
            var name = fila[i].children[0].children[1];
            var cantidad = select.length;
            for (j = 0; j < cantidad; j++) {
                if (select[j].value == name.value) {
                    select[j].selected = true;
                    break;
                }   
            }
            
        }
    }
    
    function cambiosede(){
        var select = $("#listaSedes").find('option:selected');
        url = $("#listaSedes").data('url');
        data = "idSede="+select.val()+"&nombreSede="+select.text();
        ejecutarAjax(url, data, "GET","listaSedes"); 
    }
    
    function ejecutarAjax(url,data,type,idCapa){
        $.ajax({
            url: url,
            timeout: 200000,
            type: type,
            data: data,
            dataType: 'html',
            success: function (result) {
                $("#"+idCapa).html(result);
            }
        });
    } 
    
    function loader(idDiv,load)
    {
        if(load!=""){
            $("#"+idDiv).html("<div style=' float: left; margin-left:40%;' ><img src='"+$("#rutaLoader").val()+load+"' /><br>Cargando ...</div>");
        }else{
            $("#"+idDiv).html("");
        }
    }
    function relocate(page,params,target)
    {
        var body = document.body;
        form=document.createElement('form');
        form.method = 'POST';
        form.action = page;
        form.name = 'jsform';
        form.id='jsform';
        form.target=target;
        for (index in params)
        {
        var input = document.createElement('input');
        input.type='hidden';
        input.name=index;
        input.id=index;
        input.value=params[index];
        form.appendChild(input);
        }
        body.appendChild(form);
        form.submit();
    } 
});
	


