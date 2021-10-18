/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "compras")
public class Compras implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_compra")
    @Basic(optional=false)
    private Long idCompra;
    
    @Column(name="fecha_compra")
    @Basic(optional=false)
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    
    
    @Column(name="estado_compra")
    private char estadoCompra;
    
    @Column(name="valor_total")
    private Double valorTotal;
    
    @Column(name="codigo_proveedor")
    @Basic(optional=false)
    private Long codigoProveedor;
    @Column(name = "saldo")
    private Double saldo;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "estado_compra_proveedor")
    @Size(max = 1)
    private String estadoCompraProveedor;
    @Column(name = "idfacturacompra")
    private Long idFacturaCompra;
    @Column(name = "idsede")
    private Long idsede;
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
    public Date getFechaCompra() {
        return fechaCompra;
    }

    /**
     * @param fechaCompra the fechaCompra to set
     */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * @return the estadoCompra
     */
    public char getEstadoCompra() {
        return estadoCompra;
    }

    /**
     * @param estadoCompra the estadoCompra to set
     */
    public void setEstadoCompra(char estadoCompra) {
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstadoCompraProveedor() {
        return estadoCompraProveedor;
    }

    public void setEstadoCompraProveedor(String estadoCompraProveedor) {
        this.estadoCompraProveedor = estadoCompraProveedor;
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
    
    
}
