<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="divContenedorTabla">
    <table id="datosInventario" class="tablaDatosInfo">
        <thead>
        <th>C&oacute;digo</th>
        <th>Producto</th>
        <th>Unidades</th>
        <th>Promedio</th>
        <th>Total</th>
        </thead>
        <tbody>
            <c:if test="${size eq 0}">
                <tr>
                    No se encontraron registros
                </tr>
            </c:if>

            <c:forEach items="${inventarioCliente}" var="producto">
                <tr>
                    <td>${producto.codigoProductoInventario}</td>
                    <td>${producto.nombreProducto}</td>
                    <td>${producto.unidades}</td>
                    <td>$<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${producto.promedioValorProducto}" /></td>
                    <td>$<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${producto.totalInventarioProducto}" /></td>
                    <c:set var="totalInventario" value="${totalInventario + producto.totalInventarioProducto}"></c:set>
                    </tr>    
            </c:forEach>
        </tbody>
        <tfoot></tfoot>
    </table>
    <input type="hidden" id="totalInventario" value="${totalInventario}"/>
    <br/>
    <div class="contenedorResumen">             
        <label id="totalInventarioClienteLabel" class="resumen">$<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${totalInventario}" /></label>
        <label class="resumen">
            TOTAL INVENTARIO
        </label>
    </div>
</div>