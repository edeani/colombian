<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Datos Producto</title>
        <!-- Custom Css     ================================================== -->
        <link rel="stylesheet" type="text/css" href="/css/rs-wp-v1.2.css">
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <link rel="stylesheet" type="text/css" href="/css/responsive.css">

        <!-- Fonts         ================================================== -->
        <link href='fonts/stylesheet.css' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div id="content">
            <c:if test="${estado ne 'N'}">
            <div id="rst-banner" data-background="/img/post/banner02.jpg">
                <div class="container">
                    <div class="rst-inner-banner clearfix">
                        <div class="rst-banner-content pull-left">
                            <h1>Sushi</h1>
                            <p>We opened. Tasty food &amp; drinks.</p>
                        </div>
                        <ul class="breadcrumb pull-right">
                            <li><a href="index.html">Home</a></li>
                            <li><a href="products_list.html">Store</a> </li>
                            <li>Sushi</li>
                        </ul>
                    </div>
                </div>
            </div><!-- Banner -->
            </c:if>
            <div class="container rst-main-content rst-product-detail">

                <div class="row">
                    <div class="col-sm-5">
                        <div class="rst-product-images">
                            <ul class="bxslider">
                                <li><img src="/img/post/product-slider.jpg" alt="" /></li>
                                <li><img src="/img/post/product-slider.jpg" alt="" /></li>
                                <li><img src="/img/post/product-slider.jpg" alt="" /></li>
                            </ul>
                            <div id="product-pager" class="bx-pager">
                                <a data-slide-index="0" href=""><img alt="" src="/img/post/product-slider-thumb.jpg" /></a>
                                <a data-slide-index="1" href=""><img alt="" src="/img/post/product-slider-thumb.jpg" /></a>
                                <a data-slide-index="2" href=""><img alt="" src="/img/post/product-slider-thumb.jpg" /></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-7">
                        <div class="rst-product-info main-product-detail">
                            <div class="price-product"><sup>$</sup> 135</div>
                            <span class="rst-star"><span style="width: 80%"></span></span>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
                            <p>Donec semper, dui in ultrices sollicitudin, risus tellus dignissim purus, in tempor elit risus vel purus. Maecenas faucibus mi id felis ullamcorper, at bibendum urna tempus. </p>
                            <p style="color:#abb1b8">Side dishes</p>
                            <hr />
                            <p class="stock-status"><span class="rst-stock in-stock"></span> In stock</p>
                            <form action="/">
                                <button class="btn btn-success btn-lg btn-add-to-cart">Add to cart</button>
                                <div class="inline qty-large quantity"><input type="number" step="1" min="0"  name="cart[8fe0093bb30d6f8c31474bd0764e6ac0][qty]" value="2" title="Qty" class="input-text qty text" size="4" /></div>
                            </form>
                            <hr />
                            <div class="rst-share">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-google-plus"></i></a>
                                <a href="#"><i class="fa fa-pinterest-p"></i></a>
                                <a href="#"><i class="fa fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>

             
                <br /><br /><hr /><br /><br />
                <div class="rst-recent-products">
                    <h3>Related products</h3>
                    <div class="row product-list view-tile">
                        <div class="product-item col-sm-3">
                            <div class="rst-thumbnail">
                                <a href="products_detail.html"><img alt="" src="/img/post/gallery01.jpg"></a>
                                <div class="rst-hover">
                                    Main courses
                                    <a class="addtocard" href="#"></a>
                                </div>
                            </div>
                            <div class="rst-product-info">
                                <h3><a href="products_detail.html">Hamburger</a></h3>
                                <span class="price-product"><sup>$</sup> 135</span>
                                <span data-original-title="In stock" data-placement="bottom" data-toggle="tooltip" class="rst-stock in-stock"></span>
                                <hr>
                            </div>
                        </div>
                        <div class="product-item col-sm-3">
                            <div class="rst-thumbnail">
                                <a href="products_detail.html"><img alt="" src="/img/post/gallery02.jpg"></a>
                                <div class="rst-hover">
                                    Main courses
                                    <a class="addtocard" href="#"></a>
                                </div>
                            </div>
                            <div class="rst-product-info">
                                <h3><a href="products_detail.html">Sushi</a></h3>
                                <span class="price-product"><sup>$</sup> 135</span>
                                <span data-original-title="Low stock" data-placement="bottom" data-toggle="tooltip" class="rst-stock low-stock"></span>
                                <hr>
                            </div>
                        </div>
                        <div class="product-item col-sm-3">
                            <div class="rst-thumbnail">
                                <a href="products_detail.html"><img alt="" src="/img/post/gallery03.jpg"></a>
                                <div class="rst-hover">
                                    Main courses
                                    <a class="addtocard" href="#"></a>
                                </div>
                            </div>
                            <div class="rst-product-info">
                                <h3><a href="products_detail.html">Coffee desert</a></h3>
                                <span class="price-product"><sup>$</sup> 135</span>
                                <span data-original-title="No stock" data-placement="bottom" data-toggle="tooltip" class="rst-stock no-stock"></span>
                                <hr>
                            </div>
                        </div>
                        <div class="product-item col-sm-3">
                            <div class="rst-thumbnail">
                                <a href="products_detail.html"><img alt="" src="/img/post/gallery04.jpg"></a>
                                <div class="rst-hover">
                                    Main courses
                                    <a class="addtocard" href="#"></a>
                                </div>
                            </div>
                            <div class="rst-product-info">
                                <h3><a href="products_detail.html">Pizza</a></h3>
                                <span class="price-product"><sup>$</sup> 135</span>
                                <span data-original-title="In stock" data-placement="bottom" data-toggle="tooltip" class="rst-stock in-stock"></span>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- End Content -->
    </body>
</html>
