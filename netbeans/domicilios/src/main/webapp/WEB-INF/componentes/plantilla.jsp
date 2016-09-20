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
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" href="/img/apple-touch-icon.png">
        <link rel="apple-touch-icon" sizes="72x72" href="/img/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="114x114" href="/img/apple-touch-icon-114x114.png">

        <!-- Bootstrap ================================================== -->
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="/css/jasny-bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap-slider.css">
        <link rel="stylesheet" type="text/css" href="/font-awesome-4.3.0/css/font-awesome.css">

        <!-- Plugins CSS ================================================== -->
        <link rel="stylesheet" type="text/css" href="/css/animate.css"><!-- animation -->
        <link rel="stylesheet" type="text/css" href="/css/bgndGallery.css"><!-- bgndGallery -->
        <link rel="stylesheet" type="text/css" href="/js/fancybox/jquery.fancybox.css?v=2.1.5"><!-- Fancybox  -->
        <link rel="stylesheet" type="text/css" href="/css/calendar/fullcalendar.min.css"><!-- Calendar -->
        <link rel="stylesheet" type="text/css" href="/js/bxslider/jquery.bxslider.css"><!-- BxSlider -->
        <link rel="stylesheet" type="text/css" href="/css/jquery.rs.selectbox.css"><!-- Selectbox -->	

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script type="text/javascript" src="/js/jquery.1.11.1.js"></script>
        <!--script src="/js/jquery-3.1.0.min.js"></script-->
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
                        <a href="index.html" class="rst-logo rst-table-cell"><img src="/img/logopollo.png" alt=""/></a>
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
                                <li><a href="/contenido/productos.htm">Men&uacute;</a></li>
                                <li><a href="/compras/pedido.htm">Hacer Pedido</a></li>
                                <li><a href="#contact">Contacto</a></li>
                                <sec:authorize  access="isAuthenticated()">
                                    <li><a href="/logout.htm">Cerrar Sesi&oacute;n</a></li>
                                </sec:authorize>
                            </ul>
                            <!--<a href="#" class="rst-search-bottom"><i class="fa fa-search"></i></a>-->
                        </div>
                        <div class="rst-account rst-table-cell">
                            <div class="rst-cart">
                                <a href="#" class="rst-cart-icon"><span>6</span></a>
                                <div class="rst-form-login rst-cart-info">
                                    <div class="rst-list-product">
                                        <div class="rst-product-item">
                                            <a href="#">Plato 1<span class="count">2</span> <span class="price">$25.000</span></a>
                                        </div>
                                        <div class="rst-product-item">
                                            <a href="#">Plato 2 <span class="count">2</span> <span class="price">$25.000</span></a>
                                        </div>
                                        <div class="rst-product-item">
                                            <a href="#">Plato 3 <span class="count">2</span> <span class="price">$25.000</span></a>
                                        </div>
                                    </div>
                                    <div class="rst-checkout">
                                        <a href="/compras/pedido.htm" class="btn btn-success btn-sm btnpagar"> HACER PEDIDO</a>
                                        <span class="price">$75.000</span>
                                    </div>
                                </div>
                            </div>
                            <sec:authorize  access="isAuthenticated()">
                                <a href="/signin.htm" class="rst-signup btn btn-success"><sec:authentication property="principal.nombreusuario"/></a>
                            </sec:authorize>
                            <sec:authorize  access="!isAuthenticated()">
                                <a href="/signin.htm" class="rst-signup btn btn-success">Ingresar</a>
                            </sec:authorize>
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


    <!--script src="/js/jquery-3.1.0.min.js"></script-->
    <!-- Bootstrap Js Compiled Plugins -->
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>

    <!-- Backstretch Plugins -->
    <script type="text/javascript" src="/js/jquery.backstretch.js"></script>

    <!-- Form Js -->
    <script type="text/javascript" src="/js/jquery.rs.form.js"></script>

    <!-- BxSlider -->
    <script type="text/javascript" src="/js/bxslider/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="/js/bxslider/jquery.bxslider.min.js"></script>

    <!-- Fancybox -->
    <script type="text/javascript" src="/js/fancybox/jquery.fancybox.js?v=2.1.5"></script>

    <!-- Selectbox Js -->
    <script type="text/javascript" src="/js/jquery.rs.selectbox.js"></script>

    <!-- Google Map -->
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;libraries=places"></script>

    <!-- WoW Plugins -->
    <script src="/js/wow.min.js"></script>

    <script type='text/javascript' src='/js/woocommerce.js'></script>
    <script type='text/javascript'>
        /* <![CDATA[ */
        var wc_single_product_params = {"i18n_required_rating_text": "Please select a rating", "review_rating_required": "yes"};
        /* ]]> */
    </script>
    <script type='text/javascript' src='/js/single-product.min.js'></script>

    <script type='text/javascript' src="/js/bootstrap-slider.js"></script>

    <script type='text/javascript' src="/js/owlcarousel/owl.carousel.min.js"></script>

    <script src='/js/calendar/moment.min.js'></script>

    <script type="text/javascript" src="/js/main.js"></script>
</body>
</html>
