$(document).ready(function(){
    $(".fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $(document).on("click","#buscar",function(e){
        e.preventDefault();
        var url = $("#formulario").attr("action");
        $("#divContenedorTabla").hide();
        loader("cargador", "barra.gif");
        var html = peticionAjax(url,"post","fechaInicial="+$("#fechaInicial").val());
        $("#divContenedorTabla").html(html);
        $("#divContenedorTabla").show();
        loader("cargador", "");
    });
    
    $(document).on("click",".claseBuscarPagoProveedor",function(e){
        e.preventDefault();
        var url = $(this).data("url");
        var idpagoproveedor = $(this).data("pago");
        
        var body = document.body;
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = url;
        form.name = 'jsform';
        form.id = 'jsform';
        form.target = "_blank";
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = "idpagoproveedor";
        input.id = "idpagoproveedor";
        input.value = idpagoproveedor;
        form.appendChild(input);
        body.appendChild(form);
        form.submit();
    });
    
    $(document).on("click","#click",function(e){
        e.preventDefault();
        var url = $("#formulario").attr("action");
        
        var html = peticionAjax(url,"post","fechaInicial="+$("#fechaInicial").val());
        $("#divContenedorTabla").html(html);
        $("#divContenedorTabla").show();
    });
    
});

