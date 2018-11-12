<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table id="datosInventario" class="tablaDatosInfo">
    <thead>
    <th>Fecha</th>
    <th>Tipo</th>
    <th>No. Orden</th>
    <th>Valor</th>
    <th>Mesa No.</th>
    <th>C&oacute;d. Mesera</th>
</thead>
<tbody>
    <c:forEach items="${mesas}" var="itemMesa">
        <tr>
            <td><fmt:formatDate  type="both" pattern="yyyy-MM-dd h:m:s a" value="${itemMesa.fecha}"/></td>
            <td>${itemMesa.tipo}</td>
            <td>${itemMesa.orden}</td>
            <td>
                ${itemMesa.valor}
                <input type="hidden" value="${itemMesa.valor}" class="cmpResumenvalorOrdenes"/>
            </td>
            <td>${itemMesa.mesa}</td>
            <td>${itemMesa.codMesera}</td>
        </tr>    
    </c:forEach>
</tbody>
<tfoot></tfoot>
</table>
<input id="totalOrdenes" type="hidden" value="" class="decimalordenes"/>
<div class="contenedorResumen">             
    <label id="cantidadOrenesLabel" class="resumen">
        <fmt:formatNumber type="number" pattern="###,##0" value="${cantidadOrdenes}"/>
    </label>
    <label class="resumen">
        ORDENES
    </label> 
    <label id="totalOrdenesLabel" class="resumen">
        $0
    </label>
    <label class="resumen">
        TOTAL ORDENES
    </label>
</div>