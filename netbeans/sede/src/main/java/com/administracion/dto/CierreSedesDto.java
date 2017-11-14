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
public class CierreSedesDto {
    private Long consecutivo;

    private Double totalhaber;

    private Double totaldeber;

    private Long idesede;
    
    private String sede;

    private Date fecha;

    private Date fechaGenerado;
    
    private String idcuenta;
    
    private String concepto;

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Double getTotalhaber() {
        return totalhaber;
    }

    public void setTotalhaber(Double totalhaber) {
        this.totalhaber = totalhaber;
    }

    public Double getTotaldeber() {
        return totaldeber;
    }

    public void setTotaldeber(Double totaldeber) {
        this.totaldeber = totaldeber;
    }

    public Long getIdesede() {
        return idesede;
    }

    public void setIdesede(Long idesede) {
        this.idesede = idesede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(Date fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }

    public String getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(String idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    
}
