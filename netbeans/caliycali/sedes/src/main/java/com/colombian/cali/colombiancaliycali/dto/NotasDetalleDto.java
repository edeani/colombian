/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.dto;

/**
 *
 * @author EderArmando
 */
public class NotasDetalleDto {
    
    private Long cons;
    private String cuenta;
    private String concepto;
    private String detalle;
    private Double total;

    public Long getCons() {
        return cons;
    }

    public void setCons(Long cons) {
        this.cons = cons;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
      
}
