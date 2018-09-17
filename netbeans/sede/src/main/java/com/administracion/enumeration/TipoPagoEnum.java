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
public enum TipoPagoEnum {
    PAGOS_TERCEROS(1),
    PAGOS_PROVEEDOR(2),
    PAGOS_PORCENTAJE(3);
    
    private final Integer tipo_pago;
    
    private TipoPagoEnum(Integer tipo_pago){
        this.tipo_pago = tipo_pago;
    }

    public Integer getTipo_pago() {
        return tipo_pago;
    }
    
    
}
