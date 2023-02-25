/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.dto;

/**
 *
 * @author edeani
 */
public class ReporteInventarioDTO {
    
    private String codigoProducto;
    private String descripcionProducto;
    private String stockFinal;

    /**
     * @return the codigoProducto
     */
    public String getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * @param codigoProducto the codigoProducto to set
     */
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
     * @return the descripcionProducto
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * @param descripcionProducto the descripcionProducto to set
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     * @return the stockFinal
     */
    public String getStockFinal() {
        return stockFinal;
    }

    /**
     * @param stockFinal the stockFinal to set
     */
    public void setStockFinal(String stockFinal) {
        this.stockFinal = stockFinal;
    }
    
    
    
}
