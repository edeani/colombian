$(document).ready(function () {
    $(document).on("click", "#buscarPagoProveedor", function (event) {
        event.preventDefault();
        if ($("#secuencia").val() === "" || $("#secuencia") === undefined) {
            
            dialogMessage("No. comprobante vacío");
            
        } else {

            var urlProv = $(this).attr("data-url");
            var html = peticionAjax(urlProv, "POST", "idpago=" + $("#secuencia").val() + "&tipo=" + $("#tipo").val());

            $("#contenidoFormularioPago").html(html);
            var estadoEncontrado = $("#encontrado").val();

            if (estadoEncontrado === "N") {
                dialogMessage("Pago proveedor no encontrado");
            }
        }
    });

    $(document).on("click", "#limpiarBusqueda", function (event) {
        limpiarBusquedaProveedor();
    });

    $(document).on("click", "#reset-pago", function (event) {
        event.preventDefault();

        var coleccionCompras = "";
        var filasCompras = $("#tblDatos tbody").find(".claseCompra");

        for (i = 0; i < filasCompras.length; i++) {
            coleccionCompras += $(filasCompras[i]).val();
            if (i < filasCompras.length - 1) {
                coleccionCompras += "@";
            }
        }

        var urlActualizar = $("#pagosProveedorDto").attr("url-update-compras");

        var respuesta = peticionAjax(urlActualizar, "POST", "idscompras=" + coleccionCompras + "&idProveedor=" + $("#idProveedor").val());

        if (respuesta === "ok") {
         
            location.href = $("#pagosProveedorDto").attr("action");
        } else {
            dialogMessage("Error guardando pago proveedor");
        }


    });

});

function limpiarBusquedaProveedor() {
    $("#divContenedorTabla").html("");
    $("#secuencia").val("");

    $("#cmpBeneficiario").hide();
    $("#nombreProveedor").val("");

    $("#cmpFecha").hide();
    $("#fechaPago").val("");

    $("#totalPago").val("");
    $("#cmpTotal").hide();

    $("#limpiarBusqueda").hide();

    $("#buscarPagoProveedor").show();
}


