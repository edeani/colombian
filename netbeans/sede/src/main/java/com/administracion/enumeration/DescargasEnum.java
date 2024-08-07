/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.administracion.enumeration;

/**
 *
 * @author Anlod
 */
public enum DescargasEnum {
    EXCEL("excel","xlsx"),
    PDF("pdf","pdf");
    
    
    private final String descarga;
    private final String tipo;
    
    private DescargasEnum(String descarga,String tipo){
        this.descarga = descarga;
        this.tipo=tipo;
    }
    
    public String getDescarga(){
        return  descarga;
    }
    
    public String getTipo(){
        return tipo;
    }
}
