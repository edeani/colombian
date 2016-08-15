<%-- 
    Document   : signin
    Created on : 12/08/2016, 10:10:23 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <form action="/" class="rst-form-register">
                            <h3>USUARIO NUEVO</h3>
                            <div class="form-group">
                                <input type="text" name="name" class="form-control" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control" placeholder="Contrase&ntilde;a">
                                <input type="password" name="repeat-password" class="form-control" placeholder="Repetir contrase&ntilde;a">
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-lg btn-success" value="registrase" />
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-6 rst-form-right">
                        <form action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
                            <h3>INICIAR SESI&Oacute;N</h3>
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
