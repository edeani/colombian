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
public class CuentasAutoCompletarDto {
 
    private String idCuenta;
    private String nombreCuenta;
    private String value;
    private Long nivel;
    private String tipo;
    private Long clase;
    private String con_padre;
    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getClase() {
        return clase;
    }

    public void setClase(Long clase) {
        this.clase = clase;
    }

    public String getCon_padre() {
        return con_padre;
    }

    public void setCon_padre(String con_padre) {
        this.con_padre = con_padre;
    }
    
}
