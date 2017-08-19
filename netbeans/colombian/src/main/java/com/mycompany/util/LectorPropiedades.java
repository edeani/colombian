/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;
import java.util.Properties;

/**
 *
 * @author joseefren
 */
public class LectorPropiedades {
    
    //propiedad que deseo obtener
    private String propiedad;
    //Archivo que deseo leer
    private String archivo;

    //Leo la propiedad de un archivo propertie
    public String leerPropiedad()
    {
        String valorPropiedad = "";
        try {
            Properties propertie = new Properties();
            propertie.load(LectorPropiedades.class.getResource(archivo).openStream());
            valorPropiedad = propertie.getProperty(propiedad);
        } catch (Exception e) {
        }
        return valorPropiedad;
    }
    
    //Obtengo el nombre  de la persistencia
    public String getPersitencia(String propertie)
    {
       String array [] = null;
       if(propertie != null ){ 
       array = propertie.split(",");
       return array[0];
       }
       
       return "";

    }
    
    //Devuelo el nombre de base de datos para una conexion normal
    public String getBaseDatos(String propertie)
    {
       String array [] = null;
       if(propertie != null ){ 
       array = propertie.split(",");
       return array[1]+"/"+array[2]; 
       }
       
       return "";
    }
    
    /**
     * @return the propiedad
     */
    public String getPropiedad() {
        return propiedad;
    }

    /**
     * @param propiedad the propiedad to set
     */
    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    
    
}
