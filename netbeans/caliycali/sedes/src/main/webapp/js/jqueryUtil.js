$(document).ready(function() {
    $(document).on("keypress", ".claseValidarNum", function(e) {
        return validarNUM(e);
    });
    $(document).on("keyup", ".claseFormatDec", function(e) {
        var field = this;
        formatoNumeroDecimal(field);
    });
    $(document).on("keypress", ".claseproximocampo", function(e) {
        proximoCampo(e, this, "");
    });
    $(document).on("keyup", ".claseTotalColumna", function(e) {
        sumaColumna(e, "claseTotalColumna", "totalColumna");
    });

    /**
     * Funcion para setear la sede en sesion
     */
    $(document).on("change","#sedeSession", function(){
        loader("cargador", "barra.gif");
        var respuesta = peticionAjax($("#contextpath").val()+"/sedes/ajax/setSedeSession.htm","POST","idSede="+$("#sedeSession").val());
        loader("cargador", "");
    });
});

function formatoNumeroDecimal(field) {
    var num = field.value;
        num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num === (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
    field.value = num;
}
function sumaColumna(evento, clase, idViewTotal) {
    var celdas = $("body").find("." + clase);
    var numeroCeldas = celdas.length;
    var sumaTotal = 0;

    for (i = 0; i < numeroCeldas; i++) {
        var valor = $(celdas[i]).val();
        var comas = false;
        while (valor.indexOf(",", 0) !== -1) {
            valor = valor.replace(",", "");
            comas = true;
        }
        if (!comas) {
            while (valor.indexOf(".", 0) !== -1) {
                valor = valor.replace(".", "");
            }
        }
        sumaTotal += parseInt(valor);
    }
    $("#" + idViewTotal).val(sumaTotal);
}
function proximoCampo(evento, campo, clase) {
    var code = evento.keyCode || evento.which;
    if (code === 13) {
        //var fields = objeto.parents('form:eq(0),body').find('button,input,textarea,select');
        var fields = $('body').find("button,input,textarea,select");
        var index = fields.index($(campo));
        /**
         * Si el siguiente campo esta deshabilitado
         */
        if ($(fields[index + 1]).is(":disabled")) {
            $(fields[index + 1]).attr("disabled", false);
        }

        if (index > -1 && (index + 1) < fields.length) {
            $(fields[index + 1]).focus();
        }

        /**
         * Clase para resaltar focus
         */
        if (clase !== "") {
            $(fields[index]).removeClass(clase);
            $(fields[index + 1]).addClass(clase);
        }
    }
}
function proximoCampoSinEvento(campo, clase) {

    //var fields = objeto.parents('form:eq(0),body').find('button,input,textarea,select');
    var fields = $('body').find("button,input,textarea,select");
    var index = fields.index($(campo));
    /**
     * Si el siguiente campo esta deshabilitado
     */
    if ($(fields[index + 1]).is(":disabled")) {
        $(fields[index + 1]).attr("disabled", false);
    }

    if (index > -1 && (index + 1) < fields.length) {
        $(fields[index + 1]).focus();
    }

    /**
     * Clase para resaltar focus
     */
    if (clase !== "") {
        $(fields[index]).removeClass(clase);
        $(fields[index + 1]).addClass(clase);
    }

}
function loader(idDiv, load)
{
    if (load !== "") {
        $("#" + idDiv).html("<div style=' float: left; margin-left:40%;' ><img src='"+$("#contextpath").val()+"/img/loaders/" + load + "' /><br>Cargando ...</div>");
    } else {
        $("#" + idDiv).html("");
    }
}

function peticionAjax(url, type, parametros) {
    var codigo = "";
    if (parametros !== null && parametros !== "") {
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            data: parametros,
            async: false,
            success: function(result) {
                codigo = result;
            }
        });
    } else {
        $.ajax({
            url: url,
            timeout: 20000,
            type: type,
            async: false,
            success: function(result) {
                codigo = result;
            }
        });
    }
    return codigo;
}

function validarFormulario(formulario) {
    var estadoFormulario = "";
    $(formulario).find('input,select').each(function() {
        var elemento = this;
        if ($(this).val() === "") {
            if ($(this).is(".contentRequired")) {
                $(this).addClass("campError");
                estadoFormulario = "Hay campos requeridos vac&iacute;os";
            }
        } else {
            if ($(this).is(".contentRequired")) {
                $(this).removeClass("campError");
            }
        }
    });

    return estadoFormulario;
}

function lightboxMensaje(mensaje) {
    $.colorbox({
        html: "<!DOCTYPE html><html><body><p id='mensaje'>" + mensaje + "</p></body></html>",
        initialHeight: 50,
        Height: 50
    });
}

/**
 * Los campos deben estar en el body de la tabla
 * @param {type} idtabla
 * @returns el estado de aprobacion
 */
function validarCamposTabla(idtabla) {
    var inputsTbody = $("#" + idtabla + " tbody").find("input,select");
    var aprobado = true;
    if (inputsTbody.length > 0) {
        $(inputsTbody).each(function(index) {
            if ($(this).val() === undefined) {
                aprobado = false;
                $(this).addClass("campError");
            } else if ($.trim($(this).val()) === "") {
                aprobado = false;
                $(this).addClass("campError");
            } else {
                $(this).removeClass("campError");
            }
        });
    }else{
        aprobado = false;
    }
    return aprobado;
}

function prepararCamposDecimales() {
    //Preparar campos decimales
    $(".claseFormatDec").each(function(index) {
        while ($(this).val().indexOf(",") !== -1) {
            $(this).val($(this).val().replace(",", ""));
        }
    });
}

function prepararCamposDecimalesPoint() {
    //Preparar campos decimales
    $(".claseFormatDecPoint").each(function(index) {
        while ($(this).val().indexOf(".") !== -1) {
            $(this).val($(this).val().replace(".", ""));
        }
    });
}
function prepararCamposDecimalesClase(clase) {
    //Preparar campos decimales
    $("." + clase).each(function(index) {
        while ($(this).val().indexOf(",") !== -1) {
            $(this).val($(this).val().replace(",", ""));
        }
    });
}

function agregarFila(idtabla, idTrGenerico) {
    var original = $("#" + idTrGenerico + " tbody").html();

    var filas = $("#" + idtabla + " tbody").find("tr");
    var numeroFilas = filas.length;
    $("#" + idTrGenerico + " tr").attr("id", "tr" + numeroFilas);

    var campos = $("#tr" + numeroFilas).find("input");
    for (i = 0; i < campos.length; i++) {
        var nombre = campos[i].name;
        campos[i].name = nombre.replace("@", numeroFilas + "");
    }

    $("#tr" + numeroFilas).attr("data-numero", "" + numeroFilas);
    $("#" + idtabla + " tbody").append($("#" + idTrGenerico + " tbody").html());
    $("#" + idTrGenerico + " tbody").html(original);
}

function eliminarFila(idTabla, idTablaGenerica, fila) {
    $(fila).remove();
    var filasTabla = $("#" + idTabla + " tbody").find("tr");
    var numeroFilas = filasTabla.length;
    var camposGenericos = $("#" + idTablaGenerica).find("input");

    for (i = 0; i < numeroFilas; i++) {
        var camposFilaTabla = $(filasTabla[i]).find("input");
        $(filasTabla[i]).attr("id", "tr" + i);
        $(filasTabla[i]).attr("data-numero", "" + i);
        for (j = 0; j < camposFilaTabla.length; j++) {
            if (camposGenericos[j].type !== "button") {
                var nombre = camposGenericos[j].name;
                camposFilaTabla[j].name = nombre.replace("@", i + "");
            }
        }
    }

}