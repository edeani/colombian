function validarNUM(e) {
    tecla = (document.all) ? e.keyCode : e.which;

    if (tecla == 8)
        return true;
    if (tecla == 0)
        return true;
    patron = /[1234567890\.]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

/**
 * campos: Número de campos para el próximo focus
 * objeto: Campo actual del focus
 */
function nextFocus(campos, objeto) {

    var fields = objeto.parents('form:eq(0),body').find('button,input,textarea,select');
    var index = fields.index(objeto);
    if (index > -1 && (index + 1) < fields.length) {
        fields.eq(index + campos).focus();
    }
}

/**
 * Formato para campo que coloca coma para lo smile, millones etc ...
 */
function formatCurrencyFieldText(field) {
    num = field.value;
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
    field.value = num;
}

function quitarFormato(valor) {
    if (valor != undefined) {
        if (valor != null) {
            while (valor.indexOf(",", 0) != -1) {
                valor = valor.replace(",", "");
            }
        }
    }
    return valor;
}

function trim(myString)
{
    return myString.replace(/^\s+/g, '').replace(/\s+$/g, '')
}

function getContenidoCaracteres(idContainer, content) {
    var fullStr = content + " ";
    var initial_whitespace_rExp = /^[^A-Za-z0-9ñÑáé.íóúÁÉÍÓÚ+-]+/gi;
    var left_trimmedStr = content;
    var non_alphanumerics_rExp = /[^áéíóúA-Za-z0-9ñÑÁ.ÉÍÓÚ+-]+/gi;
    var cleanedStr = left_trimmedStr.replace(non_alphanumerics_rExp, " ");
    $(idContainer).val(cleanedStr);
} 