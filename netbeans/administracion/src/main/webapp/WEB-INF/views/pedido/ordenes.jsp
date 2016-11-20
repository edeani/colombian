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
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/domicilios/pedidos.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="container rst-main-content">

                <div class="text-center">
                    <h1 class="h1pedido">Domicilios</h1>
                </div>            
                <br /><br /><br />
                <div class="row">
                    <div class="col-sm-12">
                        FI<input id="fechaInicial" value=""/> FF <input id="fechaFinal" value=""/> <button id="filtrar" >Filtrar</button>
                        
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-border-row table-card" id="tablaPedidos">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th class="product-name">Nombre</th>
                                    <th>Direcci&oacute;n</th>
                                    <th>Total</th>
                                    <th>Fecha</th>
                                    <th>Paga con</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th class="product-name">Nombre</th>
                                    <th>Direcci&oacute;n</th>
                                    <th>Total</th>
                                    <th>Fecha</th>
                                    <th>Paga con</th>
                                    <th></th>
                                </tr>
                            </tfoot>
                            <tbody id="updateListaDom">
                                <c:forEach items="${pedidos}" var="d" varStatus="indice">
                                    <c:set var="clasefila" value=""></c:set>
                                    <c:if test="${d.estadopedido eq 'R'}"><c:set var="clasefila" value="alert-danger"/></c:if> 
                                    <c:if test="${d.estadopedido eq 'A'}"><c:set var="clasefila" value="alert-success"/></c:if>
                                    <tr id="fila${indice.index}" class="fila alert ${clasefila}">
                                        <td>${d.idpedido}</td>
                                        <td class="product-name">
                                            ${d.nombre}
                                        </td>
                                        <td>${d.direccion}</td>
                                        <td><fmt:formatNumber type="number"  pattern="###.###" value="${d.totalpedido}" /></td>
                                        <td>${d.fecha}</td>
                                        <td>${d.tipopago}</td>
                                        <td>
                                            <p style="width: 135px; margin: 0px;">
                                            <div id="activeButtons${indice.index}" class="btn-controls">
                                                <a title="ver" data-row="${indice.index}" class="edit btn btn-primary btn-sm viewOrder" href="javascript:void(0);" aria-label="Edit"><i  class="fa fa-eye fa-lg" aria-hidden="true"></i></a>
                                            </div>
                                            </p>
                                        </td>
                                <form id="formDatos${indice.index}">
                                    <input type="hidden" id="pedido${indice.index}" name="idpedido" value="${d.idpedido}" class="fieldPedido"/>
                                    <input type="hidden" name="fecha" value="${d.fecha}" class="fieldFecha"/>
                                </form>        
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
