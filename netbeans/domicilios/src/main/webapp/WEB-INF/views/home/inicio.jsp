<%-- 
    Document   : inicio
    Created on : 7/08/2016, 05:05:04 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Domicilios Colombian Broaster</title>

    <link rel="stylesheet" type="text/css" href="/js/owlcarousel/owl.carousel.css"><!-- owl carousel -->
    <link rel="stylesheet" type="text/css" href="/css/home/home.css">
    <!-- Custom Css 
    ================================================== -->
    <link rel="stylesheet" type="text/css" href="/css/rs-wp-v1.2.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/responsive.css">

    <!-- Fonts 
    ================================================== -->
    <link href='/fonts/stylesheet.css' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Arimo:400,700,400italic,700italic' rel='stylesheet' type='text/css'>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCuORR5DPzE4CG5YdiEJsV8wn4TrKSbtWw&signed_in=true"  async defer></script>
</head>
<body>
    <!-- Inicio contenido del home -->
    <!-- Hero Sliders -->
    <div id="main-slider">
        <div class="owl-carousel owl-theme">

            <div class="item"><!-- Item Slider #2 -->
                <div id="slider-item-2" class="slider-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-10 col-sm-offset-1">
                                <div class="rst-table">
                                    <div class="rst-table-row">
                                        <div class="rst-table-cell t-slider" style="width: 50%;vertical-align: bottom;">
                                            <img style="opacity:0;" src="/img/post/people.png" alt="" />
                                        </div>
                                        <div class="rst-table-cell" style="width: 50%">
                                            <div class="text-right">
                                                <h2><span>EL MEJOR POLLO</span><br /><span>AL ESTILO AMERICANO</span></h2>
                                                <a class="btn btn-lg btn-danger btnslider" href="/contenido/productos.htm">Ver men&uacute;</a>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <img class="owl-main" src="/img/post/slides03.jpg" alt="" />
            </div>

            <div class="item active"><!-- Item Slider #1 -->
                <div id="slider-item-1" class="slider-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-10 col-sm-offset-1">
                                <div class="rst-table">
                                    <div class="rst-table-row">
                                        <div class="rst-table-cell t-slider" style="width: 50%">
                                            <h2>EL MEJOR POLLO<br />AL ESTILO AMERICANO</h2>


                                            <a class="btn btn-lg btn-danger btnslider " href="/contenido/productos.htm">Ver men&uacute;</a>


                                        </div>



                                        <div class="rst-table-cell" style="width: 50%">
                                            <img src="/img/post/slider3.png" alt="" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <img class="owl-main" src="/img/post/slides02.jpg" alt="" />
            </div>
            <div class="item"><!-- Item Slider #5 -->
                <div id="slider-item-3" class="slider-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-10 col-sm-offset-1">
                                <div class="rst-table">
                                    <div class="rst-table-row">
                                        <div class="rst-table-cell t-slider" style="width: 50%">
                                            <h2 class="shadow">EL MEJOR POLLO<br />AL ESTILO AMERICANO</h2>											
                                            <a class="btn btn-lg btn-danger btnslider" href="/contenido/productos.htm">Ver men&uacute;</a>


                                        </div>
                                        <div class="rst-table-cell" style="width: 50%">
                                            <div class="rst-slider-menu">
                                                <img src="/img/post/bg_slider.jpg" alt="" />
                                                <h3>MEN&Uacute;</h3>
                                                <p class="description-heading">Traditional or classic</p>

                                                <h5>Neapolitan pizza</h5>
                                                <p class="rst-price">$19.95</p>
                                                <h5>Premium pizza </h5>
                                                <p class="rst-price">$22</p>
                                                <h5>Classic pizza</h5>
                                                <p class="rst-price">$19.95</p>
                                                <h5>artisan pizza</h5>
                                                <p class="rst-price">$19.95</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <img class="owl-main" src="/img/post/slides04.jpg" alt="" />
            </div>
        </div>
    </div><!-- end Hero Sliders -->

    <!-- Container Inicio -->
    <div id="content" class="rst-main-content rst-content-full">
        <br /><br /><br />
        <div class="text-center">
            <h3>PLATOS</h3>
            <p class="description-heading">Texto complementario</p>
        </div>
        <div class="container">
            <div class="row" style="display: none;">
                <div class="col-sm-4">
                    <div class="rst-dishes-item">
                        <div class="rst-thumbnail">
                            <a href="#">
                                <img class="img-responsive" src="/img/post/dishes01.jpg" alt="" />
                                <span class="rst-price">$25.000.95</span>
                            </a>
                        </div>
                        <h3>POLLOS A LA BRASA</h3>
                        <p>Aenean dapibus facilisis urna lacinia bibendum. Maecenas sit amet lacinia dui. Donec vitae egestas ipsum.</p>
                        <a href="#" class="btn btn-danger btn-lg">PEDIR AHORA</a>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="rst-dishes-item">
                        <div class="rst-thumbnail">
                            <a href="#">
                                <img class="img-responsive" src="/img/post/dishes02.jpg" alt="" />
                                <span class="rst-price">$25.000.95</span>
                            </a>
                        </div>
                        <h3>CARNES Y PARILLA</h3>
                        <p>Aenean dapibus facilisis urna lacinia bibendum. Maecenas sit amet lacinia dui. Donec vitae egestas ipsum.</p>
                        <a href="#" class="btn btn-danger btn-lg">PEDIR AHORA</a>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="rst-dishes-item">
                        <div class="rst-thumbnail">
                            <a href="#">
                                <img class="img-responsive" src="/img/post/dishes03.jpg" alt="" />
                                <span class="rst-price">$25.000.95</span>
                            </a>
                        </div>
                        <h3>PLATOS ESPECIALES</h3>
                        <p>Aenean dapibus facilisis urna lacinia bibendum. Maecenas sit amet lacinia dui. Donec vitae egestas ipsum.</p>
                        <a href="#" class="btn btn-danger btn-lg">PEDIR AHORA</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="rst-dishes" data-background="/img/post/bg_dishes.jpg" style="display: none;">
            <div class="rst-dishes-form clearfix">
                <div class="rst-table">
                    <div class="rst-table-row">
                        <div class="rst-img-dishes rst-table-cell">
                            <img src="/img/post/img-dishes.png" alt="" />
                        </div>
                        <div class="rst-dishes-content rst-table-cell">
                            <h3>Platos desde $19.95</h3>
                            <p class="description-heading">¡ Solo por hoy !</p>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="rst-dishes-action">
                                        <h4><a href="#">Hamburguesas</a></h4>
                                        <span class="rst-price">$19.95</span>
                                        <div class="rst-img-product">
                                            <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="48.79" height="51" viewBox="0 0 48.79 51"><defs><style>.cls-1{fill:none;stroke:#5ccd69;stroke-miterlimit:10;}.cls-2{fill:#5ccd69;}</style></defs><title>hambu</title><path class="cls-1 svghv" d="M44.31,39.75H4.59c-2.14,0-1.31-4.31-1.31-4.31H45.62S46.06,39.75,44.31,39.75Z"/><rect class="cls-2 svgh" x="2.62" y="26.59" width="43.55" height="3.21"/><path class="cls-2 svgh" d="M23.67,31.76a.86.86,0,0,1,.69.27c.25,0,.64-.12,1.25-.26a7.54,7.54,0,0,0,2.49-1h-5c.15.23.28.44.37.61A1.93,1.93,0,0,0,23.67,31.76Z"/><path class="cls-2 svgh" d="M20.18,30.8h-2a1.08,1.08,0,0,0,.93.38A1.71,1.71,0,0,0,20.18,30.8Z"/><polygon class="cls-2 svgh" points="38.33 30.8 36.87 30.8 37.8 31.12 38.33 30.8"/><path class="cls-2 svgh" d="M8.2,32.57a16.81,16.81,0,0,0,3.11-1.48l.52-.29H4.46C4.89,31.51,6.18,33.22,8.2,32.57Z"/><path class="cls-2 svgh" d="M3.11,31.09l.59-.29H1.31V34H7.17A5.1,5.1,0,0,1,3.11,31.09Z"/><path class="cls-2 svgh" d="M47.48,30.8H42.82l1.91,1L44.12,33,40.5,31.05l-2.56,1.5-3.24-1.09H30.34a2.29,2.29,0,0,0-1.5.43A8.56,8.56,0,0,1,25.91,33c-1.8.42-2.29.48-2.62.05L23.2,33A1.8,1.8,0,0,1,22.3,32a3.91,3.91,0,0,0-.53-.8,4.61,4.61,0,0,1-.41.33,3.54,3.54,0,0,1-2.29.92,2.33,2.33,0,0,1-1.79-.74A2.64,2.64,0,0,0,15.7,31c-1.15-.23-2.35.45-3.74,1.24A18.17,18.17,0,0,1,8.6,33.82,4,4,0,0,1,7.49,34h40Z"/><path class="cls-2 svgh" d="M7.31,34h0Z"/><path class="cls-2 svgh" d="M4.29,30.52l-.59.29h.75C4.36,30.65,4.31,30.54,4.29,30.52Z"/><path class="cls-1 svghv" d="M3.11,25.33c3.59-7.94,10.7-14.08,20-14.08.53,0,3.58,0,4.1.06,8.79.6,14.91,6.16,18.19,13.91Z"/></svg>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="rst-dishes-action">
                                        <h4><a href="#">Pollo</a></h4>
                                        <span class="rst-price">$22</span>
                                        <div class="rst-img-product">
                                            <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="48.79" height="51" viewBox="0 0 48.79 51"><defs><style>.cls-1{fill:none;stroke:#5ccd69;stroke-miterlimit:10;stroke-width:2px;}</style></defs><title>aa</title><path class="cls-1 svghv" d="M38.08,27.69l-.12,0a5.91,5.91,0,0,0-4.12-1,9.83,9.83,0,0,0,.43-2.87V19.65a2,2,0,0,0,.3.16,2.13,2.13,0,0,0,3.94-1.28L38.2,15c-.1-1.17-.8-8.54-2-8.44s-.78,7.64-.68,8.81h0L33.84,14.2c-1.22-3.68-5-5.58-9.45-5.58s-8.32,1.95-9.49,5.71l-1.5,1h0c.1-1.17.5-8.71-.68-8.81s-1.87,7.27-2,8.44l-.31,3.54a2.12,2.12,0,0,0,3.94,1.28l.14-.08v4.08a9.84,9.84,0,0,0,.63,3.44,5.45,5.45,0,0,0-4.07.1l-.21,0C9,28,8.62,32.3,10.13,37s4.29,7.95,6.2,7.33,2.24-4.76.82-9.34l.11,0c1.48,3.53,4.12,5.88,7.13,5.88s5.45-2.16,7-5.45c-1.46,4.36-1.24,8.31.56,9s4.81-2.46,6.51-7.06S40,28.39,38.08,27.69Z"/></svg>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a href="/contenido/productos.htm" class="btn btn-lg btn-success">Ver men&uacute;</a>

                        </div>
                    </div>
                </div>
            </div>
        </div>





        <div class="rst-visitors">
            <div class="container">
                <div class="rst-menu rst-menu-home rst-table">
                    <div class="rst-table-row">

                        <a href="#" class="rst-table-cell">
                            <span class="rst-image-category">
                                <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="48.79" height="51" viewBox="0 0 48.79 51"><defs><style>.cls-1{fill:none;stroke:#5ccd69;stroke-miterlimit:10;stroke-width:2px;}</style></defs><title>aa</title><path class="cls-1 svghv" d="M38.08,27.69l-.12,0a5.91,5.91,0,0,0-4.12-1,9.83,9.83,0,0,0,.43-2.87V19.65a2,2,0,0,0,.3.16,2.13,2.13,0,0,0,3.94-1.28L38.2,15c-.1-1.17-.8-8.54-2-8.44s-.78,7.64-.68,8.81h0L33.84,14.2c-1.22-3.68-5-5.58-9.45-5.58s-8.32,1.95-9.49,5.71l-1.5,1h0c.1-1.17.5-8.71-.68-8.81s-1.87,7.27-2,8.44l-.31,3.54a2.12,2.12,0,0,0,3.94,1.28l.14-.08v4.08a9.84,9.84,0,0,0,.63,3.44,5.45,5.45,0,0,0-4.07.1l-.21,0C9,28,8.62,32.3,10.13,37s4.29,7.95,6.2,7.33,2.24-4.76.82-9.34l.11,0c1.48,3.53,4.12,5.88,7.13,5.88s5.45-2.16,7-5.45c-1.46,4.36-1.24,8.31.56,9s4.81-2.46,6.51-7.06S40,28.39,38.08,27.69Z"/></svg>
                            </span>
                            Pollo</a>


                        <a href="#" class="rst-table-cell">
                            <span class="rst-image-category">
                                <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="48.79" height="51" viewBox="0 0 48.79 51"><defs><style>.cls-1{fill:none;stroke:#5ccd69;stroke-miterlimit:10;}.cls-2{fill:#5ccd69;}</style></defs><title>sopa</title><path class="cls-1 svghv" d="M43.29,22.76H5.5A18.89,18.89,0,0,0,18.06,40.54v2.66H30.73V40.54A18.89,18.89,0,0,0,43.29,22.76Z"/><path class="cls-2 svgh" d="M40.69,9l-1-1.24L26.09,18.21h-10s2.8,3,6.25,3c2.88,0,5.28-2.07,6-2.77Z"/></svg>
                            </span>
                            sopas</a>


                        <a href="#" class="rst-table-cell">
                            <span class="rst-image-category">
                                <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="48.79" height="51" viewBox="0 0 48.79 51"><defs><style>.cls-1{fill:none;stroke:#5ccd69;stroke-miterlimit:10;}.cls-2{fill:#5ccd69;}</style></defs><title>hambu</title><path class="cls-1 svghv" d="M44.31,39.75H4.59c-2.14,0-1.31-4.31-1.31-4.31H45.62S46.06,39.75,44.31,39.75Z"/><rect class="cls-2 svgh" x="2.62" y="26.59" width="43.55" height="3.21"/><path class="cls-2 svgh" d="M23.67,31.76a.86.86,0,0,1,.69.27c.25,0,.64-.12,1.25-.26a7.54,7.54,0,0,0,2.49-1h-5c.15.23.28.44.37.61A1.93,1.93,0,0,0,23.67,31.76Z"/><path class="cls-2 svgh" d="M20.18,30.8h-2a1.08,1.08,0,0,0,.93.38A1.71,1.71,0,0,0,20.18,30.8Z"/><polygon class="cls-2 svgh" points="38.33 30.8 36.87 30.8 37.8 31.12 38.33 30.8"/><path class="cls-2 svgh" d="M8.2,32.57a16.81,16.81,0,0,0,3.11-1.48l.52-.29H4.46C4.89,31.51,6.18,33.22,8.2,32.57Z"/><path class="cls-2 svgh" d="M3.11,31.09l.59-.29H1.31V34H7.17A5.1,5.1,0,0,1,3.11,31.09Z"/><path class="cls-2 svgh" d="M47.48,30.8H42.82l1.91,1L44.12,33,40.5,31.05l-2.56,1.5-3.24-1.09H30.34a2.29,2.29,0,0,0-1.5.43A8.56,8.56,0,0,1,25.91,33c-1.8.42-2.29.48-2.62.05L23.2,33A1.8,1.8,0,0,1,22.3,32a3.91,3.91,0,0,0-.53-.8,4.61,4.61,0,0,1-.41.33,3.54,3.54,0,0,1-2.29.92,2.33,2.33,0,0,1-1.79-.74A2.64,2.64,0,0,0,15.7,31c-1.15-.23-2.35.45-3.74,1.24A18.17,18.17,0,0,1,8.6,33.82,4,4,0,0,1,7.49,34h40Z"/><path class="cls-2 svgh" d="M7.31,34h0Z"/><path class="cls-2 svgh" d="M4.29,30.52l-.59.29h.75C4.36,30.65,4.31,30.54,4.29,30.52Z"/><path class="cls-1 svghv" d="M3.11,25.33c3.59-7.94,10.7-14.08,20-14.08.53,0,3.58,0,4.1.06,8.79.6,14.91,6.16,18.19,13.91Z"/></svg>
                            </span>
                            Hamburguesas
                        </a>
                        <a href="#" class="rst-table-cell">
                            <span class="rst-image-category">
                                <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="48.79" height="51" viewBox="0 0 48.79 51"><defs><style>.cls-1{fill:none;stroke:#5ccd69;stroke-miterlimit:10;}.cls-2{fill:#5ccd69;}</style></defs><title>familia-02</title><path class="cls-1 svghv" d="M27.89,22a4.15,4.15,0,0,0,0-.48,3.47,3.47,0,0,0-3.17-3.7,3.47,3.47,0,0,0-3.17,3.7c0,.12,0,.25,0,.37A20.09,20.09,0,0,0,4.31,41.74H44.48A20.09,20.09,0,0,0,27.89,22Z"/><rect class="cls-2 svgh" x="4.31" y="43.68" width="40.17" height="2.47"/><path class="cls-2 svgh" d="M14.87,17a2.3,2.3,0,0,0,1,.64,2.7,2.7,0,0,0,1.11.11,2.64,2.64,0,0,1-1.56-1.15,2.15,2.15,0,0,1,0-1.71,8.63,8.63,0,0,1,.9-1.74c.36-.56.77-1.12,1.2-1.66a14.87,14.87,0,0,0,1.23-1.84,7.4,7.4,0,0,0,.82-2.15,3.5,3.5,0,0,0,0-1.23,2.29,2.29,0,0,0-.53-1.16,2.26,2.26,0,0,0-1-.64A2.73,2.73,0,0,0,17,4.39a2.63,2.63,0,0,1,1.56,1.16,2.14,2.14,0,0,1,0,1.71A8.51,8.51,0,0,1,17.63,9c-.36.57-.77,1.12-1.2,1.66a14.79,14.79,0,0,0-1.23,1.84,7.31,7.31,0,0,0-.82,2.15,3.47,3.47,0,0,0,0,1.23A2.36,2.36,0,0,0,14.87,17Z"/><path class="cls-2 svgh" d="M22.27,13.51a2.28,2.28,0,0,0,1,.64,2.73,2.73,0,0,0,1.11.12,2.64,2.64,0,0,1-1.56-1.15,2.14,2.14,0,0,1,0-1.71,8.37,8.37,0,0,1,.9-1.74c.37-.57.77-1.12,1.2-1.66a14.73,14.73,0,0,0,1.23-1.84A7.43,7.43,0,0,0,27,4a3.5,3.5,0,0,0,0-1.23,2.32,2.32,0,0,0-.53-1.16,2.28,2.28,0,0,0-1-.64A2.77,2.77,0,0,0,24.39.87,2.63,2.63,0,0,1,25.95,2a2.15,2.15,0,0,1,0,1.71A8.54,8.54,0,0,1,25,5.47c-.36.57-.77,1.12-1.19,1.66A14.71,14.71,0,0,0,22.6,9a7.43,7.43,0,0,0-.82,2.14,3.57,3.57,0,0,0,0,1.22A2.33,2.33,0,0,0,22.27,13.51Z"/><path class="cls-2 svgh" d="M29.87,17a2.27,2.27,0,0,0,1,.64,2.69,2.69,0,0,0,1.1.11,2.65,2.65,0,0,1-1.56-1.15,2.15,2.15,0,0,1,0-1.71,8.73,8.73,0,0,1,.9-1.74c.37-.56.77-1.12,1.2-1.66a14.23,14.23,0,0,0,1.23-1.84,7.29,7.29,0,0,0,.83-2.15,3.47,3.47,0,0,0,0-1.23,2.36,2.36,0,0,0-.52-1.16,2.27,2.27,0,0,0-1-.64A2.74,2.74,0,0,0,32,4.39a2.6,2.6,0,0,1,1.56,1.16,2.13,2.13,0,0,1,0,1.71A8.42,8.42,0,0,1,32.63,9c-.37.57-.77,1.12-1.19,1.66a14,14,0,0,0-1.23,1.84,7.29,7.29,0,0,0-.83,2.15,3.47,3.47,0,0,0,0,1.23A2.29,2.29,0,0,0,29.87,17Z"/></svg>
                            </span>
                            Combos Familiares
                        </a>
                        <a href="#" class="rst-table-cell">
                            <span class="rst-image-category">
                                <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="48.79" height="51" viewBox="0 0 48.79 51"><defs><style>.cls-1{fill:#5ccd69;}</style></defs><title>adicion</title><path class="cls-1 svghv" d="M30.1,9.43,8.27,25.81V41.57H40.52V25.81S39.72,19,30.1,9.43Zm9.21,23.63H9.47V27H39.31ZM9.47,40.37v-6H39.31v6Z"/></svg>
                            </span>
                            Porciones
                        </a>
                        <a href="#" class="rst-table-cell">
                            <span class="rst-image-category">
                                <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="48.79" height="51" viewBox="0 0 48.79 51"><defs><style>.cls-1{fill:none;stroke:#5ccd69;stroke-miterlimit:10;}.cls-2{fill:#5ccd69;}</style></defs><title>bebida</title><polygon class="cls-1 svghv" points="16.38 44.32 28.31 44.32 31.89 20.15 12.8 20.15 16.38 44.32"/><rect class="cls-2 svgh" x="16.37" y="45.51" width="11.94" height="2.3"/><path class="cls-2 svgh" d="M32.2,15.68H27l1.87-8.2,8.65-3.17-.41-1.12L27.8,6.58l-2.08,9.1H12.81a1.49,1.49,0,1,0,0,3H32.2a1.49,1.49,0,1,0,0-3Z"/></svg>
                            </span>
                            Bebidas
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <a name="contact"></a>
        <br /><br />
        <div class="container"><!--- container -->
            <div class="row"><!--- row -->
                <div class="col-sm-4">
                    <h3>Nosotros</h3>
                    <p>Estamos para servirte el mejor pollo de la ciudad, &aacute;demas tenemos platos especiales, jugos naturales, Hamburguesas.</p>
                    <p>Llevamos mas de 20 a&ntilde;os compartiendo nuestro buen sabor y atenci&oacute;n.</p>
                </div>
                <div class="col-sm-4">
                    <h3>¿En d&oacute;nde estamos?</h3>
                    <ul class="rst-latest-news">
                        <li>
                            <h4><a href="#">Direcci&oacute;n</a></h4>
                            <p>Cl. 26 # 33 A-41, Bogotá, Colombia</p>
                        </li>
                        <li>
                            <h4><a href="#">Tel&eacute;fono</a></h4>
                            
                            <p><span class="glyphicon glyphicon-phone-alt" ></span> (1) 2685558</p>
                            <div class="background-social background-whatsapp" ></div>
                            <p class="right-side-logo">
                              3142002610
                            </p>
                            <div class="background-social background-facebook"></div>
                            <p class="right-side-logo">
                                <a href="https://www.facebook.com/colombian.broaster.77">P&aacute;gina Oficial- Encuentranos en Facebook</a>
                            </p>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-4">
                    <h3>Horarios</h3>
                    <div class="rst-happy-hours">
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Lunes</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="In stock" data-placement="bottom" data-toggle="tooltip"></span> 11.00AM — 09.30PM</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Martes</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="In stock" data-placement="bottom" data-toggle="tooltip"></span> 11.00AM — 09.30PM</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Miercoles</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="Low stock" data-placement="bottom" data-toggle="tooltip"></span> 11.00AM — 09.30PM</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Jueves</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="Low stock" data-placement="bottom" data-toggle="tooltip"></span> 11.00AM — 09.30PM</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Viernes</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="In stock" data-placement="bottom" data-toggle="tooltip"></span> 11.00AM — 09.30PM</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">S&aacute;bado</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="Low stock" data-placement="bottom" data-toggle="tooltip"></span> 11.00AM — 09.30PM</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Domingos y Festivos</div>
                            <div class="pull-right hours"><span class="rst-stock low-stock" data-original-title="Low stock" data-placement="bottom" data-toggle="tooltip"></span> 11.00AM — 08.30PM</div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <br>
        </div><!--- end row -->

        <!--- Contact Detail/Map -->
        <div class="rst-contact">
            <div id="map-canvas" class="rst-contact-maps" data-zoom="15" data-center="10.731688,122.5505356"> </div>
                <div  class="rst-contact-maps"> <iframe src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCuORR5DPzE4CG5YdiEJsV8wn4TrKSbtWw&q=place_id:ChIJI6v2u9abP44RzMEdYXCSaWM&center=4.6310628,-74.083934" width="100%" height="100%" frameborder="0" style="border:0" allowfullscreen></iframe> </div>
        </div><!--- end Contact Detail/Map -->
    </div><!--- end container -->
    <!-- Fin Container Inicio -->

    <!-- Fin contenido del home -->

</body>
