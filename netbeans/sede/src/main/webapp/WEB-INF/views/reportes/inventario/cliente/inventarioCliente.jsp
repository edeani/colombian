<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/reportes/inventario.css">
<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/inventario/reporteInventarioCliente.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>

<div id="contenidoHome">
    <input type="hidden" value="<%=request.getContextPath()%>/${sessionScope.path}/img/loaders/" id="rutaLoader">
    <div id="tituloPagina">${titulo}</div>
    <div class="contentFormSimple">
        <form id="formInventarioCliente"  action="<%=request.getContextPath()%>/${sessionScope.path}/inventario/reportes/cliente/ajax/consultar.htm">
            <label>Tel&eacute;fono
                <input name="tel" id="tel" class="fechaInicial contentRequired" style="cursor: pointer;" type="text" value=""/>
            </label>
            <label>Fecha Inicial
                <input name="fechaInicial" id="fechaInicial" class="fechaInicial contentRequired" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fecha}"/>"/>
            </label>
            <label>Fecha Final
                <input name="fechaFinal" id="fechaFinal" class="fechaFinal contentRequired" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fecha}"/>"/>
            </label>
            <label>Sede
                <c:set var="sedeSeleccionada" value="${sessionScope.idusuario}"></c:set>
                    <select id="sedeSession" name="sede" class="contentRequired">
                        <option value="">Seleccionar</option>
                    <c:import url="/${sessionScope.path}/sedes/ajax/seleccionarSede.htm">
                        <c:param name="idSede" value="${sedeSeleccionada}"></c:param>
                    </c:import>
                </select>
            </label>
            <label>
                <input id="consultarInventarioCilente" type="submit" value="Aceptar" class="generalButton"/>
            </label>
        </form>
    </div>
    <!-- barra Cargando -->
    <div id="cargador"></div>             
    <div id="listaInventario" >
    </div>
</div>