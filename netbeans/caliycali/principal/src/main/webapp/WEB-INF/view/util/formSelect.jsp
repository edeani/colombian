<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:forEach items="${datos}" var="item">
    <option value="${item.id}" <c:if test="${item.id == seleccion}">selected</c:if> >${item.label}</option>
</c:forEach>
