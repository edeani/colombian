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
                        htmlInformation = $("#form-delivery").html();
                        loadInformation(htmlInformation);
                    }
                },
                cancel:function(){
                    //close
                }
            }
        });
    });
    
    $(document).on("click","#btn-wapp",function (event){
        event.preventDefault();
        
        htmlInformation = $("#form-delivery").html();
        loadInformation(htmlInformation);
    });
    
    function loadInformation(htmlInformation){
        
        $.confirm({
            title: false,
            content: htmlInformation,
            columnClass: 'small',
            buttons:{
                formSubmit:{
                    text:"Enviar",
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
    }
});


