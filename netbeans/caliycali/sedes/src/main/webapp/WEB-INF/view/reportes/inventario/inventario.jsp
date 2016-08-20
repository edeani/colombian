<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/reportes/inventario.css">
<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/inventario/reporteInventario.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"> </script>
<%@ page import="java.util.*"%>

<div id="contenidoHome">
    <input type="hidden" value="<%=request.getContextPath()%>/img/loaders/" id="rutaLoader">
    <div id="tituloPagina">${titulo}</div>
    <div id="reporteInventario" data-url="<%=request.getContextPath()%>/inventario/ajax/inventarioSede.htm">
        <div id="formFechas">
            <label>Fecha
                <input id="fecha" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fecha}"/>"/>
            </label>
            <label>
                Sede
                <select id="idsede">
                <c:import url="/chia/ajax/seleccionarSede.htm">
                </c:import>
                </select>
            </label>
            <label>
                <input id="reporte" type="button" value="Aceptar"/>
            </label>
            <label>
                <div id="cargador"></div>
            </label>
        </div>
        <div id="reporteInventarioSede" class="datosReporteSede">
            <table id="tablaInventario">
                <thead>
                    <tr>
                        <th>C&oacute;digo</th>
                        <th>Producto</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productos}" var="item">
                        <tr class="fila">
                            <td>${item.id}</td><td>${item.label}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
            <div id="resultado"></div>
                
    </div>
</div>