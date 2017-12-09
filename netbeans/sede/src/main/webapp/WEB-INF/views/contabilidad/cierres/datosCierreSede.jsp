<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<table id="tblDatos" class="tblComprobanteCierre" align="center" >
    <thead>
        <tr>
            <th>Cuenta</th>
            <th>Concepto</th>
            <th>Deber</th>
            <th>Haber</th>
        </tr>
    </thead>
    <tbody id="contenidoFactura" data-url="<%=request.getContextPath()%>/${sessionScope.path}/${sessionScope.path}/inventario/ajax/selectProducto.htm">
        <c:set var="totalHaber" value="0"/>
        <c:set var="totalDeber" value="0"/>
        <c:forEach items="${comprobanteConsolidadoSedeDto}" var="item" varStatus="numero">
            <tr style="background: green;">
                <td>
                    <c:set var="atributo" value=""/>
                        <c:if test="${not empty item.idCuenta}">
                            <c:set var="atributo" value="readonly='readonly'"/>
                        </c:if>
                    <input name="comprobanteConsolidadoSedeDto[${numero.index}].idCuenta" value="${item.idCuenta}" ${atributo} class="claseValidarNum contentRequired <c:if test="${empty item.idCuenta}">claseCuentaCierre</c:if>"/>
                </td>
                <td>
                    <input type="text" name="comprobanteConsolidadoSedeDto[${numero.index}].concepto" value="${item.concepto}"  ${atributo} />
                </td>
                <td>
                    <c:if test="${item.idCuenta != '414015'}">
                        $<input name="comprobanteConsolidadoSedeDto[${numero.index}].total" value="<fmt:formatNumber type="number  " pattern="###,##0" value="${item.total}"/>" readonly="readonly" class="claseFormatDecPoint"/>
                         <c:set var="totalDeber" value="${totalDeber + item.total}"/>
                    </c:if>
                </td>    
                <td>
                    <c:if test="${item.idCuenta == '414015'}">
                        <c:set var="totalHaber" value="${totalHaber + item.total}"/>
                        $<input name="comprobanteConsolidadoSedeDto[${numero.index}].total" value="<fmt:formatNumber type="number  " pattern="###,##0" value="${item.total}"/>" readonly="readonly" class="claseFormatDecPoint"/>
                    </c:if>
                    <c:set var="keyConcepto" value="0"/>
                    <c:if test="${not empty item.idConcepto}">
                        <c:set var="keyConcepto" value="${item.idConcepto}"/>
                    </c:if>
                        <input type="hidden" name="comprobanteConsolidadoSedeDto[${numero.index}].idConcepto" value="${keyConcepto}"/>
                        <input type="hidden" name="comprobanteConsolidadoSedeDto[${numero.index}].sede" value="${item.sede}"/>
                        <input type="hidden" name="comprobanteConsolidadoSedeDto[${numero.index}].idSede" value="${item.idSede}"/>
                        <input type="hidden" name="comprobanteConsolidadoSedeDto[${numero.index}].fecha" value="${item.fecha}"/>
                        
                </td>
            </tr>
        </c:forEach>

    </tbody>
    <tfoot>
        <tr>
            <td></td>
            <td>Sumas Iguales</td>
            <td>$<input name="totalDeber" id="totalDeber" onkeyup="" value="<fmt:formatNumber type="number  " pattern="###,##0" value="${totalDeber}"/>" readonly="readonly" class="claseFormatDecPoint"/></td>
            <td>$<input name="totalHaber" id="totalHaber" value="<fmt:formatNumber type="number  " pattern="###,##0" value="${totalHaber}"/>" readonly="readonly" class="claseFormatDecPoint"/></td>
            <c:remove var="totalHaber"/>
            <c:remove var="totalDeber"/>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="4" align="right">
                <input type="button" id="guardarComprobanteCierreSede" value="Generar" />
                <!--input type="button" value="Clonar la tabla" class="clsClonarTabla"-->
                <!--input type="button" value="Eliminar la tabla" class="clsEliminarTabla"-->
            </td>
        </tr>
    </tfoot>
</table>

