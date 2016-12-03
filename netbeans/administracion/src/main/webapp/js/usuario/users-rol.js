$(document).on("ready", function () {
    $(document).on("click", "#btn-search-rol", function () {
        $.ajax({
            url: $("#contextpath").val() + "/usuarios/ajax/buscar-x-mail-rol.htm",
            data: "email=" + $("#email").val(),
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                $("#datosUsuario").html(response);
            }
        });
    });

    $(document).on("click", "#save-rol", function (event) {
        event.preventDefault();
        
            $.ajax({
                url: $("#contextpath").val() + "/usuarios/ajax/actualizar-usuario-rol.htm",
                data: "idrol="+$("#idrol option:selected").val()+"&idusuario="+$("#idusuario").val(),
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
    });


    $("#email").keypress(function (event) {
        if (event.which === 13) {
            $("#btn-search").trigger("click");
        }
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