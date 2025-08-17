$(document).ready(function () {
  
  $(document).on("click","#reporteVentasPorcentajes",function(event){
      event.preventDefault();
      var estadoFormulario = validarFormulario("#formPorcVentas");
      if (estadoFormulario === "") {
        var form = $('#formPorcVentas').clone();
        $(form).attr("action", $("#formPorcVentas").attr("action"));
        form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "pdf"));             
        
        const link = document.createElement('a');
        link.href = $("#formPorcVentas").attr("action")+"?"+$(form).serialize(); // Replace with your PDF path
        link.download = 'reporte_porcentaje_ventas.pdf';   // Replace with desired filename
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        
      }else{
          lightboxMensaje("Hay campos vac&iacute;os");
      }
  });
  
  
  $(document).on("click","#reporteVentasPorcentajesXls",function(event){
      event.preventDefault();
      var estadoFormulario = validarFormulario("#formPorcVentas");
      if (estadoFormulario === "") {
        var form = $('#formPorcVentas').clone();
        $(form).attr("action", $("#formPorcVentas").attr("action"));
        $(form).attr("download", "reporte_porcentaje_ventas.xlsx");
        form.append($("<input></input>").attr('type', 'hidden').attr('name', 'tipo').attr('value', "excel"));             
        form.appendTo('body').submit().remove();
      }else{
          lightboxMensaje("Hay campos vac&iacute;os");
      }
  });
    
});

