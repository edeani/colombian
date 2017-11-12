jQuery(document).ready(function($){
	if( $('#countdown').length && $('#countdown').attr('data-time') != undefined && $('#countdown').attr('data-time') != '' ) {
		var austDay = new Date();
		var date = $('#countdown').attr('data-time').split("/");
		austDay = new Date( parseInt(date[0]), parseInt(date[1]) - 1, parseInt(date[2]));
		$('#countdown').countdown({until: austDay, format: 'odHM'});
	}
});