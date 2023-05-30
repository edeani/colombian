/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.NumberFormat;
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

    /**
     *
     * @param date fecha a convertir
     * @return La fecha convertida a String
     */
    public String dateTostring(Date date) {

        String formato = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);

        return dateFormat.format(date);

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
     *
     */
    public Date extractDateResultSet(ResultSet rs, String fieldName) {
        Date newDate = null;
        try {
            if (Objects.nonNull(rs.getDate(fieldName))) {
                newDate = java.sql.Date.valueOf(rs.getObject(fieldName, LocalDate.class));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Formatos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQLException " + ex.getMessage());
        }

        return newDate;
    }

    public boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
