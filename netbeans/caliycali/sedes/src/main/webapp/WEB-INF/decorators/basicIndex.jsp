<%-- 
    Document   : basic
    Created on : 4/12/2012, 07:15:01 AM
    Author     : edeani
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="<%=request.getContextPath()%>/css/estructura.css" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath()%>/css/menu.css" rel="stylesheet" type="text/css">
        <title><decorator:title default="Colombian CaliyCali"/></title>
        <decorator:head/>
    </head>
    <body>
        <div  id="cuerpo">
        <div style="display: block">
            <img src="<%=request.getContextPath()%>/img/logo_caliycali.png" />
        </div> 
        
        <decorator:body/>
        <%@ include file="/WEB-INF/components/footer.jsp" %>
        </div>
        
</html>
