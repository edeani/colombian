/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 *
 * @author Eslayfer
 */
public class Formatos {

    private static final Integer DIAS_ANYOS = 365;
    private static final String UNICODE =
            "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"
            + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD"
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177"
            + "\u00C3\u00E3\u00D5\u00F5\u00D1\u00F1"
            + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF"
            + "\u00C5\u00E5"
            + "\u00C7\u00E7"
            + "\u0150\u0151\u0170\u0171";
    private static final String PLAIN_ASCII =
            "AaEeIiOoUu" // grave
            + "AaEeIiOoUuYy" // acute
            + "AaEeIiOoUuYy" // circumflex
            + "AaOoNn" // tilde
            + "AaEeIiOoUuYy" // umlaut
            + "Aa" // ring
            + "Cc" // cedilla
            + "OoUu" // double acute
            ;

    /**
     * Metodo que recupera la cantidad de dias entre dos fechas
     *
     * @param fechaInicio
     * @param fechaFin
     * @return String
     */
    public static String recuperarDiasFechas(Date fechaInicio, Date fechaFin) {

        String rst = "";

        GregorianCalendar calendarAhora = new GregorianCalendar();
        calendarAhora.setTime(fechaFin);
        GregorianCalendar calendarInicio = new GregorianCalendar();
        calendarInicio.setTime(fechaInicio);
        int rangoAnyos = calendarAhora.get(Calendar.YEAR) - calendarInicio.get(Calendar.YEAR);
        int totalDias = (rangoAnyos * DIAS_ANYOS) + (calendarAhora.get(Calendar.DAY_OF_YEAR) - calendarInicio.get(Calendar.DAY_OF_YEAR));

        rst = totalDias == 1 ? totalDias + " d&iacute;a" : totalDias > 30 ? "M&aacute;s de 30 d&iacute;as" : totalDias + " d&iacute;as";

        return rst;
    }

    /**
     * Metodo que compara si dos fechas son iguales
     *
     * @param fechaInicio
     * @param fechaFin
     * @return boolean
     */
    public static boolean fechasIguales(Date fechaInicio, Date fechaFin) {

        boolean rst = false;

        GregorianCalendar calendarAhora = new GregorianCalendar();
        calendarAhora.setTime(fechaFin);
        GregorianCalendar calendarInicio = new GregorianCalendar();
        calendarInicio.setTime(fechaInicio);
        int rangoAnyos = calendarAhora.get(Calendar.YEAR) - calendarInicio.get(Calendar.YEAR);
        int totalDias = (rangoAnyos * DIAS_ANYOS) + (calendarAhora.get(Calendar.DAY_OF_YEAR) - calendarInicio.get(Calendar.DAY_OF_YEAR));
        if (totalDias == 0) {
            return true;
        }

        return rst;
    }

    /**
     * Metodo que normaliza un texto para generar un alias
     *
     * @param nombre
     * @return
     */
    public static String recuperarAliasNombre(String nombre) {

        if (nombre != null) {
            nombre = nombre.toUpperCase();
            nombre = nombre.trim();
            nombre = nombre.replaceAll(" ", "_")
                    .replaceAll(",", "")
                    .replaceAll("[.]", "")
                    .replaceAll(":", "")
                    .replaceAll("=", "");

            nombre = eliminarCaracteresEspeciales(nombre);
            return convertNonAscii(nombre);
        } else {
            return "";
        }
    }

    /**
     * Metodo que normaliza un texto para generar un alias
     *
     * @param nombre
     * @return
     */
    public static String recuperarAliasHerramienta(String nombre) {

        if (nombre != null) {
            nombre = nombre.toUpperCase();
            nombre = nombre.trim();
            nombre = nombre.replaceAll(" ", "_")
                    .replaceAll(",", "")
                    .replaceAll("[.]", "")
                    .replaceAll(":", "")
                    .replaceAll("=", "");
            nombre = eliminarCaracteresEspeciales(nombre);
            return "H_" + convertNonAscii(nombre);
        } else {
            return "";
        }
    }

