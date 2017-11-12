$(document).ready(function () {
    $("#nombre").keypress(function (e) {
        tecla = (document.all) ? e.keyCode : e.which;

        if (tecla == 8)
            return true;
        if (tecla == 0)
            return true;
        patron = /[A-Za-z_ áéíóúñÁÉÍÓÚÑ]/;
        te = String.fromCharCode(tecla);
        return patron.test(te);
    });
});


