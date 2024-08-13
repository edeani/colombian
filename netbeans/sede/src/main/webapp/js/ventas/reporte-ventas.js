$(document).ready(function () {
    $("#fechaInicial").datepicker({
        dateFormat: "yy-mm-dd"
    });
    $("#fechaFinal").datepicker({
        dateFormat: "yy-mm-dd"
    });

    $("#consultarVentas").click(function (event) {
        event.preventDefault();
        var tipoPeticion = "POST";
        var parametros = $("#formVentas").serialize();

        var urlVentasMesa = $("#contVentasMesa").attr("data-url");
        var htmlMesas = peticionAjaxAsync(urlVentasMesa, tipoPeticion, parametros);
        $("#contenidomesa").html(htmlMesas);

        var urlVentasDomicilio = $("#contVentasDomicilio").attr("data-url");
        var htmlDomicilio = peticionAjaxAsync(urlVentasDomicilio, tipoPeticion, parametros);
        $("#contenidodomicilio").html(htmlDomicilio);

        var urlVentasLlevar = $("#contVentasLlevar").attr("data-url");
        var htmlLlevar = peticionAjaxAsync(urlVentasLlevar, tipoPeticion, parametros);
        $("#contenidollevar").html(htmlLlevar);

        var urlVentasTotal = $("#contVentasTotal").attr("data-url");
        var htmlTotal = peticionAjaxAsync(urlVentasTotal, tipoPeticion, parametros);
        $("#contenidototal").html(htmlTotal);

        var suma_total_mesas = sumaColumnaWithoutFormat(event, "cmpResumenMesa", "totalMesas");
        formatCurrencyFieldText(document.getElementById("totalMesas"));
        $("#totalMesaLabel").html($("#totalMesas").val());

        var suma_total_dom = sumaColumnaWithoutFormat(event, "cmpResumenDomicilio", "totalDom");
        formatCurrencyFieldText(document.getElementById("totalDom"));
        $("#totalDomicilioLabel").html($("#totalDom").val());

        var suma_total_llevar = sumaColumnaWithoutFormat(event, "cmpResumenLlevar", "totalLlevar");
        formatCurrencyFieldText(document.getElementById("totalLlevar"));
        $("#totalLlevarLabel").html($("#totalLlevar").val());

        $("#totalTotal").val((suma_total_dom + suma_total_llevar + suma_total_mesas));
        formatCurrencyFieldText(document.getElementById("totalTotal"));
        $("#totalTotalLabel").html($("#totalTotal").val());
    });


    $(document).on("click", "button#reporteVentasxls", function (event) {
        event.preventDefault();
        var estadoFormReporteVentas = validarFormulario("#formVentas");
        if (estadoFormReporteVentas === "") {


            /*var url = $("#contenedorResumenBase").attr("data-download");
            var filename = 'ventas_report.xlsx';
            var request = new XMLHttpRequest();
            request.open('POST', url, true);
            request.responseType = 'blob';
            request.onload = function () {
                var link = document.createElement('a');
                document.body.appendChild(link);
                link.href = window.URL.createObjectURL(request.response);
                link.download = filename;
                link.click();
            };
            request.send();*/
            
            var form = document.createElement("form");
            $(form).attr("action", $("#contenedorResumenBase").attr("data-download"));
            $(form).attr("method", "POST");
            $(form).attr("enctype","application/x-www-form-urlencoded");
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'idSubsede').attr('value', $("#idSubsede").find("option:selected").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'fi').attr('value', $("#fechaInicial").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'ff').attr('value', $("#fechaFinal").val()));
            $(form).appendTo('body').submit().remove();
            /*
            var form = document.createElement("form");
            $(form).attr("action", $("#contenedorResumenBase").attr("data-download"));
            $(form).attr("method", "POST");
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'idSubsede').attr('value', $("#idSubsede").find("option:selected").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'fi').attr('value', $("#fechaInicial").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'ff').attr('value', $("#fechaFinal").val()));
            $(form).appendTo('body').submit().remove();
            */
        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
    
    $(document).on("click", "button#reporteMesasxls", function (event) {
        event.preventDefault();
        var estadoFormReporteVentas = validarFormulario("#formVentas");
        if (estadoFormReporteVentas === "") {
            
            var form = document.createElement("form");
            $(form).attr("action", $("#contenedorResumenBase").attr("data-download-mesa"));
            $(form).attr("method", "POST");
            $(form).attr("enctype","application/x-www-form-urlencoded");
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'idSubsede').attr('value', $("#idSubsede").find("option:selected").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'fi').attr('value', $("#fechaInicial").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'ff').attr('value', $("#fechaFinal").val()));
            $(form).appendTo('body').submit().remove();
        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
    
    $(document).on("click", "button#reporteDomiciliosxls", function (event) {
        event.preventDefault();
        var estadoFormReporteVentas = validarFormulario("#formVentas");
        if (estadoFormReporteVentas === "") {
            
            var form = document.createElement("form");
            $(form).attr("action", $("#contenedorResumenBase").attr("data-download-domicilio"));
            $(form).attr("method", "POST");
            $(form).attr("enctype","application/x-www-form-urlencoded");
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'idSubsede').attr('value', $("#idSubsede").find("option:selected").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'fi').attr('value', $("#fechaInicial").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'ff').attr('value', $("#fechaFinal").val()));
            $(form).appendTo('body').submit().remove();
        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
    
    $(document).on("click", "button#reporteLlevarxls", function (event) {
        event.preventDefault();
        var estadoFormReporteVentas = validarFormulario("#formVentas");
        if (estadoFormReporteVentas === "") {
            
            var form = document.createElement("form");
            $(form).attr("action", $("#contenedorResumenBase").attr("data-download-llevar"));
            $(form).attr("method", "POST");
            $(form).attr("enctype","application/x-www-form-urlencoded");
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'idSubsede').attr('value', $("#idSubsede").find("option:selected").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'fi').attr('value', $("#fechaInicial").val()));
            $(form).append($("<input></input>").attr('type', 'hidden').attr('name', 'ff').attr('value', $("#fechaFinal").val()));
            $(form).appendTo('body').submit().remove();
        } else {
            lightboxMensaje("Hay campos vac&iacute;os");
        }
    });
});
