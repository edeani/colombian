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
    <script src="<%=request.getContextPath()%>/js/contabilidad/cuentasBeneficiarios.js?id=1" type="text/javascript"></script>
</head> 
<!--script src="<%=request.getContextPath()%>/js/select/jquery.editable-select.pack.js" type="text/javascript"> </script-->
<div id="tituloPagina">Pagos  Terceros Caja Mayor</div>
<div id="formPago" data-url="${pageContext.servletContext.contextPath}/pagos/ajax/secuencia.htm">                                 
    <form:form commandName="${commandName}"  >
        <div id="contenidoHome"> 
            <input id="rutaLoader"value="${pageContext.servletContext.contextPath}/img/loaders/" type="hidden"/>
            <div id="tituloPagina">${titulo}</div>
            <div class="clear"></div>
            <div class="clear"></div>
            <div class="clear"></div>
            <div style="position: relative; margin-left: 115px;">
                <label style="display: none;">
                    <select id="idSede">
                        <option value="">Seleccionar</option>
                        <c:import url="/sedes/ajax/listaSedeSelect.htm"></c:import>
                    </select>
                </label>

                <label id="cmpFecha" style="display: block;">
                    Fecha
                    <form:input path="fecha" cssClass="fechaInicial"/>
                </label>
                <label id="lnkRows" style="display: none;">
                    <a href="javascript:void(0);" onclick="addRowPagos('contenidoFactura');" id="agregarFila">AgregarFila</a>
                </label>
            </div>
            <div id="cargador"></div>
            <div id="divContenedorTabla"  style="margin: 0 3% !important;" data-url ="${pageContext.servletContext.contextPath}/factura/ajax/producto.htm">
                <table id="tblDatos" class="tblPagosTerceros" align="center" style="display: block;">
                    <thead>
                        <tr>
                            <th>Sede</th>
                            <th>Cuenta</th>
                            <th>Concepto</th>
                            <th>Total</th>
                            <th width="22">&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody id="contenidoFactura" data-url ="<%=request.getContextPath()%>/inventario/ajax/selectProducto.htm">
                        <tr>
                            <td>
                                <select data-numero="0" class="claseSelectSede" id="identificadorSede0" >
                                    <!--Aquí van las opciones-->
                                </select>
                                <input type="hidden" id="inputIdentificadorSede0" name="notasDto.detallesNota[0].idSede" value=""/>    
                            </td>
                            <td>
                                <input data-numero="0" id="numerocuenta0" name="notasDto.detallesNota[0].cuenta" value="" class="ui-autocomplete-input claseValidarNum claseCuenta" autocomplete="off"/>
                            </td>
                            <td>
                                <input class="claseConceptoCuenta" id="concepto0" name="notasDto.detallesNota[0].concepto" value=""/>
                            </td>   
                            <td>
                                <input class="claseValidarNum claseFormatDec claseTotalProveedor" id="total0" name="notasDto.detallesNota[0].total" value=""/>
                            </td>
                            <td align="right">
                                <input type="button" value="-" class="clsEliminarFila">
                                <input type="hidden" id="numero0" name="notasDto.detallesNota[0].numero" value="1"/>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>Total</td>
                            <td><input name="totalPago" id="totalPago" value="0"/></td>
                        </tr>
                        <tr>    
                            <td></td>
                            <td></td>
                            <td colspan="4" align="right">
                                <input type="button" id="generar" value="Generar" />
                                <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                                <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </form:form>
</div>

