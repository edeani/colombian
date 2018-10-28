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
    <script src="<%=request.getContextPath()%>/js/pagos/pagos-proveedor-edicion.js?id=1" type="text/javascript"></script>
</head> 
<!--script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.pack.js" type="text/javascript"> </script-->
<div id="tituloPagina">Edici&oacute;n Pagos de Proveedor</div>
<div id="formPago" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/secuencia.htm">                                 

    <div id="contenidoHome"> 
        <input id="rutaLoader"value="${pageContext.servletContext.contextPath}/img/loaders/" type="hidden"/>
        <div id="tituloPagina">${titulo}</div>
        <div id="contenidoFormularioPago">
            <form:form target="_blank" commandName="${commandName}" path="PagosProveedorDto" data-urlcomprobante="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/terceros/pdf/comprobante.htm" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/terceros/guardar.htm" >
                <div class="clear"></div>
                <div class="clear"></div>
                <div class="clear"></div>
                <div style="position: relative; margin-left: 115px;">
                   

                    <label id="cmpSecuencia" >
                        No. Comprobante
                        <form:input path="secuencia" readonly="readonly"/>
                        <form:input path="tipo" type="hidden"/>
                        <input type="button" value="buscar" id="buscarPagoProveedor" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/proveedor/buscar.htm"/>
                    </label>
                    <label id="cmpBeneficiario" style="display: none;">
                        Beneficiario
                        <input id="nombreSedeFinded" value="" disabled="disabled"/>
                        <form:input path="idProveedor" type="hidden"/>
                    </label>
                    <label id="cmpFecha" style="display: none;">
                        Fecha
                        <form:input path="fechaPago" cssClass="fechaInicial claseCellFechaLight"/>
                    </label>
                    <label id="cmpFecha" style="display: none;">
                        Total
                        <form:input path="totalPago" />
                    </label>
                </div>
                <div id="cargador"></div>
                <div id="divContenedorTabla"  style="margin: 0 3% !important;" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/factura/ajax/producto.htm">
                    <table id="tblDatos" class="tblPagosTerceros" align="center" style="display: none;">
                        <thead>
                            <tr>
                                <th>Compra</th>
                                <th>Cuenta</th>
                                <th>Concepto</th>
                                <th>Detalle</th>
                                <th>Fecha</th>
                                <th>Total</th>
                                <th width="22">&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody id="contenidoFactura" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/selectProducto.htm">

                            <tr>
                                <td>
                                    <input type="text" id="numeroCompra0" name="detallePagosProveedor[0].numeroCompra" value=""/>
                                    <input type="hidden" id="inputIdentificadorSede0" name="detallePagosProveedor[0].idSede" value=""/>    
                                </td>
                                <td>
                                    <input id="idpagoproveedor0" name="detallePagosProveedor[0].idpagoproveedor" value="" type="hidden"/>
                                    <input data-numero="0" id="numerocuenta0" name="detallePagosProveedor[0].idCuenta" value="" class="ui-autocomplete-input claseValidarNum claseCuenta" autocomplete="off"/>
                                </td>
                                <td>
                                    <input class="claseConceptoCuenta" id="concepto0" name="detallePagosProveedor[0].conceptoCuenta" value=""/>
                                </td>   
                                <td>
                                    <input class="clasedescripcion claseproximocampo" id="detalle0" name="detallePagosProveedor[0].detalle" value="" maxlength="500"/>
                                </td>
                                <td>
                                    <input id="fecha0" name="detallePagosProveedor[0].fecha" value="" type="text"/>
                                </td>
                                <td>
                                    <input class="claseValidarNum claseFormatDec claseTotalProveedor" id="total0" name="detallePagosProveedor[0].total" value=""/>                                    
                                </td>
                                <td align="right">
                                    <input type="button" value="-" class="clsEliminarFila">
                                    <input type="hidden" id="numero0" name="detallePagosProveedor[0].numero" value="1"/>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td></td>
                                <td></td>
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
                                    <input type="button" id="reset-pago" value="Continuar" />
                                    <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                                    <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </form:form>
        </div>
    </div>
    <div style="display: none;">
        <form id="ver_pdf_terceros" action="<%=request.getContextPath()%>/${sessionScope.path}/pagos/terceros/pdf/comprobante.htm" target="_blank">
            <input type="hidden" value="" id="idpagoproveedor" name="idpagoproveedor"/>
        </form>
    </div>
</div>

