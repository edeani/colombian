<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${datosVenta}" var="itemVenta">
    <tr>  
        <td>${itemVenta.codigo_producto}</td>
        <td>${itemVenta.descripcion_producto}</td>
        <td>${itemVenta.numero_unidades}</td>
        <td>$<fmt:formatNumber type="number" pattern="###,##0" value="${itemVenta.valor_producto}"/></td>
        <c:choose>
            <c:when test="${reporte eq 'total'}">
                <c:set value="${itemVenta.total_producto}" var="totalProducto" />
            </c:when>
            <c:otherwise>
                <c:set value="${itemVenta.numero_unidades * itemVenta.valor_producto}" var="totalProducto" />
            </c:otherwise>
        </c:choose>
        
        <td>
            $<fmt:formatNumber type="number" pattern="###,##0" value="${totalProducto}"/>
            <input type="hidden" value="<fmt:formatNumber type="number" pattern="###,##0" value="${totalProducto}"/>" class="cmpResumen${clase}"/>
        </td>
    </tr>
</c:forEach>
