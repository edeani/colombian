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
    <script src="<%=request.getContextPath()%>/js/utilWindows.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/contabilidad/cuentasBeneficiarios.js?id=1" type="text/javascript"></script>
</head> 
<div id="tituloPagina">Pagos Proveedor Caja Mayor</div>
<div id="formProveedor" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/secuencia.htm">                              
    <input id="cuentaProveedores" value="${cuentaProveedores}" type="hidden">
    <form:form target="_blank" commandName="${commandName}" path="PagosProveedorDto" data-urlcomprobante="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/proveedores/pdf/comprobante.htm" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/ajax/proveedor/update.htm" data-home="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/proveedor/edicion/index.htm">
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
                <label id="cmpBeneficiario" >
                    Proveedor
                    <form:input path="nombreProveedor"/>
                    <form:input path="idProveedor" type="hidden"/>
                    <form:input path="tipo" type="hidden"/>
                </label>
                <label id="cmpSecuencia" style="display: block; float: left;">
                    No. Comprobante
                    <form:input path="secuencia" readonly="readonly"/>
                </label>
                <label id="cmpFecha" style="display: block; float: left;">
                    Fecha
                    <form:input path="fechaPago" cssClass="fechaInicial"/>
                </label>
                <!--+label id="lnkRows" style="display: none;">
                    <a href="javascript:void(0);" onclick="addRowPagosProveedor('contenidoComprobante');" id="agregarFila">AgregarFila</a>
                </label-->
            </div>
            <div id="cargador"></div>    
            <div id="divContenedorTabla2" style="display: block;">
                <table id="tblDatos2" class="tblPagosProveedor" align="center" style="display: block;">
                    <thead>
                        <tr>
                            <th>Compra</th>
                            <th>Fecha</th>
                            <th>Fecha Venc.</th>
                            <th>Saldo</th>
                            <th>Total</th>
                            <th width="22">&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody id="contenidoComprasPendientes" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/selectProducto.htm">

                        <c:forEach items="${comprasPendientes}" var="item" varStatus="fila">
                            <tr id="filaComprobante${fila.index}" data-identificadorcompra="${item.idCompra}" data-consecutivointerno="${item.consecutivo}">
                                <td>
                                    <input type="hidden" id="consecutivointerno${fila.index}" value="${item.consecutivo}">
                                    <input id="idCompraPendiente${fila.index}" value="${item.idCompra}">
                                </td>
                                <td>
                                    <input id="fechaCompraPendiente${fila.index}" value="${item.fechaCompra}">
                                </td>
                                <td>
                                    <input id="fechaVencimientoPendiente${fila.index}" value="${item.fechaVencimiento}">
                                </td>
                                <td>
                                    <input id="saldoPendiente${fila.index}" value="<fmt:formatNumber groupingUsed="false" value="${item.saldo}" type="number"></fmt:formatNumber>">
                                    </td>
                                    <td>
                                        <input id="valorTotalPendiente${fila.index}" value="<fmt:formatNumber value="${item.valorTotal}" groupingUsed="false" type="number"></fmt:formatNumber>">
                                    </td>
                                    <td align="right">
                                        <input type="button" value="+" class="clsAgregarFilaProveedor" data-numero="${fila.index}">
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>

            </div>
            <div id="divContenedorTabla"  style="margin: 0 1% !important; display: none;" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/factura/ajax/producto.htm">
                <table id="tblDatos" class="tblPagosProveedor" align="center" style="display: block;">
                    <thead>
                        <tr>
                            <th>Compra</th>
                            <th>Fecha Compra</th>
                            <th>Detalle</th>
                            <th>Fecha Venc.</th>
                            <th>Saldo</th>
                            <th>Total</th>
                            <th width="22">&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody id="contenidoComprobante" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/selectProducto.htm">

                    </tbody>
                    <tfoot>
                        <tr>
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
                            <td></td>
                            <td></td>
                            <td></td>
                            <td colspan="4" align="right">
                                <input type="button" id="generarComprobanteProveedor" value="Generar" />
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

