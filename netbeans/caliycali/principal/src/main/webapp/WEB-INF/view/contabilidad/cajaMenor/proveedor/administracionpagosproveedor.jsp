<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <link type="text/css" rel="stylesheet" href="/colombianCaliyCali/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="/colombianCaliyCali/css/contabilidad/pagosterceros.css">
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/contabilidad/cuentasBeneficiarios.js?id=1" type="text/javascript"></script>
</head> 
<div id="tituloPagina">Pagos Proveedor Caja Menor</div>
<div id="formProveedor" data-url="${pageContext.servletContext.contextPath}/cajamenor/ajax/secuencia.htm">     
    <input id="cuentaProveedores" value="${cuentaProveedores}" type="hidden">
    <form:form target="_blank" commandName="${commandName}" path="PagosProveedorDto" data-urlcomprobante="${pageContext.servletContext.contextPath}/cajamenor/proveedores/pdf/comprobante.htm" action="${pageContext.servletContext.contextPath}/cajamenor/ajax/proveedor/guardar.htm" >
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
                    <form:select path="nombreProveedor">
                        <option value="">Seleccionar</option>
                        <c:import url="/proveedor/ajax/listaProveedores.htm"></c:import>
                    </form:select>    
                    <form:input path="idProveedor" type="hidden"/>
                </label>
                <label id="cmpSecuencia" style="display: none;">
                    No. Comprobante
                    <form:input path="secuencia" readonly="readonly"/>
                </label>
                <label id="cmpFecha" style="display: none;">
                    Fecha
                    <form:input path="fechaPago" cssClass="fechaInicial"/>
                </label>
                <!--label id="lnkRows" style="display: none;">
                    <a href="javascript:void(0);" onclick="addRowPagosProveedor('contenidoComprobante');" id="agregarFila">AgregarFila</a>
                </label-->
            </div>
            <div id="cargador"></div>    
            <div id="divContenedorTabla2" style="display: none;">
                

            </div>
            <div id="divContenedorTabla"  style="margin: 0 1% !important; display: none;" data-url ="${pageContext.servletContext.contextPath}/factura/ajax/producto.htm">
                <table id="tblDatos" class="tblPagosProveedor" align="center" style="display: none;">
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
                    <tbody id="contenidoComprobante" data-url ="<%=request.getContextPath()%>/inventario/ajax/selectProducto.htm">
                        
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

