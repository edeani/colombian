<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/domicilios/reporte-domicilios.js" type="text/javascript"></script>
</head>
<title>Reporte Cuadre Diarios</title>
<body>
    <div id="contenidoHome">
        <div class="tituloPagina">Reporte Cuadre Diarios</div>
        <div class="contenedorEstructuraFormLarge">
            <div class="contentFormSimple">
                <form id="formDomicilios" action="<%=request.getContextPath()%>/${sessionScope.path}/mesasyllevar/colombian/ajax/consultar.htm">
                    <label>Fecha Inicial
                        <input name="fi" id="fechaInicial" class="fechaInicial contentRequired" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fecha}"/>"/>
                    </label>
                    <label>Fecha Final
                        <input name="ff" id="fechaFinal" class="fechaFinal contentRequired" style="cursor: pointer;" type="text" value="<fmt:formatDate  type="both" pattern="yyyy-MM-dd" value="${fecha}"/>"/>
                    </label>
                    <label>Sede
                        <c:set var="sedeSeleccionada" value="${sessionScope.idusuario}"></c:set>
                            <select id="idSubsede" name="idSubsede" class="contentRequired">
                                <option value="">Seleccionar</option>
                            <c:import url="/${sessionScope.path}/sedes/ajax/listaSedeSelectCredencial.htm"/>
                        </select>
                    </label>
                    <label>
                        <input id="consultarDomicilios" type="button" value="Aceptar" class="generalButton"/>
                    </label>
                </form>
            </div>
        </div>

        <div class="divContenedorTabla">
            <div id="contDomicilios" data-url="<%=request.getContextPath()%>/${sessionScope.path}/domicilios/ajax/consultar.htm">
                <table align="center" id="tabladomicilios">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Ventas</th>
                            <th>Pagos Tarjeta</th>
                            <th>Descuentos</th>
                            <th>Gastos</th>
                            <th>Consignaciones</th>
                            <th>Caja Real</th>
                        </tr>
                    </thead>
                    <tbody id="contenidodomicilios" >

                    </tbody>

                </table>
            </div>
            <input id="totalDomicilios" type="hidden" value="" class="decimaldomicilios"/>
            <div class="contenedorResumen">
                <label id="totalDomiciliosLabel" class="resumen">
                    $0
                </label>
                <label class="resumen">
                PAGOS TARJETA
                </label>
                
                <label id="totalCantDomiciliosLabel" class="resumen">
                    $0
                </label>
                <label class="resumen">
                DESCUENTOS
                </label> 
            </div>
        </div>

    </div>
</body>