/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joseefren
 */
public class Formatos {

    public String dateTostring(String fch) {
        String[] array = fch.split("/");
        int dia = Integer.parseInt(array[0]);
        int mes = Integer.parseInt(array[1]);
        int anio = Integer.parseInt(array[2]);
        return anio + "-" + mes + "-" + dia;
    }

    public String fechaMenos(String fch, int dias) {

        String[] array = fch.split("/");
        int dia = Integer.parseInt(array[0]) - dias;
        int mes = Integer.parseInt(array[1]);
        int anio = Integer.parseInt(array[2]);
        return anio + "-" + mes + "-" + dia;

    }

    public String numeroToStringFormato(Double valor) {

        NumberFormat formato = NumberFormat.getInstance(Locale.US);
        return formato.format(valor);
    }

    public String numeroToStringFormato(Float valor) {

        NumberFormat formato = NumberFormat.getInstance(Locale.US);
        return formato.format(valor);
    }

    public String numeroToStringFormato(Long valor) {

        NumberFormat formato = NumberFormat.getInstance(Locale.US);
        return formato.format(valor);
    }

    public String numeroToStringFormato(Integer valor) {

        NumberFormat formato = NumberFormat.getInstance(Locale.US);
        return formato.format(valor);
    }

    public Double stringToNumeroFormato(String cadena) {
        Double valor = null;
        if (cadena != null) {
            cadena = cadena.replace(",", "");
            valor = Double.parseDouble(cadena);
        }

        return valor;
    }

    /**
     * Fix for ResultSet when is getting a Date
     * */
    public Date extractDateResultSet(ResultSet rs, String fieldName) {
        Date newDate = null;
        try {
            if (Objects.nonNull(rs.getDate(fieldName))) {
                newDate = java.sql.Date.valueOf(rs.getObject(fieldName, LocalDate.class));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Formatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newDate;
    }

    public Date StringDateToDate(String fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(fecha);
        } catch (ParseException e) {
             Logger.getLogger(Formatos.class.getName()).log(Level.SEVERE, null, e);
        }

        return date;
    }
}
