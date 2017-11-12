/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.util;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author joseefren
 */
public class LectorPropiedades {
  
    
    //Leo la propiedad de un archivo propertie
    public String leerPropiedad(String archivo,String propiedad)
    {
        String valorPropiedad = "";
        try {
            Properties propertie = new Properties();
            propertie.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(archivo));
            valorPropiedad = propertie.getProperty(propiedad);
        } catch (IOException e) {
            System.out.println("Error leerPropiedad::"+e.getMessage());
        }
        return valorPropiedad;
    }
 
}
