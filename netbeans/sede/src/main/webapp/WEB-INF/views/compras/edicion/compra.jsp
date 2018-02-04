<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-confirm.css">
<script src="<%=request.getContextPath()%>/js/tabladinamica/manipulacion.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/compras/compras.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-confirm.js"></script>
<div id="urlGuardar" url-guardar="${pageContext.servletContext.contextPath}/${sessionScope.path}/compras/ajax/actualizar.htm"></div>
<input type="hidden" value="N" id="submit-form"/>
<div id="contenidoCompra">
    <input id="rutaLoader"value="${pageContext.servletContext.contextPath}/img/loaders/" type="hidden"/>
    <form:form commandName="${commandName}" path="DetalleCompraDTO" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/compras/compraPdf.htm" target="_blank">
        <div id="contenidoHome"> 
            <div id="tituloPagina">${titulo}</div>
            <div id="campoNumeroFactura">
                <label class="textoNegro">
                    No. Factura 
                </label>
                <form:input  type="text" path="numeroFactura" onkeypress="return validarNUM(event)"/>
                <label>
                    Proveedor
                </label>
                <form:input readonly="readonly" type="text" path="codigoProveedor"></form:input>
                    <label>
                        <input value="Buscar" type="button" id="buscarCompra" data-url="<%=request.getContextPath()%>/${sessionScope.path}/compras/ajax/buscar/compra.htm"/>
                </label>
            </div>
            <div id="cargador"></div>
            <div class="clear"></div>
            <div class="clear"></div>
            <div class="clear"></div>
            <div id="divContenedorTabla" data-url="<%=request.getContextPath()%>/${sessionScope.path}/factura/ajax/producto.htm">
                <table id="tblDatos" align="center" >
                    <form:input path="factura" type="hidden"/>
                    <thead>
                        <tr>
                            <th>C&oacute;digo</th>
                            <th>Producto</th>
                            <th>Unidades</th>
                            <th>Valor U.</th>
                            <th>Total Producto</th>
                            <th width="22">&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody id="contenidoFactura" data-url="<%=request.getContextPath()%>/${sessionScope.path}/inventario/ajax/selectProducto.htm">

                        <tr>
                            <td>
                                <select class="clsAnchoTotal primerCampo">
                                    <option value="" >
                                        Seleccione   
                                    </option>
                                    <c:import url="/${sessionScope.path}/inventario/ajax/selectProducto.htm">
                                    </c:import>
                                </select>
                                <input type="text" name="name" class="editable-select primerCampo2" id="name" autocomplete="off" style="width: 93px;" >
                            </td>
                            <td><input type="text" class="clsAnchoTotal" readonly="readonly"></td>
                            <td><input type="text" class="clsAnchoTotal unidadesCampo"   /></td>
                            <td><input type="text" class="clsAnchoTotal" readonly="readonly" /></td>
                            <td><input type="text" class="clsAnchoTotal totalCampo" /></td>
                            <td align="right"><input type="button" value="-" class="clsEliminarFila"></td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Total</td>
                            <td><form:input path="totalFactura" type="text" readonly="readonly"/>
                        </tr>
                        <tr>    
                            <td></td>
                            <td></td>
                            <td colspan="4" align="right">
                                <input type="button" id="actualizar" value="Actualizar" >
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

