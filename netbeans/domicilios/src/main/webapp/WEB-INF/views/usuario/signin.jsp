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
        <link href='/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
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
                                <springForm:errors path="nombre" cssClass="text-danger" />
                            </div>
                            <div class="form-group">
                            <springForm:input path="correo" cssClass="form-control" placeholder="Email"></springForm:input>
                            <springForm:errors path="correo" cssClass="text-danger" />
                            </div>
                            <div class="form-group">
                                <springForm:password path="password" cssClass="form-control" placeholder="Contraseña" maxlength="20"></springForm:password>
                                <springForm:errors path="password" cssClass="text-danger" />
                                <springForm:password path="confirmarpassword" cssClass="form-control" placeholder="Repetir contraseña" maxlength="20"></springForm:password>
                                <springForm:errors path="confirmarpassword" cssClass="text-danger" />
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
		
		<a href="https://api.whatsapp.com/send?phone=+573142002610&amp;text=Hola,%20deseo%20hacer%20un%20pedido%20a%20domicilio." title="Pide Domicilio" target="_blank"  class="whatsapp">
<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAMAAAAOusbgAAAAM1BMVEUAAAD///////////////////////////////////////////////////////////////+3leKCAAAAEHRSTlMAQPAQgDDAYCCg4JDQULBw4g/QJQAABD5JREFUaN7tWtmu3CAMjVkc1sD/f22rqhp6GYwhTkdVdc/jaMjBxwt2wvGNb/xrsABgfgIA7PEZ2BJR1S9QGAtDLybFSgL/FrmNoTII8XFufaa6hHTqB2mdUXUZyjxFrX3dxCPUmrCWsVrMm0O9hQAyc6+xQeiNgV8wxuNYkksLzB08Mpn89kSdTRps77bR8d2KU9MJ966OuScz9m4rmllR+oDAG3Lb7iG4JFzGzjF2m1cRtBzgK7WyEt5w7qw9Vcd8mzduekrHu8xWCbMCFMfM86IWpMQes/6TNz5RBMLa5lNtOI/bOGtDWlnghbyNeU+4TPhGyJzZZkMR9gqZFedmJHjFzNfyP83xAOKi2Fp1OxQD18SOXerJodWKhK4JA8dDgPqCo1N4knfWIKpkJW72pMG00Db9dpTbFzuwJnsyAq0S1O7cTOYMRnrTSksie7zYkJF1yco3MIEdKINz/QNJksxh7goY7KgBJCbDLLQSsYyMEB5htlg1L84HCi04LNRAaTJuk3ww0ROtI6lG7RCOfVx0HUjk8VV7ZIHWiRbjrVrWHpdEazLikS5o7S/7SJSTDR06AmL++c37QOWZ7KzOVOwmOksv3sU8NCXXJFdKN2vrO8RNN2ZDZHQpK+z6iKD24zUNDC/fVsGY2DCjiD9uwiwR0+dieJjYTMvhKTki+hA9+f00oMTJtKY8sRX0PiLiw/BDtpyYFfsUTaxlXErNwsCu7KfSqY9sZZ8j5h3oCWZZ5XL8WavTnPk83VKt1pPTiXdzVaMm+IKF1nrxd7r7iiNfIXDncaKUACYlGpIdqZHOeRpfVNAVNhkboh75P5w7PVdea2zi8HNe06tRU4pmqs1QXFZ01NG1HycFnZ68wuKcUGqPC+s7gBA00aZ4tuiuAIinx8m0uPDen0dXTBStp16fyWziiSmVZpMkrnxmYYBETF9T37mDBYQ5celSZqqm2mlhtdlwsZ/Hj++WMXB+3+A4ftJu1+48Ed9+0yLcHkT12daQJyawcybcGb1d/9EYe7MSbw8STmJgTWpGAV1jkel977yX1tlc6E8gPlSyDrx6d8iRiJBjAluMyIjIFmz5Fy+z/rJTDtt408YrRzmv6qZbXmn7NG9Z80h4mhcPgdIC3qCPx5WWTZfPKx27F9wfUtqluvEGwRFK23OXtyiCl2nVQ38bE91WVGHd4j3Sm9L51WJ4vayyrwwvo7T+zfoaz9wSbVRfaFXeeOcXxvfgqge26e37v2B3Ds6rXHWMUNysDwn1zs05V5cQYtajBiSGevOuYKnLCGgKgP19wbsYbKQ3ruulKgRvLq+0HBeTA7zSymfAHcr20kmgdIjQrjxKaGk45l669WqNVfm26o7SqbiFu7Xk3dybSmNj7bnVxFaalYZd3rQtHgdu9cVK+gXl1zbtIJsXMrjjNsIv1nx8GvaVOB8G2OMb3/hv8ANQJO0/wtUsfQAAAABJRU5ErkJggg==" alt="">	
</a>

		
    </body>
</html>
