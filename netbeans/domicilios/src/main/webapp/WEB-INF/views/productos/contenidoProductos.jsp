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
        <link href='fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
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
                <div class="rst-product-list">
                    <div class="row">
                        <div class="col-sm-3" >
                            <h4 style="display: none;">Precio</h4>
                            <div class="rst-slider" style="display: none;">
                                <span class="price-min">$0</span>
                                <input type="text" class="slider-input" value="0" data-slider-text-after="$" data-slider-min="0" data-slider-max="80000" data-slider-step="1" />
                                <span class="price-max">$80.000</span>
                            </div>                
                            <hr />
                            <h4 style="display: none;">Descuento</h4>
                            <div class="rst-slider" style="display: none;">
                                <span class="price-min">50%</span>
                                <input type="text" class="slider-input" value="0" data-slider-text-before="%" data-slider-min="50" data-slider-max="90" data-slider-step="1" />
                                <span class="price-max">90%</span>
                            </div>
                            <hr />
                            <h4>Categorias</h4>
                            <ul class="list-category">
                                <li class="active"><a href="#">Pollo</a></li>
                                <li><a href="#">Hamburguesas</a></li>
                                <li><a href="#">Sopas</a></li>
                                <li><a href="#">Combos Familiares</a></li>
                                <li><a href="#">Porciones</a></li>
                                <li><a href="#">Bebidas</a></li>
                            </ul>
                            <hr />
                            <ul class="list-tag" style="display: none;">
                                <li class="active"><a href="#">Los m&aacute;s pedidos</a></li>
                            </ul>
                        </div>
                        <div class="col-sm-9">
                            <div class="row">
                                <div class="col-sm-3">
                                    <form action="/" class="rst-form-input">
                                        <div class="form-group">
                                            <select class="form-control" name="country" id="InputCountry">
                                                <option value="Popular">Popular</option>
                                                <option value="Price">Precio</option>
                                                <option value="View">M&aacute;s pedido</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
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
                                        <c:set var="seccion" value="${p.nombreTipo}"></c:set>
                                        <c:set var="seccion_temp" value="${p.nombreTipo}"></c:set>
                                    </c:if>
                                    <div class="col-sm-12 product-item ${seccion}">
                                        <div class="rst-thumbnail">
                                            <a href="products_detail.html"><img src="/img/post/gallery10.jpg" alt="" /></a>
                                            <div class="rst-hover">
                                                ${p.nombreTipo}
                                                <a href="#" class="addtocard"></a>
                                            </div>
                                        </div>
                                        <div class="rst-product-info">
                                            <h3><a href="products_detail.html">${p.nombreproducto}</a></h3>
                                            <span class="price-product"><sup>$</sup> ${p.precioproducto}</span>
                                            <span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
                                            <hr/>
                                            <div class="rst-product-content">
                                                <p></p>
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
