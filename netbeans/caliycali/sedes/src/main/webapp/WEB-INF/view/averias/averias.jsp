<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"> </script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/averias/averias.js?id=0" type="text/javascript"></script>
</head>
<form:form commandName="${commandName}" path="DetalleAveriaDTO" action="${pageContext.servletContext.contextPath}/averia/guardar.htm" >
<div id="contenidoHome">
    
    <div id="tituloPagina">${titulo}</div>
    <div class="clear"></div>
    <div class="clear"></div>
    <div class="clear"></div>
    <div id="divContenedorTabla" data-url ="<%=request.getContextPath()%>/factura/ajax/producto.htm">
        <table id="tblDatos" align="center" >
            <form:input path="averia" type="hidden"/>
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
            <tbody id="contenidoAveria" data-url ="<%=request.getContextPath()%>/inventario/ajax/selectProducto.htm">
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
                    <td><form:input path="totalAveria" type="text" readonly="readonly"/>
                </tr>
                <tr>    
                    <td></td>
                    <td></td>
                    <td colspan="4" align="right">
                        <input type="button" id="averiar" value="Guardar" >
                        <!input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
            <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
            </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
</form:form>