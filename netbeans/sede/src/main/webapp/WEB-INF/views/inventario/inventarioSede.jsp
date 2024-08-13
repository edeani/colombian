<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/datatable/jquery.dataTables.min.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/jquery.easy-confirm-dialog.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/inventario/inventario.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/jquery.dataTables.min.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
<link href="<%=request.getContextPath()%>/css/lightbox/colorbox.css" rel="stylesheet" type="text/css">

</head>
<div id="contenidoHome">
    <div id="tituloPagina">${titulo}</div>
    <div id ="formularioInventario">
        <c:import url="/${sessionScope.path}/inventario/ajax/subsede/formularioInventario.htm">
        </c:import>
    </div>
</div>
