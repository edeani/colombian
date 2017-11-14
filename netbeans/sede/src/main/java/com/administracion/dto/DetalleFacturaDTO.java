/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

/**
 *
 * @author edeani
 */
public class DetalleFacturaDTO {
   
    private String factura;
    private String totalFactura;
    private String numeroFactura;
    private Long sede;
    private String fechaFactura;

    /**
     * @return the factura
     */
    public String getFactura() {
        
        return factura;
        
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(String factura) {
        this.factura = factura;
    }

    /**
     * @return the valorTotal
     */
    public String getTotalFactura() {
        return totalFactura;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setTotalFactura(String totalFactura) {
        this.totalFactura = totalFactura;
    }

    /**
     * @return the numeroFactura
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * @param numeroFactura the numeroFactura to set
     */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * @return the sede
     */
    public Long getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(Long sede) {
        this.sede = sede;
    }

    /**
     * @return the fechaFactura
     */
    public String getFechaFactura() {
        return fechaFactura;
    }

    /**
     * @param fechaFactura the fechaFactura to set
     */
    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
}
