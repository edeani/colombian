<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${datos}" var="item">
    <tr>  
        <td><fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${item.fecha}"/></td>
        <td>${item.dia}</td>
        <td>${item.domicilios}
            <input type="hidden" value="${item.domicilios}" class="cmpResumenDomiciliosCant"/>
        </td>
        <td>
            $<fmt:formatNumber type="number" pattern="###,##0" value="${item.valor_total}"/>
            <input type="hidden" value="${item.valor_total}" class="cmpResumenDomicilios"/>
        </td>
    </tr>
</c:forEach>
