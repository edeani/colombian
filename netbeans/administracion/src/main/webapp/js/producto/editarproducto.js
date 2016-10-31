$(document).on('ready', function () {
    $("#imagen").fileinput({showCaption: false, showUpload: false, fileType: "image",maxFileSizemaxFileSize:600,msgSizeTooLarge:"La imágen debe pesar menos de 600kb"});
    $(document).on("click", "#change-pic", function (event) {
        event.preventDefault();
        $("#change-img").hide();
        $("#content-img").show();
    });

    $('#imagen').on('fileselect', function (event, numFiles, label) {
        $("#load-pic").hide();
    });

    $('#imagen').on('filecleared', function (event) {
        $("#load-pic").show();
    });
});
