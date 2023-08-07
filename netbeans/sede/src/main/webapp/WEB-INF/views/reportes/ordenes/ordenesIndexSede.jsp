<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/ordenes/ordenes.js" type="text/javascript"></script>
</head>

<div id="contenidoHome">
    <div id="tituloPagina">Usuario Ordenes</div>
    <div class="contenedorEstructuraFormLarge">
        <div class="contentFormSimple">
            <form id="formOrdenesUsuario" target="_blank" action="<%=request.getContextPath()%>/${sessionScope.path}/ordenes/usuariosPdf.htm">
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
                    <input id="consultarOrdenesUsuario" type="submit" value="Aceptar" class="generalButton"/>
                </label>
            </form>
        </div>
        <!-- barra Cargando -->
        <div id="cargador"></div>
        
        <div id="listaOrdenesUsuario" class="divContenedorTabla" >
            <div class="contenedorResumen">             
                 
                <label id="totalGastosLabel" class="resumen">
                    $0
                </label>
                <label class="resumen">
                    TOTAL ORDENES
                </label>
                <label id="totalConsignacionesLabel" class="resumen">
                    0
                </label>
                <label class="resumen">
                    ORDENES
                </label>
            </div>
        </div>
    </div>
</div>