/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public class NotasDto {

    private Date fecha;
    private  List<NotasDetalleDto> detallesNota;
   

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<NotasDetalleDto> getDetallesNota() {
        return detallesNota;
    }

    public void setDetallesNota(List<NotasDetalleDto> detallesNota) {
        this.detallesNota = detallesNota;
    }

    
}