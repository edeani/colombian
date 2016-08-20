<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="contenedorCuenta">
    <div id="divContenedorTabla" data-url ="${pageContext.servletContext.contextPath}/factura/ajax/producto.htm">
        <table id="tblDatos" align="center" >
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Nivel</th>
                    <th>Clase</th>
                    <th>Padre</th>
                    <th width="22">&nbsp;</th>
                </tr>
            </thead>
            <tbody id="contenidoFactura" data-url ="<%=request.getContextPath()%>/inventario/ajax/selectProducto.htm">
                <tr>
                    <td>
                        <input id="nombreCta" name="nombreCta" value="${cuentasPuc.nombreCta}"/>
                    </td>
                    <td>
                        <input readonly="readonly" id="tipo" name="tipo" value="${cuentasPuc.tipo}"/>
                    </td>
                    <td>
                        <input readonly="readonly" id="nivel" name="nivel" value="${cuentasPuc.nivel}"/>
                    </td>
                    <td>
                        <input readonly="readonly" id="clase" name="clase" value="${cuentasPuc.clase}"/>
                    </td>
                    <td>
                        <input readonly="readonly" id="conPadre" name="conPadre" value="${cuentasPuc.conPadre}"/>
                    </td>
                    <td align="right"><input id="btnGuardarCuenta" class="clsEliminarFila" type="button" value="Guardar" /></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
