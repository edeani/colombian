<%@ page language="java" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/home/home_login.css">
    <script src="<%=request.getContextPath()%>/js/jquery-1.9.0.js" type="text/javascript"> </script>
    <script src="<%=request.getContextPath()%>/js/jqueryUtil.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/home/home_login.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/home/home_select_login.js" type="text/javascript"></script>
</head>
<div class="content-form-login">
    <form action="${pageContext.request.contextPath}/${urlLogin}" method="POST" id="formularioLogin" name="formularioLogin" class="formLogin">
        <ul>
            <li>
                <label>Usuario:</label>  <input value="" id="loginname" name="loginname" size="20" type="text" class="contentRequired"/>
            </li>
            <li id="lsede" data-alert="N">
                <label>Sede:</label>  
                <select value="" id="selectSedeGeneric" class="contentRequired" data-url="<%=request.getContextPath()%>/sedes/ajax/byuser.htm"
                        data-url-change="<%=request.getContextPath()%>/sedes/ajax/setSedeSession.htm">
                    <!-- Sedes del Usuario -->
                    <option value="">Seleccionar Sede</option>
                </select>
            </li>
            <li id="lpass" style="display: none;" data-view="N">
                <label>Password: </label><input  id="passwordsede" name="passwordsede" size="20" type="password" class="contentRequired"/>
            </li>
            <input type="hidden" id="rt" name="rt" value=""/>
            <input type="hidden" id="sede" name="sede" value=""/>
            <li>
                <input id="submit" type="submit"  class="buttonLogin" value="Login" />
            </li>
        </ul>
    </form>
</div>