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
<script src="<%=request.getContextPath()%>/js/contabilidad/perdidaGanancias.js" type="text/javascript"></script>
<script type="text/javascript">
    //Se utiliza para mostras mensaje si no hay registros en el reporte
    if ('${mensaje}' != '') {
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
    <div id="tituloPagina">${titulo}</div>
    <div id="formFechas">
        <form id="formulario" target="_blank" action="<%=request.getContextPath()%>/consolidado/reporte/general/perdidaganancias/pdf.htm" method="post">
            <label>
                Tipo reporte
                <select id="tipoReporte" name="tipoReporte">
                    <option value="">Seleccionar tipo de reporte</option>
                    <option value="1">Total</option>
                    <option value="2">Sedes</option>
                </select>
            </label>
            
                <label id="labelSede" style="display: none;" >Sede
                    <select id="sede" name="sede">
                        <option value="">Seleccionar</option>
                        <c:import url="/sedes/ajax/listaSedeSelect.htm">
                        </c:import>
                    </select>
                    <input id="nombreSede" type="hidden" name="nombreSede" value="">
                </label>
                <label id="labelFechaInicial" style="display: none;">Fecha Inicial
                    <input name="fechaInicial" id="fechaInicial" class="fechaInicial" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fechaInicial}"/>"/>
                </label>
                <label id="labelFechaFinal" style="display: none;">Fecha Final
                    <input name="fechaFinal" id="fechaFinal"class="fechaFinal"  style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fechaFinal}"/>"/>
                </label>
                <label id="labelAceptar" style="display: none;">
                    <input id="reporteComprasTotales" type="submit" value="Aceptar">
                </label>
            
        </form>
    </div>
</div>
