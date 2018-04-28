$(document).ready(function () {
    //Asigno datepickers a los campos que necesito, en este caso por clase dentro del html
    $(".fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(".fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(".fechaVencimiento").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(".unidadesCampo").live('keypress', function (e) {
        var code = e.keyCode || e.which;
        if (code == '13' || code == '9') {
            var object = $(this);
            focusNextInputField(1, $(this));
            return true;
        } else {

            tecla = (document.all) ? e.keyCode : e.which;

            if (tecla == 8)
                return true;
            if (tecla == 0)
                return true;
            patron = /[1234567890]/;
            te = String.fromCharCode(tecla);
            return patron.test(te);
        }
    });
    $(document).on("keydown", ".primerCampo2", function (e) {
        var code = e.keyCode || e.which;
        if (code == '9') {

            //Obtengo el input que recibió elevento
            var object = $(this);
            //Obtengo el tr que tiene el evento
            var fila = $(this).parents().get(1);
            url = $("#divContenedorTabla").data("url");
            type = "POST";
            var tramaProducto = "";
            $.ajax({
                url: url,
                timeout: 20000,
                type: type,
                data: "idProducto=" + object.val(),
                async: false,
                success: function (result) {
                    tramaProducto = result;
                }
            });

            var cols = fila.cells;

            var arrTrama = tramaProducto.split(",");
            if (arrTrama.length > 1) {
                for (i = 0; i < 2; i++) {
                    cols[i].children[0].value = arrTrama[i];

                }

                //Coloco el promedio con el valor unitario
                //cols[3].children[0].value = arrTrama[2];

                //coloco el focus en las unidades
                //lo que sucede eq que cuando obtengo el valor del keydown el nextab esta en elñ siguiente campo de ta 
                if (code == '13') {
                    focusNextInputField(2, $(this));
                } else {
                    focusNextInputField(0, $(this));
                }
            } else {
                dialogMessage(tramaProducto);

            }
        } else {
            if (code == '8')
                return true;
            if (code == '0')
                return true;

            if ((parseInt(code) < 48 || parseInt(code) > 57) && (parseInt(code) < 96 || parseInt(code) > 106)) {
                return false;
            } else {
                return true;
            }
        }
    });
    $(".totalCampo").live('keyup', function (e) {
        var code = e.keyCode || e.which;
        //Reemplazo los valores que ingreso
        this.value = this.value.replace(/[^0-9]/g, '');

        //Obtengo el input que recibió elevento
        var object = $(this);
        //Obtengo el tr que tiene el evento
        var fila = $(this).parents().get(1);
        //Obtengo los td del tr
        var cols = fila.cells;
        if (code == '13') {

            var listUrl = $("#contenidoFactura").data("url");
            var strNueva_Fila = '<tr>' +
                    '<td><select class="clsAnchoTotal primerCampo"><option value="" >Seleccione</option>' + peticionAjaxProducto(listUrl, "POST", "") + '</select><input type="text" name="name" id="name" autocomplete="off" style="width: 93px;" class="primerCampo2"></td>' +
                    '<td><input type="text" class="clsAnchoTotal" readonly="readonly"/></td>' +
                    '<td><input type="text" class="clsAnchoTotal unidadesCampo"  /></td>' +
                    '<td><input type="text" class="clsAnchoTotal" readonly="readonly"/></td>' +
                    '<td><input type="text" class="clsAnchoTotal totalCampo" /></td>' +
                    '<td align="right"><input type="button" value="-" class="clsEliminarFila"/></td>' +
                    '</tr>';

            /*obtenemos el padre del boton presionado (en este caso queremos la tabla, por eso
             utilizamos get(3)
             table -> padre 3
             tfoot -> padre 2
             tr -> padre 1
             td -> padre 0
             nosotros queremos utilizar el padre 3 para agregarle en la etiqueta
             tbody una nueva fila*/
            var objTabla = $(this).parents().get(3);

            //agregamos la nueva fila a la tabla
            $(objTabla).find('tbody').append(strNueva_Fila);

            //si el cuerpo la tabla esta oculto (al agregar una nueva fila) lo mostramos
            if (!$(objTabla).find('tbody').is(':visible')) {
                //le hacemos clic al titulo de la tabla, para mostrar el contenido
                $(objTabla).find('caption').click();
            }


            //avanzo los focus que necesito - El this debe ser un input
            focusNextInputField(2, $(this));

        } else {


            //Valores de cada input que estan en los td
            var unidadString = cols[2].children[0].value;
            var valorTotalString = cols[4].children[0].value;

            var vs = valorTotalString;
            while (valorTotalString.indexOf(",", 0) != -1) {
                valorTotalString = valorTotalString.replace(",", "");
            }

            //Elimino la parte decimal            
            var unidad = "0";

            //COndicion para que cuando haya al menos un número
            if (unidadString.length >= 1) {
                if (unidadString.charAt(0) == "0") {
                    //unidadString = unidadString.substring(1);
                    this.value = unidadString;
                }
                unidad = parseInt(unidadString);
            } else {//Si no hay nada coloco cero
                this.value = "0";
            }
            var cadena = String.fromCharCode(code);
            //Calculo el total de ese producto en la fila
            if ((parseInt(code) > 48 || parseInt(code) < 57) && (parseInt(code) > 96 || parseInt(code) < 106)) {
                //valorTotalString+=cadena;
                //$(this).value+=cadena;
            } else {
                cadena = "";
            }
            cols[3].children[0].value = "" + valorTotalString / unidad;

            //Actualizar el total de la factura
            //Obtengo a quien tiene las filas
            var totalFila = $(this).parents().get(2);
            calcularTotalFactura(totalFila);


        }
        formatCurrencyFieldText(this);
    });

    function calcularTotalFactura(totalFila) {

        //Arreglo que contiene todos los tr de la tabla en el tbody
        var tr = totalFila.children;
        //Variable acumuladora
        var totalFactura = 0;
        //Ciclo para acumular cantidad
        var totalFactura = 0;
        for (i = 0; i < tr.length; i++) {

            var valorFila = tr[i].children[4].children[0];
            if (valorFila.value != '') {
                var fila = valorFila.value;
                while (fila.indexOf(",", 0) != -1) {
                    fila = fila.replace(",", "");
                }
                totalFactura += parseInt(fila);
            }


        }

        $("#totalFactura").val(totalFactura);
        formatCurrencyFieldText(document.getElementById("totalFactura"));
    }

    function focusNextInputField(campos, objeto) {

        var fields = objeto.parents('form:eq(0),body').find('button,input,textarea,select');
        var index = fields.index(objeto);
        if (index > -1 && (index + 1) < fields.length) {
            fields.eq(index + campos).focus();
        }


    }

    $("#pre-facturar").live("click", function (e) {
        e.preventDefault();
        $.confirm({
            title: 'Seleccionar Impresora',
            content: "url:" + $("#contextpath").val() + "/" + $("#idpath").val() + "/compras/ajax/impresoras.htm",
            columnClass: 'ancho-confirm',
            buttons: {
                aceptar: {
                    text: "aceptar",
                    action: function () {
                        $("#impresora").val($("#confirm-impresora").val());
                        $("#nombreProveedor").val($("#codigoProveedor option:selected").text());
                        this.close();
                        $("#facturar").click();
                    }
                },
                cancelar: {
                    text: "cancelar",
                    action: function () {
                        console.log("Cancelado");
                    }
                }
            }
        });
    });
    $(document).on("change","#codigoProveedor",function(){
        if ($("#numeroFactura").val() !== "" && $("#codigoProveedor").val() !== "") {
            var existe = verificarCompras();
            if (existe == "true") {
                dialogMessage("La compra " + $(this).val() + ", ya existe para este proveedor");
            }
        }
    });
    $(document).on("focusout", "#numeroFactura", function () {
        if ($(this).val() !== "" && $("#codigoProveedor").val() !== "") {
            var existe = verificarCompras();
            if (existe == "true") {
                dialogMessage("La compra " + $(this).val() + ", ya existe para este proveedor");
            }
        }
    });
    $("#facturar").live("click", function (e) {
        //e.preventDefault();
        //variables en el llamado
        var type = "POST";
        var fila = $("#contenidoFactura").children();
        var factura = "";

        var respuestaVerificacion = verificarCompras();
        if (respuestaVerificacion === "false") {
            if ($("#codigoProveedor").val() != "") {
                if ($("#numeroFactura").val() != "") {
                    if ($("#fechaVencimiento").val() != "") {
                        if ($("#idsede").val() != "") {
                            if ($("#totalFactura").val() == "") {
                                $.colorbox({
                                    html: "<!DOCTYPE html><html><body><p id='mensaje'>Compra Vacia</p></body></html>",
                                    initialHeight: 50,
                                    Height: 50,
                                    width: "300px"
                                });
                            } else {
                                for (i = 0; i < fila.length; i++) {
                                    var valor = fila[i].children[2].children[0].value
                                    if (valor != "" && valor != undefined) {
                                        //valor = valor.split(".");

                                        factura += fila[i].children[0].children[0].value + "," + fila[i].children[1].children[0].value + ","
                                                + quitarFormato(valor) + "," + fila[i].children[3].children[0].value + "," + quitarFormato(fila[i].children[4].children[0].value);

                                        if (i != fila.length - 1) {
                                            factura += "@";
                                        }
                                    }
                                }

                                $("#factura").val(factura);
                                /*var dialogoGuardar = $.dialog({
                                 columnClass: 'ancho-confirm',
                                 title: 'Mensaje',
                                 content: 'Guardando',
                                 closeIcon: false
                                 });*/
                                $("#totalFactura").val(quitarFormato($("#totalFactura").val()));

                                var parametrosForm = $("#detalleCompraDTO").serialize();
                                $.ajax({
                                    url: $("#urlGuardar").attr("url-guardar"),
                                    timeout: 20000,
                                    type: "POST",
                                    data: parametrosForm,
                                    success: function (result) {
                                        //dialogoGuardar.close();
                                        $("#detalleCompraDTO").submit();
                                        $("#contenidoCompra").html(result);
                                        $(".fechaVencimiento").datepicker({
                                            dateFormat: "yy-mm-dd"
                                        });
                                        dialogMessage("Compra guardada");
                                    },
                                    error: function (jqXHR, textStatus, errorThrown) {
                                        dialogMessage("Error al guardar la compra");
                                    }
                                });


                            }
                        } else {
                            $.colorbox({
                                html: "<!DOCTYPE html><html><body><p id='mensaje'>Debe ingresar la sede</p></body></html>",
                                initialHeight: 50,
                                Height: 50,
                                width: "300px"
                            });
                        }
                    } else {
                        $.colorbox({
                            html: "<!DOCTYPE html><html><body><p id='mensaje'>Debe ingresar la fecha de Vencimiento</p></body></html>",
                            initialHeight: 50,
                            Height: 50,
                            width: "300px"
                        });
                    }
                } else {
                    $.colorbox({
                        html: "<!DOCTYPE html><html><body><p id='mensaje'>Debe ingresar n&uacute;mero de Factura</p></body></html>",
                        initialHeight: 50,
                        Height: 50,
                        width: "300px"
                    });
                }
            } else {
                $.colorbox({
                    html: "<!DOCTYPE html><html><body><p id='mensaje'>Debe seleccionar un proveedor/p></body></html>",
                    initialHeight: 50,
                    Height: 50,
                    width: "300px"
                });
            }
        } else {
            lightboxMensaje("Esta Compra ya existe");
        }
    });
    /*------------------------Actualizar Compra--------------------------*/
    $("#pre-actualizar").live("click", function (e) {
        e.preventDefault();
        $.confirm({
            title: 'Seleccionar Impresora',
            content: "url:" + $("#contextpath").val() + "/" + $("#idpath").val() + "/compras/ajax/impresoras.htm",
            columnClass: 'ancho-confirm',
            buttons: {
                aceptar: {
                    text: "aceptar",
                    action: function () {
                        $("#impresora").val($("#confirm-impresora").val());
                        this.close();
                        $("#actualizar").click();
                    }
                },
                cancelar: {
                    text: "cancelar",
                    action: function () {
                        console.log("Cancelado");
                    }
                }
            }
        });
    });

    $("#actualizar").live("click", function (e) {
        e.preventDefault();
        //variables en el llamado
        type:"POST";
        var fila = $("#contenidoFactura").children();
        var factura = "";
        if ($("#codigoProveedor").val() != "") {
            if ($("#numeroFactura").val() != "") {
                if ($("#totalFactura").val() == "") {
                    dialogMessage("Compra Vacia");
                } else {
                    for (i = 0; i < fila.length; i++) {
                        var valor = fila[i].children[2].children[0].value
                        if (valor != "" && valor != undefined) {
                            //Aca es 1 en 0
                            factura += fila[i].children[0].children[1].value + "," + fila[i].children[1].children[0].value + ","
                                    + quitarFormato(valor) + "," + fila[i].children[3].children[0].value + "," + quitarFormato(fila[i].children[4].children[0].value);

                            if (i != fila.length - 1) {
                                factura += "@";
                            }
                        }
                    }

                    $("#factura").val(factura);
                    /*var dialogoGuardar = $.dialog({
                     columnClass: 'ancho-confirm',
                     title: 'Mensaje',
                     content: 'Guardando',
                     closeIcon: false
                     });*/
                    $("#totalFactura").val(quitarFormato($("#totalFactura").val()));
                    var parametrosForm = $("#detalleCompraDTO").serialize();
                    $.ajax({
                        url: $("#urlGuardar").attr("url-guardar"),
                        timeout: 20000,
                        type: "POST",
                        data: parametrosForm,
                        success: function (result) {
                            //dialogoGuardar.close();
                            $("#submit-form").val("S");
                            $("#detalleCompraDTO").submit();
                            $("#submit-form").val("N");
                            $("#contenidoCompra").html(result);
                            dialogMessage("Guardado Exitosamente");
                        }
                    });


                    $("#detalleCompraDTO").submit();
                    console.log(factura);
                }
            } else {
                dialogMessage("Debe ingresar n&uacute;mero de Factura");

            }
        } else {
            $.colorbox({
                html: "<!DOCTYPE html><html><body><p id='mensaje'>Debe seleccionar un proveedor/p></body></html>",
                initialHeight: 50,
                Height: 50,
                width: "300px"
            });
        }
    });

    $(document).on("submit", "#detalleCompraDTO", function () {
        if ($("#submit-form").val() == "S") {
            return true;
        } else {
            return false;
        }

    });

    /*------------------------Buscar Compra--------------------------*/
    $(document).on('click', '#buscarCompra', function () {
        var url = $(this).data("url");
        var idcompra = $("#numeroFactura").val();
        var tipo = "POST";
        var params = "idcompra=" + idcompra;
        console.log(url);
        console.log(idcompra);
        loader("cargador", "barra.gif");
        var compraHTML = peticionAjaxProducto(url, tipo, params);
        $("#cargador").html("");
        $("#contenidoCompra").html(compraHTML);
    });
    $(document).on("change", "#idSede", function () {
        var valorIdSede = $("#idSede option:selected").val();
        var idSedePoint = peticionAjax($("#idsedepoint").attr("data-url"), "post", "idSede=" + valorIdSede);
        $("#idsedepoint").val(idSedePoint);
    });
    $(document).on("change", "#idsede", function () {
        var valorIdSede = $("#idsede option:selected").val();
        var idSedePoint = peticionAjax($("#idsedepoint").attr("data-url"), "post", "idSede=" + valorIdSede);
        $("#idsedepoint").val(idSedePoint);
    });
    /*------------------------Reporte de compras totales--------------------------*/
    $(document).on('click', '#reporteComprasTotalesProveedor', function (e) {

        $("#nombreProveedor").val($("#codigoProveedor option:selected").text());

    });

    $(document).on('change', ".primerCampo", function (e) {

        fila = $(this).parents().get(1);
        cols = fila.cells;

        var select = $(this).find('option:selected');
        cols[0].children[1].value = select.val();
        var elemento = $(this);

        //Consulta de datos
        //Obtengo el input que recibió elevento
        var object = $(this);
        //Obtengo el tr que tiene el evento
        var fila = $(this).parents().get(1);
        url = $("#divContenedorTabla").data("url");
        type = "POST";
        var tramaProducto = "";
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            data: "idProducto=" + object.val(),
            async: false,
            success: function (result) {
                tramaProducto = result;
            }
        });
        var listUrl = $("#contenidoFactura").data("url");

        var html = peticionAjaxProducto(listUrl, "POST", "");
        elemento.html("<select class='clsAnchoTotal primerCampo'><option value='" + select.val() + "' select>" +
                +select.val() + "</option></select>");
        elemento.append(html);

        var cols = fila.cells;

        var arrTrama = tramaProducto.split(",");
        if (arrTrama.length > 1) {
            for (i = 0; i < 2; i++) {
                cols[i].children[0].value = arrTrama[i];

            }
            //coloco el focus en las unidades
            //lo que sucede eq que cuando obtengo el valor del keydown el nextab esta en elñ siguiente campo de ta 
            focusNextInputField(2, $(this));

        } else {

            $.colorbox({
                html: "<p id='mensaje'>" + tramaProducto + "</p>",
                initialHeight: 50,
                Height: 50,
                width: "300px"
            });

        }

    });
    function verificarCompras() {
        var urlVerificacion = $("#detalleCompraDTO").attr("data-verificacion");
        var respuesta = peticionAjax(urlVerificacion, "POST", "idcompra=" + $("#numeroFactura").val()
                +"&codigoProveedor="+$("#codigoProveedor").val());
        return respuesta;
    }
    function peticionAjaxProducto(url, type, parametros) {
        var codigo = "";
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            data: parametros,
            async: false,
            success: function (result) {
                codigo = result;



            }
        });
        return codigo;
    }
    function loader(idDiv, load)
    {
        if (load != "") {
            $("#" + idDiv).html("<div style=' float: left; margin-left:40%;' ><img src='" + $("#rutaLoader").val() + load + "' /><br>Cargando ...</div>");
        } else {
            $("#" + idDiv).html("");
        }
    }
});



