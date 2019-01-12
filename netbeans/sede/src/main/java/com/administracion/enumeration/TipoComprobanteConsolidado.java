/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.enumeration;

/**
 *
 * @author 10 Spring Creators
 */
public enum TipoComprobanteConsolidado {
    CAJA_MENOR(1),
    PAGOS(2),
    CIERRES(3),
    NOTA_CREDITO(4),
    NOTA_DEBITO(5);
    
    private final Integer tipo_comprobante;
    
    private TipoComprobanteConsolidado(Integer tipo_comprobante){
        
        this.tipo_comprobante = tipo_comprobante;
        
    }

    public Integer getTipo_comprobante() {
        return tipo_comprobante;
    }
    
    
}
