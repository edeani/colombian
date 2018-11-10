<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<input type="hidden" id="totalDom"  value="${totalDom}"/>
<c:forEach items="${datos}" var="item">
    <tr>  
        <td>${item.orden}</td>
        <td>${item.telefono}</td>
        <td>${item.barrio}</td>
        <td>${item.cliente}</td>
        <td><fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${item.fecha}"/></td>
        <td>
            $<fmt:formatNumber type="number" pattern="###,##0" value="${item.valor}"/>
            <input type="hidden" value="${item.valor}" class="cmpResumenDomicilios"/>
        </td>
        <td></td>
    </tr>
</c:forEach>
