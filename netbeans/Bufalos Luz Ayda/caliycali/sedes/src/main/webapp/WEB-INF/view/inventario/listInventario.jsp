<%@page import="java.util.Date"%>
<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${inventarios}" var="inventario">
    <tr>
        <td><input type="text" class="codigoInventario primerCampo3" readonly="readonly" value="${inventario.codigoProductoInventario}"/></td>
        <td><input type="text" class="descripcionProducto" value="${inventario.descripcionProducto}"/></td>
        <td><input type="text" class="clsAnchoTotal2" onkeypress="return validarNUM(event)" value="${inventario.stockMinimo}"/></td>
        <td><input type="text" class="clsAnchoTotal2" onkeypress="return validarNUM(event)" value="${inventario.stockHoy}"/></td>
        <td><input type="text" style="cursor: pointer;" class="clsAnchoTotal2 fechaInicial" value="${inventario.fechaInicial}"/></td>
        <td><input class="clsAnchoTotal2" type="text" onkeypress="return validarNUM(event)" value="${inventario.stockReal}"/></td>
        <td><input class="clsAnchoTotal2 fechaFinal" style="cursor: pointer;" type="text" value="${inventario.fechaFinal}"/></td>
        <td><input class="clsAnchoTotal2" onkeypress="return validarNUM(event)"type="text" readonly="readonly" value="${inventario.promedio}"/></td>
        <td align="right"><input type="image" src="<%=request.getContextPath()%>/img/refresh.jpg" value="" alt=Actualizar" class="clsActualizarFila"></td>
        <td align="right"><input type="button" alt="Eliminar" tit value="-" class="clsEliminarFila"></td>
    </tr>
</c:forEach>
