<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div id="urlGuardar" url-guardar="${pageContext.servletContext.contextPath}/compras/ajax/guardar.htm"></div>
<form:form commandName="${commandName}" path="DetalleCompraDTO" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/compras/compraPdf.htm" data-verificacion="${pageContext.servletContext.contextPath}/compras/ajax/verificar/compra.htm" target="_blank">
    <div id="contenidoHome"> 
        <div id="tituloPagina">${titulo}</div>
        <div id="campoNumeroFactura">
            <label class="textoNegro">
                No. Factura 
            </label>
            <form:input type="text" path="numeroFactura" onkeypress="return validarNUM(event)"/>
            <label>
                Proveedores
            </label>
            <form:select path="codigoProveedor">
                <c:import url="/${sessionScope.path}/proveedor/ajax/listaProveedores.htm">
                    <c:param name="proveedores" value="${proveedores}"/>
                </c:import>
            </form:select>
            <input id="nombreProveedor" name="nombreProveedor" type="hidden" value=""/>
            <label>
                Fecha Vencimiento
            </label>
            <form:input path="fechaVencimiento" class="fechaVencimiento"/>
            </br>
                <c:choose>
                    <c:when test="${tipo_sede eq 1}">
                        <label>Sede
                            <select id="idsede" name="idsede" style="width: 155px;">
                                <option value="">Seleccionar</option>
                                <c:import url="/${sessionScope.path}/sedes/ajax/listaSedeSelectCredencial.htm"/>
                            </select>
                            <input type="hidden" id="idsedepoint" name="idsedepoint" value="" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/sedes/ajax/find/idsedepoint.htm"/>
                        </label>
                    </c:when>
                    <c:otherwise>
                        <div style="display: none;">
                            <select  id="idsede" name="idsede" >
                                <option value="${subSedePrincipal.id}" selected="selected" >${subSedePrincipal.sede}</option>
                            </select>
                        </div>
                        <input type="hidden" id="idsedepoint" name="idsedepoint" value="${subSedePrincipal.idsedepoint}" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/sedes/ajax/find/idsedepoint.htm"/>
                    </c:otherwise>
                </c:choose>
            </label>
            <input type="hidden" id="impresora" name="impresora" value=""/>
        </div>

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
                                <c:import url="/inventario/ajax/selectProducto.htm">
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
                            <input type="button" id="facturar" value="Facturar" >
                            <!--div style="display: none;">
                                <input type="button" id="facturar" value="Facturar" >
                            </div-->
                            <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                            <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</form:form>

