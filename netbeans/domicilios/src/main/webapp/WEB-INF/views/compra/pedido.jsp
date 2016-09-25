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
        
        <script type="text/javascript" src="/js/producto/pedido.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="container rst-main-content">
                <springForm:form action="/compras/pedido.htm" method="post" commandName="pedidoClienteDto">
                <springForm:hidden path="idpedido" cssClass="form-control" />
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
                                <tr id="fila${indice.index}" class="fila">
                                    <td class="product-name">
                                        <a data-row="${indice.index}" class="remove removeCar indiceData" href="javascript:void(0);"><i class="fa fa-close"></i></a>
                                        <img class="img-circle" src="img/post/product-card-01.jpg" alt="" />
                                        ${p.nombreproducto}
                                    </td>
                                    <td><fmt:formatNumber type="number"  pattern="###.###" value="${p.precio}" /></td>
                                    <td>
                                        <div class="quantity"><input data-row="${indice.index}" value="${p.cantidad}" type="number" min="1" step="1" id="view${indice.index}Cantidad" title="Cantidad" class="input-text qty text viewCantidad indiceData" size="4"/></div>
                                    </td>
                                    <td id="p${indice.index}viewTotalProducto" class="price indiceViewTotalProducto">$<fmt:formatNumber type="number"  pattern="###.###" value="${p.total}" /></td>
                                    <input type="hidden" id="p${indice.index}idproducto"  name="productos[${indice.index}].idproducto" value="${p.idproducto}"/>
                                    <input type="hidden" id="p${indice.index}nombreproducto" name="productos[${indice.index}].nombreproducto" value="${p.nombreproducto}"/>
                                    <input type="hidden" id="p${indice.index}precio" name="productos[${indice.index}].precio" value="${p.precio}"/> 
                                    <input type="hidden" id="p${indice.index}cantidad" name="productos[${indice.index}].cantidad" value="${p.cantidad}"/> 
                                    <input type="hidden" id="p${indice.index}total" name="productos[${indice.index}].total" value="${p.total}"/>
                                </tr>
                                </c:forEach>
                                <tr class="subtotal">
                                    <th></th>
                                    <th></th>
                                    <th>subtotal</th>
                                    <th id="viewTotalPedido" class="price">$<fmt:formatNumber type="number"  pattern="###.###" value="${pedido.total}" /></th>
                                    <input type="hidden" id="totalPedido" name="total" value="${pedido.total}"/>
                                    <input type="hidden" id="estadoPedido" name="estado" value="${pedido.estado}"/>
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
                                <springForm:input path="nombre" cssClass="form-control" value="${pedido.nombre}"/>
                                <springForm:errors path="nombre" cssClass="text-danger" />
                            </div>
                            <div class="form-group">
                                <label>Direcci&oacute;n</label>
                                <springForm:input path="direccion" cssClass="form-control" value="${pedido.direccion}"/>
                                <springForm:errors path="direccion" cssClass="text-danger" />
                            </div>
                            <div class="form-group">
                                <label>Identificacion</label>
                                <springForm:input path="cedula" cssClass="form-control" value="${pedido.cedula}" />
                                <springForm:errors path="cedula" cssClass="text-danger" />
                            </div>
                            <div class="form-group">
                                <label>Tel&eacute;fono</label>
                                <springForm:input path="telefono" cssClass="form-control" value="${pedido.telefono}"/>
                                <springForm:errors path="telefono" cssClass="text-danger" />
                            </div>
                        </div>
                        <div class="col-sm-6 checkout-payment">
                            
                            <h4 class="titulocheck"><span class="rst-circle">2</span>SISTEMA DE PAGO</h4>
                            <div class="form-group">
                                <label>Seleccione sistema de pago</label>
                                <springForm:select path="medioPago" cssClass="form-control">
                                    <c:forEach items="${tiposPago}" var="tp">
                                        <option value="${tp.idtipo}">${tp.nombre}</option>
                                    </c:forEach>
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
