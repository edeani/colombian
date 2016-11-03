$(document).on('ready', function () {
    //$('#tablaDomicilios').DataTable();

    $(document).on("click", ".viewOrder", function () {

    });
    $(document).on("click", ".aceptOrder", function () {
        var fila = $(this).attr("data-row");
        var inputIdpedido = $(".fieldPedido")[fila];

        $.confirm({
            confirmButtonClass: 'btn-success',
            cancelButtonClass: 'btn-danger',
            title: 'Alerta!',
            content: 'Domicilio #8 Eder Armando Anillo Lora<ul class="list-group">'+
  '<li class="list-group-item">Cras justo odio</li>'+
  '<li class="list-group-item">Dapibus ac facilisis in</li>'+
  '<li class="list-group-item">Morbi leo risus</li>'+
  '<li class="list-group-item">Porta ac consectetur ac</li>'+
  '<li class="list-group-item">Vestibulum at eros</li>'+
  '</ul >',
            confirmButton: 'Si',
            cancelButton: 'NO, para nada !',
            confirm: function () {
                $.ajax({
                    url: "/administracion/pedidos/ajax/aprobar.htm",
                    data: "idpedido=" + $(inputIdpedido).val(),
                    type: 'POST',
                    timeout: 20000,
                    success: function (response) {
                        //removeProductTable(fila);
                        if (response === "OK") {
                            $("#fila" + fila).removeClass("alert alert-danger");
                            $("#fila" + fila).addClass("alert alert-success");
                            $($(".aceptOrder")[fila]).hide();
                            var rejectDiv = $(".rejectOrder")[fila];

                            $(rejectDiv).show();

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

            },
            cancel: function () {

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
                    $($(".aceptOrder")[fila]).show();

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