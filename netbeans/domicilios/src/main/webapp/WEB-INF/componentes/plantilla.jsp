<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/jasny-bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap-slider.css">
        <link rel="stylesheet" type="text/css" href="font-awesome-4.3.0/css/font-awesome.css">

        <!-- Plugins CSS ================================================== -->
        <link rel="stylesheet" type="text/css" href="css/animate.css"><!-- animation -->
        <link rel="stylesheet" type="text/css" href="css/bgndGallery.css"><!-- bgndGallery -->
        <link rel="stylesheet" type="text/css" href="/js/fancybox/jquery.fancybox.css?v=2.1.5"><!-- Fancybox  -->
        <link rel="stylesheet" type="text/css" href="css/calendar/fullcalendar.min.css"><!-- Calendar -->
        <link rel="stylesheet" type="text/css" href="/js/bxslider/jquery.bxslider.css"><!-- BxSlider -->
        <link rel="stylesheet" type="text/css" href="css/jquery.rs.selectbox.css"><!-- Selectbox -->	
        <link rel="stylesheet" type="text/css" href="/js/owlcarousel/owl.carousel.css"><!-- owl carousel -->

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
    <!-- Preloader -->
    <div id="pageLoading">
        <div class="bouncing">
            <em class="icon-food"></em>
        </div>
    </div>
    <!--- Wrapper -->
    <div id="wrapper" class="home-page">
        <sitemesh:write property='body'/>
        <!--- Footer -->
        <footer id="footer">
            <div class="container">
                <div class="row">
                    <div class="rst-table">
                        <div class="rst-table-row">
                            <div class="rst-copyright rst-table-cell">
                                <a href="#" class="rst-logo-footer"><img src="img/logo-footer.png" alt="" /></a>&copy; 2014 FoodUp. All right reserved.
                            </div>
                            <div class="rst-menu-footer rst-table-cell">
                                <ul>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="menu.html">Menu</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                </ul>
                            </div>
                            <div class="rst-note rst-table-cell">
                                <p>Thanks for watching. Go to start.</p>
                            </div>
                        </div>
                    </div>
                    <a class="rst-backtop" href="#"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
        </footer>
    </div><!--- End Wrapper -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="js/jquery.1.11.1.js"></script>
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

    <script type='text/javascript' src='js/woocommerce.js'></script>
    <script type='text/javascript'>
        /* <![CDATA[ */
        var wc_single_product_params = {"i18n_required_rating_text": "Please select a rating", "review_rating_required": "yes"};
        var wc_single_product_params = {"i18n_required_rating_text": "Please select a rating", "review_rating_required": "yes"};
        /* ]]> */
    </script>
    <script type='text/javascript' src='js/single-product.min.js'></script>

    <script type='text/javascript' src="/js/bootstrap-slider.js"></script>

    <script type='text/javascript' src="/js/owlcarousel/owl.carousel.min.js"></script>

    <script src='js/calendar/moment.min.js'></script>
    <script src='js/calendar/fullcalendar.min.js'></script>

    <script type="text/javascript" src="/js/main.js"></script>
</body>
</html>
