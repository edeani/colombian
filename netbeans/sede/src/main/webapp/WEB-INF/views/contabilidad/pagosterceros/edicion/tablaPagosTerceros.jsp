<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form:form target="_blank" commandName="${commandName}" path="PagosTercerosDto" data-urlcomprobante="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/terceros/pdf/comprobante.htm" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/terceros/guardar.htm" >
    <div id="campoNumeroFactura">
        <label class="textoNegro" style="display: none;">
            No. Factura 
        </label>
        <div id="labelSede"></div> 
    </div>
    <div class="clear"></div>
    <div class="clear"></div>
    <div class="clear"></div>
    <div style="position: relative; margin-left: 115px;">
        <label style="display: none;">
            <form:select path="idSede">
                <option value="">Seleccionar</option>
                <c:import url="/${sessionScope.path}/sedes/ajax/listaSedeSelect.htm"></c:import>
            </form:select>
            <form:input path="sede" type="hidden"/>
        </label>

        <label id="cmpSecuencia" >
            No. Comprobante
            <form:input path="secuencia" readonly="readonly"/>
            <input type="button" style="display: none;" value="buscar" id="buscarPago" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/terceros/buscar.htm"/>
        </label>
        <label id="cmpBeneficiario" >
            Beneficiario
            <input id="nombreSedeFinded" name="nombreBeneficiario" value="${pagosTercerosDto.nombreBeneficiario}" disabled="disabled"/>
            <form:input path="idBeneficiario" type="hidden"/>
        </label>
        <label id="cmpFecha">
            Fecha
            <form:input path="fechaPago" cssClass="fechaInicial"/>
        </label>
        <label id="lnkRows">
            <a href="javascript:void(0);" onclick="addRowPagos('contenidoFactura');" id="agregarFila">AgregarFila</a>
        </label>
        <label id="lnkRows2">
            <a href="javascript:void(0);" onclick="limpiarPagos();" id="limpiar">Limpiar</a>
        </label>
    </div>
    <div id="cargador"></div>
    <div id="divContenedorTabla"  style="margin: 0 3% !important;" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/factura/ajax/producto.htm">
        <table id="tblDatos" class="tblPagosTerceros" align="center">
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
            <c:forEach var="itemPago" items="${pagosTercerosDto.detallePagosTerceros}" varStatus="indice">
                <tr>
                    <td>
                        <select data-numero="${indice.index}" class="claseSelectSede" id="identificadorSede${indice.index}" >
                            <c:import url="/${sessionScope.path}/sedes/ajax/listaSedeSelect.htm">
                                <c:param name="seleccion" value="${itemPago.idSede}"/>
                            </c:import>
                        </select>
                        <input type="hidden" id="inputIdentificadorSede${indice.index}" name="detallePagosTerceros[${indice.index}].idSede" value="${itemPago.idSede}"/>
                    </td>
                    <td>
                        <input id="idpagotercero${indice.index}" name="detallePagosTerceros[${indice.index}].idpagotercero" value="" type="hidden"/>
                    <input data-numero="${indice.index}" id="numerocuenta${indice.index}" name="detallePagosTerceros[${indice.index}].idCuenta" value="${itemPago.idCuenta}" class="ui-autocomplete-input claseValidarNum claseCuenta" autocomplete="off"/>
                    </td>
                    <td>
                        <input class="claseConceptoCuenta" id="concepto${indice.index}" name="detallePagosTerceros[${indice.index}].conceptoCuenta" value="${itemPago.conceptoCuenta}"/>
                    </td>
                    <td>
                        <input class="clasedescripcion claseproximocampo" id="detalle${indice.index}" name="detallePagosTerceros[${indice.index}].detalle" value="${itemPago.detalle}" maxlength="500"/>
                    </td>
                    <td>
                        <fmt:formatNumber type="number" pattern="###,##0" value="${itemPago.total}" var="totalFormat"/>
                        <input class="claseValidarNum claseFormatDec claseTotalProveedor" id="total${indice.index}" name="detallePagosTerceros[${indice.index}].total" value="${fn:replace(totalFormat,'.',',')}"/>
                        <input id="fechaPago${indice.index}" name="detallePagosTerceros[${indice.index}].fecha" value="" type="hidden"/>
                    </td>
                    <td>
                        <input type="button" value="-" class="clsEliminarFila">
                    <input type="hidden" id="numero${indice.index}" name="detallePagosTerceros[${indice.index}].numero" value="${indice.index + 1}"/>
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
                    <td><input name="totalPago" id="totalPago" value="<fmt:formatNumber type="number" pattern="###" value="${pagosTercerosDto.totalPago}"/>"</td>
                </tr>
                <tr>    
                    <td></td>
                    <td></td>
                    <td colspan="4" align="right">
                        <input type="button" id="generar" value="Actualizar" />
                        <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                        <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
                    </td>
                </tr>
            </tfoot>
        </table>
    </div>
</form:form>