    /**
     * Metodo que recupera un long de un string
     *
     * @param cadena
     * @return
     */
    public static Long recuperarNumeroString(String cadena) {
        Pattern numerosPattern = Pattern.compile("\\d+");
        Long nro = 0L;
        String rst = "";
        if (cadena != null) {
            Matcher m = numerosPattern.matcher(cadena);
            while (m.find()) {
                rst += m.group();
            }
            if (!rst.equals("")) {
                nro += Long.parseLong(rst);
            }

        }
        return nro;
    }

    /**
     * Metodo que recupera un int de un string
     *
     * @param cadena
     * @return
     */
    public static int recuperarNumeroStringToInt(String cadena) {
        Pattern numerosPattern = Pattern.compile("\\d+");
        int nro = 0;
        String rst = "";
        if (cadena != null) {
            Matcher m = numerosPattern.matcher(cadena);
            while (m.find()) {
                rst += m.group();
            }
            if (!rst.equals("")) {
                nro += Integer.parseInt(rst);
            }

        }
        return nro;
    }

    /**
     * Metodo que valida si el contenido de una cadena es numerico
     *
     * @param cadena
     * @return
     */
    public static boolean esNumero(String cadena) {
        Pattern numerosPattern = Pattern.compile("\\d+");
        boolean rst = false;
        if (cadena != null) {
            if (!cadena.equals("")) {
                Matcher m = numerosPattern.matcher(cadena);
                if (m.matches()) {
                    rst = true;
                }
            }
        }
        return rst;
    }

