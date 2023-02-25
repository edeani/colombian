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
public enum GeneralPropertiesSedeEnum {
    
    ADDITIONAL_PERCENTAGE_INVENTORY("additional_perc_inventory");
    
    private final String propertie;
    
    private GeneralPropertiesSedeEnum(String propertie) {   
        this.propertie=propertie;
    }

    public String getPropertie() {
        return propertie;
    }
    
    
}
