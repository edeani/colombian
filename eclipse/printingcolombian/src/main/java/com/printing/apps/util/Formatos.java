package com.printing.apps.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatos {

	public static String dateTostringFechacompleta(Date date){
        String formato = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);

        return dateFormat.format(date);
    }
	
}
