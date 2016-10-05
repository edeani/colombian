/* global field */

$(document).ready(function () {

    $(document).on('change', '.viewCantidad', function () {
        var indice = $(this).attr("data-row");
        var cantidad = $(this).val();
        //Total Producto
        var precioProducto = parseInt($("#p" + indice + "precio").val());
        var precioTotalAnterior = parseInt($("#p" + indice + "cantidad").val()) * precioProducto;

        var continuar = false;
        $.ajax({
            url: "/contenido/ajax/carrito/cantidad/actualizar.htm",
            data: "idproducto=" + $("#p" + indice + "idproducto").val() + "&precioProducto=" + precioProducto + "&cantidad=" + cantidad,
            async: false,
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                if (response === "OK") {
                    continuar = true;
                }
            }
        });
        if (continuar) {
            $("#p" + indice + "cantidad").val(cantidad);
            var precioTotalNuevo = parseInt(cantidad) * precioProducto;
            $("#p" + indice + "total").val(precioTotalNuevo);
            $("#p" + indice + "viewTotalProducto").html("$" + precioTotalNuevo);

            //Total Pedido
            var variacion = precioTotalAnterior - precioTotalNuevo;
            var totalPedidoAnterior = parseInt($("#totalPedido").val());

            var totalPedidoNuevo = totalPedidoAnterior - variacion;
            $("#totalPedido").val(totalPedidoNuevo);
            $("#viewTotalPedido").html("$" + totalPedidoNuevo);
        }
    });

    $(document).on("click", ".removeCar", function (event) {
        event.preventDefault();

        var indice = $(this).attr("data-row");
        var idproducto = $("#p" + indice + "idproducto").val();
        var continuar = false;
        $.ajax({
            url: "/compras/ajax/carrito/eliminar.htm",
            data: "idproducto=" + idproducto,
            async: false,
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                if (response === "OK") {
                    continuar = true;
                }
            }
        });
        if (continuar) {
            var variacion = parseInt($("#p" + indice + "total").val());
            var totalPedidoAnterior = parseInt($("#totalPedido").val());


            $("#fila" + indice).remove();
            var totalPedidoNuevo = totalPedidoAnterior - variacion;
            $("#totalPedido").val(totalPedidoNuevo);
            $("#viewTotalPedido").html("$" + totalPedidoNuevo);

            for (i = 0; i < $(".fila").length; i++) {
                var fila = $(".fila")[i];
                var indiceAnteriorFila = parseInt($(fila).attr("id").split("fila")[1]);
                if (indiceAnteriorFila !== i) {
                    $(fila).attr("id", "fila" + i);
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


                    $("#fila" + i + "td.indiceViewTotalProducto").attr("id", "p" + i + "viewTotalProducto");
                }
            }
        }
    });
});


