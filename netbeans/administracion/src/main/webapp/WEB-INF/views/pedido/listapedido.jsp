<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${pedidos}" var="d" varStatus="indice">
    <c:set var="clasefila" value=""></c:set>
    <c:if test="${d.estadopedido eq 'R'}"><c:set var="clasefila" value="alert-danger"/></c:if> 
    <c:if test="${d.estadopedido eq 'A'}"><c:set var="clasefila" value="alert-success"/></c:if>
    <tr id="fila${indice.index}" class="fila alert ${clasefila}">
        <td>${d.idpedido}</td>
        <td class="product-name">
            ${d.nombre}
        </td>
        <td>${d.direccion}</td>
        <td><fmt:formatNumber type="number"  pattern="###.###" value="${d.totalpedido}" /></td>
<td>${d.fecha}</td>
<td>${d.tipopago}</td>
<td>
    <p style="width: 135px; margin: 0px;">
    <div id="activeButtons${indice.index}" class="btn-controls">
        <a title="ver" data-row="${indice.index}" class="edit btn btn-primary btn-sm viewOrder" href="javascript:void(0);" aria-label="Edit"><i  class="fa fa-eye fa-lg" aria-hidden="true"></i></a>
    </div>
</p>
<form id="formDatos${indice.index}" style="display: none;">
    <input type="hidden" id="pedido${indice.index}" name="idpedido" value="${d.idpedido}" class="fieldPedido"/>
    <input type="hidden" name="fecha" value="${d.fecha}" class="fieldFecha"/>
</form> 
</td>       
</tr>
</c:forEach>
