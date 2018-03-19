$(document).ready(function () {

    $("#selectSede").focusin(function () {
        var user = $("#username").val();
        if (user !== "") {
            cargarSedes(user);
        } else {
            alert("Ingrese su nombre de usuario");
        }
    });

    $("#selectSede").on("change", function () {
        var url = $("#selectSede").attr("data-url-change");
        $.ajax({
            url: url,
            timeout: 20000,
            type: "POST",
            data: "nombreSede=" + $(this).find(":selected").text(),
            async: false,
            success: function (result) {
                if ($("#lpass").attr("data-view") === "N") {
                    $("#lpass").show();
                    $("#lpass").attr("data-view", "S");
                }
            }
        });
    });

    $("#selectSedeGeneric").focusin(function () {
        var user = $("#loginname").val();
        if (user !== "") {
            cargarSedesGeneric(user);
        } else {
            alert("Ingrese su nombre de usuario");
        }
    });

    $("#selectSedeGeneric").on("change", function () {
        var url = $("#selectSedeGeneric").attr("data-url-change");
        var sedeVal = $(this).find(":selected").text();
        var rtVal = "/" + sedeVal + "/home.htm";
        $("#rt").val(rtVal);
        $("#sede").val(sedeVal);
        $.ajax({
            url: url,
            timeout: 20000,
            type: "POST",
            data: "nombreSede=" + sedeVal,
            async: false,
            success: function (result) {
                if ($("#lpass").attr("data-view") === "N") {
                    $("#lpass").show();
                    $("#lpass").attr("data-view", "S");
                }
            }
        });
    });
    
    function cargarSedesGeneric(username) {
        var url = $("#selectSedeGeneric").attr("data-url");
        $("#selectSedeGeneric").attr("disabled", "disabled");
        /*var msjDialogo = $.dialog({
         title: 'Mensaje',
         content: 'Cargando sedes'
         });*/
        $.ajax({
            url: url,
            timeout: 20000,
            type: "POST",
            data: "username=" + $("#loginname").val(),
            async: false,
            success: function (result) {
                //msjDialogo.close();
                var primeraOpcion = '<option value="">Seleccionar Sede</option>';
                $("#selectSedeGeneric").html(primeraOpcion + result);
                $("#selectSedeGeneric").attr("disabled", false);
            }
        });


    }
    
    function cargarSedes(username) {
        var url = $("#selectSede").attr("data-url");
        $("#selectSede").attr("disabled", "disabled");
        /*var msjDialogo = $.dialog({
         title: 'Mensaje',
         content: 'Cargando sedes'
         });*/
        $.ajax({
            url: url,
            timeout: 20000,
            type: "POST",
            data: "username=" + $("#username").val(),
            async: false,
            success: function (result) {
                //msjDialogo.close();
                var primeraOpcion = '<option value="">Seleccionar Sede</option>';
                $("#selectSede").html(primeraOpcion + result);
                $("#selectSede").attr("disabled", false);
            }
        });


    }
});


