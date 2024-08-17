$(document).ready(function() {

    $("#fechaInicial").datepicker({dateFormat: "yy-mm-dd"});
    $("#fechaFinal").datepicker({dateFormat: "yy-mm-dd"});


    $(document).on("click","#consultarInventarioCilente",function(event) {
        event.preventDefault();
        var url = $("#formInventarioCliente").attr("action");
        var msjInvCliente = validarFormulario("#formInventarioCliente");
        if (msjInvCliente === "") {
            $("#listaInventario").html(peticionAjax(url, "POST", $("#formInventarioCliente").serialize()));
        } else {
            lightboxMensaje(msjInvCliente);
        }
        
    });

    $(document).on("click","#consultarInventarioClientexls",function(event) {
        event.preventDefault();
        var url = $("#formInventarioCliente").attr("action");
        var msjInvCliente = validarFormulario("#formInventarioCliente");
        if (msjInvCliente === "") {
            var form = document.createElement("form");
            $(form).attr("action", $("#consultarInventarioClientexls").attr("data-download"));
            $(form).attr("method", "POST");
            $(form).attr("enctype","application/x-www-form-urlencoded");
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'sede').attr('value', $("#sedeSession").find("option:selected").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'fechaInicial').attr('value', $("#fechaInicial").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'fechaFinal').attr('value', $("#fechaFinal").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'tel').attr('value', $("#tel").val()));
            $(form).appendTo('body').submit().remove();
    
        } else {
            lightboxMensaje(msjInvCliente);
        }
        
    });

    
});

