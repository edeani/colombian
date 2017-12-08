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
    <script src="<%=request.getContextPath()%>/js/colombian/tiempoReal/cierres.js" type="text/javascript"></script>

    <script type="text/javascript">
        //Se utiliza para mostras mensaje si no hay registros en el reporte
        if ('${mensaje}' !== '') {
            $.colorbox({
                html: "<p id='mensaje'>${mensaje}</p>",
                initialHeight: 50,
                Height: 50,
                close: "aceptar"
            });
        }
    </script>
</head>
<div id="contenidoHome">
    <div id="tituloPagina">Tiempo Real</div>
    <div class="contenedorEstructuraForm">
        <div class="contentFormSimple">
            <form id="formCierreDiario" action="<%=request.getContextPath()%>/${sessionScope.path}/tiemporeal/ajax/calcular.htm">
                <label>Fecha
                    <input name="fecha" id="fecha" class="fecha contentRequired" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fechaInicial}"/>"/>
                </label>
                <label>Sede
                    <c:set var="sedeSeleccionada" value="${sessionScope.idusuario}"></c:set>
                    <select id="sedeSession" name="sede" class="contentRequired">
                            <option value="">Seleccionar</option>
                        <c:import url="/sedes/ajax/seleccionarSede.htm">
                            <c:param name="idSede" value="${sedeSeleccionada}"></c:param>
                        </c:import>
                    </select>
                </label>
                <label>
                    <input id="consultarCierreDiario" type="submit" value="Aceptar" class="generalButton"/>
                </label>
            </form>
        </div>
        <!-- barracargando -->
        <div id="cargador"></div>             
        <div id="cierreDiario" class="contentFormCol">
        </div>
    </div>
</div>
