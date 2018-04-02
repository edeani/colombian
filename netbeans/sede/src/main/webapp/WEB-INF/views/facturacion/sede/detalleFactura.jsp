<%@page import="java.util.Date"%>
<%@ taglib prefix="secured" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:choose>
    <c:when test="${fn:length(productos) == 0}">
        <tr>
            <input id="numeroSede" name="numeroSede" type="hidden" value=""/>
            <input id="nombreSede" name="nombreSede" type="hidden" value=""/>
            <input id="totalFact" name="totalFact" type="hidden" value=""/>
            <input id="estadoFactura" name="estadoFactura" type="hidden" value=""/>
        </tr>
        <tr>
            <td>
                <input readonly="readonly" type="text" name="name" id="name" autocomplete="off" style="width: 93px;" class="primerCampo2"/>
            </td>
            <td><input type="text" class="clsAnchoTotal" readonly="readonly"></td>
            <td><input type="text" class="clsAnchoTotal unidadesCampo" readonly="readonly"/></td>
            <td><input type="text" class="clsAnchoTotal" readonly="readonly" /></td>
            <td><input type="text" class="clsAnchoTotal totalCampo" readonly="readonly"/></td>
        </tr>
        
    </c:when>
    <c:otherwise>
        <tr>
             <input id="numeroSede" name="numeroSede" type="hidden" value="${subsede.idsedepoint}"/>
             <input id="nombreSede" name="nombreSede" type="hidden" value="${subsede.sede}"/>
             <input id="totalFact" type="hidden" value="${totalFactura}"/>
             <input id="estadoFactura" name="estadoFactura" type="hidden" value="${estadoFactura}"/>
        </tr>
        <c:forEach items="${productos}" var="producto">
            <tr>
                <td>
                    <input readonly="readonly" type="text" name="name" id="name" autocomplete="off" style="width: 93px;" class="primerCampo2" value="${producto.codigo}" />
                </td>
                <td><input type="text" class="clsAnchoTotal" readonly="readonly" value="${producto.producto}" ></td>
                <td><input type="text" class="clsAnchoTotal unidadesCampo" value="${producto.unidades}" readonly="readonly"/> </td>
                <td><input type="text" class="clsAnchoTotal" readonly="readonly" value="${producto.valorUnitario}" readonly="readonly"/></td>
                <td><input type="text" class="clsAnchoTotal totalCampo" value="${producto.totalProducto}" readonly="readonly"/></td>
            </tr>
        </c:forEach>
    </c:otherwise>
</c:choose>
