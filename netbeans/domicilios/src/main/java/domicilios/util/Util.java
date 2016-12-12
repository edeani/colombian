/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class Util {
    public static Integer firstItemPage(Integer page,Integer cantidad){
        return cantidad*(page-1);
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
