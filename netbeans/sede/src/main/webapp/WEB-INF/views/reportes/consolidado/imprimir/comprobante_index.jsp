<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/contabilidad/imprimirComprobante.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/contabilidad/imprimirComprobantesPago.js" type="text/javascript"></script>
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
        <form id="formulario" target="_blank" action="<%=request.getContextPath()%>/${sessionScope.path}/pagos/ajax/comprobante/buscar.htm">
            <label id="labelFechaInicial">Comprobantes por fecha
                <input name="fechaInicial" id="fechaInicial" class="fechaInicial" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fechaInicial}"/>"/>
            </label>
            <label id="labelAceptar">
                <input id="buscar" type="button" value="Buscar"/>
            </label>
        </form>
    </div>
    <div id="cargador"></div>
    <div id="divContenedorTabla" class="claseContenedorImprimir" style="display: none;">

    </div>            
</div>
