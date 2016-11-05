$(document).on('ready', function () {
    //$('#tablaDomicilios').DataTable();

    $(document).on("click", ".viewOrder", function () {
        /*
         * function () {
         var self = this;
         return $.ajax({
         url: '/administracion/pedidos/ajax/ver-detalle.htm',
         dataType: 'html',
         method: 'post',
         timeout: 6000,
         }).done(function (response) {
         self.setContent(response);
         }).fail(function () {
         self.setContent('Ocurri&oacute; un problema al realizar la operaci&oacute;n');
         });
         }
         * 
         */
    });
    $(document).on("click", ".aceptOrder", function () {
        var fila = $(this).attr("data-row");
        var inputIdpedido = $(".fieldPedido")[fila];
        var idpedido = $(inputIdpedido).val();
        $.confirm({
            confirmButtonClass: 'btn-success',
            cancelButtonClass: 'btn-danger',
            title: 'Alerta!',
            content: function () {
                var self = this;
                return $.ajax({
                    url: '/administracion/pedidos/ajax/ver-detalle.htm',
                    dataType: 'html',
                    data:'idpedido='+idpedido,
                    method: 'post',
                    timeout: 6000,
                }).done(function (response) {
                    self.setContent(response);
                }).fail(function () {
                    self.setContent('Ocurri&oacute; un problema al realizar la operaci&oacute;n');
                });
            },
            confirmButton: 'Si',
            cancelButton: 'NO, para nada !',
            confirm: function () {
                $.ajax({
                    url: "/administracion/pedidos/ajax/aprobar.htm",
                    data: "idpedido=" + idpedido,
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