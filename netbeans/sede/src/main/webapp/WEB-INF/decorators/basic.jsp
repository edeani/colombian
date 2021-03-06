<%-- 
    Document   : basic
    Created on : 4/12/2012, 07:15:01 AM
    Author     : edeani
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="<%=request.getContextPath()%>/css/estructura.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/menu.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-confirm.css">
        <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/js/cali.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/js/jquery-confirm.js"></script>
        <title>${sessionScope.path} <sitemesh:write property="title"/> </title>
        <sitemesh:write property="head"/>
    </head>
    <body>
        <div  id="cuerpo">
            <%@ include file="/WEB-INF/componentes/header.jsp" %>
            <sitemesh:write property="body"/>
            <%@ include file="/WEB-INF/componentes/footer.jsp" %>
        </div>
        <input type="hidden" id="contextpath" value="${pageContext.servletContext.contextPath}"/>
        <input type="hidden" id="idpath" value="${sessionScope.path}"/>
    </body>
</html>
