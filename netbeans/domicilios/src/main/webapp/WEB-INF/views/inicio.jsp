<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de Inicio</title>
        <script src="/domicilios/js/jquery-3.1.0.min.js"></script>
    </head>
    <body>
        Holaaaa Eder
        -------------------------------------------
        ${mensaje}
        -------------------------------------------
        <br>
        <input type="button" value="crear" id="crearUsuario"/>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#crearUsuario").click(function (event) {
                    event.preventDefault();
                    $.ajax({
                        url: '/domicilios/crear.htm',
                        timeout: 20000,
                        type: "POST",
                        dataType: 'html',
                        async: false,
                        success: function (respuesta) {
                           console.log(respuesta);
                        }
                    });
                });
            });
        </script>
    </body>
</html>