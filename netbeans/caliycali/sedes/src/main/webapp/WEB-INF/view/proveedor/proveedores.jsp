<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="item" items="${proveedores}">
    <tr>
        <td>${item.idproveedor}</td>
        <td>${item.nombre}</td>
        <td>${item.direccion}</td>
        <td>${item.telefono}</td>
        <td>${item.nit}</td>
    </tr>
</c:forEach>
