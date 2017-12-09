<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
<script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/lightbox/jquery.colorbox-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/tabladinamica/facturas.js" type="text/javascript"> </script>
<script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"> </script>
<script type="text/javascript">
    //Se utiliza para mostras mensaje si no hay registros en el reporte
    if('${mensaje}' != ''){
        $.colorbox({
                    html:"<p id='mensaje'>${mensaje}</p>",
                    initialHeight:50,
                    Height:50,
                    close:"aceptar"
                });
    }
</script>
<div id="contenidoHome">
    <div id="tituloPagina">${titulo}</div>
    <div id="formFechas" data-url="<%=request.getContextPath()%>/${sessionScope.path}/${sessionScope.path}/factura/reportes/ventasTotalesPDF.htm">
        <form target="_blank" method ="POST" action="<%=request.getContextPath()%>/${sessionScope.path}/factura/reportes/ventasTotalesPDF.htm">
            <label>Fecha Inicial
                <input name="fechaInicial" id="fechaInicial" class="fechaInicial" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fechaInicial}"/>"/>
            </label>
            <label>Fecha Final
                <input name="fechaFinal" id="fechaFinal"class="fechaFinal"  style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fechaFinal}"/>"/>
            </label>
            <label>
                <input id="reporteVentasTotales"  type="submit" value="Aceptar"/>
            </label>
        </form>
    </div>
</div>
