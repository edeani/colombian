$(document).on('ready', function() {
    /**
     *     border-color: transparent;
     *     color: #a94442;
     *     background-color: #f2dede;
     */
    $("#imagen").fileinput({showCaption: false, showUpload: false, fileType: "image",maxFileSizemaxFileSize:600,msgSizeTooLarge:"La imágen debe pesar menos de 600kb"});
});