    /**
     * Metodo para convertir a codigo ascci
     *
     * @param s
     * @return
     */
    public static String convertNonAscii(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int pos = UNICODE.indexOf(c);
            if (pos > -1) {
                sb.append(PLAIN_ASCII.charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Metodo para agregar un error a un modelAndView
     *
     * @param bindingResult
     * @param filed
     * @param mensaje
     */
    public static void agregarError(BindingResult bindingResult, String filed, String mensaje) {
        FieldError fieldError = new FieldError(bindingResult.getObjectName(), filed, mensaje);
        bindingResult.addError(fieldError);
    }

    /**
     * Metodo para recuperar el alias de las columas del usuario
     *
     * @param nombre
     * @return
     */
    public static String recuperarAliasColumnasUsuario(String nombre) {

        if (nombre != null) {
            nombre = nombre.toUpperCase();
            nombre = nombre.trim();
            nombre = nombre.replaceAll(" ", "_");
            return "u." + convertNonAscii(nombre);
        } else {
            return "";
        }
    }

    /**
     * Metodo para recuperar el alias de las columnas de las herramientas
     *
     * @param nombre
     * @return
     */
    public static String recuperarAliasColumnasHerramienta(String nombre) {

        if (nombre != null) {
            nombre = nombre.toUpperCase();
            nombre = nombre.trim();
            nombre = nombre.replaceAll(" ", "_");
            return "r." + convertNonAscii(nombre);
        } else {
            return "";
        }
    }

    /**
     * Metodo para recuperar el caracter valido de un caracter codificado
     *
     * @param cadena
     * @return
     */
    public static String decodeHtmlISO88591(String cadena) {

        String rst = cadena.replaceAll("&Aacute;", "\u00C1")
                .replaceAll("&aacute;", "\u00C1")
                .replaceAll("&Aacute;", "\u00E1")
                .replaceAll("&Eacute;", "\u00C9")
                .replaceAll("&eacute;", "\u00E9")
                .replaceAll("&Iacute;", "\u00CD")
                .replaceAll("&iacute;", "\u00ED")
                .replaceAll("&Oacute;", "\u00D3")
                .replaceAll("&oacute;", "\u00F3")
                .replaceAll("&Uacute;", "\u00DA")
                .replaceAll("&uacute;", "\u00FA")
                .replaceAll("&Ntilde;", "\u00D1")
                .replaceAll("&ntilde;", "\u00F1")
                .replace("ÃƒÂ¡", "\u00E1")
                .replace("ÃƒÂ©", "\u00E9")
                .replace("Ãƒ*", "\u00ED")
                .replace("ÃƒÂ³", "\u00F3")
                .replace("ÃƒÂº", "\u00FA")
                .replace("ÃƒÂ�", "\u00C1")
                .replace("Ãƒâ€°", "\u00C9")
                .replace("ÃƒÂ�", "\u00CD")
                .replace("Ãƒâ€œ", "\u00D3")
                .replace("ÃƒÅ¡", "\u00DA")
                .replace("ÃƒÂ±", "\u00F1")
                .replace("Ãƒâ€˜", "\u00D1");

        return rst;
    }

    /**
     * Metdo para eliminar caracteres especiales de una cadena
     *
     * @param cadena
     * @return
     */
    public static String eliminarCaracteresEspeciales(String cadena) {
        String rst = cadena.replaceAll("\\?", "")
                .replaceAll("\\$", "")
                .replaceAll("\\%", "")
                .replaceAll("\\#", "")
                .replaceAll("\\~", "")
                .replaceAll("/", "")
                .replaceAll("|", "")
                .replaceAll("'", "");
        return rst;
    }

    /**
     * Metodo que genera textos aleatorios
     *
     * @param longitud
     * @return
     */
    public static String generarTextoAleatorio(int longitud) {
        String rst = "";
        int letras[][] = new int[3][2];
        letras[0][0] = 97;
        letras[0][1] = 122;
        letras[1][0] = 65;
        letras[1][1] = 90;
        letras[2][0] = 48;
        letras[2][1] = 57;
        Random r = new Random();
        for (int i = 0; i < longitud; i++) {
            int fila = r.nextInt(letras.length);
            rst += (char) ((int) (Math.random() * (letras[fila][1] - letras[fila][0])) + letras[fila][0]);
        }
        return rst;
    }

    public String dateTostring(String fch) {
        String[] array = fch.split("/");
        int dia = Integer.parseInt(array[0]);
        int mes = Integer.parseInt(array[1]);
        int anio = Integer.parseInt(array[2]);
        return anio + "-" + mes + "-" + dia;
    }

    /**
     *
     * @param date fecha a convertir
     * @return La fecha convertida a String
     */
    public static String dateTostring(Date date) {

        String formato = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);

        return dateFormat.format(date);

    }
    /**
     * Devuelve una fecha con string  formato yyyy-MM-dd
     * @param fecha
     * @return 
     */
    public static Date StringDateToDate(String fecha){
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
	try {
		date = formatter.parse(fecha);
		System.out.println(date);
		System.out.println(formatter.format(date));
 
	} catch (ParseException e) {
            System.out.println("StringDateToDate "+e.getMessage());
	}
        
        return date;
    }
    public static String dateTostringFechacompleta(Date date){
        String formato = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);

        return dateFormat.format(date);
    }
    /**
     *
     * @param date fecha a procesar
     * @return
     */
    public static int obtenerAnio(Date date) {

        if (null == date) {

            return 0;

        } else {

            String formato = "yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));

        }

    }

    /**
     *
     * @param date Fecha a procesar
     * @return El mes de la fecha
     */
    public static int obtenerMes(Date date) {

        if (null == date) {

            return 0;

        } else {

            String formato = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));

        }

    }

    /**
     *
     * @param date Fecha a procesar
     * @return El día de la fecha
     */
    public static int obtenerDia(Date date) {

        if (null == date) {

            return 0;

        } else {

            String formato = "dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return Integer.parseInt(dateFormat.format(date));

        }

    }

    /**
     * Función que retorna el Lunes de la se
     * @return El Lunes de la semana actual 
     */
    public static Date lunesSemana() {
        Calendar calendarioInicioSemana = Calendar.getInstance();
        calendarioInicioSemana.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendarioInicioSemana.set(Calendar.HOUR_OF_DAY, 0);
        calendarioInicioSemana.set(Calendar.MINUTE, 0);
        calendarioInicioSemana.set(Calendar.SECOND, 0);
        
        Date date = calendarioInicioSemana.getTime();
        
        return date;
    }
   
    /**
     * 
     * @param date
     * @param dias
     * @return La fecha con los días sumados en String
     */
    public static String sumarFechasDias(Date date,int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.DATE, dias);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    
    public static String formatearNumeros(String formato, Double numero) {

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formatea = new DecimalFormat(formato, simbolos);

        return formatea.format(numero);
        
    }
    
    /**
     * Esta funcion convierte una cadena en numero
     * @param cadena
     * @return 
     */
    public static Long convertToLong(String cadena){
        Long numero = null;
        try {
            numero = Long.parseLong(cadena);
        } catch (NumberFormatException e) {
            System.out.println("convertToLong No se pudo convertir::"+e.getMessage());
        }
        return numero;
    }
}
