$(document).ready(function () {
    $('.categoria').click(function (e) {
        e.preventDefault();
        volver = $(this).attr('href');
        for(i=0;i< $('.categoria').length;i++){
         $($('.categoria')[i]).parent().removeClass("active");
        }
        $(this).parent().addClass("active");
       // setTimeout(function () {
            $('html, body').animate({
                scrollTop: $(volver).offset().top
            }, 2000);
        //}, 1000);

        $('#categoriasDiv').animate({
            'marginTop': $(volver).offset().top - $("#listaDivProd").offset().top
        }, 2000);
    });
});