<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${tipos}" var="t" varStatus="indice">
    <tr id="fila${indice.index}" class="fila">
        <td>${t.idtipo}</td>
        <td class="product-name">
            ${t.nombre} 
        </td>
        <td>
            ${t.estado}
        </td>
        <td data-index="${indice.index}">
            <a href="javascript:void(0);" class="editar"><i aria-hidden="true" class="fa fa-edit fa-2x"></i></a>
            <form id="form${indice.index}">
                <input name="nombre" value="${t.nombre}" type="hidden"/>
                <input name="estado" value="${t.estado}" type="hidden"/>
                <input name="idtipopago" value="${t.idtipo}" type="hidden"/> 
            </form>
        </td>

    </tr>
</c:forEach>