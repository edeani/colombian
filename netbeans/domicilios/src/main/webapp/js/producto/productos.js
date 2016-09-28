$(document).ready(function () {
    $(document).on('click','.categoria',function (e) {
        
        var volver = "#"+$(this).attr('data-href');
        
        for (i = 0; i < $('.categoria').length; i++) {
            $($('.categoria')[i]).parent().removeClass("active");
            
        }
        $(this).parent().addClass("active");
        $('html, body').animate({
            scrollTop: $(volver).offset().top
        }, 1000);


    });
    
    $(window).scroll(function () {
       $("#categoriasDiv").stop().animate({"marginTop": (posicionBarraLateral()) + "px", "marginLeft": ($(window).scrollLeft()) + "px"}, "slow");
    });
    
    $(document).on("click",".addCar",function(event){
        event.preventDefault();
        var idproducto = $(this).attr("data-id");
        var precio = $(this).attr("data-precio");
        var cantidad = $(this).attr("data-cantidad");
        var nombre = $(this).attr("data-nombre");
        
        var existe = false;
        if($(".itemCar").length === 0){
            $("#doOrderResumen").show();
        }
        for(i=0;i<$(".itemCar").length;i++){
            var obj = $(".itemCar")[i];
            if($(obj).attr("data-idproducto") === idproducto){
                existe=true;
                break;  
            }
        }
        var url = "";
        if(existe){
            url = "/contenido/ajax/carrito/actualizar.htm";
        }else{
            url = "/contenido/ajax/carrito/agregar.htm";
        }
         
        $.ajax({
            url:url,
            data: "idproducto="+idproducto+"&precioProducto="+precio+"&nombreProducto="+nombre,
            async: false,
            type: 'POST',
            timeout: 20000,
            success: function (html) {
                if (existe){
                    var num = parseInt($("#update"+idproducto).attr("data-cantidad"))+parseInt(cantidad);
                    $("#update"+idproducto).html(num);
                    $("#update"+idproducto).attr("data-cantidad",num);
                }else{
                    $("#listCar").append(html);
                }
                actualizarTotalPedido($("#totalPedido").val(),precio);
            }
        });
    });
    
    $(document).on("click",".removeCar",function(event){
        event.preventDefault();
        var elementoProducto = $(this).parent().parent();
        var idproducto= $(elementoProducto).attr("data-idproducto");
        var totalProducto = $(elementoProducto).attr("data-total");
        $.ajax({
            url:"/compras/ajax/carrito/eliminar.htm",
            data: "idproducto="+idproducto,
            async: false,
            type: 'POST',
            timeout: 20000,
            success: function (html) {
               $(elementoProducto).remove();
                actualizarTotalPedido($("#totalPedido").val(),"-"+totalProducto);
                if($(".itemCar").length === 0){
                    $("#doOrderResumen").hide();
                }
            }
        });
    });
    function actualizarTotalPedido(totalPedido,totalProducto){
         $("#totalPedido").val(parseInt(totalPedido)+parseInt(totalProducto));
         $("#totalPrice").html("$"+$("#totalPedido").val());
    }
    function posicionBarraLateral(){
            /*console.log("Window::"+$(window).scrollTop());
            console.log("listaDivProd::"+$("#categoriasDiv").offset().top);
            console.log("height::"+$("div.backstretch").height());*/
            if($(window).scrollTop() >= 454 )
            return $(window).scrollTop() - $("#listaDivProd").offset().top /*- $("#listaDivProd").offset().top*/;
            else
            return $("#listaDivProd").offset().top - 485;
    }
    
    
    
});