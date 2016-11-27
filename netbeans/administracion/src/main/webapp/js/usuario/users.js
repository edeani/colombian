$(document).on("ready", function () {
    $(document).on("click", "#btn-search", function () {
        $.ajax({
            url: $("#contextpath").val() + "/usuarios/ajax/buscar-x-mail.htm",
            data: "email=" + $("#email").val(),
            type: 'POST',
            timeout: 20000,
            success: function (response) {
                $("#datosUsuario").html(response);
            }
        });
    });

    $(document).on("click", "#save-user", function (event) {
        event.preventDefault();
        var resumenDireccion = "";
        var continuar = true;
        if ($("#componente").val() !== "" && $("#datoComponente").val() !== "" && $("#datoComponente1").val() !== "" && $("#datoComponente2").val() !== "") {
            resumenDireccion = "" + $("#componente").val() + " " + $("#datoComponente").val() + " #" + $("#datoComponente1").val() + "-" + $("#datoComponente2").val();
        } else {
            continuar = false;
            $("#direccion").val("");
        }
        if (resumenDireccion.length > 0) {
            var elementos = resumenDireccion.split(" ");
            if (elementos.length === 3) {
                $("#direccion").val(resumenDireccion);
            } else {
                $("#direccion").val("");
                continuar = false;
            }
        }
        if (continuar) {
            $.ajax({
                url: $("#contextpath").val() + "/usuarios/ajax/actualizar-usuario.htm",
                data: $("#usuarioDto").serialize(),
                type: 'POST',
                timeout: 20000,
                success: function (response) {
                    if(response.indexOf("OK")!==-1){
                    }else{
                        
                    }
                }
            });
        }else{
            console.log("Dirección incorrecta");
        }
    });
    
    
    $(document).on("change","#estado",function(){
        var estadoUser = $("#estado option:selected").val();
                        if(estadoUser==="A"){
                            $("#datos1").removeClass("bg-warning");
                            $("#datos1").removeClass("bg-danger");
                            $("#datos2").removeClass("bg-warning");
                            $("#datos2").removeClass("bg-danger");
                            
                            $("#datos1").addClass("bg-succes");
                            $("#datos2").addClass("bg-succes");
                        }else if(estadoUser==="I"){
                            $("#datos1").removeClass("bg-succes");
                            $("#datos1").removeClass("bg-danger");
                            $("#datos2").removeClass("bg-succes");
                            $("#datos2").removeClass("bg-danger");
                            
                            $("#datos1").addClass("bg-warning");
                            $("#datos2").addClass("bg-warning");
                        }else if(estadoUser==="B"){
                            $("#datos1").removeClass("bg-succes");
                            $("#datos1").removeClass("bg-warning");
                            $("#datos2").removeClass("bg-succes");
                            $("#datos2").removeClass("bg-warning");
                            
                            $("#datos1").addClass("bg-danger");
                            $("#datos2").addClass("bg-danger");
                        }
                        
                   
    });
});


