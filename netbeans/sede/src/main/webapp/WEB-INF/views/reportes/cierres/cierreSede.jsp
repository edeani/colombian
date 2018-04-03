<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/contabilidad/pagosterceros.css">
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/contabilidad/reporteCierres.js" type="text/javascript"></script>
</head> 
<!--script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.pack.js" type="text/javascript"> </script-->
<div id="formFactura" data-url="${pageContext.servletContext.contextPath}/factura/ajax/formFactura.htm">                                 
    <form method="POST" target="_blank" id="formReporteComprobante" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/consolidado/comprobante/reporte/sede/pdf.htm" >
        <div id="contenidoHome"> 
            <input id="rutaLoader"  value="${pageContext.servletContext.contextPath}/img/loaders/" type="hidden"/>
            <div id="tituloPagina">${titulo}</div>
            <div class="clear"></div>
            <div>
                <label>
                    Sede
                    <select name="idsede" id="idsede" class="contentRequired">
                        <option value="">Seleccionar</option>
                        <c:import url="/${sessionScope.path}/sedes/ajax/listaSedeSelectCredencial.htm"></c:import>
                    <select>
                </label>
                <label id="cmpFechaInicial">
                    Fecha Inicio
                    <input name="fechaInicial" id="fechaInicial" class="contentRequired"/>
                </label>
                <label id="cmpFechaFinal">
                    Fecha Fin
                    <input name="fechaFinal" id="fechaFinal" class="contentRequired"/>
                </label>
                <label id="lnkRows">
                    <input type="button" value="Generar reporte" id="consultarReporteConsolidadoSede"/>
                </label>
                <label id="agregarFila" style="display: none;">
                    <a href="javascript:void(0);" id="agregarFilaCierreSede">Agregar</a> 
                </label>
            </div>
            <div id="cargador"></div>
        </div>
    </form>
</div>

