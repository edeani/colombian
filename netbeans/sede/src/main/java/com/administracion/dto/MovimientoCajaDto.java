/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dto;

/**
 *
 * @author Jose Efren
 */
public class MovimientoCajaDto {
    
    private String fecha;
    private Double deber;
    private Double haber;
    private Double saldo;
    private String concepto;
    private Long idcomprobante;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getDeber() {
        return deber;
    }

    public void setDeber(Double deber) {
        this.deber = deber;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Long getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(Long idcomprobante) {
        this.idcomprobante = idcomprobante;
    }
    
    
}
