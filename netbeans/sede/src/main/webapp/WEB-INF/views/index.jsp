<%@ page language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/home/home_login.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js" type="text/javascript"> </script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/home/home_login.js" type="text/javascript"></script>
</head>
<div>
    <form:form action="${pageContext.request.contextPath}/j_spring_security_check" method="POST" id="formularioLogin" name="formularioLogin" class="formLogin">
        <ul>
            <li>
                <label>Usuario:</label>  <input value="" id="j_username" name="j_username" size="20" type="text" class="contentRequired"/>
            </li>
            <li>
                <label>Password: </label><input  id="j_password" name="j_password" size="20" type="password" class="contentRequired"/>
            </li>
            <li>
                <button id="submit" type="submit"  class="buttonLogin">Login</button>
            </li>
        </ul>
    </form:form>
</div>