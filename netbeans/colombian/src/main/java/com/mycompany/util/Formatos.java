/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author joseefren
 */
public class Formatos {
    
    public String dateTostring(String fch)
    {
        String[] array = fch.split("/");
         int dia = Integer.parseInt(array[0]);
         int mes = Integer.parseInt(array[1]);
         int anio = Integer.parseInt(array[2]);
         return anio+"-"+mes+"-"+dia;
    }
    
     public String fechaMenos(String fch, int dias){
     
        
         String[] array = fch.split("/");
         int dia = Integer.parseInt(array[0]) - dias;
         int mes = Integer.parseInt(array[1]);
         int anio = Integer.parseInt(array[2]);
         return anio+"-"+mes+"-"+dia;
         
   }  
     
     public  String numeroToStringFormato(Double valor){
        
        NumberFormat formato = NumberFormat.getInstance(Locale.US);
        return formato.format(valor);
     }
     
      public  String numeroToStringFormato(Float valor){
        
        NumberFormat formato = NumberFormat.getInstance(Locale.US);
        return formato.format(valor);
     }
     
     public  String numeroToStringFormato(Long valor){
        
        NumberFormat formato = NumberFormat.getInstance(Locale.US);
        return formato.format(valor);
     }
     
     public  String numeroToStringFormato(Integer valor){
        
        NumberFormat formato = NumberFormat.getInstance(Locale.US);
        return formato.format(valor);
     }
     
     public Double stringToNumeroFormato(String cadena)
     {
         Double valor = null;
         if(cadena != null)
         {
             cadena = cadena.replace(",", "");
             valor = Double.parseDouble(cadena);
         }
         
         return valor;
     }
}
