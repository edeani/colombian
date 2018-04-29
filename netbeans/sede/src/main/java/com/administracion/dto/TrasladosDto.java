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
public class TrasladosDto {

    private Date fechaTraslado;
    private Long idFacturaOrigen;
    private Long idSedeOrigen;
    private String sedeOrigen;
    private Long idFacturaDestino;
    private Long idSedeDestino;
    private String sedeDestino;

    public Date getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(Date fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    public Long getIdFacturaOrigen() {
        return idFacturaOrigen;
    }

    public void setIdFacturaOrigen(Long idFacturaOrigen) {
        this.idFacturaOrigen = idFacturaOrigen;
    }

    public Long getIdSedeOrigen() {
        return idSedeOrigen;
    }

    public void setIdSedeOrigen(Long idSedeOrigen) {
        this.idSedeOrigen = idSedeOrigen;
    }

    public String getSedeOrigen() {
        return sedeOrigen;
    }

    public void setSedeOrigen(String sedeOrigen) {
        this.sedeOrigen = sedeOrigen;
    }

    public Long getIdFacturaDestino() {
        return idFacturaDestino;
    }

    public void setIdFacturaDestino(Long idFacturaDestino) {
        this.idFacturaDestino = idFacturaDestino;
    }

    public Long getIdSedeDestino() {
        return idSedeDestino;
    }

    public void setIdSedeDestino(Long idSedeDestino) {
        this.idSedeDestino = idSedeDestino;
    }

    public String getSedeDestino() {
        return sedeDestino;
    }

    public void setSedeDestino(String sedeDestino) {
        this.sedeDestino = sedeDestino;
    }
    
    

}
