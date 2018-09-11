<%-- 
    Document   : basic
    Created on : 4/12/2012, 07:15:01 AM
    Author     : edeani
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="<%=request.getContextPath()%>/css/estructura.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/menu.css" rel="stylesheet" type="text/css">
        <title><sitemesh:write property='title'/></title>
    <sitemesh:write property='head'/>
</head>
<body>
    <div  id="cuerpo">
        <c:choose>
            <c:when test="${viewlogo eq 'I'}">
                <div class="title-login">
                    Bienvenido a la Plataforma
                </div> 
            </c:when>
            <c:otherwise>
                <div style="display: block">
                    <img src="${sessionScope.foto}" />
                </div> 
            </c:otherwise>
        </c:choose>



        <sitemesh:write property='body'/>
        <%@ include file="/WEB-INF/componentes/footer.jsp" %>
        <input type="hidden" id="contextpath" value="${pageContext.servletContext.contextPath}"/>
        <input type="hidden" id="idpath" value="${sessionScope.path}"/>
    </div>
</body>
</html>
