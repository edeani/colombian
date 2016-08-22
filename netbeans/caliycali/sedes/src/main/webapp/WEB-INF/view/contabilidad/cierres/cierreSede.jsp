<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <link type="text/css" rel="stylesheet" href="/chia/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="/chia/css/contabilidad/pagosterceros.css">
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/contabilidad/cuentasCierres.js" type="text/javascript"></script>
</head> 
<!--script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.pack.js" type="text/javascript"> </script-->
<div id="formFactura" data-url="${pageContext.servletContext.contextPath}/factura/ajax/formFactura.htm">                                 
    <form:form target="_blank" commandName="${commandName}" path="ComprobanteCierreSedesDto" data-urlcomprobantepdf="${pageContext.servletContext.contextPath}/consolidado/comprobante/sede/pdf.htm" data-urlcomprobante="${pageContext.servletContext.contextPath}/consolidado/ajax/comprobante/sede/generar.htm" action="${pageContext.servletContext.contextPath}/consolidado/ajax/comprobante/sede/guardar.htm" >
        <div id="contenidoHome"> 
            <input id="rutaLoader"value="${pageContext.servletContext.contextPath}/img/loaders/" type="hidden"/>
            <div id="tituloPagina">${titulo}</div>
            <div>
                <form:input type="hidden" path="consecutivo"/>
            </div>
            <div class="clear"></div>
            <div>
                <label>
                    Sede
                    <form:select path="idSede" class="contentRequired">
                        <option value="">Seleccionar</option>
                        <c:import url="/sedes/ajax/listaSedeSelect.htm"></c:import>
                    </form:select>
                    <form:input path="nombreSede" type="hidden"/>
                </label>
                <label id="cmpFecha">
                    Fecha
                    <form:input path="fecha" class="contentRequired"/>
                </label>
                <label id="lnkRows">
                    <input type="button" value="Generar reporte" id="generarReporteConsolidadoSede"/>
                </label>
                <label id="agregarFila" style="display: none;">
                    <a href="javascript:void(0);" id="agregarFilaCierreSede">Agregar</a> 
                </label>
            </div>
            <div id="cargador"></div>
            <div id="divContenedorTabla"  style="margin: 0 3% !important;" data-url ="${pageContext.servletContext.contextPath}/factura/ajax/producto.htm">
                
            </div>
        </div>
    </form:form>
</div>

