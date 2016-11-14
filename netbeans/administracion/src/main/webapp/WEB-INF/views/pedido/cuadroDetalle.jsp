<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<br/>
<c:choose>
    <c:when test="${pedido.estado=='A'}">
        <c:set var="cssClass" value="panel-success"></c:set>
    </c:when>
    <c:when test="${pedido.estado=='R'}">
        <c:set var="cssClass" value="panel-danger"></c:set>
    </c:when>
    <c:when test="${pedido.estado=='P'}">
        <c:set var="cssClass" value="panel-primary"></c:set>
    </c:when>
</c:choose>
<div class="panel ${cssClass}">
    <!-- Default panel contents -->
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">Fecha y Hora: ${fecha}</div>
        </div>
    </div>
    <%--Inicio del Fomulario de pedido de la aminitración --%> 
    <springForm:form action="#" method="post" commandName="pedidoClienteDto">
        <springForm:hidden path="idpedido"  />
        <div class="panel-body">
            <div class="row">
                <div class="col-md-4 list-group-item"><span class="label label-primary">Identificaci&oacute;n</span> ${pedido.cedula}</div>
                <div class="col-md-4 list-group-item"><span class="label label-primary">Nombre</span> ${pedido.nombre}</div>
                <div class="col-md-4 list-group-item"><span class="label label-primary">correo</span> ${correo}</div>
            </div>
            <div class="row">
                <div class="col-md-4 list-group-item"><span class="label label-primary">Direcci&oacute;n</span> ${pedido.direccion}</div>
                <div class="col-md-4 list-group-item"><span class="label label-primary">Tipo pago</span> ${tipopago}</div>
                <div class="col-md-4 list-group-item"><span class="label label-primary">Tel&eacute;fono</span> ${pedido.telefono}</div>
            </div>
            <div class="row">
                <div class="col-md-12 list-group-item"><span class="label label-primary">Comentarios</span> El ejemplo de mis comentarios</div>
            </div>
            <div class="row">
                <div class="col-md-6 list-group-item" style="border: #FFF">
                    <div  style="margin: 5px 10px;">
                        <button id="addProductoPanel" class="btn btn-primary" ><span class="fa fa-plus fa-lg" aria-hidden="true"> </span></button>
                    </div>
                </div>
                <div class="col-md-6 list-group-item" style="border: #FFF">
                    <div class="text-right" style="margin: 5px 10px;" >
                        <button id="updatePedidoPanel" class="btn btn-primary" title="Agregar Producto">Actualizar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Table -->
        <table class="table table-card">
            <thead>
                <tr>
                    <th>Id Producto</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${pedido.productos}" varStatus="i">
                    <tr id="f${i.index}" class="f">
                        <td>${p.idproducto}</td>
                        <td><a href="${pageContext.request.contextPath}/productos/editar-producto.htm?idproducto=${p.idproducto}" target="_blank" title="Ver Detalle de Producto">${p.nombreproducto}</a> </td>
                        <td><fmt:formatNumber type="number"  pattern="###.###" value="${p.precio}" /></td>
                        <td>
                            <div class="quantity buttons_added">
                                <input type="button" value="-" class="minus">
                                <input data-row="${i.index}" value="${p.cantidad}" type="number" min="1" step="1" id="view${i.index}Cantidad" title="Cantidad" class="input-text qty text viewCantidad indiceData" size="4"/>
                                <input type="button" value="+" class="plus">
                            </div>
                        </td>
                        <td id="p${i.index}viewTotalProducto" class="indiceViewTotalProducto"><fmt:formatNumber type="number"  pattern="###.###" value="${p.total}" /></td>
                        <td><a data-row="${i.index}" class="remove removeCar indiceData" href="javascript:void(0);"><i class="fa fa-close"></i></a></td>
                <input type="hidden" id="p${i.index}idproducto"  name="productos[${i.index}].idproducto" value="${p.idproducto}"/>
                <input type="hidden" id="p${i.index}nombreproducto" name="productos[${i.index}].nombreproducto" value="${p.nombreproducto}"/>
                <input type="hidden" id="p${i.index}precio" name="productos[${i.index}].precio" value="${p.precio}"/> 
                <input type="hidden" id="p${i.index}cantidad" name="productos[${i.index}].cantidad" value="${p.cantidad}"/> 
                <input type="hidden" id="p${i.index}total" name="productos[${i.index}].total" value="${p.total}"/>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>Total Pedido:</td>
            <td id="viewTotalPedido"><fmt:formatNumber type="number"  pattern="###.###" value="${pedido.total}" /></td>
            <input type="hidden" id="totalPedido" name="total" value="${pedido.total}"/>
            </tfoot>
        </table>
    </springForm:form>
</div>
