<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<td><input type="text" value="${idProducto}"class="clsAnchoTotal primerCampo"></td>
    <td><input type="text" value="${producto.producto}" class="clsAnchoTotal" readonly="readonly"></td>
    <td><input type="text" value="${producto.valor}" class="clsAnchoTotal" readonly="readonly"></td>
    <td><input type="text" class="clsAnchoTotal unidadesCampo clsAgregarFilaCampo"></td>
    <td><input type="text" class="clsAnchoTotal" readonly="readonly"></td>
    <td align="right"><input type="button" value="-" class="clsEliminarFila"></td>
