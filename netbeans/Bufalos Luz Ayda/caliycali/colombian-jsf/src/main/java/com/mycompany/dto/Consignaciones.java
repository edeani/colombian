/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import java.util.Date;

/**
 *
 * @author joseefren
 */
public class Consignaciones {
    private static final long serialVersionUID = 1L;
   
    private Integer idconsignacion;
    
    private Date fechaConsignacion;
    
    private float valorConsignacion;
    
    private String nombreCajero;
    
    private Date fecha;

    public Consignaciones() {
    }

    public Consignaciones(Integer idconsignacion) {
        this.idconsignacion = idconsignacion;
    }

    public Consignaciones(Integer idconsignacion, Date fechaConsignacion, float valorConsignacion, String nombreCajero, Date fecha) {
        this.idconsignacion = idconsignacion;
        this.fechaConsignacion = fechaConsignacion;
        this.valorConsignacion = valorConsignacion;
        this.nombreCajero = nombreCajero;
        this.fecha = fecha;
    }

    public Integer getIdconsignacion() {
        return idconsignacion;
    }

    public void setIdconsignacion(Integer idconsignacion) {
        this.idconsignacion = idconsignacion;
    }

    public Date getFechaConsignacion() {
        return fechaConsignacion;
    }

    public void setFechaConsignacion(Date fechaConsignacion) {
        this.fechaConsignacion = fechaConsignacion;
    }

    public float getValorConsignacion() {
        return valorConsignacion;
    }

    public void setValorConsignacion(float valorConsignacion) {
        this.valorConsignacion = valorConsignacion;
    }

    public String getNombreCajero() {
        return nombreCajero;
    }

    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
    
}
