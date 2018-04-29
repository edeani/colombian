$(document).on('ready', function() {

    $("#imagen").fileinput({showCaption: false, showUpload: false, showCancel: false, fileType: "image"
        ,maxFileSizemaxFileSize:5120,msgSizeTooLarge:"La imágen debe pesar menos de 5M",resizeImage: true
        ,resizeImageQuality:1.00,resizeDefaultImageType:"image/png"});
    
    $(document).on("click","#subir",function(e){
        e.preventDefault();
        $("#formpho").submit();
    });
});
