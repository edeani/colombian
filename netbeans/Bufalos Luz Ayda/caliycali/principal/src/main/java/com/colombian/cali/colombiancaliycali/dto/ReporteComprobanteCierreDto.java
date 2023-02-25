/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.dto;

/**
 *
 * @author Jose Efren
 */
public class ReporteComprobanteCierreDto {
    private String idCuenta;
    private String concepto;
    private Double haber;
    private Double deber;

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Double getDeber() {
        return deber;
    }

    public void setDeber(Double deber) {
        this.deber = deber;
    }
    
}
