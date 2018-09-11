<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="total_" value="0"/>
<table id="datosGastos" class="">
    <tbody>
        <c:forEach var="item" items="${gastos}">
            <c:set var="total_" value="${total_ + item.valor_gastos}"/>
            <tr data-tt-id="${item.cod}">
                <td>
                    ${item.nombre} <em>$<fmt:formatNumber type="number" pattern="###,##0" value="${item.valor_gastos}"/></em>
                </td> 
            </tr>
            <c:forEach var="item2" items="${item.nivel2}">
                <tr data-tt-id="${item2.cod}" data-tt-parent-id="${item2.padre}">
                    <td>
                        ${item2.nombre} $<fmt:formatNumber type="number" pattern="###,##0" value="${item2.valor_gastos}"/>
                    </td> 
                </tr>
                <c:forEach  var="item3" items="${item2.nivel3}">
                    <tr data-tt-id="${item3.cod}" data-tt-parent-id="${item3.padre}">
                        <td>
                            ${item3.nombre} $<fmt:formatNumber type="number" pattern="###,##0" value="${item3.valor_gastos}"/>
                        </td> 
                    </tr>
                </c:forEach>
            </c:forEach>
        </c:forEach>
        <tr>
            <td>TOTAL $<fmt:formatNumber type="number" pattern="###,##0" value="${total_}"/></td>
        </tr>
    </tbody>
</table>