/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enums;

/**
 *
 * @author EderArmando
 */
public enum EnumClasePago {
    PAGO_TARJETA("Pagos Tarjeta"),
    DESCUENTO("Descuentos");
    
    private final String texto;
    
    EnumClasePago(String texto){
        this.texto =texto;
    }
    
    public String texto(){
        return this.texto;
    }
}
