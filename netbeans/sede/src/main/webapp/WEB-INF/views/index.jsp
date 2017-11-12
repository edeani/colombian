<%-- 
    Document   : signin
    Created on : 12/08/2016, 10:10:23 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Colombian</title>
        <!-- Custom Css 
        ================================================== -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/responsive.css">

        <!-- Fonts 
        ================================================== -->
        <link href='<%=request.getContextPath()%>/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <!-- Contenido Login Registro -->
        <div id="content">
            <div id="rst-banner" data-background="<%=request.getContextPath()%>/img/post/banner03.jpg">
                <div class="container">
                    <div class="rst-inner-banner clearfix">
                        <div class="rst-banner-content pull-left">
                            <h1>INICIAR SESI&Oacute;N</h1>
                        </div>
                    </div>
                </div>
            </div><!-- Banner -->
            <div class="container rst-main-content">
                <div class="row rst-form-input">
                    <div class="col-sm-6 rst-form-right">
                        <a href="index.html" class="rst-logo rst-table-cell"><img src="<%=request.getContextPath()%>/img/logopollo.png" alt=""/></a>
                    </div>
                </div>
                <div class="row rst-form-input">
                    <div class="col-sm-6 rst-form-right">
                        <form action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
                            <c:if test="${param.error == 'true'}">
                            <div class="form-group">
                                <span class="text-danger">
                                Usuario o Password Incorrectos
                                </span>
                            </div>
                            </c:if>
                            <div class="form-group">
                                <input type="text" name="username" class="form-control" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control" placeholder="Contrase&ntilde;a">
                            </div>
                            <div class="form-group" style="display: none;">
                                <label for="remember">
                                    <input type="checkbox" name="remember" id="remember">
                                    Recordarme
                                </label>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-lg btn-success" value="Inciar sesi&oacute;n" />
                            </div>
                        </form>
                    </div>
                </div>

            </div><!-- End Content -->

        </div>
        <!-- Fin Contenido Login Registro -->
    </body>
</html>
