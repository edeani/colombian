<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/jquery.easy-confirm-dialog.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/inventario/inventario.js" type="text/javascript"> </script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
<link href="/colombianCaliyCali/css/lightbox/colorbox.css" rel="stylesheet" type="text/css">

</head>
<div id="contenidoHome">
    <div id="tituloPagina">${titulo}</div>
    <div id ="formularioInventario">
        <c:import url="/inventario/ajax/formularioInventario.htm">
        </c:import>
    </div>
</div>
