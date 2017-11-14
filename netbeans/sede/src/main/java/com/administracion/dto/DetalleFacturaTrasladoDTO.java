/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

/**
 *
 * @author edeani
 */
public class DetalleFacturaTrasladoDTO {
    private String factura;
    private String totalFactura;
    private String numeroFactura;
    private Long sedeOrigen;
    private Long sedeDestino;

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
     * @return the sedeOrigen
     */
    public Long getSedeOrigen() {
        return sedeOrigen;
    }

    /**
     * @param sedeOrigen the sedeOrigen to set
     */
    public void setSedeOrigen(Long sedeOrigen) {
        this.sedeOrigen = sedeOrigen;
    }

    /**
     * @return the sedeDestino
     */
    public Long getSedeDestino() {
        return sedeDestino;
    }

    /**
     * @param sedeDestino the sedeDestino to set
     */
    public void setSedeDestino(Long sedeDestino) {
        this.sedeDestino = sedeDestino;
    }

}
