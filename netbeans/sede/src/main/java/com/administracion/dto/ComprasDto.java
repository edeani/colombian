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
public class ComprasDto {
    private Long idCompra;
    private String fechaCompra;
    private String estadoCompra;
    private Double valorTotal;
    private Long codigoProveedor;
    private String estadoCompraProveedor;
    private Double saldo;
    private String fechaVencimiento;
    private Long idFacturaCompra;
    private Long idSede;

    /**
     * @return the idCompra
     */
    public Long getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the fechaCompra
     */
    public String getFechaCompra() {
        return fechaCompra;
    }

    /**
     * @param fechaCompra the fechaCompra to set
     */
    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * @return the estadoCompra
     */
    public String getEstadoCompra() {
        return estadoCompra;
    }

    /**
     * @param estadoCompra the estadoCompra to set
     */
    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    /**
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the codigoProveedor
     */
    public Long getCodigoProveedor() {
        return codigoProveedor;
    }

    /**
     * @param codigoProveedor the codigoProveedor to set
     */
    public void setCodigoProveedor(Long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    /**
     * @return the estadoCompraProveedor
     */
    public String getEstadoCompraProveedor() {
        return estadoCompraProveedor;
    }

    /**
     * @param estadoCompraProveedor the estadoCompraProveedor to set
     */
    public void setEstadoCompraProveedor(String estadoCompraProveedor) {
        this.estadoCompraProveedor = estadoCompraProveedor;
    }

    /**
     * @return the saldo
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the fechaVencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Long getIdFacturaCompra() {
        return idFacturaCompra;
    }

    public void setIdFacturaCompra(Long idFacturaCompra) {
        this.idFacturaCompra = idFacturaCompra;
    }

    public Long getIdSede() {
        return idSede;
    }

    public void setIdSede(Long idSede) {
        this.idSede = idSede;
    }
}
