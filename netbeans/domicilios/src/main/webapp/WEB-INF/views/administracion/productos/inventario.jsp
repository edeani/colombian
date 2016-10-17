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
        <link rel="stylesheet" type="text/css" href="/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link rel="stylesheet" type="text/css" href="/css/responsive.css">

        <!-- Fonts ================================================== -->
        <link href='/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

        <script type='text/javascript' src='/js/woocommerce.js'></script>

        <script type="text/javascript" src="/js/producto/pedido.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="container rst-main-content">

                <div class="text-center">
                    <h1 class="h1pedido">Prodcutos</h1>
                </div>            
                <br /><br /><br />
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <table class="table table-border-row table-card">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th class="product-name">Nombre</th>
                                    <th>Descripci&oacute;n</th>
                                    <th class="price">Precio</th>
                                    <th>Tipo</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${productos}" var="p" varStatus="indice">
                                    <tr id="fila${indice.index}" class="fila">
                                        <td>${p.idproducto}</td>
                                        <td class="product-name">
                                            <img class="img-circle" src="img/post/product-card-01.jpg" alt="" />
                                            ${p.nombreproducto}
                                        </td>
                                        <td>${p.descripcion}</td>
                                        <td><fmt:formatNumber type="number"  pattern="###.###" value="${p.precioproducto}" /></td>
                                        <td>${p.nombreTipo}</td>
                                        <td>
                                            <a data-row="${indice.index}" class="edit editProduct" href="javascript:void(0);"><i style="color: #149bdf;" class="fa fa-edit"></i></a>
                                            <a data-row="${indice.index}" class="remove removeProduct" href="javascript:void(0);"><i style="color: red;" class="fa fa-close"></i></a>
                                            
                                        </td>
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
