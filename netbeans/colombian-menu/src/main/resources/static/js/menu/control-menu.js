$(document).ready(function () {
   
   const dataRefProduct = "data-ref-prod";
   const refId = "#";
   const classProdSelected = "container-item-selected-product";
   
   localStorage.clear();
    /**
     * Open cart
     */
    $(document).on("click", "#carrito", function () {
        $("#cartModal").toggle();

        let htmlCart = $("#content-cart").html();
        $.confirm({
            title: false,
            content: htmlCart,
            columnClass: 'medium',
            buttons: {
                formSubmit: {
                    text: "Hacer pedido",
                    btnClass: "btn-blue",
                    action: function () {
                        htmlInformation = $("#form-delivery").html();
                        loadInformation(htmlInformation);
                    }
                },
                cancel: function () {
                    //close
                }
            }
        });
    });

    /**
     * add product
     */
    $(document).on("click", ".add-product", function (event) {
        event.preventDefault();

        var currentComment = refId +$(this).attr("data-pre-comment")+ $(this).attr(dataRefProduct);
        

        

        let currentProductId = refId+$(this).attr("data-pre-prod") + $(this).attr(dataRefProduct);
        let refInputComment = refId +$(this).attr("data-pre-input-comment")+$(this).attr(dataRefProduct);
        
        
        let resumeProduct = addProduct(currentProductId,refInputComment,$(this));
        
        if ($(currentComment).is(":hidden")) {
            $(currentComment).show();
        }
        
        $(this).parent().parent().addClass(classProdSelected);
    });

    $(document).on("click", "#btn-wapp", function (event) {
        event.preventDefault();

        let htmlInformation = $("#form-delivery").html();
        loadInformation(htmlInformation)
        ;
    });

    function loadInformation(htmlInformation) {

        $.confirm({
            title: false,
            content: htmlInformation,
            columnClass: 'small',
            buttons: {
                formSubmit: {
                    text: "Enviar",
                    btnClass: "btn-blue",
                    action: function () {
                        alert("Yesss");
                    }
                },
                cancel: function () {
                    //close
                }
            }
        });
    }
    
    function addProduct(referenceProduct,currentComment,currentProductObject){
        
        let productItem =  getProduct(referenceProduct,currentProductObject);
        
        productItem.cantidad += 1;
        productItem.total = productItem.precio*productItem.cantidad;
        productItem.comentario = $(currentComment).val();
        
        localStorage.setItem(referenceProduct,JSON.stringify(productItem));
        
        return productItem;
    }
    
    function removeProduct(referenceProductAdd){
        
    }
    
    function getProduct(referenceProduct,currentProductObject){
        let prod = getAttrJsonLocalStorage(referenceProduct);
        if($.isEmptyObject(prod)){
            prod = initializeProduct();
            prod.nombre = currentProductObject.attr("data-name");
            prod.precio = currentProductObject.attr("data-price");
            prod.total = prod.precio;
        }
                
        return prod;
    }
    
    function getAttrJsonLocalStorage(name) {
        let attribute = localStorage.getItem(name);
        return JSON.parse(attribute) || {};
    }

    function initializeProduct() {
        let productStructure = {
                nombre:"",
                precio:"",
                cantidad:0,
                total:0,
                adicional:"",
                comentario:""
        
        };
        return productStructure;

    }
    
     

});


