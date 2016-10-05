<%-- 
    Document   : contenidoProductos
    Created on : 12-ago-2016, 12:55:45
    Author     : edeani
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Productos Colombian</title>
        <!-- Custom Css  ================================================== -->
        <link rel="stylesheet" type="text/css" href="/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link rel="stylesheet" type="text/css" href="/css/responsive.css">

        <!-- Fonts 
        ================================================== -->
        <link href='/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        
        <c:choose>
            <c:when test="${dispositivo == 'desktop'}">
            <script type="text/javascript" src="/js/producto/productos.js"></script>
            </c:when>
            <c:otherwise>
                <script type="text/javascript" src="/js/${dispositivo}/producto/productos.js"></script>    
            </c:otherwise>
        </c:choose>
    </head>
    <body>
        <!-- Inicio contenido de productos -->
        <div id="content">
            <div id="rst-banner" data-background="/img/post/banner02.jpg">
                <div class="container">
                    <div class="rst-inner-banner clearfix">
                        <div class="rst-banner-content pull-left">
                            <h1>MEN&Uacute;</h1>
                        </div>
                    </div>
                </div>
            </div><!-- Banner -->
            <div class="container rst-main-content">
                <div id="listaDivProd" class="rst-product-list">
                    <div class="row">
                        <div id="categoriasDiv" class="col-sm-4">
                            <h4>Categorias</h4>
                            <ul class="list-category">
                                <c:forEach items="${categorias}" var="c" varStatus="indice">
                                    <c:if test="${indice.index == 0}" ><c:set value="active " var="claseActiva"></c:set></c:if>
                                    <li data-href="${c.nombre}" class="${claseActiva}" style="cursor: pointer;"><a href="#${c.nombre}" class="categoria">${c.nombre}</a></li>
                                        <c:set value="" var="claseActiva"></c:set>
                                    </c:forEach>
                            </ul>
                            <h4>Resumen Pedido</h4>
                            <div id="carSummary" class="rst-form-login rst-cart-info">
                                <c:import url="/compras/ajax/resumen.htm"></c:import>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div class="row">
                                <div class="col-sm-9">
                                    <div class="rst-view">
                                        <a href="#" class="rst-tile"></a>
                                        <a href="#" class="rst-list active"></a>
                                    </div>
                                </div>
                            </div>

                            <div class="row product-list view-list">
                                <c:set var="seccion" value=""></c:set>
                                <c:set var="seccion_temp" value=""></c:set>
                                <c:forEach items="${productos}" var="p">
                                    <c:if test="${seccion_temp ne p.nombreTipo}">
                                        <c:set var="seccion" value="id='${p.nombreTipo}'"></c:set>
                                        <c:set var="seccion_temp" value="${p.nombreTipo}"></c:set>
                                    </c:if>
                                    <div class="col-sm-10 product-item" ${seccion}>
                                        <div class="rst-thumbnail">
                                            <a class="addCar addtocard" data-id="${p.idproducto}" data-nombre="${p.nombreproducto}" data-cantidad="1" data-data-nombprecio="${p.precioproducto}" href="#"><img src="/img/post/gallery10.jpg" alt="" /></a>
                                            <div class="rst-hover">
                                                ${p.nombreTipo}
                                                <a class="addCar addtocard" data-id="${p.idproducto}" data-nombre="${p.nombreproducto}" data-cantidad="1" data-precio="${p.precioproducto}" href="#"></a>
                                            </div>
                                        </div>
                                        <div class="rst-product-info">
                                            <h3><a class="addCar" data-id="${p.idproducto}" data-nombre="${p.nombreproducto}" data-cantidad="1" data-precio="${p.precioproducto}" href="#">${p.nombreproducto}</a></h3>
                                            <span class="price-product"><sup>$</sup> ${p.precioproducto}</span>
                                            <span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
                                            <hr/>
                                            <div class="rst-product-content">
                                                <p>${p.descripcion}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <c:set var="seccion" value=""></c:set>
                                </c:forEach>

                            </div><!-- End Product List-->
                            <nav class="wp-pagenavi">
                                <c:if test="${pages  > 1}">
                                    <a class="btn btn-sm btn-success page-prev" href="#">Atr&aacute;s</a>
                                </c:if>
                                <c:forEach var="indice" begin="1" end="${pages}">
                                    <c:choose>
                                        <c:when test="${indice==actualPage}">
                                            <span class="btn btn-sm btn-success page-numbers current">${indice}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="page-numbers" href="#">${indice}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${pages  > 1}">
                                    <a class="btn btn-sm btn-success page-next" href="#">Siguiente</a>            
                                </c:if>            
                            </nav>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- Inicio contenido de productos -->
    </body>
</html>
