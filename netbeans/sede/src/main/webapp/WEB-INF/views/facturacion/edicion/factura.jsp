<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
<script src="<%=request.getContextPath()%>/js/tabladinamica/manipulacion.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/tabladinamica/facturas.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.js" type="text/javascript"> </script>
<!--script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.pack.js" type="text/javascript"> </script-->

<div id="formFactura" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/factura/ajax/actualizar.htm">
<form:form target="_blank" commandName="${commandName}" path="DetalleFacturaDTO" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/${sessionScope.path}/factura/facturaVentaActualizadaPDF.htm" >
    <div id="contenidoHome"> 
        <input id="rutaLoader"value="<%=request.getContextPath()%>/img/loaders/" type="hidden"/>
        <div id="tituloPagina">${titulo}</div>
        <div id="campoNumeroFactura">
            <label class="textoNegro">
                No. Factura 
            </label>
            <form:input path="numeroFactura" type="text"  title="Número Factura" />
            <form:input path="sede" type="hidden"/>
            <input type="button" id="btnBuscar" value="Buscar"/>
            <div id="labelSede">
            </div> 
            <div id="cargador"></div>
            
            <input type="hidden" id="ids" value=""/>
            <input type="hidden" id="ns" value=""/>
        </div>
        <div class="clear"></div>
        <div class="clear"></div>
        <div class="clear"></div>
        <div id="divContenedorTabla" data-url="<%=request.getContextPath()%>/${sessionScope.path}/${sessionScope.path}/factura/ajax/producto.htm">
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
                <tbody id="contenidoFactura" data-url="<%=request.getContextPath()%>/${sessionScope.path}/${sessionScope.path}/inventario/ajax/selectProducto.htm">
                    <tr>
                        <input id="numeroSede" name="numeroSede" type="hidden" value=""/>
                        <input id="nombreSede" name="nombreSede" type="hidden" value=""/>
                        <input id="totalFact" name="totalFact" type="hidden" value=""/>
                    </tr>
                    <tr>
                        <td>
                            <select class="clsAnchoTotal primerCampo">
                                <option value="" >
                                    Seleccione   
                                </option>
                                <c:import url="/inventario/ajax/selectProducto.htm">
                                </c:import>
                            </select>
                            <input type="text" name="name" id="name" autocomplete="off" style="width: 93px;" class="primerCampo2">
                        </td>
                        <td><input type="text" class="clsAnchoTotal" readonly="readonly"></td>
                        <td><input type="text" class="clsAnchoTotal unidadesCampo" /></td>
                        <td><input type="text" class="clsAnchoTotal" readonly="readonly" /></td>
                        <td><input type="text" readonly="readonly" class="clsAnchoTotal totalCampo" /></td>
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
                            <input type="button" id="actualizar" value="Facturar" >
                            <!input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
                </td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</form:form>
</div>

