$(document).on('ready', function() {
    $("#imagen").fileinput({showCaption: false, showUpload: false, fileType: "image",maxFileSizemaxFileSize:600,msgSizeTooLarge:"La imágen debe pesar menos de 600kb"});
});
