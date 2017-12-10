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
    <script src="<%=request.getContextPath()%>/js/contabilidad/pagosConsolidadoSede.js" type="text/javascript"></script>
</head> 
<div id="tituloPagina">Procentaje Sedes Bancos</div>
<div id="formPagoConsolidadoSede" data-urlconsecutivo="${pageContext.servletContext.contextPath}/${sessionScope.path}/cajamenor/ajax/secuencia.htm" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/cajamenor/ajax/consolidado/porcentaje/sede/generar.htm">                                 
    <form:form target="_blank" commandName="${commandName}" path="PagosConsolidadoSedeDto" data-urlcomprobante="${pageContext.servletContext.contextPath}/${sessionScope.path}/cajamenor/terceros/pdf/comprobante.htm" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/${sessionScope.path}/cajamenor/ajax/consolidado/sede/guardar.htm" >
        <div id="contenidoHome"> 
            <input id="rutaLoader"value="${pageContext.servletContext.contextPath}/${sessionScope.path}/img/loaders/" type="hidden"/>
            <div id="tituloPagina">${titulo}</div>
            <div class="clear"></div>
            <div style="position: relative; margin-left: 115px;">
                <label id="cmpBeneficiario" >
                    Beneficiario
                    <form:select path="nombreBeneficiario">
                        <option value="">Seleccionar</option>
                        <c:import url="/beneficiarios/ajax/select.htm"></c:import>
                    </form:select>    
                    <form:input path="idBeneficiario" type="hidden"/>
                </label>
                <label id="cmpSecuencia" style="display: none;">
                    No. Comprobante
                    <form:input path="secuencia" readonly="readonly"/>
                </label>
                <label id="cmpFecha" style="display: none;">
                    Fecha
                    <form:input path="fechaPago" cssClass="fechaInicial"/>
                </label>
            </div>
            <div style="position: relative; margin-left: 115px;">
                <label id="cmpCuentaConsolidado" style="display: none;">
                    Cuenta
                    <input id="idCuentaConsolidado" value="" class="claseValidarNum"/>
                </label>
                <label id="cmpDetalle" style="display: none;">
                    Detalle
                    <input id="detalleConsolidado" value=""/>
                </label>
                <label id="cmpTotalPagar" style="display: none;">
                    Total Pagar
                    <input id="totalPagar" value="" class="claseFormatDec"/>
                </label>
            </div>
            <div id="cargador"></div>
            <div class="clear"></div>
            <div id="divContenedorTabla"  style="margin: 0 3% !important;" data-url="${pageContext.servletContext.contextPath}/${sessionScope.path}/factura/ajax/producto.htm">

            </div>
        </div>
    </form:form>
</div>

