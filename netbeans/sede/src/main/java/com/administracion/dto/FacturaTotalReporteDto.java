/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dto;

import java.util.Date;

/**
 *
 * @author Jose Efren
 */
public class FacturaTotalReporteDto {
    private Long numero_factura;
    private Date fecha_factura;
    private String sede;
    private Long valor_total;

    /**
     * @return the numero_factura
     */
    public Long getNumero_factura() {
        return numero_factura;
    }

    /**
     * @param numero_factura the numero_factura to set
     */
    public void setNumero_factura(Long numero_factura) {
        this.numero_factura = numero_factura;
    }

    /**
     * @return the fecha_factura
     */
    public Date getFecha_factura() {
        return fecha_factura;
    }

    /**
     * @param fecha_factura the fecha_factura to set
     */
    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    /**
     * @return the sede
     */
    public String getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(String sede) {
        this.sede = sede;
    }

    /**
     * @return the valor_total
     */
    public Long getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(Long valor_total) {
        this.valor_total = valor_total;
    }
}
