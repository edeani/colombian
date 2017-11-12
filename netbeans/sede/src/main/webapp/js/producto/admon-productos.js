var tableProd = null;
$(document).ready(function () {
    tableDomi = configuracionTabla();
    $(document).on("click", "a.removeProduct", function (event) {
        event.preventDefault();
        var fila = $(this).attr("data-row");
        var idproducto = $("#idproducto" + fila).val();
        var nombre = $("#nombre" + fila).val();
        $.confirm({
            confirmButtonClass: 'btn-success',
            cancelButtonClass: 'btn-danger',
            title: 'Alerta!',
            content: '¿Desea confirmar la inactivación del producto "' + nombre + '" con id ' + idproducto + '?',
            confirmButton: 'Si',
            cancelButton: 'NO, para nada !',
            confirm: function () {
                $.ajax({
                    url: "/administracion/productos/eliminar-producto.htm",
                    data: "idproducto=" + $("#idproducto" + fila).val(),
                    type: 'POST',
                    timeout: 20000,
                    success: function (response) {
                        //removeProductTable(fila);
                        $("#fila"+fila).addClass("alert alert-danger");
                        $("#activeButtons"+fila).hide();
                        $("#inactiveButtons"+fila).show();
                        $.dialog({
                            icon: 'fa fa-check',
                            title: 'Mensaje',
                            content: 'El producto "' + nombre + '" con id:' + idproducto + ' ha sido inactivado con éxito',
                        });
                    }
                });

            },
            cancel: function () {

            }
        });
    });
/**
 * Remueve una fila de la tabla de productos
 * @param {type} fila
 * @returns {undefined}
 */
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

$(document).on("click", "a.recoverProduct", function (event) {
        event.preventDefault();
        var fila = $(this).attr("data-row");
        var idproducto = $("#idproducto" + fila).val();
        var nombre = $("#nombre" + fila).val();
        $.ajax({
            url: "/administracion/productos/activar-producto.htm",
            data: "idproducto=" + $("#idproducto" + fila).val(),
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                //removeProductTable(fila);
                $("#fila"+fila).removeClass("alert alert-danger");
                $("#activeButtons"+fila).show();
                $("#inactiveButtons"+fila).hide();
                $.dialog({
                    icon: 'fa fa-check',
                    title: 'Mensaje',
                    content: 'El producto "' + nombre + '" con id:' + idproducto + ' ha sido activado con éxito',
                });
            }
        });
});

function configuracionTabla() {
        var conf = $('#tablaProductos').DataTable({
            lengthMenu: [[10, 30, 40], [10, 30, 40]],
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
        });
        return conf;
    }
