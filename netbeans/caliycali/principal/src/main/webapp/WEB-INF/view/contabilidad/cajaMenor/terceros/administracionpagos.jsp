<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/contabilidad/pagosterceros.css">
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/contabilidad/cuentasBeneficiarios.js?id=1" type="text/javascript"></script>
</head> 
<!--script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.pack.js" type="text/javascript"> </script-->

<div id="tituloPagina">Pagos  Terceros Caja Menor</div>
<div id="formPago" data-url="${pageContext.servletContext.contextPath}/cajamenor/ajax/secuencia.htm">                                 
    <form:form target="_blank" commandName="${commandName}" path="PagosTercerosDto" data-urlcomprobante="${pageContext.servletContext.contextPath}/cajamenor/terceros/pdf/comprobante.htm" action="${pageContext.servletContext.contextPath}/cajamenor/ajax/terceros/guardar.htm" >
        <div id="contenidoHome"> 
            <input id="rutaLoader"value="${pageContext.servletContext.contextPath}/img/loaders/" type="hidden"/>
            <div id="tituloPagina">${titulo}</div>
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
                        <c:import url="/sedes/ajax/listaSedeSelect.htm"></c:import>
                    </form:select>
                    <form:input path="sede" type="hidden"/>
                </label>
                <label id="cmpBeneficiario" >
                    Beneficiario
                    <form:select path="nombreBeneficiario">
                        <option value="">Seleccionar</option>
                        <c:import url="/beneficiarios/ajax/select.htm"></c:import>
                    </form:select>    
                    <form:input path="idBeneficiario" type="hidden"/>
                </label>
                <label id="cmpSecuencia" style="display: none;">
                    No. Comprobante
                    <form:input path="secuencia" readonly="readonly"/>
                </label>
                <label id="cmpFecha" style="display: none;">
                    Fecha
                    <form:input path="fechaPago" cssClass="fechaInicial"/>
                </label>
                <label id="lnkRows" style="display: none;">
                    <a href="javascript:void(0);" onclick="addRowPagos('contenidoFactura');" id="agregarFila">AgregarFila</a>
                </label>
            </div>
            <div id="cargador"></div>
            <div id="divContenedorTabla"  style="margin: 0 3% !important;" data-url ="${pageContext.servletContext.contextPath}/factura/ajax/producto.htm">
                <table id="tblDatos" class="tblPagosTerceros" align="center" style="display: none;">
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
                    <tbody id="contenidoFactura" data-url ="<%=request.getContextPath()%>/inventario/ajax/selectProducto.htm">
                        <tr>
                            <td>
                                <select data-numero="0" class="claseSelectSede" id="identificadorSede0" >
                                    <!--Aqu� van las opciones-->
                                </select>
                                <input type="hidden" id="inputIdentificadorSede0" name="detallePagosTerceros[0].idSede" value=""/>    
                            </td>
                            <td>
                                <input id="idpagotercero0" name="detallePagosTerceros[0].idpagotercero" value="" type="hidden"/>
                                <input data-numero="0" id="numerocuenta0" name="detallePagosTerceros[0].idCuenta" value="" class="ui-autocomplete-input claseValidarNum claseCuenta" autocomplete="off"/>
                            </td>
                            <td>
                                <input class="claseConceptoCuenta" id="concepto0" name="detallePagosTerceros[0].conceptoCuenta" value=""/>
                            </td>   
                            <td>
                                <input class="clasedescripcion claseproximocampo" id="detalle0" name="detallePagosTerceros[0].detalle" value="" maxlength="500"/>
                            </td>
                            <td>
                                <input class="claseValidarNum claseFormatDec claseTotalProveedor" id="total0" name="detallePagosTerceros[0].total" value=""/>
                                <input id="fechaPago0" name="detallePagosTerceros[0].fecha" value="" type="hidden"/>
                            </td>
                            <td align="right">
                                <input type="button" value="-" class="clsEliminarFila">
                                <input type="hidden" id="numero0" name="detallePagosTerceros[0].numero" value="1"/>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Total</td>
                            <td><input name="totalPago" id="totalPago" value="0"/></td>
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
            </div>
        </div>
    </form:form>
</div>

