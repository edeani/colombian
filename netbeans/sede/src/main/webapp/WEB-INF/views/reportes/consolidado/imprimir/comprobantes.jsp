<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${pagos.size() gt 0}">
        <div class="tituloTabla">
        <label>Comprobantes</label>
        </div>
        <table id="tblDatos" class="" align="center" style="display: block;">
            <thead>
                <tr>
                    <th>Pago</th>
                    <th>Beneficiario</th>
                    <th>Fecha</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody id="comprobantesImprimir">
                <c:set var="urlComprobante" value="${pageContext.servletContext.contextPath}/${sessionScope.path}/pagos/proveedores/pdf/comprobante.htm"></c:set>
                <c:forEach items="${pagos}" var="item" varStatus="indice">
                    <tr id="filaComprobante${indice.index}" style="background: green;">
                            <td>
                                <a data-url="${urlComprobante}" data-pago="${item.idpagos}" href="javascript:void(0);" class="claseBuscarPagoProveedor">${item.idpagos}</a>
                            </td>
                            <td>
                                ${item.nombreProveedor}
                            </td>
                            <td>
                                ${item.fecha}
                            </td>
                            <td>
                                $<fmt:formatNumber type="number" pattern="###,##0" value="${item.total}"/>
                            </td>
                        </tr>
                  </c:forEach> 
                <c:remove var="urlComprobante"/>
                      
            </tbody>
        </table>
</c:when>
<c:otherwise>
    <script type="text/javascript">
        lightboxMensaje("No hay pagos");
    </script>
</c:otherwise>
            
</c:choose>