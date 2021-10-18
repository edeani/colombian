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
public class ConsignacionesMapper {

    private Date fechaConsignacion;
    private String valorConsignacion;
    private String nombreCajero;

    /**
     * @return the fechaConsignacion
     */
    public Date getFechaConsignacion() {
        return fechaConsignacion;
    }

    /**
     * @param fechaConsignacion the fechaConsignacion to set
     */
    public void setFechaConsignacion(Date fechaConsignacion) {
        this.fechaConsignacion = fechaConsignacion;
    }

    /**
     * @return the valorConsignacion
     */
    public String getValorConsignacion() {
        return valorConsignacion;
    }

    /**
     * @param valorConsignacion the valorConsignacion to set
     */
    public void setValorConsignacion(String valorConsignacion) {
        this.valorConsignacion = valorConsignacion;
    }

    /**
     * @return the nombreCajero
     */
    public String getNombreCajero() {
        return nombreCajero;
    }

    /**
     * @param nombreCajero the nombreCajero to set
     */
    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }
    
}
