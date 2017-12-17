<%@ page language="java" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/home/home_login.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"> </script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/home/home_login.js" type="text/javascript"></script>
</head>
<div>
    <form action="${pageContext.request.contextPath}/${urlLogin}" method="POST" id="formularioLogin" name="formularioLogin" class="formLogin">
        <ul>
            <li>
                <label>Usuario:</label>  <input value="" id="loginname" name="loginname" size="20" type="text" class="contentRequired"/>
            </li>
            <li>
                <label>Password: </label><input  id="passwordsede" name="passwordsede" size="20" type="password" class="contentRequired"/>
            </li>
            <input type="hidden" id="rt" name="rt" value="${rt}"/>
            <input type="hidden" id="sede" name="sede" value="${sedePath}"/>
            <li>
                <input id="submit" type="submit"  class="buttonLogin" value="Login" />
            </li>
        </ul>
    </form>
</div>