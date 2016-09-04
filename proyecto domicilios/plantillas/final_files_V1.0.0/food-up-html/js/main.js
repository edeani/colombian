//
// This is The Scripts used for Simply Theme
//

//Google Map
if( jQuery('.rst-contact-maps').length && jQuery('.rst-contact-maps').attr('data-center') != '' ) {
	var zoom = jQuery('.rst-contact-maps').attr('data-zoom');
	if( zoom == '' ) zoom = 10;
	
	var rs_location = jQuery('#map-canvas').attr('data-center');
	var rs_location_x = rs_location.substring(0, rs_location.indexOf(",")); 
	var rs_location_y = rs_location.substring(rs_location.indexOf(",")+1);
  
	function initialize() {
	  var map = new google.maps.Map(document.getElementById('map-canvas'), {
		center: new google.maps.LatLng( rs_location_x,rs_location_y ),
		zoom: parseFloat(zoom)
	  });

		map.setOptions({draggable: true, zoomControl: false, scrollwheel: false, disableDoubleClickZoom: true});
		var infowindow = new google.maps.InfoWindow();
		var service = new google.maps.places.PlacesService(map);
	  
	}
	jQuery(document).ready(function() {
		google.maps.event.addDomListener(window, 'load', initialize);
		initialize();
	});
}


