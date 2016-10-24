<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--[if IE]><meta http-equiv="x-ua-compatible" content="IE=9" /><![endif]-->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title><sitemesh:write property='title'/></title>
        <!-- Favicons    ================================================== -->
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" href="<%=request.getContextPath()%>/img/apple-touch-icon.png">
        <link rel="apple-touch-icon" sizes="72x72" href="<%=request.getContextPath()%>/img/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="114x114" href="<%=request.getContextPath()%>/img/apple-touch-icon-114x114.png">

        <!-- Bootstrap ================================================== -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jasny-bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-slider.css">
        <link rel="stylesheet" type="text/css" href="/font-awesome-4.3.0/css/font-awesome.css">

        <!-- Plugins CSS ================================================== -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/animate.css"><!-- animation -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bgndGallery.css"><!-- bgndGallery -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/fancybox/jquery.fancybox.css?v=2.1.5"><!-- Fancybox  -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/calendar/fullcalendar.min.css"><!-- Calendar -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/bxslider/jquery.bxslider.css"><!-- BxSlider -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.rs.selectbox.css"><!-- Selectbox -->	

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.1.11.1.js"></script>
        <!--script src="<%=request.getContextPath()%>/js/jquery-3.1.0.min.js"></script-->
    <sitemesh:write property='head'/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <!--- Wrapper -->
    <div id="wrapper">
        <header id="header">
            <div class="container">
                <div class="rst-table">
                    <div class="rst-table-row">
                        <a href="<%=request.getContextPath()%>/home.htm" class="rst-logo rst-table-cell"><img src="<%=request.getContextPath()%>/img/logopollo.png" alt=""/></a>
                        <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <div class="rst-nav-menu collapse navbar-collapse bs-navbar-collapse rst-table-cell">
                            <form class="rst-search-mobie" action="/">
                                <button class="rst-submit"><i class="fa fa-search"></i></button>
                                <input type="text" value="" />
                            </form>
                            <ul class="rst-main-menu">
                                <li class="current-menu-item"><a href="/home.htm">Inicio</a></li>
                                <li><a href="<%=request.getContextPath()%>/productos/inventario.htm">Productos</a></li>
                                <li><a href="<%=request.getContextPath()%>/pedidos/domicilios.htm">Pedidos</a></li>
                                <sec:authorize  access="isAuthenticated()">
                                <li><a href="<%=request.getContextPath()%>/logout.htm">Cerrar Sesi&oacute;n</a></li>
                                </sec:authorize>
                            </ul>
                            <!--<a href="#" class="rst-search-bottom"><i class="fa fa-search"></i></a>-->
                        </div>
                        
                    </div>
                </div><!-- End Top Header -->
                <div class="rst-search rst-table">
                    <div class="rst-table-row">
                        <div class="rst-table-cell">
                            <form action="/">
                                <input type="text" placeholder="type and hit enter" value="" />
                            </form>
                            <a href="#" class="rst-hide-form"><i class="fa fa-close"></i></a>
                        </div>
                    </div>
                </div><!-- End Top Header -->
            </div>
        </header>	

        <%-- WRITTING BODY--%>
        <sitemesh:write property='body'/>
        <%-- WRITTING BODY--%>
        <!--- Footer -->

        <footer id="footer">
            <div class="container">
                <div class="row">
                    <div class="rst-table">
                        <div class="rst-table-row">
                            <div class="rst-copyright rst-table-cell">
                                &copy; 2016 / Colombian Broaster - Todos los derechos reservados.
                            </div>
                            <div class="rst-note rst-table-cell">
                                <p>IR ARRIBA</p>
                            </div>
                        </div>
                    </div>
                    <a class="rst-backtop" href="#"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
        </footer>
        <a name="contact1"></a>
    </div><!--- End Wrapper -->


    <!--script src="<%=request.getContextPath()%>/js/jquery-3.1.0.min.js"></script-->
    <!-- Bootstrap Js Compiled Plugins -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

    <!-- Backstretch Plugins -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.backstretch.js"></script>

    <!-- Form Js -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.rs.form.js"></script>

    <!-- BxSlider -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bxslider/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bxslider/jquery.bxslider.min.js"></script>

    <!-- Fancybox -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/fancybox/jquery.fancybox.js?v=2.1.5"></script>

    <!-- Selectbox Js -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.rs.selectbox.js"></script>

    <script type='text/javascript' src="<%=request.getContextPath()%>/js/bootstrap-slider.js"></script>

    <script type='text/javascript' src="<%=request.getContextPath()%>/js/owlcarousel/owl.carousel.min.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/main.js"></script>
</body>
</html>
