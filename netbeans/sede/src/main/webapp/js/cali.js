$(document).ready(function(){
    $("#listaSedes").live("change",function(){
        var select = $(this).find('option:selected');
        url = $("#listaSedes").data('url');
        
        data = "idSede="+select.val()+"&nombreSede="+select.text();
        ejecutarAjax(url, data, "GET","listaSedes");
    });
    
    function ejecutarAjax(url,data,type,idCapa){
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            data: data,
            dataType: 'html',
            success: function (result) {
                $("#"+idCapa).html(result);
            } 
        });
    }
});


