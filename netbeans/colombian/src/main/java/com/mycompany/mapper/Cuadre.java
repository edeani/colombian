/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mapper;

import java.util.Date;

/**
 *
 * @author joseefren
 */
public class Cuadre {
    private Date fecha;
    private String valorVentas;
    private String valorGastos;
    private String valorConsignaciones;
    private String ValorCajaReal;
    private String valorPagosTarjeta;
    private String valorDescuentos;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the valorVentas
     */
    public String getValorVentas() {
        return valorVentas;
    }

    /**
     * @param valorVentas the valorVentas to set
     */
    public void setValorVentas(String valorVentas) {
        this.valorVentas = valorVentas;
    }

    /**
     * @return the valorGastos
     */
    public String getValorGastos() {
        return valorGastos;
    }

    /**
     * @param valorGastos the valorGastos to set
     */
    public void setValorGastos(String valorGastos) {
        this.valorGastos = valorGastos;
    }

    /**
     * @return the valorConsignaciones
     */
    public String getValorConsignaciones() {
        return valorConsignaciones;
    }

    /**
     * @param valorConsignaciones the valorConsignaciones to set
     */
    public void setValorConsignaciones(String valorConsignaciones) {
        this.valorConsignaciones = valorConsignaciones;
    }

    /**
     * @return the ValorCajaReal
     */
    public String getValorCajaReal() {
        return ValorCajaReal;
    }

    /**
     * @param ValorCajaReal the ValorCajaReal to set
     */
    public void setValorCajaReal(String ValorCajaReal) {
        this.ValorCajaReal = ValorCajaReal;
    }

    public String getValorPagosTarjeta() {
        return valorPagosTarjeta;
    }

    public void setValorPagosTarjeta(String valorPagosTarjeta) {
        this.valorPagosTarjeta = valorPagosTarjeta;
    }

    public String getValorDescuentos() {
        return valorDescuentos;
    }

    public void setValorDescuentos(String valorDescuentos) {
        this.valorDescuentos = valorDescuentos;
    }
    
    
    
}
