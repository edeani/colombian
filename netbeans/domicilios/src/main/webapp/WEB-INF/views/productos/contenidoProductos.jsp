<%-- 
    Document   : contenidoProductos
    Created on : 12-ago-2016, 12:55:45
    Author     : edeani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header id="header">
			<div class="container">
				<div class="rst-table">
					<div class="rst-table-row">
						<a href="index.html" class="rst-logo rst-table-cell"><img src="/img/logo.png" alt="" /></a>
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
								<li><a href="index.html">Home</a></li>
								<li class="menu-item-has-children">
									<a href="#">Pages</a>
									<ul class="sub-menu">
										<li><a href="about.html">About</a></li>
										<li class="active"><a href="products_list.html">Store</a></li>
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
												<span class="input-group-addon"><img src="/img/mail.png" alt="" /></span>
												<input class="form-control" type="email" placeholder="Your email" />
											</div>
											<div class="input-group">
												<span class="input-group-addon"><img src="/img/unlock.png" alt="" /></span>
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

		<div id="content">
		<div id="rst-banner" data-background="/img/post/banner02.jpg">
			<div class="container">
				<div class="rst-inner-banner clearfix">
					<div class="rst-banner-content pull-left">
						<h1>Store</h1>
						<p>We opened. Tasty food &amp; drinks.</p>
					</div>
					<ul class="breadcrumb pull-right">
						<li><a href="index.html">Home</a></li>
						<li><a href="products_list.html">Store</a> </li>
					</ul>
				</div>
			</div>
		</div><!-- Banner -->
		<div class="container rst-main-content">
			<div class="text-center">
				<p>Sed at dictum aliquam porta felis et semper venenatis. Praesent condimentum laoreet leo, sit amet luctus <br /> est semper non. Mauris tincidunt lorem vel lorem venenatis volutpat. </p>
			</div>
			<div class="rst-product-list">
				<div class="row">
					<div class="col-sm-3">
						<h4>Price</h4>
						<div class="rst-slider">
							<span class="price-min">$0</span>
							<input type="text" class="slider-input" value="0" data-slider-text-after="$" data-slider-min="0" data-slider-max="1200" data-slider-step="1" />
							<span class="price-max">$1200</span>
						</div>
						<hr />
						<h4>Discount</h4>
						<div class="rst-slider">
							<span class="price-min">50%</span>
							<input type="text" class="slider-input" value="0" data-slider-text-before="%" data-slider-min="50" data-slider-max="90" data-slider-step="1" />
							<span class="price-max">90%</span>
						</div>
						<hr />
						<h4>Categories</h4>
						<ul class="list-category">
							<li class="active"><a href="#">Soups</a></li>
							<li><a href="#">Main courses</a></li>
							<li><a href="#">Side dishes</a></li>
							<li><a href="#">Salads</a></li>
							<li><a href="#">Pies</a></li>
							<li><a href="#">Deserts</a></li>
						</ul>
						<hr />
						<h4>Tags</h4>
						<ul class="list-tag">
							<li class="active"><a href="#">New</a></li>
							<li><a href="#">Bestsellers</a></li>
							<li><a href="#">Action</a></li>
						</ul>
					</div>
					<div class="col-sm-9">
						<div class="row">
							<div class="col-sm-3">
								<form action="/" class="rst-form-input">
									<div class="form-group">
										<select class="form-control" name="country" id="InputCountry">
											<option value="Popular">Popular</option>
											<option value="Price">Price</option>
											<option value="View">View</option>
										</select>
									</div>
								</form>
							</div>
							<div class="col-sm-9">
								<div class="rst-view">
									<a href="#" class="rst-tile"></a>
									<a href="#" class="rst-list active"></a>
								</div>
							</div>
						</div>
						
						<div class="row product-list view-list">
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery01.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Hamburger</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery02.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Sushi</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock low-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="Low stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery03.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Coffee desert</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock no-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="No stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery04.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Pizza</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery08.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Berries</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery10.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Chicken</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery11.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Fish</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery12.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Onion</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery13.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Fruit salade</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery14.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">Cup of coffee</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery15.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">kebab</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
							<div class="col-sm-12 product-item">
								<div class="rst-thumbnail">
									<a href="products_detail.html"><img src="/img/post/gallery01.jpg" alt="" /></a>
									<div class="rst-hover">
										Main courses
										<a href="#" class="addtocard"></a>
									</div>
								</div>
								<div class="rst-product-info">
									<h3><a href="products_detail.html">hamburger</a></h3>
									<span class="price-product"><sup>$</sup> 135</span>
									<span class="rst-stock in-stock" data-toggle="tooltip" data-placement="bottom" data-original-title="In stock"></span>
									<hr />
									<div class="rst-product-content">
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut auctor dolor scelerisque lorem posuere, sit amet malesuada justo rhoncus. Nulla nunc ipsum, suscipit vitae tincidunt eget, tempor id quam. Ut ultricies consequat risus, quis fringilla libero placerat a. Nullam ut orci neque. Donec vestibulum facilisis semper. Curabitur varius mi in egestas ultrices. In vel viverra metus.</p>
									</div>
								</div>
							</div>
						</div><!-- End Product List-->
						<nav class="wp-pagenavi">
							<a class="btn btn-sm btn-success page-prev" href="#">Previous</a>
							<span class="btn btn-sm btn-success page-numbers current">1</span>
							<a class="page-numbers" href="#">2</a>
							<a class="page-numbers" href="#">3</a>
							<a class="page-numbers" href="#">4</a>
							<a class="page-numbers" href="#">5</a>
							<a class="btn btn-sm btn-success page-next" href="#">Next</a>
						</nav>
					</div>
				</div>
			</div>
		</div><!-- End Content -->
    </body>
</html>
