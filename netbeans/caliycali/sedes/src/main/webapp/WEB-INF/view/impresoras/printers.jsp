<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<select id="confirm-impresora" name="confirm-impresora">
<c:forEach items="${impresoras}" var="item">
    <option value="${item}" <c:if test="${item == defaultPrinter}">selected</c:if> >${item}</option>
</c:forEach>
</select>