<%-- 
    Document   : clientes
    Created on : 20/11/2016, 10:23:25 PM
    Author     : edeani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Administración de usuarios</title>
        <!-- Custom Css ================================================== -->
        <link rel="stylesheet" type="text/css" href="/administracion/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/main.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/responsive.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/jquery-confirm.css">
        <!-- Fonts ================================================== -->
        <link href='<%=request.getContextPath()%>/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/woocommerce.js'></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-confirm.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/usuario/users.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="container rst-main-content">
                <div class="text-center">
                    <h1 class="h1pedido">Usuarios</h1>
                </div>
                <br />
                <div class="row">
                    <div class="col-md-10">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">Ingrese el mail del cliente</span>
                            <input id="email" class="form-control" value="" placeholder="e-mail"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="input-group">
                            <button id="btn-search" class="btn btn-default" style="background: #ec652f"><i class="fa fa-search fa-lg" aria-hidden="true"></i>Buscar</button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            
                        </div>
                        <div class="form-group">
                            
                        </div>
                        <div class="form-group">
                            
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            
                        </div>
                        <div class="form-group">
                            
                        </div>
                        <div class="form-group">
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
