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
public class ReporteConsolidadoDto {
    
    private String sede;
    private Long consignacion;
    private Long ventas;
    private Long compras;
    private Long gastos;

    /**
     * @return the sede
     */
    public String getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(String sede) {
        this.sede = sede;
    }

    /**
     * @return the consignacion
     */
    public Long getConsignacion() {
        return consignacion;
    }

    /**
     * @param consignacion the consignacion to set
     */
    public void setConsignacion(Long consignacion) {
        this.consignacion = consignacion;
    }

    /**
     * @return the ventas
     */
    public Long getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(Long ventas) {
        this.ventas = ventas;
    }

    /**
     * @return the compras
     */
    public Long getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(Long compras) {
        this.compras = compras;
    }

    /**
     * @return the gastos
     */
    public Long getGastos() {
        return gastos;
    }

    /**
     * @param gastos the gastos to set
     */
    public void setGastos(Long gastos) {
        this.gastos = gastos;
    }
    
    
    
    
}
