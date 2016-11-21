<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr>
<c:choose>
    <c:when test="${not empty mensaje}">
        ${mensaje}
    </c:when>
    <c:otherwise>
        ${usuario.nombreusuario}
    </c:otherwise>
</c:choose>
</tr>
