<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/tabladinamica/estilos.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/util.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/ventas/reporte-ventas.js" type="text/javascript"></script>
</head>
<body>
    <div id="contenidoHome">
        <div class="tituloPagina">Reporte Ventas ${sede} Broaster</div>
        <div class="contenedorEstructuraFormLarge">
            <div class="contentFormSimple">
                <form id="formVentas" action="<%=request.getContextPath()%>/${sessionScope.path}/mesasyllevar/colombian/ajax/consultar.htm">
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
                        <input id="consultarVentas" type="button" value="Aceptar" class="generalButton"/>
                    </label>
                </form>
            </div>
        </div>
        <div class="tituloPagina">Reporte Ventas Mesa</div>
        <div class="divContenedorTabla">
            <div id="contVentasMesa" data-url="<%=request.getContextPath()%>/${sessionScope.path}/ventas/ajax/ventasmesa.htm">
                <table align="center" id="tablamesa">
                    <thead>
                        <tr>
                            <th>C&oacute;digo</th>
                            <th>Descrici&oacute;n</th>
                            <th>Unidades</th>
                            <th>Valor Producto</th>
                            <th>Valor Total</th>
                        </tr>
                    </thead>
                    <tbody id="contenidomesa" >

                    </tbody>

                </table>
            </div>
            <input id="totalMesas" type="hidden" value="" class="decimalventa"/>
            <div class="contenedorResumen">
                TOTAL MESAS
                <label id="totalMesaLabel" class="resumen">
                    $0
                </label> 
            </div>
        </div>
        <div class="tituloPagina">Reporte Ventas Domicilio</div>
        <div class="divContenedorTabla">
            <div id="contVentasDomicilio" data-url="<%=request.getContextPath()%>/${sessionScope.path}/ventas/ajax/ventasdomicilio.htm">
                <table align="center" id="tabladomicilio">
                    <thead>
                        <tr>
                            <th>C&oacute;digo</th>
                            <th>Descripci&oacute;n</th>
                            <th>Unidades</th>
                            <th>Valor Producto</th>
                            <th>Valor Total</th>
                        </tr>
                    </thead>
                    <tbody id="contenidodomicilio" >

                    </tbody>

                </table>
            </div>
            <input id="totalDom" type="hidden" value="" class="decimaldom"/>
            <div class="contenedorResumen">
                TOTAL DOMICILIOS
                <label id="totalDomicilioLabel" class="resumen">
                    $0
                </label> 
            </div>
        </div>
        <div class="tituloPagina">Reporte Ventas Llevar</div>
        <div class="divContenedorTabla">
            <div id="contVentasLlevar" data-url="<%=request.getContextPath()%>/${sessionScope.path}/ventas/ajax/ventasmostrador.htm">
                <table align="center" id="tablallevar">
                    <thead>
                        <tr>
                            <th>C&oacute;digo</th>
                            <th>Descrici&oacute;n</th>
                            <th>Unidades</th>
                            <th>Valor Producto</th>
                            <th>Valor Total</th>
                        </tr>
                    </thead>
                    <tbody id="contenidollevar" >

                    </tbody>

                </table>
            </div>
            <input id="totalLlevar" type="hidden" value="" class="decimalllevar"/>
            <div class="contenedorResumen">
                TOTAL LLEVAR
                <label id="totalLlevarLabel" class="resumen">
                    $0
                </label> 
            </div>
        </div>
        <div class="tituloPagina">Reporte Venta Total</div>
        <div class="divContenedorTabla">
            <div id="contVentasTotal" data-url="<%=request.getContextPath()%>/${sessionScope.path}/ventas/ajax/ventastotal.htm">
                <table align="center" id="tablatotal">
                    <thead>
                        <tr>
                            <th>C&oacute;digo</th>
                            <th>Descrici&oacute;n</th>
                            <th>Unidades</th>
                            <th>Valor Producto</th>
                            <th>Valor Total</th>
                        </tr>
                    </thead>
                    <tbody id="contenidototal" >

                    </tbody>

                </table>
            </div>
            <input id="totalTotal" type="hidden" value="" class="decimaltotal"/>
            <div class="contenedorResumen">
                TOTAL VENTAS
                <label id="totalTotalLabel" class="resumen">
                    $0
                </label> 
            </div>
        </div>
    </div>
</body>