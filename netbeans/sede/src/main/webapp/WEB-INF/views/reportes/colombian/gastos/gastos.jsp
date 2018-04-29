<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tree-table/jquery.treetable.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/tree-table/jquery.treetable.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/colombian/gastos/reporte-gastos.js" type="text/javascript"></script>

</head>
<div id="contenidoHome">
    <div id="tituloPagina">Reporte Gastos</div>
    <div class="contenedorEstructuraForm">
        <div class="">
            <form id="formGastos" action="<%=request.getContextPath()%>/${sessionScope.path}/gastos/ajax/generar-reporte.htm">
                <label>Fecha Inicial
                    <input name="fechaInicial" id="fechaInicial" class="fecha contentRequired" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fecha}"/>"/>
                </label>
                <label>Fecha Final
                    <input name="fechaFinal" id="fechaFinal" class="fecha contentRequired" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fecha}"/>"/>
                </label>
                <label>Sede
                    <select id="idSede" name="idSede" class="contentRequired">
                        <option value="">Seleccionar</option>
                        <c:import url="/${sessionScope.path}/sedes/ajax/listaSedeSelectCredencial.htm"/>
                    </select>
                    <input id="nombreSede" name="nombreSede" type="hidden"/>
                </label>
                <label>
                    <input id="consultarGastos" type="submit" value="Aceptar" class="generalButton"/>
                </label>
            </form>
        </div>
        <!-- barracargando -->
        <div id="cargador"></div>             
        <div id="reporteGastos" class="contentFormCol">
        </div>
    </div>
</div>
