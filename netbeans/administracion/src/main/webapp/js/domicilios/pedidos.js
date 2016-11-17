var valorTextoProd = "";
var prodSeleccionado = "[]";
var sizeFilas = 0;
var dialogAddProduct = null;
$(document).on('ready', function () {
    $('#tablaPedidos').DataTable();
    $(document).on("click", ".viewOrder", function () {
        var fila = $(this).attr("data-row");
        var inputIdpedido = $("#pedido" + fila);
        var idpedido = $(inputIdpedido).val();
        var formDatosAdicionales = $("#formDatos" + fila);
        $.confirm({
            closeIcon: true,
            closeIconClass: 'fa fa-close',
            title: false,
            columnClass: 'col-md-10 col-md-offset-1',
            escapeKey: true,
            backgroundDismiss: true,
            buttons: {
                aceptar: {
                    btnClass: 'btn-success',
                    text: 'Aceptar',
                    action: function () {
                        $.ajax({
                            url: "/administracion/pedidos/ajax/aprobar.htm",
                            data: "idpedido=" + idpedido,
                            type: 'POST',
                            timeout: 20000,
                            success: function (response) {
                                if (response === "OK") {
                                    $("#f" + fila).removeClass("alert alert-danger");
                                    $("#f" + fila).addClass("alert alert-success");
                                    $($(".aceptOrder")[fila]).hide();
                                    var rejectDiv = $(".rejectOrder")[fila];
                                    $(rejectDiv).show();
                                    mensajePedido('El pedido "' + $($(".fieldPedido")[fila]).val() + ' ha sido aprobado con &eacute;xito');
                                } else {
                                    mensajePedido(response);
                                }
                            }
                        });
                    }
                },
                rechazar: {
                    btnClass: 'btn-danger',
                    text: 'Rechazar',
                    action: function () {
                        $.ajax({
                            url: "/administracion/pedidos/ajax/rechazar.htm",
                            data: "idpedido=" + idpedido,
                            type: 'POST',
                            timeout: 20000,
                            success: function (response) {
                                //removeProductTable(fila);
                                if (response === "OK") {
                                    $("#f" + fila).removeClass("alert alert-succes");
                                    $("#f" + fila).addClass("alert alert-danger");
                                    $($(".rejectOrder")[fila]).hide();
                                    $($(".aceptOrder")[fila]).show();
                                    mensajePedido('El pedido "' + $($(".fieldPedido")[fila]).val() + ' ha sido inactivado con &eacute;xito');
                                } else {
                                    mensajePedido(response);
                                }
                            }
                        });
                    }

                }
            },
            content: function () {
                var self = this;
                return $.ajax({
                    url: '/administracion/pedidos/ajax/ver-detalle.htm',
                    dataType: 'html',
                    data: $(formDatosAdicionales).serialize(),
                    method: 'post',
                    timeout: 10000
                }).done(function (response) {
                    self.setContent(response);
                }).fail(function () {
                    self.setContent('Ocurri&oacute; un problema al realizar la operaci&oacute;n');
                });
            },
        });


    });
    $(document).on('change', '.viewCantidad', function () {
        var indice = $(this).attr("data-row");
        var cantidad = $(this).val();
        //Total Producto
        var precioProducto = parseInt($("#p" + indice + "precio").val());
        var precioTotalAnterior = parseInt($("#p" + indice + "cantidad").val()) * precioProducto;


        $("#p" + indice + "cantidad").val(cantidad);
        var precioTotalNuevo = parseInt(cantidad) * precioProducto;
        $("#p" + indice + "total").val(precioTotalNuevo);
        $("#p" + indice + "viewTotalProducto").html(precioTotalNuevo);

        //Total Pedido
        var variacion = precioTotalAnterior - precioTotalNuevo;
        var totalPedidoAnterior = parseInt($("#totalPedido").val());

        var totalPedidoNuevo = totalPedidoAnterior - variacion;
        $("#totalPedido").val(totalPedidoNuevo);
        $("#viewTotalPedido").html(totalPedidoNuevo);

    });

    $(document).on("click", ".removeCar", function (event) {
        event.preventDefault();

        var indice = $(this).attr("data-row");
        var idproducto = $("#p" + indice + "idproducto").val();

        var variacion = parseInt($("#p" + indice + "total").val());
        var totalPedidoAnterior = parseInt($("#totalPedido").val());


        $("#f" + indice).remove();
        var totalPedidoNuevo = totalPedidoAnterior - variacion;
        $("#totalPedido").val(totalPedidoNuevo);
        $("#viewTotalPedido").html(totalPedidoNuevo);

        actualizarIndicesPedido("f", "f");
    });

    $(document).on("keypress", ".viewCantidad", function (event) {
        if (event.which === 13) {
            event.preventDefault();
            $(this).next().focus();
        }
    });
    $(document).on("click", "#addProductoPanel", function (event) {
        event.preventDefault();
        sizeFilas = $(".f").length;

        $.dialog({
            closeIcon: true,
            closeIconClass: 'fa fa-close',
            escapeKey: true,
            backgroundDismiss: true,
            columnClass: 'col-md-10 col-md-offset-1',
            title: false,
            content: function () {
                dialogAddProduct = this;
                var self = this;
                return $.ajax({
                    url: $("#contextpath").val() + '/productos/ajax/admin/nuevo-producto.htm',
                    dataType: 'html',
                    method: 'post',
                    timeout: 10000
                }).done(function (response) {
                    self.setContent(response);
                }).fail(function () {
                    self.setContent('Ocurri&oacute; un problema al realizar la operaci&oacute;n');
                });
            },
            onContentReady: function () {
                $("#textoProducto").autocomplete({
                    minLength: 2,
                    source: $("#contextpath").val() + "/productos/ajax/autocompletar.htm",
                    select: function (event, ui) {
                        valorTextoProd = ui.item.value;
                        prodSeleccionado = ui.item;
                        return false;
                    }
                    , close: function (event, ui) {
                        $("#textoProducto").val(valorTextoProd);
                        var totalCompraProducto = parseInt(prodSeleccionado.precio) * parseInt($("#viewAddCantidad").val());
                        $("#totalProducto").val(totalCompraProducto);
                        prodSeleccionado.cantidad = parseInt($("#viewAddCantidad").val());
                        $('#addProductoOrder').prop('disabled', false);
                        /*$.ajax({
                         url: $("#contextpath").val() + "/productos/ajax/admin/content-producto.htm",
                         data: "jsonProducto=" + $.toJSON(jsonSelected)+"&sizeFilas="+sizeFilas,
                         type: 'POST',
                         timeout: 20000,
                         success: function (response) {
                         
                         }
                         });*/


                    }
                });
            }
        });
    });

    $(document).on("change", "#viewAddCantidad", function () {
        var cantidadActual = parseInt($(this).val());
        var precioProdSel = prodSeleccionado.precio;
        prodSeleccionado.cantidad = cantidadActual;
        if (precioProdSel !== null && precioProdSel !== undefined) {
            $("#totalProducto").val(cantidadActual * parseInt(precioProdSel));
            $('#addProductoOrder').prop('disabled', false);
        } else {
            $('#addProductoOrder').prop('disabled', true);
        }
    });

    $(document).on("click", "#addProductoOrder", function (event) {
        event.preventDefault();
        //TODO: Agregar producto a la grilla del pedido
        $.ajax({
            url: $("#contextpath").val() + "/productos/ajax/admin/content-producto.htm",
            data: "jsonProducto=" + $.toJSON(prodSeleccionado) + "&sizeFilas=" + sizeFilas,
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                $("#listProduct").append(response);
                var tot = parseInt(prodSeleccionado.precio)*parseInt(prodSeleccionado.cantidad)+parseInt($("#totalPedido").val());
                $("#viewTotalPedido").html(tot);
                $("#totalPedido").val(tot);
                dialogAddProduct.close();
                dialogAddProduct = null;
            }
        }).fail(function () {
            dialogAddProduct.setContent('Ocurri&oacute; un problema al realizar la operaci&oacute;n');
        });
    });
    
    function mensajePedido(mensaje) {
        $.dialog({
            icon: 'fa fa-check',
            title: 'Mensaje',
            content: mensaje
        });
    }

    function actualizarIndicesPedido(clase, id) {
        for (i = 0; i < $("." + clase).length; i++) {
            var fila = $("." + clase)[i];
            var indiceAnteriorFila = parseInt($(fila).attr("id").split(id)[1]);
            if (indiceAnteriorFila !== i) {
                $(fila).attr("id", id + i);
                var objUpdateData = $(fila).find(".indiceData");
                for (j = 0; j < $(objUpdateData).length; j++) {
                    $($(objUpdateData)[j]).attr("data-row", i);
                }

                $("#p" + indiceAnteriorFila + "idproducto").attr("id", "p" + i + "idproducto");
                $("#p" + indiceAnteriorFila + "nombreproducto").attr("id", "p" + i + "nombreproducto");
                $("#p" + indiceAnteriorFila + "precio").attr("id", "p" + i + "precio");
                $("#p" + indiceAnteriorFila + "cantidad").attr("id", "p" + i + "cantidad");
                $("#p" + indiceAnteriorFila + "total").attr("id", "p" + i + "total");

                $("#p" + i + "idproducto").attr("name", "productos[" + i + "].idproducto");
                $("#p" + i + "nombreproducto").attr("name", "productos[" + i + "].nombreproducto");
                $("#p" + i + "precio").attr("name", "productos[" + i + "].precio");
                $("#p" + i + "cantidad").attr("name", "productos[" + i + "].cantidad");
                $("#p" + i + "total").attr("name", "productos[" + i + "].total");


                $("#f" + i + " td.indiceViewTotalProducto").attr("id", "p" + i + "viewTotalProducto");
            }
        }
    }
    function procesarPedido(mensaje, accion) {


    }
});