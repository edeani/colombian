/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.util;

/**
 *
 * @author Jose Efren
 */
public class GenericService {
    private LectorPropiedades propiedades;
    
    public GenericService(){
        propiedades =  new LectorPropiedades();
    }

    public LectorPropiedades getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(LectorPropiedades propiedades) {
        this.propiedades = propiedades;
    }
}
