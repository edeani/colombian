$(document).on('ready', function () {
    //$('#tablaDomicilios').DataTable();

    $(document).on("click", ".viewOrder", function () {

    });
    $(document).on("click", ".aceptOrder", function () {
        var fila = $(this).attr("data-row");
        var inputIdpedido = $(".fieldPedido")[fila];
        $.ajax({
            url: "/administracion/pedidos/ajax/aprobar.htm",
            data: "idpedido=" + $(inputIdpedido).val(),
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                //removeProductTable(fila);
                if (response === "OK") {
                    $("#fila" + fila).addClass("alert alert-success");
                    $($(".aceptOrder")[fila]).hide();
                    var rejectDiv = $(".rejectOrder")[fila];
                    if ($(rejectDiv).length > 0) {
                        $(rejectDiv).show();
                    }
                    $.dialog({
                        icon: 'fa fa-check',
                        title: 'Mensaje',
                        content: 'El pedido "' + $($(".fieldPedido")[fila]).val() + ' ha sido aprobado con éxito',
                    });
                } else {
                    $.dialog({
                        icon: 'fa fa-check',
                        title: 'Mensaje',
                        content: response
                    });
                }
            }
        });
    });
    $(document).on("click", ".rejectOrder", function () {
        var fila = $(this).attr("data-row");
        var inputIdpedido = $(".fieldPedido")[fila];
        $.ajax({
            url: "/administracion/pedidos/ajax/rechazar.htm",
            data: "idpedido=" + $(inputIdpedido).val(),
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                //removeProductTable(fila);
                if (response === "OK") {
                    $("#fila" + fila).removeClass("alert alert-succes");
                    $("#fila" + fila).addClass("alert alert-danger");
                    $($(".rejectOrder")[fila]).hide();
                    var rejectDiv = $(".aceptOrder")[fila];
                    if ($(rejectDiv).length > 0) {
                        $(rejectDiv).show();
                    }
                    $.dialog({
                        icon: 'fa fa-check',
                        title: 'Mensaje',
                        content: 'El pedido "' + $($(".fieldPedido")[fila]).val() + ' ha sido inactivado con éxito',
                    });
                } else {
                    $.dialog({
                        icon: 'fa fa-check',
                        title: 'Mensaje',
                        content: response
                    });
                }
            }
        });
    });
});