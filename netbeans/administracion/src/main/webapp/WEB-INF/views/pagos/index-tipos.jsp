<%-- 
    Document   : pedido
    Created on : 13/09/2016, 06:05:27 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Tipos de Pago</title>
        <!-- Custom Css ================================================== -->
        <link rel="stylesheet" type="text/css" href="/administracion/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/main.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/responsive.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/jquery-confirm.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/datatable/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/jquery/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/jquery-confirm-pedidos.css">

        <!-- Fonts ================================================== -->
        <link href='<%=request.getContextPath()%>/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-ui.min.js"></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/woocommerce.js'></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-confirm.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/json/jquery.json.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/tipos-pago/tipos-pago.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="container rst-main-content">

                <div class="text-center">
                    <h1 class="h1pedido">Tipos de Pago</h1>
                </div>            
                <br />
                <div class="row">
                    <div class="col-sm-7 col-lg-offset-3">
                        <table class="table table-border-row table-card" id="tablaPedidos">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th class="product-name">Nombre</th>
                                    <th>Estado</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th class="product-name">Nombre</th>
                                    <th>Estado</th>
                                    <th></th>
                                </tr>
                            </tfoot>
                            <tbody id="listaPagos">
                                <c:import url="/tipos-pago/ajax/list.htm"></c:import>
                            </tbody>
                        </table>
                    </div>
                </div>
                <br /><br /><br />
            </div>
        </div><!-- End Content -->
    </body>
</html>
