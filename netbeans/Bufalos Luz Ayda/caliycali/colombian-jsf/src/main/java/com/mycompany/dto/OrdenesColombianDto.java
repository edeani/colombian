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
public class OrdenesColombianDto {
    
    
    private Date fecha;
    private String dia;
    private Double domicilios;
    private Long valor_total;

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
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    public Double getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(Double domicilios) {
        this.domicilios = domicilios;
    }

    public Long getValor_total() {
        return valor_total;
    }

    public void setValor_total(Long valor_total) {
        this.valor_total = valor_total;
    }

  

    
    
}
