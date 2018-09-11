<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table id="datosInventario" class="tablaDatosInfo">
    <thead>
    <th>C&oacute;digo</th>
    <th>Producto</th>
    <th>Inicial</th>
    <th>Compras</th>
    <th>Ventas</th>
    <th>Aver&iacute;as y Consumo</th>
    <th>Traslados</th>
    <th>Final</th>
    <th>Real</th>
    <th>Diferencia</th>
</thead>
<tbody>
    <c:forEach items="${inventario}" var="producto">
        <tr>
            <td>${producto.codigo_producto_inventario}</td>
            <td>${producto.producto}</td>
            <td>${producto.inicial}</td>
            <td>${producto.compras}</td>
            <td>${producto.ventas}</td>
            <td>${producto.averias}</td>
            <td>${producto.traslados}</td>
            <c:set var="total_final" value="${producto.compras+producto.inicial-producto.ventas-producto.averias-producto.traslados}"/>
            <td>${total_final}</td>
            <td>${producto.fisico}</td>
            <td>${producto.fisico - total_final}</td>
        </tr>    
    </c:forEach>
</tbody>
<tfoot></tfoot>
</table>