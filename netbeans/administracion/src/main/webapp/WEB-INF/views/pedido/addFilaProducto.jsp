<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tr id="f${sizeFilas}" class="f">
    <td>${producto.idproducto}</td>
    <td><a href="<%=request.getContextPath()%>/productos/editar-producto.htm?idproducto=${producto.idproducto}" target="_blank" title="Ver Detalle de Producto">${producto.value}</a></td>
    <td><fmt:formatNumber type="number"  pattern="###.###" value="${producto.precio}" /></td>
    <td>
        <div class="quantity buttons_added">
            <input type="button" value="-" class="minus">
            <input data-row="${sizeFilas}" value="1" type="number" min="1" step="1" id="view${sizeFilas}Cantidad" title="Cantidad" class="input-text qty text viewCantidad indiceData" size="4">
            <input type="button" value="+" class="plus">
        </div>
    </td>
    <td id="p${sizeFilas}viewTotalProducto" class="indiceViewTotalProducto"><fmt:formatNumber type="number"  pattern="###.###" value="${total}" /></td>
    <td><a data-row="${sizeFilas}" class="remove removeCar indiceData" href="javascript:void(0);"><i class="fa fa-close"></i></a></td>
<input type="hidden" id="p${sizeFilas}idproducto" name="productos[${sizeFilas}].idproducto" value="${producto.idproducto}">
<input type="hidden" id="p${sizeFilas}nombreproducto" name="productos[${sizeFilas}].nombreproducto" value="${producto.value}">
<input type="hidden" id="p${sizeFilas}precio" name="productos[${sizeFilas}].precio" value="${producto.precio}"> 
<input type="hidden" id="p${sizeFilas}cantidad" name="productos[${sizeFilas}].cantidad" value="${producto.cantidad}"> 
<input type="hidden" id="p${sizeFilas}total" name="productos[${sizeFilas}].total" value="${total}">
</tr>

