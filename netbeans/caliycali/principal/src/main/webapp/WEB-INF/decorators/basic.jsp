<%-- 
    Document   : basic
    Created on : 4/12/2012, 07:15:01 AM
    Author     : edeani
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="<%=request.getContextPath()%>/css/estructura.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/estilos.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/menu.css" rel="stylesheet" type="text/css">
        <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"> </script>
        <script src="<%=request.getContextPath()%>/js/cali.js" type="text/javascript"> </script>
        <title><decorator:title default="Colombian CaliyCali"/></title>
        <decorator:head/>
    </head>
    <body>
        <input type="hidden" id="contextpath" value="${pageContext.servletContext.contextPath}"/>
        <div  id="cuerpo">
            <%@ include file="/WEB-INF/components/header.jsp" %>
            <decorator:body/>
            <%@ include file="/WEB-INF/components/footer.jsp" %>
        </div>
    </body>
</html>