function main() {

(function () {
   'use strict'
	//Script
	//-----------------------------------
    jQuery(document).ready(function($){
		
		//Backstretch
		if( $('[data-background]').length ) {
			$('[data-background]').each(function() {
				var img = $(this).attr('data-background');
				$(this).backstretch(img);
			});
		}
		
		$('.popup-gallery').click(function(event){
			event.preventDefault();
			var id = $(this).attr('href');
			if( $(window).width() > 767 ) {
				var width = $(id).find('.bxslider img:first').get(0).width || 800;
				var height = $(id).find('.bxslider img:first').get(0).height || $(window).height();
				height += 60;
			}
			else {
				width = $(window).width() - 40;
				height = $(window).height();
			}
			$(id).find('.bxslider').hide();
			$.fancybox({
				href: id,
				minWidth: width,
				maxWidth: width,
				minHeight: height,
				maxHeight: height,
				helpers: {
					overlay: {
					  locked: false
					}
				},
				scrolling 	: 'no',
				openEffect	: 'elastic',
				closeEffect	: 'elastic',
				padding		: 0,
				afterShow   : function() {
					$(id).find('.bxslider').show();
					var data_pager = $(id).find('.bxslider').attr('data-pager');
					if($('.fancybox-wrap .bxslider').data('bxslider')){
						// $('.fancybox-wrap .bxslider').data('bxslider').reloadSlider({
							// mode: 'fade',
							// adaptiveHeight: true,
							// pagerCustom: data_pager,
							// nextText: '<i class="fa fa-chevron-right"></i>',
							// prevText: '<i class="fa fa-chevron-left"></i>',
							// onSlideAfter : function($slideElement, oldIndex, newIndex) {
								// var height_slider = $('.fancybox-wrap .bx-viewport').height();
								// var height_screen = $(window).height();
								// $('.fancybox-wrap').css('top', (height_screen-height_slider)/2 );
							// }
						// });
					}
					else{
						$('.fancybox-wrap .bxslider').data('bxslider', $('.fancybox-wrap .bxslider').bxSlider({
							mode: 'fade',
							adaptiveHeight: true,
							pagerCustom: data_pager,
							nextText: '<i class="fa fa-chevron-right"></i>',
							prevText: '<i class="fa fa-chevron-left"></i>',
							onSlideAfter : function($slideElement, oldIndex, newIndex) {
								var height_slider = $('.fancybox-wrap .bx-viewport').height();
								var height_screen = $(window).height();
								$('.fancybox-wrap').css('top', (height_screen-height_slider)/2 );
							}
						}));
					}
				}
			});
		});
		
		$('.owl-carousel').owlCarousel({
			
			slideSpeed : 300,
			paginationSpeed : 400,
			singleItem: true,
			pagination: false,
			navigation: false,
			lazyLoad:true,
			items:1,
			video:true,
			
		});
		
		$('.rst-product-images .bxslider').bxSlider({
			mode: 'fade',
			adaptiveHeight: true,
			controls : false,
			pagerCustom: '#product-pager'
		});
		
		$('.rst-popup-term').click(function(event){
			event.preventDefault();
			var id = $(this).attr('href');
			$(id).find('.bxslider').hide();
			var width = 1030;
			$.fancybox({
				href: id,
				maxWidth: width,
				// scrolling: 'yes',
				helpers: {
					overlay: {
					  locked: false
					}
				},
				openEffect	: 'elastic',
				closeEffect	: 'elastic',
				padding		: 0,
				afterShow   : function() {
					$(id).find('.bxslider').show();
					if($('.fancybox-wrap .bxslider').data('bxslider')){
						$('.fancybox-wrap .bxslider').data('bxslider').reloadSlider({
							slideWidth: 130,
							minSlides: 2,
							maxSlides: 3,
							pager: false,
							slideMargin: 18,
							nextText: '<i class="fa fa-chevron-right"></i>',
							prevText: '<i class="fa fa-chevron-left"></i>'
						});
					}
					else{
						$('.fancybox-wrap .bxslider').data('bxslider', $('.fancybox-wrap .bxslider').bxSlider({
							slideWidth: 130,
							minSlides: 2,
							maxSlides: 3,
							pager: false,
							slideMargin: 18,
							nextText: '<i class="fa fa-chevron-right"></i>',
							prevText: '<i class="fa fa-chevron-left"></i>'
						}));
					}
				}
			});
		});
		
		$(document).on('click', '[data-team-image]', function(e){
			e.preventDefault();
			if( $(this).attr('data-team-image') != '' ) {
				var team_image = $(this).attr('data-team-image');
				$(this).parents('.popup-term').find('.rst-team-image img').attr('src',team_image);
			}
		});
		
		//Slider
		
		$('.slider-input').each(function(){
			var t = $(this);
			t.slider({
				formatter: function(value) {
					var text_after = '';
					var text_before = '';
					
					if( t.attr('data-slider-text-after') != undefined ) {
						text_after = t.attr('data-slider-text-after');
					}
					if( t.attr('data-slider-text-before') != undefined ) {
						text_before = t.attr('data-slider-text-before');
					}
					return text_after + value + text_before;
				}
			});
		});
		
		//Menu
		$('button.navbar-toggle').click(function(){
			$(this).toggleClass('active');
		});
		$('.rst-nav-menu li.menu-item-has-children > a').click(function(e) {
			if( ! $(this).hasClass('current') ) {
				e.preventDefault();
				$(this).parent().find('>.sub-menu').slideDown();
				$(this).addClass('current');
			}
		});
		
		
		//ToolTip
		$('[data-toggle="tooltip"]').tooltip();
		$('[data-toggle="light-tooltip"]').tooltip({
			template: '<div class="tooltip light-tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
		});
		
		//Back To Top
		$('.rst-backtop').click(function(e) {
			e.preventDefault();
			$('html, body').animate({scrollTop:0}, 'slow');
		});
		
		//Search Header 
		$('.rst-search-bottom').click(function(e){
			e.preventDefault();
			$('.rst-search').addClass('open');
			$('#header').addClass('while');
		});
		$('.rst-hide-form').click(function(e){
			e.preventDefault();
			$('.rst-search').removeClass('open');
			$('#header').removeClass('while');
		});
		
		//Select Box
		$('.form-group select').rsSelectBox();
		$('.rst-drop-location').rsSelectBox({
			change: function( event, new_value, custom_ui, select ){
				$('.rst-address .address').html(event);
			}
		});
		
		//Term
		$('.rst-testimonials .bxslider').bxSlider({
		  mode: 'horizontal',
		  pager: false,
		  auto: true,
		  nextText: '<i class="fa fa-chevron-right"></i>',
		  prevText: '<i class="fa fa-chevron-left"></i>',
		});
		
		//Product View
		$('.rst-view .rst-list').click(function(e){
			e.preventDefault();
			$(this).parent().find('.active').removeClass('active');
			$(this).addClass('active');
			$('.product-list').addClass('view-list').removeClass('view-tile');
			$('.product-list .product-item').addClass('col-sm-12').removeClass('col-sm-4');
		});
		$('.rst-view .rst-tile').click(function(e){
			e.preventDefault();
			$(this).parent().find('.active').removeClass('active');
			$(this).addClass('active');
			$('.product-list').removeClass('view-list').addClass('view-tile');
			$('.product-list .product-item').removeClass('col-sm-12').addClass('col-sm-4');
		});
		
		$('.rst-main-menu .sub-menu').each(function(){
			$(this).addClass('rst-check');
			var max_width = $(this).outerWidth(true);
			$(this).css('min-width', max_width+2);
		}).promise().done(function(){
			$('.rst-check').removeClass('rst-check');
		});
		
		$('#calendar').fullCalendar({
			defaultDate: '2015-02-12',
			editable: false,
			eventLimit: true, // allow "more" link when too many events
			header: {
				left:   'prev',
				center: 'title',
				right:  'next'
			},
			events: [
				{
					title: 'All Day Event',
					start: '2015-02-01'
				},
				{
					title: 'Long Event',
					start: '2015-02-07'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2015-02-09T16:00:00'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2015-02-16T16:00:00'
				},
				{
					title: 'Conference',
					start: '2015-02-11'
				},
				{
					title: 'Meeting',
					start: '2015-02-12T10:30:00',
				},
				{
					title: 'Birthday Party',
					start: '2015-02-13T07:00:00'
				},
				{
					title: 'Click for Google',
					url: 'http://google.com/',
					start: '2015-02-28'
				}
			]
		});
		
		
		//Check validate send mail
		if( $("#contactForm").length ) {
			$("#contactForm input,#contactForm textarea").jqBootstrapValidation({
				preventSubmit: true,
				submitError: function($form, event, errors) {
					// additional error messages or events
				},
				submitSuccess: function($form, event) {
					event.preventDefault(); // prevent default submit behaviour
					// get values from FORM
					var name = $("input#name").val();
					var problem = $("select#problem").val();
					var email = $("input#email").val();
					var message = $("textarea#message").val();
					var firstName = name; // For Success/Failure Message
					// Check for white space in name for Success/Fail message
					if (firstName.indexOf(' ') >= 0) {
						firstName = name.split(' ').slice(0, -1).join(' ');
					}
					$.ajax({
						url: "././submit.php",
						type: "POST",
						data: {
							name: name,
							problem: problem,
							email: email,
							message: message
						},
						cache: false,
						success: function() {
							// Success message
							$('#success').html("<div class='alert alert-success'>");
							$('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
								.append("</button>");
							$('#success > .alert-success')
								.append("<strong>Your message has been sent. </strong>");
							$('#success > .alert-success')
								.append('</div>');

							//clear all fields
							$('#contactForm').trigger("reset");
						},
						error: function() {
							// Fail message
							$('#success').html("<div class='alert alert-danger'>");
							$('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
								.append("</button>");
							$('#success > .alert-danger').append("<strong>Sorry " + firstName + ", it seems that my mail server is not responding. Please try again later!");
							$('#success > .alert-danger').append('</div>');
							//clear all fields
							$('#contactForm').trigger("reset");
						},
					})
				},
				filter: function() {
					return $(this).is(":visible");
				}
			});
		}
		
	});
	
	jQuery(window).load(function(){
		$ = jQuery;
		$('.rst-main-menu .sub-menu').each(function(){
			$(this).addClass('rst-check');
			var max_width = $(this).outerWidth(true);
			$(this).css('min-width', max_width+2);
		}).promise().done(function(){
			$('.rst-check').removeClass('rst-check');
		});
		
		setTimeout(function() {
			$('#pageLoading').fadeOut(700);
		}, 500);

	});
	
}());

}
main();