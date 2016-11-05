<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

Domicilio #${idpedido} ${nombre}
<ul class="list-group">
<c:forEach var="p" items="${detalle}">
    <li class="list-group-item">${p.idproducto.idproducto} ${p.idproducto.nombreproducto} Cantidad:${p.cantidadorden}</li>
</c:forEach>

</ul>