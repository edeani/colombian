$(document).ready(function(){
   $("#fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $("#fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });
    
    $(document).on("submit","#formulario",function(e){
        var mensaje = validarFormulario(this);
        if(mensaje!==""){
            e.preventDefault();
            lightboxMensaje(mensaje);
        }
    });
    $(document).on("change","#sede",function(){
       var nombre = $("#sede option[value='"+$(this).val()+"']").text();
       var id = $(this).val();
       if(id===""){
           $("#nombreSede").val(id);
       }else{
           $("#nombreSede").val(nombre);
       }
    });
    $(document).on("change","#tipoReporte",function(){
        var valorSeleccion = $(this).val();
        //Si es el total
        if(valorSeleccion==="1"){
            $("#labelFechaInicial").show();
            $("#labelFechaFinal").show();
            $("#labelAceptar").show();
            $("#sede").removeClass("contentRequired");
            $("#sede option[value='']").attr("selected",true);
            $("#labelSede").hide();
            $("#nombreSede").val("");
        //Si es por sede    
        }else if(valorSeleccion==="2"){
            $("#labelFechaInicial").show();
            $("#labelFechaFinal").show();
            $("#sede").addClass("contentRequired");
            $("#sede").removeClass("campError");
            $("#labelSede").show();
            $("#labelAceptar").show();
            $("#nombreSede").val("");
        //si vuelve a en blanco    
        }else if(valorSeleccion===""){
            $("#labelFechaInicial").hide();
            $("#labelFechaFinal").hide();
            $("#sede").removeClass("contentRequired");
            $("#sede option[value='']").attr("selected",true);
            $("#labelSede").hide();
            $("#labelAceptar").hide();
            $("#nombreSede").val("");
        }
    });
});

