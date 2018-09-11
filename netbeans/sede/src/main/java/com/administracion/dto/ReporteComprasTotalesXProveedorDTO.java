/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

import java.util.Date;

/**
 *
 * @author user
 */
public class ReporteComprasTotalesXProveedorDTO {
    
    private Long numero_compra;
    private Date fecha_compra;
    private Double valor_total;

    /**
     * @return the numero_compra
     */
    public Long getNumero_compra() {
        return numero_compra;
    }

    /**
     * @param numero_compra the numero_compra to set
     */
    public void setNumero_compra(Long numero_compra) {
        this.numero_compra = numero_compra;
    }

    /**
     * @return the fecha_compra
     */
    public Date getFecha_compra() {
        return fecha_compra;
    }

    /**
     * @param fecha_compra the fecha_compra to set
     */
    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }
    /**
     * @return the valor_total
     */
    public Double getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }
    
    
    
}
