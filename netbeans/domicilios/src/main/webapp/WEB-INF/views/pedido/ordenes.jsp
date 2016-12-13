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
        <title>Administraci&oacute;n de Productos</title>
        <!-- Custom Css ================================================== -->
        <link rel="stylesheet" type="text/css" href="/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link rel="stylesheet" type="text/css" href="/css/responsive.css">
        <link rel="stylesheet" type="text/css" href="/css/jquery-confirm.css">
        <link rel="stylesheet" type="text/css" href="/css/datatable/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="/css/jquery/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="/css/jquery-confirm-pedidos.css">

        <!-- Fonts ================================================== -->
        <link href='<%=request.getContextPath()%>/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-ui.min.js"></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/woocommerce.js'></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-confirm.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/json/jquery.json.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/domicilios/pedidos.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="container rst-main-content">
                <div class="text-center">
                    <h1 class="h1pedido">Domicilios</h1>
                </div>            
                <br />
                </br></br>
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-border-row table-card" id="tablaPedidos">
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th class="product-name">Direcci&oacute;n</th>
                                    <th>Modo de pago</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Fecha</th>
                                    <th class="product-name">Direcci&oacute;n</th>
                                    <th>Modo de pago</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </tfoot>
                            <tbody id="updateListaDom">
                                <c:forEach items="${pedidos}" var="d" varStatus="indice">
                                    <tr>
                                        <td>${d.fecha}</td>
                                        <td>${d.direccion}</td>
                                        <td>${d.tipopago}</td>
                                        <td>${d.total}</td>
                                        <td>
                                            <div class="btn-controls">
                                                <a title="ver" data-idpedido="${d.idpedido}" class="edit btn btn-primary btn-sm viewOrder" href="javascript:void(0);" aria-label="Edit"><i  class="fa fa-eye fa-lg" aria-hidden="true"></i></a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
                <br /><br /><br />
            </div>
        </div><!-- End Content -->
    </body>
</html>
