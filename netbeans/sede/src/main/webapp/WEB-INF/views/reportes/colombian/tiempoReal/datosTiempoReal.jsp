<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul>
    <li>
        <label>
            Caja Inicial
        </label>
    </li>
    <li style="width: 200px !important;">
        $<input  id="cajaInicial" value="<fmt:formatNumber type="number" pattern="###,##0" value="${cierreDiario.cajaInicial}"/>"></input>
    </li>
</ul>
<ul>
    <li>
        <label>
            Ventas
        </label>
    </li>
    <li style="width: 200px !important;">
        $<input id="ventas" value="<fmt:formatNumber type="number" pattern="###,##0" value="${cierreDiario.ventas}"/>"></input>
    </li>
</ul>
<ul>
    <li>
        <label>
            Gastos
        </label>
    </li>
    <li style="width: 200px !important;">
        $<input id="gastos"value="<fmt:formatNumber type="number" pattern="###,##0" value="${cierreDiario.gastos}"/>"></input>
    </li>
</ul>
<ul>
    <li>
        <label>
            Total Consginaciones
        </label>
    </li>
    <li style="width: 200px !important;">
        $<input  id="consignaciones" value="<fmt:formatNumber type="number" pattern="###,##0" value="${cierreDiario.consignaciones}"/>"></input>
    </li>
</ul>
<c:if test="${not empty cierreDiario.pagosTarjetas}">
    <ul>
        <li>
            <label>
                Total Pagos Tarjetas
            </label>
        </li>
        <li style="width: 200px !important;">
            $<input  id="pagosTarjeta" value="<fmt:formatNumber type="number" pattern="###,##0" value="${cierreDiario.pagosTarjetas}"/>"></input>
        </li>
    </ul>
</c:if>
<c:if test="${not empty cierreDiario.descuentos}">
<ul>
    <li>
        <label>
            Total Descuentos
        </label>
    </li>
    <li style="width: 200px !important;">
        $<input class="campoTiempoReal" id="descuentos" value="<fmt:formatNumber type="number" pattern="###,##0" value="${cierreDiario.descuentos}"/>"></input>
    </li>
</ul>
</c:if>
<ul>
    <li>
        <label>
            Caja Final  
        </label>
    </li>
    <li style="width: 200px !important;">
        $<input path="cajaFinal" value="<fmt:formatNumber type="number" pattern="###,##0" value="${cierreDiario.cajaFinal}"/>"></input>
    </li>
</ul>
<table id="datosCierre" class="tablaDatosInfo">
    <thead>
    <th>Fecha</th><th>Nombre</th><th>Valor Consignaci&oacute;n</th>
</thead>
<tbody>
    <c:forEach items="${cierreDiario.listaConsignaciones}" var="detalleConsignacion">
        <tr>
            <td>${detalleConsignacion.fechaConsignacion}</td>
            <td>${detalleConsignacion.nombreCajero}</td>
            <td><fmt:formatNumber type="number" pattern="###,##0" value="${detalleConsignacion.valorConsignacion}"/></td>
        </tr>    
    </c:forEach>
</tbody>
<tfoot></tfoot>
</table>