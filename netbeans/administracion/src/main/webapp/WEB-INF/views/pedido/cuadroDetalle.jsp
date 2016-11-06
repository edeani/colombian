<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-6">&iquest;Desea Aprobar el Domicilio #${idpedido}?</div>
            <div class="col-md-6 text-right"> Fecha y Hora: ${fecha}</div>
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4 list-group-item"><span class="label label-primary">Identificaci&oacute;n</span> ${cedula}</div>
            <div class="col-md-4 list-group-item"><span class="label label-primary">Nombre</span> ${nombre}</div>
            <div class="col-md-4 list-group-item"><span class="label label-primary">correo</span> ${correo}</div>
        </div>
        <div class="row">
            <div class="col-md-4 list-group-item"><span class="label label-primary">Direcci&oacute;n</span> ${direccion}</div>
            <div class="col-md-4 list-group-item"><span class="label label-primary">Tipo pago</span> ${tipopago}</div>
            <div class="col-md-4 list-group-item"><span class="label label-primary">Tel&eacute;fono</span> ${telefono}</div>
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
                    <td>${p.idproducto.nombreproducto}</td>
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
