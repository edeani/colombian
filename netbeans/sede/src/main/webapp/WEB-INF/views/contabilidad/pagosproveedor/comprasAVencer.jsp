<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${comprasPendientes.size() gt 0}">
        <label style="color: white;">Compras Pendientes</label>
        <table id="tblDatos2" class="tblPagosProveedor" align="center" style="display: block;">
            <thead>
                <tr>
                    <th>Compra</th>
                    <th>Fecha</th>
                    <th>Fecha Venc.</th>
                    <th>Saldo</th>
                    <th>Total</th>
                    <th width="22">&nbsp;</th>
                </tr>
            </thead>
            <tbody id="contenidoComprasPendientes" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/selectProducto.htm">
                
                    <c:forEach items="${comprasPendientes}" var="item" varStatus="fila">
                        <tr id="filaComprobante${fila.index}" data-identificadorcompra="${item.idCompra}">
                            <td>
                                <input id="idCompraPendiente${fila.index}" value="${item.idCompra}">
                            </td>
                            <td>
                                <input id="fechaCompraPendiente${fila.index}" value="${item.fechaCompra}">
                            </td>
                            <td>
                                <input id="fechaVencimientoPendiente${fila.index}" value="${item.fechaVencimiento}">
                            </td>
                            <td>
                                <input id="saldoPendiente${fila.index}" value="<fmt:formatNumber groupingUsed="false" value="${item.saldo}" type="number"></fmt:formatNumber>">
                            </td>
                            <td>
                                <input id="valorTotalPendiente${fila.index}" value="<fmt:formatNumber value="${item.valorTotal}" groupingUsed="false" type="number"></fmt:formatNumber>">
                            </td>
                            <td align="right">
                                <input type="button" value="+" class="clsAgregarFilaProveedor" data-numero="${fila.index}">
                            </td>
                        </tr>
                    </c:forEach>
                
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <label style="color: white;">
           No hay compras pendientes con este proveedor
        </label>
    </c:otherwise>
</c:choose>