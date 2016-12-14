<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="panel">
        <springForm:hidden path="idpedido"  />
        <div class="panel-body">
            <div class="row">
                <div class="col-md-4 list-group-item"><span class="label label-primary">Pedido No. </span> ${pedido.idpedido}</div>
                <div class="col-md-4 list-group-item"><span class="label label-primary">Tipo pago</span> ${tipopago}</div>
                <div class="col-md-4 list-group-item"><span class="label label-primary">Fecha </span> ${fecha}</div>
            </div>
            <div class="row">
                <div class="col-md-12 list-group-item"><span class="label label-primary">Comentarios</span> ${pedido.comentarios}</div>
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
                </tr>
            </thead>
            <tbody id="listProduct">
                <c:forEach var="p" items="${pedido.productos}" varStatus="i">
                    <tr id="f${i.index}" class="f">
                        <td>${p.idproducto}</td>
                        <td><a href="${pageContext.request.contextPath}/productos/editar-producto.htm?idproducto=${p.idproducto}" target="_blank" title="Ver Detalle de Producto">${p.nombreproducto}</a> </td>
                        <td><fmt:formatNumber type="number"  pattern="###.###" value="${p.precio}" /></td>
                        <td>
                            ${p.cantidad}
                        </td>
                        <td id="p${i.index}viewTotalProducto" class="indiceViewTotalProducto"><fmt:formatNumber type="number"  pattern="###.###" value="${p.total}" /></td>
                        
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
            <td>Total Pedido:</td>
            <td id="viewTotalPedido"><fmt:formatNumber type="number"  pattern="###.###" value="${pedido.total}" /></td>
            </tfoot>
        </table>
</div>