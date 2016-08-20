<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <link type="text/css" rel="stylesheet" href="/colombianCaliyCali/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="/colombianCaliyCali/css/contabilidad/pagosterceros.css">
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/pagos/reportePagos.js" type="text/javascript"></script>
</head> 
<!--script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.pack.js" type="text/javascript"> </script-->
<div id="formFactura">                                 
    <form method="POST" target="_blank" id="formReportePagos" action="${pageContext.servletContext.contextPath}/pagos/pdf/sede/todos.htm" >
        <div id="contenidoHome"> 
            <input id="rutaLoader"  value="${pageContext.servletContext.contextPath}/img/loaders/" type="hidden"/>
            <div id="tituloPagina">${titulo}</div>
            <div class="clear"></div>
            <div>
                <label>
                    Sede
                    <select name="idsede" id="idsede" class="contentRequired">
                        <option value="">Seleccionar</option>
                        <c:import url="/chia/ajax/listaSedeSelect.htm"></c:import>
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
                    <input type="button" value="Generar reporte" id="consultarReportePagosSede"/>
                </label>
            </div>
            <div id="cargador"></div>
        </div>
    </form>
</div>

