$(document).ready(function(){
    
    $(document).on("click","#carrito",function(){
        $("#cartModal").toggle();
        
        let htmlCart = $("#content-cart").html();
        $.confirm({
            title: false,
            content: htmlCart,
            columnClass: 'medium',
            buttons:{
                formSubmit:{
                    text:"Hacer pedido",
                    btnClass: "btn-blue",
                    action: function(){
                        alert("Yesss");
                    }
                },
                cancel:function(){
                    //close
                }
            }
        });
    });
});


