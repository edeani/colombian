<%-- 
    Document   : inicio
    Created on : 7/08/2016, 05:05:04 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Domicilios Colombian Broaster</title>

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
    <link href='http://fonts.googleapis.com/css?family=Arimo:400,700,400italic,700italic' rel='stylesheet' type='text/css'>

</head>
<body>
    <header id="header">
        <div class="container">
            <div class="rst-table">
                <div class="rst-table-row">
                    <a href="index.html" class="rst-logo rst-table-cell"><img src="img/logo-home.png" alt="" /></a>
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
                            <li class="current-menu-item"><a href="index.html">Home</a></li>
                            <li class="menu-item-has-children">
                                <a href="#">Pages</a>
                                <ul class="sub-menu">
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="products_list.html">Store</a></li>
                                    <li><a href="shortcodes.html">Shortcodes</a></li>
                                    <li><a href="team.html">Team</a></li>
                                    <li><a href="404.html">404</a></li>
                                    <li><a href="coming_soon.html">Coming Soon</a></li>
                                    <li><a href="events.html">Events</a></li>
                                    <li><a href="recipes.html">Recipes</a></li>
                                    <li><a href="recipe_detail.html">Recipe Detail</a></li>
                                    <li><a href="gallery.html">Gallery</a></li>
                                    <li><a href="reservation.html">Reservation</a></li>
                                    <li><a href="account_setting.html">Personal setting</a></li>
                                </ul>
                            </li>
                            <li><a href="menu.html">Menu</a></li>
                            <li><a href="contact.html">Contact</a></li>
                            <li class="menu-item-has-children">
                                <a href="#">Log In</a>
                                <div class="sub-menu rst-form-login">
                                    <form action="/">
                                        <div class="input-group">
                                            <span class="input-group-addon"><img src="img/mail.png" alt="" /></span>
                                            <input class="form-control" type="email" placeholder="Your email" />
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-addon"><img src="img/unlock.png" alt="" /></span>
                                            <input class="form-control" type="password" name="password" placeholder="Password" />
                                        </div>
                                        <input type="submit" class="btn btn-success" value="LOg in" />
                                    </form>
                                </div>
                            </li>
                        </ul>
                        <a href="#" class="rst-search-bottom"><i class="fa fa-search"></i></a>
                    </div>
                    <div class="rst-account rst-table-cell">
                        <div class="rst-cart">
                            <a href="#" class="rst-cart-icon"><span>2</span></a>
                            <div class="rst-form-login rst-cart-info">
                                <div class="rst-list-product">
                                    <div class="rst-product-item">
                                        <a href="#">Sashimi &amp; vasabi <span class="count">2</span> <span class="price">$20</span></a>
                                    </div>
                                    <div class="rst-product-item">
                                        <a href="#">Dinner menu <span class="count">2</span> <span class="price">$25</span></a>
                                    </div>
                                    <div class="rst-product-item">
                                        <a href="#">Salade menu <span class="count">2</span> <span class="price">$25</span></a>
                                    </div>
                                </div>
                                <div class="rst-checkout">
                                    <a href="checkout.html" class="btn btn-success btn-sm">Check out</a>
                                    <span class="price">$70</span>
                                </div>
                            </div>
                        </div>
                        <a href="register.html" class="rst-signup btn btn-success">Sign up</a>
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

    <!-- Hero Sliders -->
    <div id="main-slider">
        <div class="owl-carousel owl-theme">
            <div class="item active"><!-- Item Slider #1 -->
                <div id="slider-item-1" class="slider-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-10 col-sm-offset-1">
                                <div class="rst-table">
                                    <div class="rst-table-row">
                                        <div class="rst-table-cell" style="width: 50%">
                                            <h2>We opened. <br />Tasty food & drinks.</h2>
                                            <p>Sed in massa metus. Vestibulum eu felis fermentum.</p>

                                            <a class="btn btn-lg btn-danger" href="#">Browse menu</a>
                                            <a class="rst-down while" href="#"><i class="fa fa-chevron-down"></i></a>

                                        </div>
                                        <div class="rst-table-cell" style="width: 50%">
                                            <img src="img/post/slider3.png" alt="" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <img class="owl-main" src="img/post/slides02.jpg" alt="" />
            </div>
            <div class="item"><!-- Item Slider #2 -->
                <div id="slider-item-2" class="slider-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-10 col-sm-offset-1">
                                <div class="rst-table">
                                    <div class="rst-table-row">
                                        <div class="rst-table-cell" style="width: 50%;vertical-align: bottom;">
                                            <img src="img/post/people.png" alt="" />
                                        </div>
                                        <div class="rst-table-cell" style="width: 50%">
                                            <div class="text-right">
                                                <h2>We opened. <br />Tasty food & drinks.</h2>
                                                <p>Sed in massa metus. Vestibulum eu felis fermentum.</p>

                                                <a class="btn btn-lg btn-danger" href="#">Browse menu</a>
                                                <a class="rst-down while" href="#"><i class="fa fa-chevron-down"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <img class="owl-main" src="img/post/slides03.jpg" alt="" />
            </div>
            <div class="item"><!-- Item Slider #4 -->
                <a class="owl-video" href="http://vimeo.com/23924346"></a>
                <img class="owl-main" src="img/post/slides01.jpg" alt="" />
            </div>
            <div class="item"><!-- Item Slider #5 -->
                <div id="slider-item-3" class="slider-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-10 col-sm-offset-1">
                                <div class="rst-table">
                                    <div class="rst-table-row">
                                        <div class="rst-table-cell" style="width: 50%">
                                            <h2>We opened. <br />Tasty food & drinks.</h2>
                                            <p>Sed in massa metus. Vestibulum eu felis fermentum.</p>

                                            <a class="btn btn-lg btn-danger" href="#">Browse menu</a>
                                            <a class="rst-down while" href="#"><i class="fa fa-chevron-down"></i></a>

                                        </div>
                                        <div class="rst-table-cell" style="width: 50%">
                                            <div class="rst-slider-menu">
                                                <img src="img/post/bg_slider.jpg" alt="" />
                                                <h3>Menu</h3>
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
                <img class="owl-main" src="img/post/slides04.jpg" alt="" />
            </div>
        </div>
    </div><!-- end Hero Sliders -->

    <div id="content" class="rst-main-content rst-content-full">
        <div class="rst-newsletter">
            <strong><span>Subscribe to our newsletter</span></strong>
            <form action="/"><input type="text" value="" class="has-success" placeholder="Your Email" /></form>
        </div>
        <br /><br /><br />
        <div class="text-center">
            <h3>Dishes of the day</h3>
            <p class="description-heading">Sed in massa metus. Vestibulum eu felis fermentum.</p>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="rst-dishes-item">
                        <div class="rst-thumbnail">
                            <a href="#">
                                <img class="img-responsive" src="img/post/dishes01.jpg" alt="" />
                                <span class="rst-price">$25.95</span>
                            </a>
                        </div>
                        <h3>Breakfast</h3>
                        <p>Aenean dapibus facilisis urna lacinia bibendum. Maecenas sit amet lacinia dui. Donec vitae egestas ipsum.</p>
                        <a href="#" class="btn btn-danger btn-lg">Add to cart</a>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="rst-dishes-item">
                        <div class="rst-thumbnail">
                            <a href="#">
                                <img class="img-responsive" src="img/post/dishes02.jpg" alt="" />
                                <span class="rst-price">$25.95</span>
                            </a>
                        </div>
                        <h3>Sushi menu</h3>
                        <p>Aenean dapibus facilisis urna lacinia bibendum. Maecenas sit amet lacinia dui. Donec vitae egestas ipsum.</p>
                        <a href="#" class="btn btn-danger btn-lg">Add to cart</a>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="rst-dishes-item">
                        <div class="rst-thumbnail">
                            <a href="#">
                                <img class="img-responsive" src="img/post/dishes03.jpg" alt="" />
                                <span class="rst-price">$25.95</span>
                            </a>
                        </div>
                        <h3>Hamburger</h3>
                        <p>Aenean dapibus facilisis urna lacinia bibendum. Maecenas sit amet lacinia dui. Donec vitae egestas ipsum.</p>
                        <a href="#" class="btn btn-danger btn-lg">Add to cart</a>
                    </div>
                </div>
            </div>
        </div>
        <hr />
        <div class="container">
            <div class="rst-menu rst-menu-home rst-table">
                <div class="rst-table-row">
                    <a href="#" class="rst-table-cell">
                        <span class="rst-image-category">
                            <svg width="40px" height="31px" viewBox="0 0 40 31" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g transform="translate(-320.000000, -85.000000)" fill="#58cc65">
                            <g transform="translate(320.000000, 85.000000)">
                            <path d="M19.984,0 C10.046,0 -0.016,2.061 -0.016,6 C-0.016,19.785 8.956,31 19.984,31 C31.012,31 39.984,19.785 39.984,6 C39.984,2.061 29.922,0 19.984,0 L19.984,0 Z M19.984,29 C10.819,29 3.236,20.198 2.129,8.863 C5.611,10.932 12.829,12 19.984,12 C27.139,12 34.357,10.932 37.839,8.863 C36.732,20.198 29.149,29 19.984,29 L19.984,29 Z M19.984,10 C8.338,10 1.984,7.357 1.984,6 C1.984,4.643 8.338,2 19.984,2 C31.63,2 37.984,4.643 37.984,6 C37.984,7.357 31.63,10 19.984,10 L19.984,10 Z"></path>
                            </g>
                            </g>
                            </g>
                            </svg>
                        </span>
                        Soups
                    </a>
                    <a href="#" class="rst-table-cell">
                        <span class="rst-image-category">
                            <svg width="22px" height="38px" viewBox="0 0 22 38" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g transform="translate(-209.000000, -241.000000)" fill="#58cc65">
                            <g transform="translate(209.000000, 241.000000)">
                            <path d="M17.679,23.304 C17.226,25.021 16.265,26.534 14.981,27.56 L14.981,31.338 C16.176,31.907 16.98,33.13 16.98,34.5 C16.98,36.43 15.41,38 13.481,38 C12.515,38 11.622,37.601 10.981,36.946 C10.339,37.601 9.446,38 8.481,38 C6.551,38 4.981,36.43 4.981,34.5 C4.981,33.13 5.785,31.907 6.981,31.338 L6.981,27.56 C5.696,26.534 4.735,25.021 4.283,23.305 C1.583,20.851 -0.019,17.026 -0.019,13 C-0.019,5.832 4.915,0 10.981,0 C17.046,0 21.98,5.832 21.98,13 C21.98,17.025 20.378,20.85 17.679,23.304 L17.679,23.304 Z M8.481,36 C9.191,36 9.809,35.496 9.949,34.801 C10.044,34.335 10.454,34 10.93,34 L11.031,34 C11.507,34 11.917,34.335 12.012,34.801 C12.152,35.496 12.771,36 13.481,36 C14.308,36 14.981,35.327 14.981,34.5 C14.981,33.79 14.477,33.171 13.781,33.03 C13.316,32.936 12.981,32.526 12.981,32.05 L12.981,28 L8.981,28 L8.981,32.05 C8.981,32.526 8.646,32.936 8.18,33.03 C7.485,33.171 6.981,33.79 6.981,34.5 C6.981,35.327 7.654,36 8.481,36 L8.481,36 Z M10.981,2 C6.018,2 1.981,6.935 1.981,13 C1.981,16.571 3.42,19.937 5.83,22.003 C5.994,22.144 6.109,22.334 6.155,22.545 C6.473,23.967 7.244,25.193 8.271,26 L13.69,26 C14.717,25.193 15.488,23.967 15.806,22.545 C15.852,22.334 15.967,22.144 16.13,22.003 C18.541,19.937 19.98,16.571 19.98,13 C19.98,6.935 15.943,2 10.981,2 L10.981,2 Z"></path>
                            </g>
                            </g>
                            </g>
                            </svg>
                        </span>
                        Main courses
                    </a>
                    <a href="#" class="rst-table-cell">
                        <span class="rst-image-category">
                            <svg width="25px" height="45px" viewBox="0 0 25 45" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g transform="translate(-331.000000, -236.000000)" fill="#58cc65">
                            <g transform="translate(332.000000, 236.000000)">
                            <path d="M8.489,15 C9.318,15 9.99,14.328 9.99,13.5 C9.99,12.672 9.318,12 8.489,12 C7.661,12 6.989,12.672 6.989,13.5 C6.989,14.328 7.661,15 8.489,15 L8.489,15 Z M14.49,17 C15.319,17 15.991,16.329 15.991,15.5 C15.991,14.672 15.319,14 14.49,14 C13.662,14 12.99,14.672 12.99,15.5 C12.99,16.329 13.662,17 14.49,17 L14.49,17 Z M15.491,11 C16.32,11 16.991,10.328 16.991,9.5 C16.991,8.672 16.32,8 15.491,8 C14.662,8 13.99,8.672 13.99,9.5 C13.99,10.328 14.662,11 15.491,11 L15.491,11 Z M13.99,38 C13.437,38 12.99,38.448 12.99,39 L12.99,42.2 C12.99,42.634 12.532,43 11.99,43 C11.448,43 10.99,42.634 10.99,42.2 L10.99,39 C10.99,38.448 10.542,38 9.99,38 C9.437,38 8.989,38.448 8.989,39 L8.989,42.2 C8.989,43.744 10.335,45 11.99,45 C13.645,45 14.991,43.744 14.991,42.2 L14.991,39 C14.991,38.448 14.543,38 13.99,38 L13.99,38 Z M23.375,21.076 C23.001,20.922 22.571,21.006 22.285,21.293 L21.566,22.012 C21.488,21.839 21.405,21.666 21.307,21.5 C21.757,20.737 21.992,19.886 21.992,19 C21.992,18.114 21.757,17.263 21.307,16.5 C21.757,15.737 21.992,14.886 21.992,14 C21.992,12.758 21.526,11.578 20.694,10.664 C20.892,10.124 20.992,9.566 20.992,9 C20.992,6.556 19.229,4.516 16.907,4.084 C16.475,1.763 14.435,0 11.99,0 C9.545,0 7.505,1.763 7.073,4.084 C4.751,4.516 2.988,6.556 2.988,9 C2.988,9.566 3.088,10.124 3.286,10.664 C2.454,11.578 1.988,12.758 1.988,14 C1.988,14.886 2.223,15.737 2.673,16.5 C2.223,17.263 1.988,18.114 1.988,19 C1.988,19.886 2.223,20.737 2.673,21.5 C2.571,21.673 2.484,21.852 2.404,22.033 L1.693,21.322 C1.406,21.037 0.978,20.952 0.603,21.106 C0.228,21.26 -0.015,21.625 -0.015,22.029 L-0.015,29.016 C-0.015,33.42 3.569,37.002 7.973,37.002 L14.961,37.002 C14.966,37.002 14.97,37 14.974,37 L15.991,37 C20.403,37 23.993,33.411 23.993,29 L23.993,22 C23.993,21.596 23.749,21.231 23.375,21.076 L23.375,21.076 Z M15.991,35 L11.404,35 L21.992,24.414 L21.992,29 C21.992,32.309 19.3,35 15.991,35 L15.991,35 Z M1.986,24.443 L10.56,33.016 L8.573,35.002 L7.973,35.002 C4.672,35.002 1.986,32.317 1.986,29.016 L1.986,24.443 L1.986,24.443 Z M4.667,20.872 C4.223,20.322 3.988,19.675 3.988,19 C3.988,18.325 4.223,17.678 4.667,17.129 C4.963,16.762 4.963,16.238 4.667,15.872 C4.223,15.322 3.988,14.675 3.988,14 C3.988,13.104 4.399,12.258 5.117,11.679 C5.493,11.375 5.599,10.846 5.367,10.422 C5.116,9.96 4.988,9.482 4.988,9 C4.988,7.346 6.334,6 7.989,6 C8.542,6 8.989,5.552 8.989,5 C8.989,3.346 10.335,2 11.99,2 C13.645,2 14.991,3.346 14.991,5 C14.991,5.552 15.438,6 15.991,6 C17.645,6 18.992,7.346 18.992,9 C18.992,9.482 18.864,9.96 18.613,10.422 C18.381,10.846 18.486,11.375 18.863,11.679 C19.581,12.258 19.992,13.104 19.992,14 C19.992,14.675 19.757,15.322 19.313,15.872 C19.017,16.238 19.017,16.762 19.313,17.129 C19.757,17.678 19.992,18.325 19.992,19 C19.992,19.675 19.757,20.322 19.313,20.872 C19.017,21.238 19.017,21.762 19.313,22.129 C19.673,22.573 19.887,23.083 19.959,23.619 L11.974,31.602 L4.019,23.648 C4.086,23.102 4.301,22.581 4.667,22.129 C4.963,21.762 4.963,21.238 4.667,20.872 L4.667,20.872 Z"></path>
                            </g>
                            </g>
                            </g>
                            </svg>
                        </span>
                        Side dishes
                    </a>
                    <a href="#" class="rst-table-cell">
                        <span class="rst-image-category">
                            <svg width="20px" height="41px" viewBox="0 0 20 41" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g transform="translate(-570.000000, -80.000000)" fill="#58cc65">
                            <g transform="translate(570.000000, 80.000000)">
                            <path d="M19.883,15.056 L15.8,36.096 C15.145,39.478 12.407,40.991 9.984,40.991 C7.562,40.991 4.824,39.478 4.168,36.096 L0.086,15.056 C-0.21,13.535 0.181,11.983 1.158,10.798 C2.099,9.657 3.475,9.003 4.934,9.003 L15.034,9.003 C16.493,9.003 17.87,9.658 18.811,10.799 C19.788,11.984 20.178,13.536 19.883,15.056 L19.883,15.056 Z M17.268,12.071 C16.707,11.393 15.893,11.003 15.034,11.003 L4.934,11.003 C4.075,11.003 3.26,11.392 2.701,12.071 C2.105,12.793 1.868,13.743 2.049,14.675 L3.664,23 L7.987,23 C8.54,23 8.987,23.448 8.987,24 C8.987,24.552 8.54,25 7.987,25 L4.052,25 L6.131,35.715 C6.6,38.133 8.5,38.991 9.984,38.991 C11.468,38.991 13.368,38.133 13.837,35.715 L14.752,31 L10.987,31 C10.434,31 9.987,30.552 9.987,30 C9.987,29.448 10.434,29 10.987,29 L15.141,29 L16.887,20 L12.987,20 C12.434,20 11.987,19.552 11.987,19 C11.987,18.448 12.434,18 12.987,18 L17.275,18 L17.92,14.675 C18.101,13.743 17.864,12.794 17.268,12.071 L17.268,12.071 Z M9.987,8 C7.23,8 4.987,5.757 4.987,3 C4.987,2.448 5.434,2 5.987,2 C6.54,2 6.987,2.448 6.987,3 C6.987,4.654 8.333,6 9.987,6 C11.641,6 12.987,4.654 12.987,3 C12.987,2.448 13.434,2 13.987,2 C14.54,2 14.987,2.448 14.987,3 C14.987,5.757 12.744,8 9.987,8 L9.987,8 Z M9.987,5 C9.434,5 8.987,4.552 8.987,4 L8.987,1 C8.987,0.448 9.434,0 9.987,0 C10.54,0 10.987,0.448 10.987,1 L10.987,4 C10.987,4.552 10.54,5 9.987,5 L9.987,5 Z"></path>
                            </g>
                            </g>
                            </g>
                            </svg>
                        </span>
                        Salads
                    </a>
                    <a href="#" class="rst-table-cell">
                        <span class="rst-image-category">
                            <svg width="36px" height="35px" viewBox="0 0 36 35" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g transform="translate(-82.000000, -242.000000)" fill="#58cc65">
                            <g transform="translate(82.000000, 242.000000)">
                            <path d="M34.216,17.93 C32.524,20.844 29.871,22.948 27.001,23.696 L27.001,31.002 C27.001,33.629 22.478,35.003 18.009,35.003 C13.54,35.003 9.017,33.629 9.017,31.002 L9.017,23.696 C6.147,22.948 3.495,20.844 1.802,17.93 C0.122,15.039 -0.403,11.741 0.362,8.881 C0.937,6.734 2.226,4.992 3.994,3.978 C6.097,2.772 8.749,2.681 11.273,3.71 C12.978,1.334 15.393,-0.01 18.009,-0.01 C20.626,-0.01 23.04,1.334 24.746,3.71 C27.27,2.681 29.923,2.771 32.024,3.978 C33.792,4.992 35.082,6.734 35.656,8.881 C36.421,11.74 35.896,15.038 34.216,17.93 L34.216,17.93 Z M33.727,9.398 C33.284,7.746 32.352,6.472 31.031,5.714 C29.28,4.709 26.977,4.775 24.837,5.867 C24.37,6.104 23.799,5.942 23.528,5.494 C22.184,3.268 20.172,1.991 18.009,1.991 C15.846,1.991 13.835,3.268 12.491,5.494 C12.219,5.942 11.646,6.104 11.182,5.867 C10.061,5.295 8.88,4.993 7.768,4.993 C6.757,4.993 5.821,5.235 4.987,5.714 C3.666,6.472 2.734,7.746 2.292,9.399 C1.664,11.747 2.115,14.49 3.529,16.924 C5.089,19.609 7.575,21.469 10.179,21.9 C10.662,21.98 11.015,22.398 11.015,22.887 L11.015,31.002 C11.015,31.583 13.466,33.002 18.009,33.002 C22.552,33.002 25.003,31.583 25.003,31.002 L25.003,22.887 C25.003,22.398 25.357,21.98 25.839,21.9 C28.443,21.469 30.929,19.609 32.489,16.924 C33.904,14.49 34.355,11.747 33.727,9.398 L33.727,9.398 Z M22.975,15.239 C22.861,15.693 22.454,15.996 22.007,15.996 C21.926,15.996 21.845,15.987 21.763,15.966 C21.228,15.833 20.903,15.289 21.036,14.753 L22.035,10.752 C22.17,10.215 22.714,9.891 23.247,10.024 C23.783,10.158 24.108,10.701 23.974,11.237 L22.975,15.239 L22.975,15.239 Z M14.255,15.966 C14.174,15.987 14.092,15.996 14.012,15.996 C13.564,15.996 13.157,15.693 13.043,15.239 L12.044,11.237 C11.91,10.701 12.236,10.158 12.771,10.024 C13.305,9.891 13.849,10.215 13.983,10.752 L14.982,14.753 C15.116,15.289 14.79,15.833 14.255,15.966 L14.255,15.966 Z"></path>
                            </g>
                            </g>
                            </g>
                            </svg>
                        </span>
                        Pies
                    </a>
                    <a href="#" class="rst-table-cell">
                        <span class="rst-image-category">
                            <svg width="36px" height="36px" viewBox="0 0 36 36" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g transform="translate(-442.000000, -242.000000)" fill="#58cc65">
                            <g transform="translate(442.000000, 242.000000)">
                            <path d="M17.994,-0.006 C8.069,-0.006 -0.006,8.067 -0.006,17.99 C-0.006,27.914 8.069,35.987 17.994,35.987 C27.918,35.987 35.994,27.914 35.994,17.99 C35.994,8.067 27.918,-0.006 17.994,-0.006 L17.994,-0.006 Z M17.994,1.993 C22.228,1.993 26.072,3.658 28.937,6.353 C26.439,7.595 23.948,6.99 21.119,6.299 C18.176,5.581 14.839,4.767 11.393,6.425 C9.497,7.337 8.604,8.969 7.74,10.547 C6.592,12.644 5.578,14.479 2.3,14.894 C3.747,7.551 10.231,1.993 17.994,1.993 L17.994,1.993 Z M17.994,33.988 C9.171,33.988 1.994,26.811 1.994,17.99 C1.994,17.634 2.024,17.285 2.047,16.934 C6.743,16.527 8.262,13.759 9.495,11.507 C10.272,10.087 10.943,8.86 12.26,8.227 C15.061,6.881 17.774,7.542 20.644,8.241 C23.788,9.008 27.031,9.796 30.36,7.854 C32.629,10.615 33.994,14.146 33.994,17.99 C33.994,26.811 26.816,33.988 17.994,33.988 L17.994,33.988 Z M17.994,11.992 C14.685,11.992 11.994,14.683 11.994,17.99 C11.994,21.299 14.685,23.99 17.994,23.99 C21.302,23.99 23.994,21.299 23.994,17.99 C23.994,14.683 21.302,11.992 17.994,11.992 L17.994,11.992 Z M17.994,21.99 C15.787,21.99 13.994,20.196 13.994,17.99 C13.994,15.785 15.787,13.991 17.994,13.991 C20.2,13.991 21.994,15.785 21.994,17.99 C21.994,20.196 20.2,21.99 17.994,21.99 L17.994,21.99 Z"></path>
                            </g>
                            </g>
                            </g>
                            </svg>
                        </span>
                        Deserts
                    </a>
                </div>
            </div>
        </div>
        <div class="rst-dishes" data-background="img/post/bg_dishes.jpg">
            <div class="rst-dishes-form clearfix">
                <div class="rst-table">
                    <div class="rst-table-row">
                        <div class="rst-img-dishes rst-table-cell">
                            <img src="img/post/img-dishes.png" alt="" />
                        </div>
                        <div class="rst-dishes-content rst-table-cell">
                            <h3>Dishes from $19.95</h3>
                            <p class="description-heading">Sed in massa metus. Vestibulum eu felis fermentum.</p>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="rst-dishes-action">
                                        <h4><a href="#">Fruit salad</a></h4>
                                        <span class="rst-price">$19.95</span>
                                        <div class="rst-img-product">
                                            <svg width="20px" height="41px" viewBox="0 0 20 41" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                            <g transform="translate(-570.000000, -80.000000)" fill="#58cc65">
                                            <g transform="translate(570.000000, 80.000000)">
                                            <path d="M19.883,15.056 L15.8,36.096 C15.145,39.478 12.407,40.991 9.984,40.991 C7.562,40.991 4.824,39.478 4.168,36.096 L0.086,15.056 C-0.21,13.535 0.181,11.983 1.158,10.798 C2.099,9.657 3.475,9.003 4.934,9.003 L15.034,9.003 C16.493,9.003 17.87,9.658 18.811,10.799 C19.788,11.984 20.178,13.536 19.883,15.056 L19.883,15.056 Z M17.268,12.071 C16.707,11.393 15.893,11.003 15.034,11.003 L4.934,11.003 C4.075,11.003 3.26,11.392 2.701,12.071 C2.105,12.793 1.868,13.743 2.049,14.675 L3.664,23 L7.987,23 C8.54,23 8.987,23.448 8.987,24 C8.987,24.552 8.54,25 7.987,25 L4.052,25 L6.131,35.715 C6.6,38.133 8.5,38.991 9.984,38.991 C11.468,38.991 13.368,38.133 13.837,35.715 L14.752,31 L10.987,31 C10.434,31 9.987,30.552 9.987,30 C9.987,29.448 10.434,29 10.987,29 L15.141,29 L16.887,20 L12.987,20 C12.434,20 11.987,19.552 11.987,19 C11.987,18.448 12.434,18 12.987,18 L17.275,18 L17.92,14.675 C18.101,13.743 17.864,12.794 17.268,12.071 L17.268,12.071 Z M9.987,8 C7.23,8 4.987,5.757 4.987,3 C4.987,2.448 5.434,2 5.987,2 C6.54,2 6.987,2.448 6.987,3 C6.987,4.654 8.333,6 9.987,6 C11.641,6 12.987,4.654 12.987,3 C12.987,2.448 13.434,2 13.987,2 C14.54,2 14.987,2.448 14.987,3 C14.987,5.757 12.744,8 9.987,8 L9.987,8 Z M9.987,5 C9.434,5 8.987,4.552 8.987,4 L8.987,1 C8.987,0.448 9.434,0 9.987,0 C10.54,0 10.987,0.448 10.987,1 L10.987,4 C10.987,4.552 10.54,5 9.987,5 L9.987,5 Z"></path>
                                            </g>
                                            </g>
                                            </g>
                                            </svg>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="rst-dishes-action">
                                        <h4><a href="#">Neapolitan pizza</a></h4>
                                        <span class="rst-price">$22</span>
                                        <div class="rst-img-product">
                                            <svg width="22px" height="38px" viewBox="0 0 22 38" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                            <g transform="translate(-209.000000, -241.000000)" fill="#58cc65">
                                            <g transform="translate(209.000000, 241.000000)">
                                            <path d="M17.679,23.304 C17.226,25.021 16.265,26.534 14.981,27.56 L14.981,31.338 C16.176,31.907 16.98,33.13 16.98,34.5 C16.98,36.43 15.41,38 13.481,38 C12.515,38 11.622,37.601 10.981,36.946 C10.339,37.601 9.446,38 8.481,38 C6.551,38 4.981,36.43 4.981,34.5 C4.981,33.13 5.785,31.907 6.981,31.338 L6.981,27.56 C5.696,26.534 4.735,25.021 4.283,23.305 C1.583,20.851 -0.019,17.026 -0.019,13 C-0.019,5.832 4.915,0 10.981,0 C17.046,0 21.98,5.832 21.98,13 C21.98,17.025 20.378,20.85 17.679,23.304 L17.679,23.304 Z M8.481,36 C9.191,36 9.809,35.496 9.949,34.801 C10.044,34.335 10.454,34 10.93,34 L11.031,34 C11.507,34 11.917,34.335 12.012,34.801 C12.152,35.496 12.771,36 13.481,36 C14.308,36 14.981,35.327 14.981,34.5 C14.981,33.79 14.477,33.171 13.781,33.03 C13.316,32.936 12.981,32.526 12.981,32.05 L12.981,28 L8.981,28 L8.981,32.05 C8.981,32.526 8.646,32.936 8.18,33.03 C7.485,33.171 6.981,33.79 6.981,34.5 C6.981,35.327 7.654,36 8.481,36 L8.481,36 Z M10.981,2 C6.018,2 1.981,6.935 1.981,13 C1.981,16.571 3.42,19.937 5.83,22.003 C5.994,22.144 6.109,22.334 6.155,22.545 C6.473,23.967 7.244,25.193 8.271,26 L13.69,26 C14.717,25.193 15.488,23.967 15.806,22.545 C15.852,22.334 15.967,22.144 16.13,22.003 C18.541,19.937 19.98,16.571 19.98,13 C19.98,6.935 15.943,2 10.981,2 L10.981,2 Z"></path>
                                            </g>
                                            </g>
                                            </g>
                                            </svg>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a href="#" class="btn btn-lg btn-success">Browse menu</a>
                            <a href="#" class="rst-down"><i class="fa fa-chevron-down"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="rst-newsletter black">
            <strong><span>Subscribe to our newsletter</span></strong>
            <form action="/"><input type="text" value="" class="has-success" placeholder="Your Email" /></form>
        </div>

        <div class="container">
            <br /><br />
            <div class="text-center">
                <h3>Book a table</h3>
                <p class="description-heading">Sed in massa metus. Vestibulum eu felis fermentum.</p>
            </div>
            <form class="rst-form-input rst-book" action="/">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="form-group">
                            <input type="text" placeholder="Full name" class="form-control" name="name">
                        </div>
                        <div class="form-group">
                            <input type="text" placeholder="Month" class="form-control" name="month">
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <input type="email" placeholder="Your email" class="form-control" name="email">
                        </div>
                        <div class="form-group">
                            <input type="text" placeholder="Day" class="form-control" name="day">
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <input type="text" placeholder="Phone" class="form-control" name="phone">
                        </div>
                        <div class="form-group">
                            <select name="" class="form-control control-inline">
                                <option value="">Hour</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                            <select name="" class="form-control control-inline">
                                <option value="">Min</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                            <select name="" class="form-control control-inline">
                                <option value="AM">AM</option>
                                <option value="PM">PM</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <select name="" class="form-control">
                                <option value="">Numbers guests</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="text" placeholder="Comments" class="form-control" name="comments">
                        </div>
                    </div>
                </div>
                <div class="rst-form-reservation text-center">
                    <input type="submit" class="btn btn-lg btn-danger" value="Book a table">
                </div>
            </form>

            <br /><br /><hr /><br /><br />
            <div class="rst-digits rst-table">
                <div class="rst-table-row">
                    <a class="rst-table-cell" href="#">
                        <svg xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 40 31" height="31px" width="40px">
                        <g fill-rule="evenodd" fill="none" stroke-width="1" stroke="none">
                        <g fill="#58cc65" transform="translate(-320.000000, -85.000000)">
                        <g transform="translate(320.000000, 85.000000)">
                        <path d="M19.984,0 C10.046,0 -0.016,2.061 -0.016,6 C-0.016,19.785 8.956,31 19.984,31 C31.012,31 39.984,19.785 39.984,6 C39.984,2.061 29.922,0 19.984,0 L19.984,0 Z M19.984,29 C10.819,29 3.236,20.198 2.129,8.863 C5.611,10.932 12.829,12 19.984,12 C27.139,12 34.357,10.932 37.839,8.863 C36.732,20.198 29.149,29 19.984,29 L19.984,29 Z M19.984,10 C8.338,10 1.984,7.357 1.984,6 C1.984,4.643 8.338,2 19.984,2 C31.63,2 37.984,4.643 37.984,6 C37.984,7.357 31.63,10 19.984,10 L19.984,10 Z"/>
                        </g>
                        </g>
                        </g>
                        </svg>
                        <span class="rst-count">35</span>
                        Soups
                    </a>
                    <a class="rst-table-cell" href="#">
                        <svg xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 22 38" height="38px" width="22px">
                        <g fill-rule="evenodd" fill="none" stroke-width="1" stroke="none">
                        <g fill="#58cc65" transform="translate(-209.000000, -241.000000)">
                        <g transform="translate(209.000000, 241.000000)">
                        <path d="M17.679,23.304 C17.226,25.021 16.265,26.534 14.981,27.56 L14.981,31.338 C16.176,31.907 16.98,33.13 16.98,34.5 C16.98,36.43 15.41,38 13.481,38 C12.515,38 11.622,37.601 10.981,36.946 C10.339,37.601 9.446,38 8.481,38 C6.551,38 4.981,36.43 4.981,34.5 C4.981,33.13 5.785,31.907 6.981,31.338 L6.981,27.56 C5.696,26.534 4.735,25.021 4.283,23.305 C1.583,20.851 -0.019,17.026 -0.019,13 C-0.019,5.832 4.915,0 10.981,0 C17.046,0 21.98,5.832 21.98,13 C21.98,17.025 20.378,20.85 17.679,23.304 L17.679,23.304 Z M8.481,36 C9.191,36 9.809,35.496 9.949,34.801 C10.044,34.335 10.454,34 10.93,34 L11.031,34 C11.507,34 11.917,34.335 12.012,34.801 C12.152,35.496 12.771,36 13.481,36 C14.308,36 14.981,35.327 14.981,34.5 C14.981,33.79 14.477,33.171 13.781,33.03 C13.316,32.936 12.981,32.526 12.981,32.05 L12.981,28 L8.981,28 L8.981,32.05 C8.981,32.526 8.646,32.936 8.18,33.03 C7.485,33.171 6.981,33.79 6.981,34.5 C6.981,35.327 7.654,36 8.481,36 L8.481,36 Z M10.981,2 C6.018,2 1.981,6.935 1.981,13 C1.981,16.571 3.42,19.937 5.83,22.003 C5.994,22.144 6.109,22.334 6.155,22.545 C6.473,23.967 7.244,25.193 8.271,26 L13.69,26 C14.717,25.193 15.488,23.967 15.806,22.545 C15.852,22.334 15.967,22.144 16.13,22.003 C18.541,19.937 19.98,16.571 19.98,13 C19.98,6.935 15.943,2 10.981,2 L10.981,2 Z"/>
                        </g>
                        </g>
                        </g>
                        </svg>
                        <span class="rst-count">18</span>
                        Main courses
                    </a>
                    <a class="rst-table-cell" href="#">
                        <svg xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 25 45" height="45px" width="25px">
                        <g fill-rule="evenodd" fill="none" stroke-width="1" stroke="none">
                        <g fill="#58cc65" transform="translate(-331.000000, -236.000000)">
                        <g transform="translate(332.000000, 236.000000)">
                        <path d="M8.489,15 C9.318,15 9.99,14.328 9.99,13.5 C9.99,12.672 9.318,12 8.489,12 C7.661,12 6.989,12.672 6.989,13.5 C6.989,14.328 7.661,15 8.489,15 L8.489,15 Z M14.49,17 C15.319,17 15.991,16.329 15.991,15.5 C15.991,14.672 15.319,14 14.49,14 C13.662,14 12.99,14.672 12.99,15.5 C12.99,16.329 13.662,17 14.49,17 L14.49,17 Z M15.491,11 C16.32,11 16.991,10.328 16.991,9.5 C16.991,8.672 16.32,8 15.491,8 C14.662,8 13.99,8.672 13.99,9.5 C13.99,10.328 14.662,11 15.491,11 L15.491,11 Z M13.99,38 C13.437,38 12.99,38.448 12.99,39 L12.99,42.2 C12.99,42.634 12.532,43 11.99,43 C11.448,43 10.99,42.634 10.99,42.2 L10.99,39 C10.99,38.448 10.542,38 9.99,38 C9.437,38 8.989,38.448 8.989,39 L8.989,42.2 C8.989,43.744 10.335,45 11.99,45 C13.645,45 14.991,43.744 14.991,42.2 L14.991,39 C14.991,38.448 14.543,38 13.99,38 L13.99,38 Z M23.375,21.076 C23.001,20.922 22.571,21.006 22.285,21.293 L21.566,22.012 C21.488,21.839 21.405,21.666 21.307,21.5 C21.757,20.737 21.992,19.886 21.992,19 C21.992,18.114 21.757,17.263 21.307,16.5 C21.757,15.737 21.992,14.886 21.992,14 C21.992,12.758 21.526,11.578 20.694,10.664 C20.892,10.124 20.992,9.566 20.992,9 C20.992,6.556 19.229,4.516 16.907,4.084 C16.475,1.763 14.435,0 11.99,0 C9.545,0 7.505,1.763 7.073,4.084 C4.751,4.516 2.988,6.556 2.988,9 C2.988,9.566 3.088,10.124 3.286,10.664 C2.454,11.578 1.988,12.758 1.988,14 C1.988,14.886 2.223,15.737 2.673,16.5 C2.223,17.263 1.988,18.114 1.988,19 C1.988,19.886 2.223,20.737 2.673,21.5 C2.571,21.673 2.484,21.852 2.404,22.033 L1.693,21.322 C1.406,21.037 0.978,20.952 0.603,21.106 C0.228,21.26 -0.015,21.625 -0.015,22.029 L-0.015,29.016 C-0.015,33.42 3.569,37.002 7.973,37.002 L14.961,37.002 C14.966,37.002 14.97,37 14.974,37 L15.991,37 C20.403,37 23.993,33.411 23.993,29 L23.993,22 C23.993,21.596 23.749,21.231 23.375,21.076 L23.375,21.076 Z M15.991,35 L11.404,35 L21.992,24.414 L21.992,29 C21.992,32.309 19.3,35 15.991,35 L15.991,35 Z M1.986,24.443 L10.56,33.016 L8.573,35.002 L7.973,35.002 C4.672,35.002 1.986,32.317 1.986,29.016 L1.986,24.443 L1.986,24.443 Z M4.667,20.872 C4.223,20.322 3.988,19.675 3.988,19 C3.988,18.325 4.223,17.678 4.667,17.129 C4.963,16.762 4.963,16.238 4.667,15.872 C4.223,15.322 3.988,14.675 3.988,14 C3.988,13.104 4.399,12.258 5.117,11.679 C5.493,11.375 5.599,10.846 5.367,10.422 C5.116,9.96 4.988,9.482 4.988,9 C4.988,7.346 6.334,6 7.989,6 C8.542,6 8.989,5.552 8.989,5 C8.989,3.346 10.335,2 11.99,2 C13.645,2 14.991,3.346 14.991,5 C14.991,5.552 15.438,6 15.991,6 C17.645,6 18.992,7.346 18.992,9 C18.992,9.482 18.864,9.96 18.613,10.422 C18.381,10.846 18.486,11.375 18.863,11.679 C19.581,12.258 19.992,13.104 19.992,14 C19.992,14.675 19.757,15.322 19.313,15.872 C19.017,16.238 19.017,16.762 19.313,17.129 C19.757,17.678 19.992,18.325 19.992,19 C19.992,19.675 19.757,20.322 19.313,20.872 C19.017,21.238 19.017,21.762 19.313,22.129 C19.673,22.573 19.887,23.083 19.959,23.619 L11.974,31.602 L4.019,23.648 C4.086,23.102 4.301,22.581 4.667,22.129 C4.963,21.762 4.963,21.238 4.667,20.872 L4.667,20.872 Z"/>
                        </g>
                        </g>
                        </g>
                        </svg>
                        <span class="rst-count">20</span>
                        Side dishes
                    </a>
                    <a class="rst-table-cell" href="#">
                        <svg xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 20 41" height="41px" width="20px">
                        <g fill-rule="evenodd" fill="none" stroke-width="1" stroke="none">
                        <g fill="#58cc65" transform="translate(-570.000000, -80.000000)">
                        <g transform="translate(570.000000, 80.000000)">
                        <path d="M19.883,15.056 L15.8,36.096 C15.145,39.478 12.407,40.991 9.984,40.991 C7.562,40.991 4.824,39.478 4.168,36.096 L0.086,15.056 C-0.21,13.535 0.181,11.983 1.158,10.798 C2.099,9.657 3.475,9.003 4.934,9.003 L15.034,9.003 C16.493,9.003 17.87,9.658 18.811,10.799 C19.788,11.984 20.178,13.536 19.883,15.056 L19.883,15.056 Z M17.268,12.071 C16.707,11.393 15.893,11.003 15.034,11.003 L4.934,11.003 C4.075,11.003 3.26,11.392 2.701,12.071 C2.105,12.793 1.868,13.743 2.049,14.675 L3.664,23 L7.987,23 C8.54,23 8.987,23.448 8.987,24 C8.987,24.552 8.54,25 7.987,25 L4.052,25 L6.131,35.715 C6.6,38.133 8.5,38.991 9.984,38.991 C11.468,38.991 13.368,38.133 13.837,35.715 L14.752,31 L10.987,31 C10.434,31 9.987,30.552 9.987,30 C9.987,29.448 10.434,29 10.987,29 L15.141,29 L16.887,20 L12.987,20 C12.434,20 11.987,19.552 11.987,19 C11.987,18.448 12.434,18 12.987,18 L17.275,18 L17.92,14.675 C18.101,13.743 17.864,12.794 17.268,12.071 L17.268,12.071 Z M9.987,8 C7.23,8 4.987,5.757 4.987,3 C4.987,2.448 5.434,2 5.987,2 C6.54,2 6.987,2.448 6.987,3 C6.987,4.654 8.333,6 9.987,6 C11.641,6 12.987,4.654 12.987,3 C12.987,2.448 13.434,2 13.987,2 C14.54,2 14.987,2.448 14.987,3 C14.987,5.757 12.744,8 9.987,8 L9.987,8 Z M9.987,5 C9.434,5 8.987,4.552 8.987,4 L8.987,1 C8.987,0.448 9.434,0 9.987,0 C10.54,0 10.987,0.448 10.987,1 L10.987,4 C10.987,4.552 10.54,5 9.987,5 L9.987,5 Z"/>
                        </g>
                        </g>
                        </g>
                        </svg>
                        <span class="rst-count">55</span>
                        Salads
                    </a>
                    <a class="rst-table-cell" href="#">
                        <svg xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 36 35" height="35px" width="36px">
                        <g fill-rule="evenodd" fill="none" stroke-width="1" stroke="none">
                        <g fill="#58cc65" transform="translate(-82.000000, -242.000000)">
                        <g transform="translate(82.000000, 242.000000)">
                        <path d="M34.216,17.93 C32.524,20.844 29.871,22.948 27.001,23.696 L27.001,31.002 C27.001,33.629 22.478,35.003 18.009,35.003 C13.54,35.003 9.017,33.629 9.017,31.002 L9.017,23.696 C6.147,22.948 3.495,20.844 1.802,17.93 C0.122,15.039 -0.403,11.741 0.362,8.881 C0.937,6.734 2.226,4.992 3.994,3.978 C6.097,2.772 8.749,2.681 11.273,3.71 C12.978,1.334 15.393,-0.01 18.009,-0.01 C20.626,-0.01 23.04,1.334 24.746,3.71 C27.27,2.681 29.923,2.771 32.024,3.978 C33.792,4.992 35.082,6.734 35.656,8.881 C36.421,11.74 35.896,15.038 34.216,17.93 L34.216,17.93 Z M33.727,9.398 C33.284,7.746 32.352,6.472 31.031,5.714 C29.28,4.709 26.977,4.775 24.837,5.867 C24.37,6.104 23.799,5.942 23.528,5.494 C22.184,3.268 20.172,1.991 18.009,1.991 C15.846,1.991 13.835,3.268 12.491,5.494 C12.219,5.942 11.646,6.104 11.182,5.867 C10.061,5.295 8.88,4.993 7.768,4.993 C6.757,4.993 5.821,5.235 4.987,5.714 C3.666,6.472 2.734,7.746 2.292,9.399 C1.664,11.747 2.115,14.49 3.529,16.924 C5.089,19.609 7.575,21.469 10.179,21.9 C10.662,21.98 11.015,22.398 11.015,22.887 L11.015,31.002 C11.015,31.583 13.466,33.002 18.009,33.002 C22.552,33.002 25.003,31.583 25.003,31.002 L25.003,22.887 C25.003,22.398 25.357,21.98 25.839,21.9 C28.443,21.469 30.929,19.609 32.489,16.924 C33.904,14.49 34.355,11.747 33.727,9.398 L33.727,9.398 Z M22.975,15.239 C22.861,15.693 22.454,15.996 22.007,15.996 C21.926,15.996 21.845,15.987 21.763,15.966 C21.228,15.833 20.903,15.289 21.036,14.753 L22.035,10.752 C22.17,10.215 22.714,9.891 23.247,10.024 C23.783,10.158 24.108,10.701 23.974,11.237 L22.975,15.239 L22.975,15.239 Z M14.255,15.966 C14.174,15.987 14.092,15.996 14.012,15.996 C13.564,15.996 13.157,15.693 13.043,15.239 L12.044,11.237 C11.91,10.701 12.236,10.158 12.771,10.024 C13.305,9.891 13.849,10.215 13.983,10.752 L14.982,14.753 C15.116,15.289 14.79,15.833 14.255,15.966 L14.255,15.966 Z"/>
                        </g>
                        </g>
                        </g>
                        </svg>
                        <span class="rst-count">85</span>
                        Pies
                    </a>
                    <a class="rst-table-cell" href="#">
                        <svg xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg" version="1.1" viewBox="0 0 36 36" height="36px" width="36px">
                        <g fill-rule="evenodd" fill="none" stroke-width="1" stroke="none">
                        <g fill="#58cc65" transform="translate(-442.000000, -242.000000)">
                        <g transform="translate(442.000000, 242.000000)">
                        <path d="M17.994,-0.006 C8.069,-0.006 -0.006,8.067 -0.006,17.99 C-0.006,27.914 8.069,35.987 17.994,35.987 C27.918,35.987 35.994,27.914 35.994,17.99 C35.994,8.067 27.918,-0.006 17.994,-0.006 L17.994,-0.006 Z M17.994,1.993 C22.228,1.993 26.072,3.658 28.937,6.353 C26.439,7.595 23.948,6.99 21.119,6.299 C18.176,5.581 14.839,4.767 11.393,6.425 C9.497,7.337 8.604,8.969 7.74,10.547 C6.592,12.644 5.578,14.479 2.3,14.894 C3.747,7.551 10.231,1.993 17.994,1.993 L17.994,1.993 Z M17.994,33.988 C9.171,33.988 1.994,26.811 1.994,17.99 C1.994,17.634 2.024,17.285 2.047,16.934 C6.743,16.527 8.262,13.759 9.495,11.507 C10.272,10.087 10.943,8.86 12.26,8.227 C15.061,6.881 17.774,7.542 20.644,8.241 C23.788,9.008 27.031,9.796 30.36,7.854 C32.629,10.615 33.994,14.146 33.994,17.99 C33.994,26.811 26.816,33.988 17.994,33.988 L17.994,33.988 Z M17.994,11.992 C14.685,11.992 11.994,14.683 11.994,17.99 C11.994,21.299 14.685,23.99 17.994,23.99 C21.302,23.99 23.994,21.299 23.994,17.99 C23.994,14.683 21.302,11.992 17.994,11.992 L17.994,11.992 Z M17.994,21.99 C15.787,21.99 13.994,20.196 13.994,17.99 C13.994,15.785 15.787,13.991 17.994,13.991 C20.2,13.991 21.994,15.785 21.994,17.99 C21.994,20.196 20.2,21.99 17.994,21.99 L17.994,21.99 Z"/>
                        </g>
                        </g>
                        </g>
                        </svg>
                        <span class="rst-count">125</span>
                        Deserts
                    </a>
                </div>
            </div>
            <br /><br /><br />
        </div>

        <div class="rst-visitors" data-background="img/post/bg_visitors.jpg">
            <div class="container">
                <div class="text-center">
                    <h3>Happy visitors</h3>
                    <p class="description-heading">Sed in massa metus. Vestibulum eu felis fermentum.</p>
                </div>
                <div class="rst-testimonials rst-visitors-slider">
                    <ul class="bxslider">
                        <li class="rst-author rst-author-img">
                            <div class="rst-author-inner">
                                <div class="rst-avatar-author"><img src="img/post/author-2.png" class="img-circle" alt="" /></div>
                                <h3>Sam Doe</h3>
                                <p>“Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sit amet vehicula mauris. Integer vitae magna massa. Suspendisse potenti. Ut ultricies nisi sed felis molestie gravida. Integer sed magna commodo, luctus sapien accumsan, dictum purus. Mauris nec purus ac dui laoreet ullamcorper ultrices non libero.”</p>
                            </div>
                        </li>
                        <li class="rst-author rst-author-img">
                            <div class="rst-author-inner">
                                <div class="rst-avatar-author"><img src="img/post/author-3.png" class="img-circle" alt="" /></div>
                                <h3>Lisa Doe</h3>
                                <p>“Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sit amet vehicula mauris. Integer vitae magna massa. Suspendisse potenti. Ut ultricies nisi sed felis molestie gravida. Integer sed magna commodo, luctus sapien accumsan, dictum purus. Mauris nec purus ac dui laoreet ullamcorper ultrices non libero.”</p>
                            </div>
                        </li>
                        <li class="rst-author rst-author-img">
                            <div class="rst-author-inner">
                                <div class="rst-avatar-author"><img src="img/post/author-1.png" class="img-circle" alt="" /></div>
                                <h3>John Doe</h3>
                                <p>“Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sit amet vehicula mauris. Integer vitae magna massa. Suspendisse potenti. Ut ultricies nisi sed felis molestie gravida. Integer sed magna commodo, luctus sapien accumsan, dictum purus. Mauris nec purus ac dui laoreet ullamcorper ultrices non libero.”</p>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="text-center">
                    <a class="btn btn-lg btn-success" href="#">Browse menu</a>
                    <a class="rst-down while" href="#"><i class="fa fa-chevron-down"></i></a>
                </div>
            </div>
        </div>

        <div class="rst-newsletter black">
            <strong><span>Subscribe to our newsletter</span></strong>
            <form action="/"><input type="text" value="" class="has-success" placeholder="Your Email" /></form>
        </div>

        <div class="container">
            <div class="text-center">
                <br /><br />
                <h3>Team</h3>
                <p class="description-heading">Sed in massa metus. Vestibulum eu felis fermentum.</p>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="rst-team-item">
                        <div class="rst-thumbnail">
                            <img src="img/post/team-thumb.jpg" alt="" />
                            <div class="rst-opacity"><a class="rst-popup-term" href="#team-01">+</a></div>
                        </div>
                        <div class="rst-team-info">
                            <h3>John Doe</h3>
                            <p>Chef</p>
                        </div>
                    </div>
                    <div id="team-01" class="popup-term clearfix">
                        <div class="rst-team-image">
                            <img src="img/post/team01.jpg" alt="" />
                            <div class="substate"><span>I am creative director.</span></div>
                        </div>
                        <div class="rst-team-info">
                            <h3>John Doe</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vestibulum ligula fringilla, volutpat augue eget, condimentum libero. Nulla ligula velit, pulvinar in dictum a, venenatis eu sem. Donec vitae magna neque. Quisque tristique ipsum et malesuada porta. Vivamus eros nisi, congue sed scelerisque non, blandit vitae diam. Phasellus sem arcu, vulputate ut mauris et, faucibus venenatis ipsum. </p>
                            <div class="rst-team-social">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-dribbble"></i></a>
                            </div>
                            <br /><hr /><br />
                            <ul class="rst-term-slider bxslider">
                                <li>
                                    <a data-team-image="img/post/team01.jpg" href="#">
                                        <img class="img-circle" src="img/post/team-thumb01.jpg" alt="" />
                                        <span class="img-circle rst-opacity"></span>
                                    </a>
                                </li>
                                <li>
                                    <a data-team-image="img/post/term02.jpg" href="#">
                                        <img class="img-circle" src="img/post/team-thumb02.jpg" alt="" />
                                        <span class="img-circle rst-opacity"></span>
                                    </a>
                                </li>
                                <li>
                                    <a data-team-image="img/post/term03.jpg" href="#">
                                        <img class="img-circle" src="img/post/team-thumb03.jpg" alt="" />
                                        <span class="img-circle rst-opacity"></span>
                                    </a>
                                </li>
                                <li>
                                    <a data-team-image="img/post/team01.jpg" href="#">
                                        <img class="img-circle" src="img/post/team-thumb01.jpg" alt="" />
                                        <span class="img-circle rst-opacity"></span>
                                    </a>
                                </li>
                                <li>
                                    <a data-team-image="img/post/term02.jpg" href="#">
                                        <img class="img-circle" src="img/post/team-thumb02.jpg" alt="" />
                                        <span class="img-circle rst-opacity"></span>
                                    </a>
                                </li>
                                <li>
                                    <a data-team-image="img/post/term03.jpg" href="#">
                                        <img class="img-circle" src="img/post/team-thumb03.jpg" alt="" />
                                        <span class="img-circle rst-opacity"></span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="rst-team-item">
                        <div class="rst-thumbnail">
                            <img src="img/post/team-thumb04.jpg" alt="" />
                            <div class="rst-opacity"><a class="rst-popup-term" href="#team-01">+</a></div>
                        </div>
                        <div class="rst-team-info">
                            <h3>John Doe</h3>
                            <p>Chef</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="rst-team-item">
                        <div class="rst-thumbnail">
                            <img src="img/post/team-thumb05.jpg" alt="" />
                            <div class="rst-opacity"><a class="rst-popup-term" href="#team-01">+</a></div>
                        </div>
                        <div class="rst-team-info">
                            <h3>John Doe</h3>
                            <p>Chef</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="text-center">
                <a href="#" class="btn btn-lg btn-danger">Browse menu</a>
                <a href="#" class="rst-down"><i class="fa fa-chevron-down"></i></a>
            </div>
        </div>

        <br /><br /><hr /><br /><br />
        <div class="container"><!--- container -->
            <div class="row"><!--- row -->
                <div class="col-sm-4">
                    <h3>About</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac eros eu sem pulvinar cursus in vel dui. Vivamus condimentum orci facilisis ligula congue, eu egestas odio lacinia commodo. </p>

                    <p>In dignissim cursus varius. Donec venenatis dapibus ullamcorper. In nec sapien elit. Maecenas mattis ornare ligula, eu feugiat dui commodo in. Duis vehicula sagittis dolor in commodo. </p>
                </div>
                <div class="col-sm-4">
                    <h3>Latest news</h3>
                    <ul class="rst-latest-news">
                        <li>
                            <h4><a href="#">Lorem ipsum dolor sit amet, consectetur</a></h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac eros eu sem pulvinar cursus
                                in vel dui. Phasellus vulputate nulla ut nibh viverra vehicula.</p>
                            <span class="date">12 november, 2014</span>
                        </li>
                        <li>
                            <h4><a href="#">Lorem ipsum dolor sit amet, consectetur</a></h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac eros eu sem pulvinar cursus
                                in vel dui. Phasellus vulputate nulla ut nibh viverra vehicula.</p>
                            <span class="date">12 november, 2014</span>
                        </li>
                    </ul>
                </div>
                <div class="col-sm-4">
                    <h3>Happy hours</h3>
                    <div class="rst-happy-hours">
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Monday</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="In stock" data-placement="bottom" data-toggle="tooltip"></span> 07.00 — 23.00</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Thuesday</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="In stock" data-placement="bottom" data-toggle="tooltip"></span> 07.00 — 21.00</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Wednesday</div>
                            <div class="pull-right hours"><span class="rst-stock low-stock" data-original-title="Low stock" data-placement="bottom" data-toggle="tooltip"></span> 18.00 — 21.00</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Thurstday</div>
                            <div class="pull-right hours"><span class="rst-stock low-stock" data-original-title="Low stock" data-placement="bottom" data-toggle="tooltip"></span> 18.00 — 22.00</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Friday</div>
                            <div class="pull-right hours"><span class="rst-stock in-stock" data-original-title="In stock" data-placement="bottom" data-toggle="tooltip"></span> 07.00 — 22.00</div>
                        </div>
                        <div class="clearfix rst-happy-date">
                            <div class="pull-left date">Saturday & Sunday</div>
                            <div class="pull-right hours"><span class="rst-stock low-stock" data-original-title="Low stock" data-placement="bottom" data-toggle="tooltip"></span> 18.00 — 24.00</div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--- end row -->

        <!--- Newsletter -->
        <div class="rst-newsletter">
            <strong><span>Subscribe to our newsletter</span></strong>
            <form action="/"><input type="text" placeholder="Your Email" class="has-success" value=""></form>
        </div><!--- end Newsletter -->

        <!--- Contact Detail/Map -->
        <div class="rst-contact">
            <div id="map-canvas" class="rst-contact-maps" data-zoom="15" data-center="10.731688,122.5505356"> </div>
            <div class="rst-opacity"></div>
            <div class="rst-contact-add">
                <h3>Where are we?</h3>
                <p>Contact is right now!</p>
                <div class="rst-address">

                    <i class="fa fa-map-marker"></i>
                    <select name="" class="rst-drop-location">
                        <option value="Street 15, Manhattan, New York">USA</option>
                        <option value="Street 115, India">India</option>
                        <option value="Street 253, Russia">Russia</option>
                    </select>
                    <div class="address">Street 15, Manhattan, New York</div>
                </div>
            </div>
        </div><!--- end Contact Detail/Map -->
    </div><!--- end container -->
</body>
