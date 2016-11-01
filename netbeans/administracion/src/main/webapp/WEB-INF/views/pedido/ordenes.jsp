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
                    <div class="col-sm-10 col-sm-offset-1">
                        <table class="table table-border-row table-card" id="tablaDomicilios">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th class="product-name">Nombre</th>
                                    <th>Descripci&oacute;n</th>
                                    <th>Precio</th>
                                    <th>Tipo</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th class="product-name">Nombre</th>
                                    <th>Descripci&oacute;n</th>
                                    <th>Precio</th>
                                    <th>Tipo</th>
                                    <th></th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach items="${domicilios}" var="p" varStatus="indice">
                                    <tr id="fila${indice.index}" class="fila <c:if test="${p.estado=='I'}">alert alert-danger</c:if>">
                                        <td>${p.idproducto}</td>
                                        <td class="product-name">
                                            <img class="img-circle" src="<%=request.getContextPath()%>/img/post/product-card-01.jpg" alt="" />
                                            ${p.nombreproducto}
                                        </td>
                                        <td>${p.descripcion}</td>
                                        <td><fmt:formatNumber type="number"  pattern="###.###" value="${p.precioproducto}" /></td>
                                        <td>${p.nombreTipo}</td>
                                        <td>
                                            <p style="width: 135px; margin: 0px;">
                                                <c:set var="displayactives" value=""></c:set>
                                                <c:set var="displayinactives" value=""></c:set>
                                                <c:choose>
                                                <c:when test="${p.estado=='A'}">
                                                    <c:set var="displayactives" value="block"></c:set>
                                                    <c:set var="displayinactives" value="none"></c:set>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="displayactives" value="none"></c:set>
                                                    <c:set var="displayinactives" value="block"></c:set>
                                                </c:otherwise>
                                                </c:choose>    
                                            <div id="activeButtons${indice.index}" style="display: ${displayactives};">
                                                 <a title="Editar" data-row="${indice.index}" class="edit btn btn-primary btn-sm editProduct" href="<%=request.getContextPath()%>/productos/editar-producto.htm?idproducto=${p.idproducto}" aria-label="Edit"><i  class="fa fa-edit fa-lg" aria-hidden="true"></i></a>
                                                 <a title="Inactivar"title="Activar Producto" data-row="${indice.index}" class="btn btn-danger removeP btn-sm removeProduct" href="javascript:void(0);" aria-label="Delete"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></a>
                                            </div>
                                            <div id="inactiveButtons${indice.index}" style="display: ${displayinactives};">
                                                <a title="Activar" data-row="${indice.index}" class="edit btn btn-success btn-sm recoverProduct" href="<%=request.getContextPath()%>/productos/recuperar-producto.htm?idproducto=${p.idproducto}" aria-label="Edit"><i  class="fa fa-check-square fa-lg" aria-hidden="true"></i></a>
                                            </div>


                                            </p>
                                        </td>
                                <input type="hidden" value="${p.idproducto}" id="idproducto${indice.index}" class="fieldProducto"/>
                                <input type="hidden" value="${p.nombreproducto}" id="nombre${indice.index}" class="fieldNombreProducto"/>
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
