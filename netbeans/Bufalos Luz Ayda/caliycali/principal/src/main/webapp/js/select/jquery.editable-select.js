
$('.primerCampo').change(function(){ 
	$('#name').val($(this).val());
})

function modify(){
	$('#name').val($('.primerCampo').val());
	output();
}

function output(){
	$('p').text('value: ' + $('#name').val());
}

$('#name').on('click', function(){
	$(this).select()
}).on('blur', function(){
	output();
})

modify();