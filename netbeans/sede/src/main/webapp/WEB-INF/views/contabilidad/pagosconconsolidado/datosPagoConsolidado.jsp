<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table id="tblPagosConsolidado" class="tblPagosTerceros" align="center" style="display: none;">
    <thead>
        <tr>
            <th>Sede</th>
            <th>Cuenta</th>
            <th>Concepto</th>
            <th>Detalle</th>
            <th>Total</th>
            <th width="22">&nbsp;</th>
        </tr>
    </thead>
    <tbody id="contenidoFactura" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/selectProducto.htm">
        <c:forEach var="item" varStatus="fila" items="${detallePagosCosolidadoSedeDto}">
        <tr>
            <td>
                <input type="text" id="inputIdentificadorSede${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].nombreSede" value="${item.nombreSede}"/>    
                <input type="hidden" id="inputIdentificadorSede${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].idSede" value="${item.idSede}"/>    
            </td>
            <td>
                <input id="idpagotercero${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].idporcentajeventa" value="${item.idporcentajeventa}" type="hidden"/>
                <input data-numero="${fila.index}" id="numerocuenta${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].idCuenta" value="${item.idCuenta}" class="ui-autocomplete-input claseValidarNum claseCuenta" autocomplete="off"/>
            </td>
            <td>
                <input class="claseConceptoCuenta" id="concepto${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].conceptoCuenta" value="${item.conceptoCuenta}"/>
            </td>   
            <td>
                <input class="clasedescripcion claseproximocampo" id="detalle${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].detalle" value="${item.detalle}" maxlength="500"/>
            </td>
            <td>
                <input class="claseValidarNum claseFormatDec claseTotalProveedor" id="total${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].total" value=""/>
                <input id="fechaPago${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].fecha" value="${fechaActual}" type="hidden"/>
            </td>
            <td align="right">
                <input type="hidden" id="porcentaje${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].porcentaje" value="${item.porcentaje}"/>
                <input type="hidden" id="numero${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].numero" value="${fila.index}"/>
                <input type="hidden" id="idPago${fila.index}" name="detallePagosCosolidadoSedeDtos[${fila.index}].idPago" value=""/>
            </td>
        </tr>
        </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>Total</td>
            <td><input name="total" id="total" value="0" class="claseFormatDec"/></td>
        </tr>
        <tr>    
            <td></td>
            <td></td>
            <td colspan="4" align="right">
                <input type="button" id="generar" value="Generar" />
                <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
            </td>
        </tr>
    </tfoot>
</table>
