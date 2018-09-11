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
public class ReporteTotalCuentasXNivelDto {
    private Long idSede;
    private String sede;
    private Double nivel1;
    private Double nivel2;
    private Double nivel3;
    private Double nivel4;
    private Double nivel5;

    public Double getNivel1() {
        return nivel1;
    }

    public void setNivel1(Double nivel1) {
        this.nivel1 = nivel1;
    }

    public Double getNivel2() {
        return nivel2;
    }

    public void setNivel2(Double nivel2) {
        this.nivel2 = nivel2;
    }

    public Double getNivel3() {
        return nivel3;
    }

    public void setNivel3(Double nivel3) {
        this.nivel3 = nivel3;
    }

    public Double getNivel4() {
        return nivel4;
    }

    public void setNivel4(Double nivel4) {
        this.nivel4 = nivel4;
    }

    public Double getNivel5() {
        return nivel5;
    }

    public void setNivel5(Double nivel5) {
        this.nivel5 = nivel5;
    }

    public Long getIdSede() {
        return idSede;
    }

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
    
}
