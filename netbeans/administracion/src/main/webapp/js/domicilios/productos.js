$(document).on('ready',function () {
     //$('#tablaDomicilios').DataTable();
     
     $(document).on("click",".viewOrder",function (){
         
     });
     $(document).on("click",".aceptOrder",function (){
         var fila= $(this).attr("data-row");
         $.ajax({
                    url: "/administracion/domicilios/ajax/aprobar.htm",
                    data: "idpedido=" + $("#idpedido" + fila).val(),
                    type: 'POST',
                    timeout: 20000,
                    success: function (response) {
                        //removeProductTable(fila);
                        if(response=="OK"){
                            
                        }else{
                            
                        }
                        $("#fila"+fila).addClass("alert alert-danger");
                        $("#activeButtons"+fila).hide();
                        $("#inactiveButtons"+fila).show();
                        $.dialog({
                            icon: 'fa fa-check',
                            title: 'Mensaje',
                            content: 'El producto "' + nombre + '" con id:' + idproducto + ' ha sido inactivado con éxito',
                        });
                    }
                });
     });
     $(document).on("click",".rejectOrder",function (){
         
     });
});