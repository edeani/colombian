/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enums;

/**
 *
 * @author edeani
 */
public enum EnumTipoOrden {
    MESAS(1,"Mesas"),
    LLEVAR(2,"Llevar"),
    DOMICILIOS(3,"Domicilios");
    
    private final String tipo;
    private final Integer id;
    
    EnumTipoOrden(Integer id,String tipo){
        this.tipo = tipo;
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getId() {
        return id;
    }
    
    
}
