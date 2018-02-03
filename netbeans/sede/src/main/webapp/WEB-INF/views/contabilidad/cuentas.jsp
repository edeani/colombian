<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/jquery-ui.css" rel="stylesheet" type="text/css">
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/contabilidad/cuentas.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
</head>
<div id="contenidoHome"> 

    <form:form commandName="${commandName}" path="cuentasPuc" action="${pageContext.servletContext.contextPath}/${sessionScope.path}/cuentas/ajax/buscar.htm" >
        <label>
            Cuenta
            <form:input path="codCta" onkeypress="return validarNUM(event)"/>
            <!--input id="btnBuscarCuenta" type="button" value="buscar"/-->
        </label>
        <div id="cargador"></div>
        <div id="contenedorCuenta"></div>
    </form:form>
</div>