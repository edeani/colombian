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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edeani
 */
@Entity
@Table(name = "detalle_compra")
public class DetalleCompra implements Serializable {
    private static long serialVersionUID = 1L;
      
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_compra")
    private Long id_compra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "codigo_producto_inventario")
    @ManyToOne
    private Long codigo_producto_inventario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencial_compra")
    private int secuencialCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_producto")
    private Double valorProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_unidades")
    private Long numeroUnidades;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_proveedor")
    private Long codigoProveedor;

    public int getSecuencialCompra() {
        return secuencialCompra;
    }

    public void setSecuencialCompra(int secuencialCompra) {
        this.secuencialCompra = secuencialCompra;
    }

    public Double getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(Double valorProducto) {
        this.valorProducto = valorProducto;
    }

    public Long getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(Long numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public Long getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(Long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    /**
     * @return the consecutivo
     */
   /* public Long getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */ /**
    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    } */

    /**
     * @return the id_compra
     */
    public Long getNumeroCompra() {
        return id_compra;
    }

    /**
     * @param compras the id_compra to set
     */
    public void setNumeroCompra(Long compras) {
        this.id_compra = compras;
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
     * @return the codigo_producto_inventario
     */
    public Long getProducto() {
        return codigo_producto_inventario;
    }

    /**
     * @param producto the codigo_producto_inventario to set
     */
    public void setProducto(Long producto) {
        this.codigo_producto_inventario = producto;
    }

}
