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
        <title>Tú pedido</title>
        <!-- Custom Css ================================================== -->
        <link rel="stylesheet" type="text/css" href="/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link rel="stylesheet" type="text/css" href="/css/responsive.css">

        <!-- Fonts ================================================== -->
        <link href='/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div id="content">
            <div class="container rst-main-content">
                <springForm:form action="/compras/guardar.htm" method="post" commandName="pedidoClienteDto">
                <div class="text-center">
                    <h1 class="h1pedido">HACER PEDIDO</h1>
                </div>            
                <br /><br /><br />
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <table class="table table-border-row table-card">
                            <thead>
                                <tr>
                                    <th class="product-name">Nombre</th>
                                    <th>PRECIO</th>
                                    <th>CANTIDAD</th>
                                    <th class="price">TOTAL</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pedido.productos}" var="p" varStatus="indice">
                                <tr>
                                    <td class="product-name">
                                        <a class="remove" href="#"><i class="fa fa-close"></i></a>
                                        <img class="img-circle" src="img/post/product-card-01.jpg" alt="" />
                                        ${p.nombreproducto}
                                    </td>
                                    <td><fmt:formatNumber type="number"  pattern="###.###" value="${p.precio}" /></td>
                                    <td>
                                        <div class="quantity"><input type="number" step="1" min="0"  name="cart[8fe0093bb30d6f8c31474bd0764e6ac0][qty]" value="${p.cantidad}" title="Qty" class="input-text qty text" size="4" /></div>
                                    </td>
                                    <td class="price">$<fmt:formatNumber type="number"  pattern="###.###" value="${p.total}" /></td>
                                    <input type="hidden"  name="productos[${indice.index}].idproducto" value="${p.idproducto}"/>
                                    <input type="hidden"  name="productos[${indice.index}].nombreproducto" value="${p.nombreproducto}"/>
                                    <input type="hidden"  name="productos[${indice.index}].precio" value="${p.precio}"/> 
                                    <input type="hidden"  name="productos[${indice.index}].cantidad" value="${p.cantidad}"/> 
                                    <input type="hidden"  name="productos[${indice.index}].total" value="${p.total}"/>
                                </tr>
                                </c:forEach>
                                <tr class="subtotal">
                                    <th></th>
                                    <th></th>
                                    <th>subtotal</th>
                                    <th class="price">$<fmt:formatNumber type="number"  pattern="###.###" value="${pedido.total}" /></th>
                                    <input type="hidden"  name="total" value="${pedido.total}"/>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <br /><br /><br />
                <div class="rst-form-input form-checkout">
                    <div class="row">
                        <div class="col-sm-6 checkout-address">
                            <h4 class="titulocheck"><span class="rst-circle">1</span>TUS DATOS</h4>
                            <div class="form-group">
                                <label>Nombre completo</label>
                                <springForm:input path="nombre" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <label>Direcci&oacute;n</label>
                                <springForm:input path="direccion" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <label>Tel&eacute;fono</label>
                                <springForm:input path="telefono" cssClass="form-control" />
                            </div>
                            
                        </div>
                        <div class="col-sm-6 checkout-payment">
                            <h4 class="titulocheck"><span class="rst-circle">2</span>SISTEMA DE PAGO</h4>
                            <div class="form-group">
                                <label>Seleccione sistema de pago</label>
                                <springForm:select path="medioPago" cssClass="form-control">
                                    <option value="1">Mastercard</option>
                                    <option value="2">Visa</option>
                                </springForm:select>
                            </div>
                            <div class="form-group">
                                <label>Comentarios</label>
                                <springForm:textarea path="comentarios" cssClass="form-control" rows="5"/>
                            </div>

                        </div>
                        <div class="col-sm-12">
                            <div class="form-group btnpago">
                                <input class="btn btn-danger btn-lg" type="submit" value="Hacer pedido">
                            </div>
                        </div>
                    </div>
                </div>
                </springForm:form>
            </div>
        </div><!-- End Content -->
    </body>
</html>
