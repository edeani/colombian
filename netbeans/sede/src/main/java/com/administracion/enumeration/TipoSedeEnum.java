/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.enumeration;

/**
 *
 * @author EderArmando
 */
public enum TipoSedeEnum {
    NORMAL(1),
    PRINCIPAL(2);
    
    private final Integer tipo_sede;
    
    private TipoSedeEnum(Integer tipo_sede){
        this.tipo_sede=tipo_sede;
    }

    public Integer getTipo_sede() {
        return tipo_sede;
    }
    
    
}
