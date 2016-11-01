/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.enumeration;

/**
 *
 * @author edeani
 */
public enum ExtencionesEnum {
    JPG(".jpg"),
    PNG(".png"),
    GIF(".gif");
    
    private final String ext; 
    private ExtencionesEnum(String extension) {   
        this.ext=extension;
    }
    
    public String getExt(){
        return ext;
    }
}
