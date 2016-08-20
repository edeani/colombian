/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.dto;



/**
 *
 * @author user
 */
public class InventarioDTO {
    
    private String codigoProductoInventario;
    private String fechaInicial;
    private String fechaFinal;
    private String stockMinimo;
    private String stockHoy;
    private String stockReal;
    private String descripcionProducto;
    private String promedio;
    /**
     * @return the codigoProductoInventario
     */
    public String getCodigoProductoInventario() {
        return codigoProductoInventario;
    }

    /**
     * @param codigoProductoInventario the codigoProductoInventario to set
     */
    public void setCodigoProductoInventario(String codigoProductoInventario) {
        this.codigoProductoInventario = codigoProductoInventario;
    }

    /**
     * @return the fechaInicial
     */
    public String getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public String getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the stockMinimo
     */
    public String getStockMinimo() {
        return stockMinimo;
    }

    /**
     * @param stockMinimo the stockMinimo to set
     */
    public void setStockMinimo(String stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    /**
     * @return the stockHoy
     */
    public String getStockHoy() {
        return stockHoy;
    }

    /**
     * @param stockHoy the stockHoy to set
     */
    public void setStockHoy(String stockHoy) {
        this.stockHoy = stockHoy;
    }

    /**
     * @return the stockReal
     */
    public String getStockReal() {
        return stockReal;
    }

    /**
     * @param stockReal the stockReal to set
     */
    public void setStockReal(String stockReal) {
        this.stockReal = stockReal;
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
     * @return the promedio
     */
    public String getPromedio() {
        return promedio;
    }

    /**
     * @param promedio the promedio to set
     */
    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }
    
}
