$(document).ready(function(){
    $(document).on("click","#submit",function(event){
        var mensaje = validarFormulario("#formularioLogin");
        if(mensaje===""){
            //$("#formularioLogin").submit();
        }else{
            event.preventDefault();
            alert("Campos requeridos");
        }
    });
});


