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
public enum EstadosEnum {
    Activo("A"),
    Inactivo("I"),
    Bloqueado("B");
    
    private final String estado;
    
    private EstadosEnum(String estado) {   
        this.estado=estado;
    }
    
    public String getEstado(){
        return estado;
    }
}
