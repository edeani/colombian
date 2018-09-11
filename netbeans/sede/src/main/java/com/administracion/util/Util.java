/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class Util {

    public static Integer firstItemPage(Integer page, Integer cantidad) {
        return cantidad * (page - 1);
    }

    /**
     * Operaciones Date con días
     *
     * @param date
     * @param dias
     * @return
     */
    public static String sumarFechasDias(Date date, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.DATE, dias);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
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
}
