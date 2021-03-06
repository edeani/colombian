$(document).ready(function () {
    $(document).on("click", "a.removeProduct", function (event) {
        event.preventDefault();
        var fila = $(this).attr("data-row");
        var idproducto = $("#idproducto" + fila).val();
        var nombre = $("#nombre" + fila).val();
        $.confirm({
            confirmButtonClass: 'btn-success',
            cancelButtonClass: 'btn-danger',
            title: 'Alerta!',
            content: '¿Desea confirmar la eliminación del producto "' + nombre + '" con id ' + idproducto + '?',
            confirmButton: 'Si',
            cancelButton: 'NO, para nada !',
            confirm: function () {
                $.ajax({
                    url: "/administracion/productos/eliminar-producto.htm",
                    data: "idproducto=" + $("#idproducto" + fila).val(),
                    type: 'POST',
                    timeout: 20000,
                    success: function (response) {
                        removeProductTable(fila);
                        $.dialog({
                            icon: 'fa fa-check',
                            title: 'Mensaje',
                            content: 'El producto "' + nombre + '" con id:' + idproducto + ' ha sido eliminado con éxito',
                        });
                    }
                });

            },
            cancel: function () {

            }
        });
    });

    function removeProductTable(fila) {
        $("#fila" + fila).remove();

        for (i = 0; i < $(".fila").length; i++) {
            $($(".fila")[i]).attr("id", "fila" + i);
            $($(".editProduct")[i]).attr("data-row", i);
            $($(".removeProduct")[i]).attr("data-row", i);

            $($(".fieldProducto")[i]).attr("id", "idproducto" + i);
            $($(".fieldNombreProducto")[i]).attr("id", "nombre" + i);
        }
    }
});


