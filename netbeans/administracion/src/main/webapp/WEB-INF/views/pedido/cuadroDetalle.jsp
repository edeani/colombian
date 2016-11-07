<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${estado=='A'}">
        <c:set var="mensaje" value="&iquest;Desea Aprobar el Domicilio #${idpedido}?"></c:set>
    </c:when>
    <c:when test="${estado=='R'}">
        <c:set var="mensaje" value="&iquest;Desea Rechazar el Domicilio #${idpedido}?"></c:set>
    </c:when>
</c:choose>
<div class="panel ${cssClass}">
    <!-- Default panel contents -->
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6 lead">${mensaje}</div>
            <div class="col-md-6 text-right"> Fecha y Hora: ${fecha}</div>
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4 list-group-item"><span class="label label-primary">Identificaci&oacute;n</span> ${usuario.cedula}</div>
            <div class="col-md-4 list-group-item"><span class="label label-primary">Nombre</span> ${usuario.nombreusuario}</div>
            <div class="col-md-4 list-group-item"><span class="label label-primary">correo</span> ${usuario.correo}</div>
        </div>
        <div class="row">
            <div class="col-md-4 list-group-item"><span class="label label-primary">Direcci&oacute;n</span> ${usuario.direccion}</div>
            <div class="col-md-4 list-group-item"><span class="label label-primary">Tipo pago</span> ${tipopago}</div>
            <div class="col-md-4 list-group-item"><span class="label label-primary">Tel&eacute;fono</span> ${usuario.telefono}</div>
        </div>
    </div>
    <!-- Table -->
    <table class="table">
        <thead>
            <tr>
                <th>Id Producto</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${detalle}">
                <tr>
                    <td>${p.idproducto.idproducto}</td>
                    <td><a href="${pageContext.request.contextPath}/productos/editar-producto.htm?idproducto=${p.idproducto.idproducto}" target="_blank" title="Ver Detalle de Producto">${p.idproducto.nombreproducto}</a> </td>
                    <td>${p.preciounitario}</td>
                    <td>${p.cantidadorden}</td>
                    <td>${p.totalproducto}</td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>Total Pedido: <fmt:formatNumber type="number"  pattern="###.###" value="${totalpedido}" /></td>
        </tfoot>
    </table>
</div>
