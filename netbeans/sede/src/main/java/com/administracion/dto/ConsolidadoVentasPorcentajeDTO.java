/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dto;

/**
 *
 * @author Anlod
 */
public class ConsolidadoVentasPorcentajeDTO {
    
    private String subsede;
    private Float valorTotal;
    private Float valorPorcentaje;
    private Float porcentaje;

    public String getSubsede() {
        return subsede;
    }

    public void setSubsede(String subsede) {
        this.subsede = subsede;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Float getValorPorcentaje() {
        return valorPorcentaje;
    }

    public void setValorPorcentaje(Float valorPorcentaje) {
        this.valorPorcentaje = valorPorcentaje;
    }
    
    
}
