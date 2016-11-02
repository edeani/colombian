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
        <title>Administración de Productos</title>
        <!-- Custom Css ================================================== -->
        <link rel="stylesheet" type="text/css" href="/administracion/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/main.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/responsive.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/jquery-confirm.css">
        <link rel="stylesheet" type="text/css" href="/administracion/css/datatable/jquery.dataTables.min.css">

        <!-- Fonts ================================================== -->
        <link href='<%=request.getContextPath()%>/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

        <script type='text/javascript' src='<%=request.getContextPath()%>/js/woocommerce.js'></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-confirm.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/producto/admon-domicilios.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/dataTables.select.min.js"></script>
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
                        <table class="table table-border-row table-card" id="tablaDomicilios">
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
                            <tbody>
                                <c:forEach items="${pedidos}" var="d" varStatus="indice">
                                    <tr id="fila${indice.index}" class="fila <c:if test="${d.estadopedido=='R'}">alert alert-danger</c:if>">
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

                                            <div id="activeButtons${indice.index}" style="display: ${displayactives};">
                                                <a title="ver" data-row="${indice.index}" class="edit btn btn-primary btn-sm viewOrder" href="<%=request.getContextPath()%>/productos/editar-producto.htm?idproducto=${p.idproducto}" aria-label="Edit"><i  class="fa fa-eye fa-lg" aria-hidden="true"></i></a>
                                                <c:if test="${d.estadopedido eq 'P' or d.estadopedido eq 'R'}">
                                                <a title="Aprobar" data-row="${indice.index}" class="edit btn btn-success btn-sm aceptOrder" href="<%=request.getContextPath()%>/productos/recuperar-producto.htm?idproducto=${p.idproducto}" aria-label="Edit"><i  class="fa fa-check-square fa-lg" aria-hidden="true"></i></a>
                                                </c:if>
                                                <c:if test="${d.estadopedido eq 'P' or d.estadopedido eq 'A'}">
                                                <a title="Rechazar" data-row="${indice.index}" class="btn btn-danger removeP btn-sm rejectOrder" href="javascript:void(0);" aria-label="Delete"><i class="fa fa-times fa-lg" aria-hidden="true"></i></a>
                                                </c:if>
                                            </div>
                                            </p>
                                        </td>
                                <input type="hidden" value="${d.idpedido}" id="idpedido${indice.index}" class="fieldPedido"/>
                                <!--td>
                                    <div class="quantity"><input data-row="${indice.index}" value="" type="number" min="1" step="1" id="view${indice.index}Cantidad" title="Cantidad" class="input-text qty text viewCantidad indiceData" size="4"/></div>
                                </td-->

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
