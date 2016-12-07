var tableTiposPago = null;
var basePath = "";
$(document).on('ready', function () {
    basePath = $("#contextpath").val();
    tableTiposPago = configuracionTabla();

    $(document).on("click", ".editar", function (event) {
        var fila = $(this).parent().attr("data-index");
        $.confirm({
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
                            url: basePath + "/tipos-pago/ajax/actualizar.htm",
                            data: $("#formProcess").serialize(),
                            type: 'POST',
                            timeout: 20000,
                            success: function (response) {
                                if (response === "OK") {
                                    mensajePedido('Actualizado');
                                    $.ajax({
                                        url: $("#contextpath").val() + "/tipos-pago/ajax/list.htm",
                                        type: 'POST',
                                        timeout: 20000,
                                        success: function (response) {
                                            tableTiposPago.clear();
                                            tableTiposPago.destroy();
                                            $("#listaPagos").html(response);
                                            tableTiposPago = configuracionTabla();
                                        }
                                    });
                                } else {
                                    mensajePedido(response);
                                }
                            }
                        });
                    }
                },
                cancelar: {
                    btnClass: 'btn-danger',
                    text: 'Cancelar',
                    action: function () {

                    }
                }
            },
            content: function () {
                var self = this;
                return $.ajax({
                    url: basePath + '/tipos-pago/ajax/tipo-producto.htm',
                    dataType: 'html',
                    data: $("#form" + fila).serialize(),
                    method: 'post',
                    timeout: 10000
                }).done(function (response) {
                    self.setContent(response);
                }).fail(function () {
                    self.setContent('Ocurri&oacute; un problema al realizar la operaci&oacute;n');
                });
            }
        });
    });
   
    function mensajePedido(mensaje) {
        $.dialog({
            icon: 'fa fa-check',
            title: 'Mensaje',
            content: '<div class="alert alert-success alert-dismissible" role="alert">' +
                    '<strong></strong> ' + mensaje + '</div>'
        });
    }

    function configuracionTabla() {
        var conf = $('#tablaPedidos').DataTable({
            lengthMenu: [[5], [5]],
            language: {
                info: "Formas de Pago _START_ hasta _END_ de _TOTAL_",
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
});