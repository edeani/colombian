$(document).on("ready", function () {

    $(document).on("click", "#save-user", function (event) {
        event.preventDefault();
        var resumenDireccion = "";
        var continuar = true;
        if ($("#componente").val() !== "" && $("#datoComponente").val() !== "" && $("#datoComponente1").val() !== "" && $("#datoComponente2").val() !== "") {
            resumenDireccion = "" + $("#componente").val() + " " + $("#datoComponente").val() + " #" + $("#datoComponente1").val() + "-" + $("#datoComponente2").val();
            var elemComponente = $("#componente").val().split(" ");
        } else {
            continuar = false;
            $("#direccion").val("");
        }
        if (resumenDireccion.length > 0) {
            var elementos = resumenDireccion.split(" ");
            if ((elementos.length === 3)||(elementos.length===4 && elemComponente.length===2)) {
                $("#direccion").val(resumenDireccion);
            } else {
                $("#direccion").val("");
                continuar = false;
            }
        }
        if (continuar) {
            $.ajax({
                url: "/user/ajax/actualizar-usuario.htm",
                data: $("#usuarioDto").serialize(),
                type: 'POST',
                timeout: 20000,
                success: function (response) {
                    var updateUser = getCookie("updateUser");
                    if (updateUser === "S") {
                        $.dialog({
                            icon: 'fa fa-check',
                            title: 'Mensaje',
                            content: 'Guardado con &Eacute;xito'
                        });
                    } else {
                        $("#datosUsuario").html(response);
                    }
                    guardarCookie("updateUser", "", adicionarFecha(-1));
                }
            });
        } else {
            console.log("Dirección incorrecta");
        }
    });
    
    $(document).on("click",".viewOrder",function(event){
        event.preventDefault();
        var idpedido = $(this).attr("data-idpedido");
        $.dialog({
            title:"Tú pedido",
            columnClass: 'col-md-10 col-md-offset-1',
            content: function () {
                var self = this;
                return $.ajax({
                    url: '/user/ajax/detalle-orden.htm',
                    dataType: 'html',
                    data: "idpedido="+idpedido,
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
    $(document).on("input", ".numeric", function () {
        this.value = this.value.replace(/[^\d\.\-]/g, '');
        
    });

    $(document).on("keypress",".textnumber",function (e) {
        var regex = new RegExp("^[a-zA-Z0-9]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }

        e.preventDefault();
        return false;
    });
});

function getCookie(nombre) {
    var cookies = document.cookie;
    if (!cookies)
        return false;
    var comienzo = cookies.indexOf(nombre);
    if (comienzo === -1)
        return false;
    comienzo = comienzo + nombre.length + 1;
    cantidad = cookies.indexOf("; ", comienzo) - comienzo;
    if (cantidad <= 0)
        cantidad = cookies.length;
    return cookies.substr(comienzo, cantidad);
}

function guardarCookie(nombre, valor, fecha) {
    document.cookie = nombre + "=" + valor + ";expires=" + fecha + ";path=/";
}

/**
 * Genera fecha de expiracion con dias apartir de hoy
 * @param ndias
 * @returns {Date}
 */
function adicionarFecha(ndias) {
    var fechaActual = new Date();
    var anno = fechaActual.getFullYear();
    var mes = fechaActual.getMonth();// En js los meses van de 0 a 11
    var dia = fechaActual.getDate();

    var stotalFecha = miSumarFechaDias(ndias, dia + "/" + mes + "/" + anno);
    var arr = stotalFecha.split("/");
    var totalFecha = new Date(arr[2], arr[1], arr[0]);

    return totalFecha;

}

/**
 * Genera fecha de expiracion en dias desde la fecha indicada
 * @param d
 * @param fecha
 * @returns
 */
function miSumarFechaDias(d, fecha)
{
    var Fecha = new Date();
    var sFecha = fecha || (Fecha.getDate() + "/" + (Fecha.getMonth() + 1) + "/" + Fecha.getFullYear());
    var sep = sFecha.indexOf('/') !== -1 ? '/' : '-';
    var aFecha = sFecha.split(sep);
    var fecha = aFecha[2] + '/' + aFecha[1] + '/' + aFecha[0];
    fecha = new Date(fecha);
    fecha.setDate(fecha.getDate() + parseInt(d));
    var anno = fecha.getFullYear();
    var mes = fecha.getMonth() + 1;
    var dia = fecha.getDate();
    mes = (mes < 10) ? ("0" + mes) : mes;
    dia = (dia < 10) ? ("0" + dia) : dia;
    var fechaFinal = dia + sep + mes + sep + anno;
    return (fechaFinal);
}


