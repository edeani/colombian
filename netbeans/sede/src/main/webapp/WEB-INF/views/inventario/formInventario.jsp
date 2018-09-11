<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="divContenedorTabla" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/eliminarProducto.htm">
    <form id="idInventario" action="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/insertarProducto.htm">
        <table align="center">
            <thead>
                <tr>
                    <th>C&oacute;digo</th>
                    <th>Nombre</th>
                    <th>Inventario Mínimo</th>
                    <th>Inventario Inicial</th>
                    <th>Fecha Inicial</th>
                    <th>Inventario Final</th>
                    <th>Fecha Final</th>
                    <th>Promedio</th>
                </tr>
            </thead>
            <tbody id="productoInventario">
                <tr>
                    <td><input name="codigoProductoInventario" id="codigoProductoInventario" type="text" class="codigoInventario contentRequired primerCampo3"  onkeypress="return validarNUM(event)" value=""/></td>
                    <td><input name="descripcionProducto" id="descripcionProducto" type="text" class="contentRequired descripcionProducto" value=""/></td>
                    <td><input name="stockMinimo" id="stockMinimo" type="text" class="contentRequired clsAnchoTotal" onkeypress="return validarNUM(event)" value=""/></td>
                    <td><input name="stockHoy" id="stockHoy" type="text" class="contentRequired clsAnchoTotal" onkeypress="return validarNUM(event)" value=""/></td>
                    <td><input name="fechaInicial" id="fechaInicial" type="text" style="cursor: pointer;" readonly="readonly" class="contentRequired clsAnchoTotal fechaInicial" onkeypress="return validarNUM(event)" value=""/></td>
                    <td><input name="stockReal" id="stockReal" class="contentRequired clsAnchoTotal" type="text" onkeypress="return validarNUM(event)" value=""/></td>
                    <td><input name="fechaFinal" id="fechaFinal" class="contentRequired clsAnchoTotal fechaFinal" readonly="readonly" style="cursor: pointer;" type="text" value=""/></td>
                    <td><input name="promedio" id="promedio" class="contentRequired clsAnchoTotal" type="text" onkeypress="return validarNUM(event)" value=""/></td>
                    <td align="right"><input type="button" alt="Agregar" value="+" class="clsAgregarFila"></td>
                </tr>
            </tbody>
        </table>
    </form>
        <div>
            <br>
        </div>    
    <table align="center" id="tablaInventario">
        <thead>
            <tr>
                <th>C&oacute;digo</th>
                <th>Nombre</th>
                <th>Inventario Mínimo</th>
                <th>Inventario Inicial</th>
                <th>Fecha Inicial</th>
                <th>Inventario Final</th>
                <th>Fecha Final</th>
                <th>Promedio</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody id="contenidoInventario" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/listInventario.htm" 
               data-actualizar="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/actualizarProducto.htm">
            <c:import url="/${sessionScope.path}/inventario/ajax/listInventario.htm">
            </c:import>
        </tbody>
        
    </table>
</div>