<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${datos}" var="item">
    <tr>  
        <td><fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${item.fecha}"/></td>
        <td>
            <fmt:formatNumber type="number" pattern="###,##0" value="${item.valorVentas}"/>
            <input type="hidden" value="${item.valorVentas}" class="cmpResumenvalorVentas"/>
        </td>
        <td>
            <fmt:formatNumber type="number" pattern="###,##0" value="${item.valorPagosTarjeta}"/>
            <input type="hidden" value="${item.valorPagosTarjeta}" class="cmpResumenvalorPagosTarjeta"/>
        </td>
        <td>
            <fmt:formatNumber type="number" pattern="###,##0" value="${item.valorDescuentos}"/>
            <input type="hidden" value="${item.valorDescuentos}" class="cmpResumenvalorDescuentos"/>
        </td>
        <td>
            <fmt:formatNumber type="number" pattern="###,##0" value="${item.valorGastos}"/>
            <input type="hidden" value="${item.valorGastos}" class="cmpResumenvalorGastos"/>
        </td>
        <td>
            <fmt:formatNumber type="number" pattern="###,##0" value="${item.valorConsignaciones}"/>
            <input type="hidden" value="${item.valorConsignaciones}" class="cmpResumenvalorConsignaciones"/>
        </td>
        <td><fmt:formatNumber type="number" pattern="###,##0" value="${item.valorCajaReal}"/></td>
        <td></td>
    </tr>
</c:forEach>
