/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

/**
 *
 * @author user
 */
public class DetalleCompraDTO {
    
    private String factura;
    private String totalFactura;
    private String numeroFactura;
    private String codigoProveedor;
    private String nombreProveedor;
    private String fechaVencimiento;
    private String fecha;
    private String estadoCompraProveedor;
    private String impresora;
    private Double saldo;
    private Long idFacturaCompra;
    private Long idsede;
    private Integer idsedepoint;
    private Double totalFacturaAnterior;
    /**
     * @return the compra
     */
    public String getFactura() {
        return factura;
    }

    /**
     * @param factura
     */
    public void setFactura(String factura) {
        this.factura = factura;
    }

    /**
     * @return the totalFactura
     */
    public String getTotalFactura() {
        return totalFactura;
    }

    /**
     * @param totalFactura the totalFactura to set
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
     * @return the codigoProveedor
     */
    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    /**
     * @param codigoProveedor the codigoProveedor to set
     */
    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstadoCompraProveedor() {
        return estadoCompraProveedor;
    }

    public void setEstadoCompraProveedor(String estadoCompraProveedor) {
        this.estadoCompraProveedor = estadoCompraProveedor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Long getIdFacturaCompra() {
        return idFacturaCompra;
    }

    public void setIdFacturaCompra(Long idFacturaCompra) {
        this.idFacturaCompra = idFacturaCompra;
    }

    public Long getIdsede() {
        return idsede;
    }

    public void setIdsede(Long idsede) {
        this.idsede = idsede;
    }

    public String getImpresora() {
        return impresora;
    }

    public void setImpresora(String impresora) {
        this.impresora = impresora;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getIdsedepoint() {
        return idsedepoint;
    }

    public void setIdsedepoint(Integer idsedepoint) {
        this.idsedepoint = idsedepoint;
    }

    public Double getTotalFacturaAnterior() {
        return totalFacturaAnterior;
    }

    public void setTotalFacturaAnterior(Double totalFacturaAnterior) {
        this.totalFacturaAnterior = totalFacturaAnterior;
    }
        
}
