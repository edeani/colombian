<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty pedido}">
<div id="listCar" class="rst-list-product">
    <c:forEach items="${pedido.productos}" var="producto" >
        <div class="rst-product-item itemCar" data-idproducto="${producto.idproducto}" data-total="${producto.total}">
            <a href="#">${producto.nombreproducto} <span id="update${producto.idproducto}" data-cantidad="${producto.cantidad}" class="count">${producto.cantidad}</span></a><span class="price">$${producto.total} <button type="button" class="btn-xs removeCar" data-toggle="tooltip" data-placement="right" title="Eliminar">-</button></span>
        </div>
    </c:forEach>
</div>
<c:set value="none" var="mostrar"></c:set>
<c:if test="${0 lt pedido.total}">
    <c:set value="block" var="mostrar"></c:set>
</c:if>
<div id="doOrderResumen" class="rst-checkout" style="display: ${mostrar};">
    <a href="/compras/pedido.htm" class="btn btn-success btn-sm btnpagar"> HACER PEDIDO</a>
    <span id="totalPrice" class="price">$${pedido.total}</span>
    <input type="hidden" id="totalPedido" value="${pedido.total}"/>
</div>
</c:if>
