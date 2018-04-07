<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <form:input readonly="readonly" type="hidden" path="codigoProveedor"></form:input>
            <input id="nameProveedor" value="${sproveedor}"/>
            <input type="hidden" value="${sproveedor}" id="nombreProveedor" name="nombreProveedor"/>
            <c:choose>
                <c:when test="${tipo_sede eq 1}">
                    <label>Sede
                        <select id="idsede" name="idsede" style="width: 155px;">
                            <option value="">Seleccionar</option>
                            <c:import url="/${sessionScope.path}/sedes/ajax/seleccionarSede.htm">
                                <c:param name="idSede">${detalleCompraDTO.idsede}</c:param>
                            </c:import>
                        </select>
                        <form:hidden path="idsedepoint" />
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

            <label>
                <input value="Buscar" type="button" id="buscarCompra" data-url="<%=request.getContextPath()%>/${sessionScope.path}/compras/ajax/buscar/compra.htm"/>
            </label>
        </div>
        <div id="datosAdicionalesFactura">
            <label>Fecha</label>
            <form:input readonly="readonly"  path="fecha"></form:input>
                <label>Fecha de Vencimiento</label>
            <form:input readonly="readonly"  path="fechaVencimiento"></form:input>
                <label>Cancelada</label>
            <form:input readonly="readonly"  path="estadoCompraProveedor"></form:input>
                <label>Saldo</label>
            <form:input readonly="readonly"  path="saldo"></form:input>
                <input type="hidden" id="impresora" name="impresora" value=""/>
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
                    <c:set var="ind" value="0"/>
                    <c:choose>
                        <c:when test="${not empty detalleCompras}">
                            <c:forEach var="item" items="${detalleCompras}">
                                <tr>
                                    <td>
                                        <select class="clsAnchoTotal primerCampo">
                                            <option value="" >
                                                Seleccione   
                                            </option>
                                            <c:import url="/inventario/ajax/selectProducto.htm">
                                                <c:param name="selecion">${item.codigo}</c:param>
                                            </c:import>
                                        </select>
                                        <input value="${item.codigo}" type="text" name="name" class="editable-select primerCampo2" id="name" autocomplete="off" style="width: 93px;" >
                                        <input value="${item.codigo}" type="hidden" name="codigo${ind}" class="editable-select primerCampo2" id="codigo${ind}" autocomplete="off" style="width: 93px;" >
                                    </td>
                                    <td><input value="${item.producto}" type="text" class="clsAnchoTotal" readonly="readonly"></td>
                                    <td><input value="${item.unidades}" type="text" class="clsAnchoTotal unidadesCampo"   /></td>
                                    <td><input value="${item.promedio}" type="text" class="clsAnchoTotal" readonly="readonly" /></td>
                                    <td><input value="${item.valor}" type="text" class="clsAnchoTotal totalCampo" /></td>
                                    <td align="right"><input type="button" value="-" class="clsEliminarFila"></td>
                                </tr>   
                                <c:set var="ind" value="${ind+1}"/>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
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
                        </c:otherwise>
                    </c:choose>
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
                            <input type="button" id="actualizar" value="Actualizar" />
                            <!--div style="display: none;">
                            <input type="button" id="actualizar" value="Actualizar" >
                            </div-->
                            <!input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
                </td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</form:form>


