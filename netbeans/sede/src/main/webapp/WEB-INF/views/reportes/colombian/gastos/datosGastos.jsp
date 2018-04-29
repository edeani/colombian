<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table id="datosGastos" class="">
    <tbody>
        <c:set var="nombreant" value=""/>
        <c:set var="nombreant2" value=""/>
        <c:forEach items="${gastos}" var="detalleGastos" varStatus="indice">
            <c:if test="${detalleGastos.nivel1 eq 1 and detalleGastos.nombre1 ne nombreant}">
                <tr data-tt-id="${detalleGastos.cod1}">
                    <td>${detalleGastos.nombre1}</td>
                </tr>
                <c:forEach items="${gastos2}" var="detalleGastos2" varStatus="indice">
                    <c:if test="${detalleGastos2.nivel2 eq 2 and detalleGastos2.nombre2 ne nombreant2 and detalleGastos2.padre2 eq detalleGastos.cod1}">
                        <tr data-tt-id="${detalleGastos.cod2}" data-tt-parent-id="${detalleGastos.padre2}">
                            <td>${detalleGastos.nombre2}</td>
                        </tr>
                    </c:if>
                    <c:set var="nombreant2" value="${detalleGastos.nombre2}"/>
                </c:forEach>
            </c:if>
            <c:set var="nombreant" value="${detalleGastos.nombre1}"/>
        </c:forEach>
        
        <c:forEach items="${gastos}" var="detalleGastos">
            <c:if test="${detalleGastos.nivel3 eq 3}">
                <tr data-tt-id="${detalleGastos.cod3}" data-tt-parent-id="${detalleGastos.padre3}">
                    <td>${detalleGastos.nombre3}</td>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>