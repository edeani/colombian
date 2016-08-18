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
        <link rel="stylesheet" type="text/css" href="css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">

        <!-- Fonts 
        ================================================== -->
        <link href='fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        
        <script type="text/javascript" src="/js/usuario/signin.js"></script>
    </head>
    <body>
        <!-- Contenido Login Registro -->
        <div id="content">
            <div id="rst-banner" data-background="/img/post/banner03.jpg">
                <div class="container">
                    <div class="rst-inner-banner clearfix">
                        <div class="rst-banner-content pull-left">
                            <h1>REGISTRO</h1>
                        </div>
                    </div>
                </div>
            </div><!-- Banner -->
            <div class="container rst-main-content">

                <div class="row rst-form-input">
                    <div class="col-sm-6 rst-form-left">
                        <springForm:form action="/signin.htm" cssClass="rst-form-register" method="POST" commandName="usuarioRegistroDto">
                            <h3>USUARIO NUEVO</h3>
                            <div class="form-group">
                                <springForm:input path="nombre" cssClass="form-control" placeholder="Nombre" maxlength="100"></springForm:input>
                                <springForm:errors path="nombre" cssClass="error" />
                            </div>
                            <div class="form-group">
                            <springForm:input path="correo" cssClass="form-control" placeholder="Email"></springForm:input>
                            <springForm:errors path="correo" cssClass="error" />
                            </div>
                            <div class="form-group">
                                <springForm:password path="password" cssClass="form-control" placeholder="Contraseña" maxlength="20"></springForm:password>
                                <springForm:errors path="password" cssClass="error" />
                                <springForm:password path="confirmarpassword" cssClass="form-control" placeholder="Repetir contraseña" maxlength="20"></springForm:password>
                                <springForm:errors path="confirmarpassword" cssClass="error" />
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-lg btn-success" value="registrase" />
                            </div>
                        </springForm:form>
                    </div>
                    <div class="col-sm-6 rst-form-right">
                        <form action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
                            <h3>INICIAR SESI&Oacute;N</h3>
                            <c:if test="${param.error == 'true'}">
                            <div class="form-group">
                                Usuario o Password Incorrectos
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
