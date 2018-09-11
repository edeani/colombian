$(document).ready(function() {

    $("#fecha").datepicker({dateFormat: "yy-mm-dd"});

    /*$(".botonReporte").live('click',function(){
     var sede = $(this).val();
     var capa = "datos"+sede; 
     var fecha = $("#fecha").val();
     cargarAjaxReporte(capa, sede, fecha);
     });
     */
    $("#reporte").click(function() {
        var sede = $("#idsede").val();
        var nombreSede = $("#idsede option:selected").text();
        var datosReporte = null;
        var dr = null;
        var errorStatus = false;
        loader("cargador", "barra.gif");
        $.ajax({
            url: $("#contextpath").val()+"/"+$("#idpath").val()+"/inventario/ajax/sede.htm",
            timeout: 20000,
            type: "POST",
            data: "fecha=" + $("#fecha").val() + "&sede=" + nombreSede,
            dataType: 'json',
            async: false,
            success: function(result) {
                //datosReporte = jQuery.parseJSON(result);
                dr = result;
            },
            error: function(error){
                $("#cargador").html("No se pudo conectar a la Base de datos");
                errorStatus = true;
            }
        });
        var datos = "";
        for (i in datosReporte) {
            datos += datosReporte[i].stockFinal + "::";
        }

        var $objTabla = $('#tablaInventario');
        var filas = $objTabla.find('tbody tr');
        var heads = $objTabla.find('thead tr');
        var idcolumn = "head" + nombreSede;
        var classcell = "cell" + nombreSede;
        var etiquetaCelda = '<td class="' + classcell + ' celda">';
        var etiquetaHead = '<th id="' + idcolumn + '">';
        var hayDatos=false;
        var sedeFecha = $("#fecha").val()+"<br/>"+nombreSede;
        if ($("." + classcell).length == 0)
        {
            
            agregarHead(heads, etiquetaHead, sedeFecha);
            hayDatos = llenarFilas(filas, dr, etiquetaCelda);
            /*$.each(dr, function(index, item) {
             datos += dr[index].stockFinal + "::";
             hayDatos = true;
             $(etiquetaCelda).html(
             dr[index].stockFinal
             ).appendTo(filas[index]);
             });*/
            if (!hayDatos) {
                llenarFilasVacias(filas, etiquetaCelda);
            }
        } else {
            var filasClass = $("." + classcell);
            hayDatos = actualizarFilas(filasClass, dr);
            if (!hayDatos) {
                actualizarFilasVacias(filasClass);
            }
            actualizarHead(idcolumn,sedeFecha);
        }
        if(hayDatos){
            $("#cargador").html("Sede "+nombreSede+" consultada");
        }else{
            if(!errorStatus)
                $("#cargador").html("No hay registros "+nombreSede);

                
        }
    });

    function agregarHead(heads, etiquetaHead, nombreSede) {
        $(etiquetaHead).html(
               nombreSede
                ).appendTo(heads);
    }
    function actualizarHead(idHead, sedeFecha) {
        $("#"+idHead).html(
               sedeFecha
                );
    }
    function llenarFilas(filas, valores, etiquetaCelda) {
        var hayDatos = false;
        $.each(valores, function(index, item) {
            hayDatos = true;
            $(etiquetaCelda).html(
                    valores[index].stockFinal
                    ).appendTo(filas[index]);
        });
        return hayDatos;
    }
    function llenarFilasVacias(filas, etiquetaCelda) {
        var vacio = "";
        var tamProductos = filas.length;
        for (i = 0; i < tamProductos; i++) {
            $(etiquetaCelda).html(
                    vacio
                    ).appendTo(filas[i]);
        }
    }

    function actualizarFilas(filas, valores) {
        var hayDatos = false;
        $.each(valores, function(index, item) {
            hayDatos = true;
            $(filas[index]).html(valores[index].stockFinal);
            /*$(etiquetaCelda).html(
             dr[index].stockFinal
             ).appendTo(filas[index]);*/
        });
        return hayDatos;
    }

    function actualizarFilasVacias(filas) {
        var vacio = "";
        var tamProductos = filas.length;
        for (i = 0; i < tamProductos; i++) {
            $(filas[i]).html(vacio);
        }
    }
    function cargarAjaxReporte(capa, sede, fecha) {
        var url = $("#reporteInventario").data("url");
        type = "POST";
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            data: "fecha=" + $("#fecha").val() + "&sede=" + sede,
            dataType: 'html',
            async: true,
            success: function(result) {
                if (trim(result) == "") {
                    $("#" + capa).html("La base de datos est&aacute; desconectada");
                } else {
                    $("#" + capa).html(result);
                }

            }
            , error: function(result) {
                $("#" + capa).html("La base de datos est&aacute; desconectada");
            }
        });

    }

    function loader(idDiv, load)
    {
        if (load != "") {
            $("#" + idDiv).html("<div style=' float: left; margin-left:40%;' ><img src='" + $("#rutaLoader").val() + load + "' /><br></div>");
        } else {
            $("#" + idDiv).html("");
        }
    }
});

