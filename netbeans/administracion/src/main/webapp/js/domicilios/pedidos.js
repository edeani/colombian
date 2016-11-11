$(document).on('ready', function () {
    $('#tablaPedidos').DataTable();

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
        var formDatosAdicionales = $(".formDatos")[fila];
        $.confirm({
            columnClass: 'col-md-10 col-md-offset-1',
            confirmButtonClass: 'btn-success',
            cancelButtonClass: 'btn-danger',
            title: false,
            confirmButton: 'Si',
            cancelButton: 'NO, para nada !',
            content: function () {
                var self = this;
                return $.ajax({
                    url: '/administracion/pedidos/ajax/ver-detalle.htm',
                    dataType: 'html',
                    data: $(formDatosAdicionales).serialize() + "&cssClass=panel-success&estado=A",
                    method: 'post',
                    timeout: 6000
                }).done(function (response) {
                    self.setContent(response);
                }).fail(function () {
                    self.setContent('Ocurri&oacute; un problema al realizar la operaci&oacute;n');
                });
            },
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
                            mensajePedido('El pedido "' + $($(".fieldPedido")[fila]).val() + ' ha sido aprobado con &eacute;xito');
                        } else {
                            mensajePedido(response);
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
        var idpedido = $(inputIdpedido).val();
        var formDatosAdicionales = $(".formDatos")[fila];
        $.confirm({
            columnClass: 'col-md-10 col-md-offset-1',
            confirmButtonClass: 'btn-success',
            cancelButtonClass: 'btn-danger',
            title: false,
            confirmButton: 'Si',
            cancelButton: 'NO, para nada !',
            content: function () {
                var self = this;
                return $.ajax({
                    url: '/administracion/pedidos/ajax/ver-detalle.htm',
                    dataType: 'html',
                    data: $(formDatosAdicionales).serialize() + "&cssClass=panel-danger&estado=R",
                    method: 'post',
                    timeout: 6000
                }).done(function (response) {
                    self.setContent(response);
                }).fail(function () {
                    self.setContent('Ocurri&oacute; un problema al realizar la operaci&oacute;n');
                });
            },
            confirm: function () {
                $.ajax({
                    url: "/administracion/pedidos/ajax/rechazar.htm",
                    data: "idpedido=" + idpedido,
                    type: 'POST',
                    timeout: 20000,
                    success: function (response) {
                        //removeProductTable(fila);
                        if (response === "OK") {
                            $("#fila" + fila).removeClass("alert alert-succes");
                            $("#fila" + fila).addClass("alert alert-danger");
                            $($(".rejectOrder")[fila]).hide();
                            $($(".aceptOrder")[fila]).show();
                            mensajePedido('El pedido "' + $($(".fieldPedido")[fila]).val() + ' ha sido inactivado con &eacute;xito');
                        } else {
                            mensajePedido(response);
                        }
                    }
                });
            },cancel: function () {
            }
        });

    });
    function mensajePedido(mensaje) {
        $.dialog({
            icon: 'fa fa-check',
            title: 'Mensaje',
            content: mensaje
        });
    }
});