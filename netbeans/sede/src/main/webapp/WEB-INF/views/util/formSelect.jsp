<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${datos}" var="item">
    <option value="${item.id}" <c:if test="${item.id == seleccion}">selected</c:if> >${item.label}</option>
</c:forEach>
