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
                                <li><img style="width: 260px;" src="/img/post/gallery10.jpg" alt="Foto" /></li>
                            </ul>
                        </div>
                        
                    </div>
                    <div class="col-sm-7">

                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa  fa-cutlery"></i></span>
                            <input class="form-control" type="text" placeholder="Nombre" maxlength="50">
                        </div>
                        <br />
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa  fa-usd fa-lg"></i></span>
                            <input class="form-control" type="text" placeholder="Precio" maxlength="50">
                        </div>
                        <br />
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-comment"></i></span>
                            <textarea class="form-control" type="text" placeholder="Descripción" maxlength="200" rows="5"></textarea>
                        </div>
                        <br />
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa  fa-tag"></i></span>
                            <select class="form-control">
                                <option value="">Categor&iacute;a</option>
                                <option value="2">2</option>
                                <option value="1">1</option>
                            </select>
                        </div>
                        <br />
                        <div class="rst-product-info main-product-detail">
                            <form action="/">
                                <button class="btn btn-success btn-lg"><i class="fa fa-save fa-lg"></i> Guardar</button>
                            </form>
                            <hr />
                        </div>
                    </div>
                </div>
               <button class="btn btn-primary"><i class="fa fa-camera-retro fa-lg" aria-hidden="true"></i> Im&aacute;gen</button>
                
            </div>
        </div><!-- End Content -->
    </body>
</html>
