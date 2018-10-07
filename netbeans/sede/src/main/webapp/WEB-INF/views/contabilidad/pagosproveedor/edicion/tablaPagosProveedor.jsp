<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<input type="hidden" value="${encontrado}" id="encontrado"/>
<form:form target="_blank" commandName="${commandName}" path="PagosProveedorDto" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/proveedor/edicion/administrar.htm" url-update-compras="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/proveedor/actualizar.htm" >
    <div class="clear"></div>
    <div class="clear"></div>
    <div class="clear"></div>
    <div  style="position: relative; margin-left: 40px; height: 30px;">
        <label id="cmpSecuencia" style="display: block; float: left;">
            No. Comprobante
            <form:input path="secuencia" readonly="readonly"/>
            <form:input path="tipo" type="hidden"/>
            <input type="button" style="display: none;" value="buscar" id="buscarPagoProveedor" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/proveedor/buscar.htm"/>
        </label>
        <label id="cmpBeneficiario" style="display: block; float: left;">
            Beneficiario
            <form:input path="nombreProveedor"/>
            <form:input path="idProveedor" type="hidden"/>
        </label>
        <label id="cmpFecha" style="display: block; float: left;">
            Fecha
            <form:input path="fechaPago" cssClass="fechaInicial"/>
        </label>
        <label id="cmpTotal" style="display: block; float: left;">
            Total
            <form:input path="totalPago" />
            <input type="button" value="Limpiar" id="limpiarBusqueda" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/proveedor/buscar.htm"/>
        </label>
    </div>
    <div id="cargador"></div>
    <div id="divContenedorTabla"  style="margin: 0 3% !important;" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/factura/ajax/producto.htm">
        <table id="tblDatos" class="tblPagosTerceros" align="center">
            <thead>
                <tr>
                    <th>Compra</th>
                    <th>Cuenta</th>
                    <!--th>Concepto</th-->
                    <th>Detalle</th>
                    <th>Fecha</th>
                    <th>Total</th>
                    
                </tr>
            </thead>
            <tbody id="content">
                <c:forEach var="itemPago" items="${pagosProveedorDto.detallePagosProveedor}" varStatus="indice">
                    <tr>
                        <td style=" ">
                            <input class="claseCompra" type="text" id="numeroCompra${indice.index}" name="detallePagosProveedor[${indice.index}].numeroCompra" value="${itemPago.numeroCompra}"/>
                            <input type="hidden" id="inputIdentificadorSede${indice.index}" name="detallePagosProveedor[${indice.index}].idSede" value="${itemPago.idSede}"/>    
                        </td>
                        <td>
                            <input id="idpagoproveedor${indice.index}" name="detallePagosProveedor[${indice.index}].idpagoproveedor" value="${itemPago.idpagoproveedor}" type="hidden"/>
                            <input data-numero="${indice.index}" id="numerocuenta${indice.index}" name="detallePagosProveedor[${indice.index}].idCuenta" value="${itemPago.idCuenta}" class="ui-autocomplete-input claseValidarNum claseCuenta" autocomplete="off"/>
                        </td>
                        <!--td>
                            <input class="claseConceptoCuenta" id="concepto${indice.index}" name="detallePagosProveedor[${indice.index}].conceptoCuenta" value="${itemPago.conceptoCuenta}"/>
                        </td-->   
                        <td>
                            <input class="clasedescripcion claseproximocampo" id="detalle${indice.index}" name="detallePagosProveedor[${indice.index}].detalle" value="${itemPago.detalle}" maxlength="500"/>
                        </td>
                        <td>
                            <input id="fecha0" name="detallePagosProveedor[${indice.index}].fecha" value="${itemPago.fecha}" type="text"/>
                        </td>
                        <td>
                            <fmt:formatNumber type="number" pattern="###,##0" value="${itemPago.total}" var="totalFormat"/>
                            <input class="claseValidarNum claseFormatDec claseTotalProveedor" id="total${indice.index}" name="detallePagosProveedor[${indice.index}].total" value="${totalFormat}"/>                                    
                            <input type="hidden" id="numero${indice.index}" name="detallePagosProveedor[${indice.index}].numero" value="${itemPago.numero}"/>
                        </td>
                        
                    </tr>

                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td></td>
                    <td></td>
                    <!--td></td-->
                    <td></td>
                    <td>Total</td>
                    <td><input name="totalPago" id="totalPago" value="<fmt:formatNumber type="number" pattern="###" value="${pagosProveedorDto.totalPago}"/>"</td>
                </tr>
                <tr>    
                    <td></td>
                    <td></td>
                    <td colspan="4" align="right">
                        <input type="button" id="reset-pago" value="Continuar" />
                        <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                        <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
                    </td>
                </tr>
            </tfoot>
        </table>
    </div>
</form:form>