/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edeani
 */
@Entity
@Table(name = "detalle_factura")
public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Column(name="numero_detalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroDetalle;
    @Basic(optional = false)
    @Column(name = "numero_factura")
    private Long numeroFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_producto_inventario")
    private Inventario producto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "secuencial_factura")
    private int secuencialFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_producto")
    private double valorProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_unidades")
    private Long numeroUnidades;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_proveedor")
    private Long codigoProveedor;
    

    public double getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(double valorProducto) {
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
     * @return the numeroDetalle
     */
    public Long getNumeroDetalle() {
        return numeroDetalle;
    }

    /**
     * @param numeroDetalle the numeroDetalle to set
     */
    public void setNumeroDetalle(Long numeroDetalle) {
        this.numeroDetalle = numeroDetalle;
    }

    /**
     * @return the numeroFactura
     */
    public Long getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * @param numeroFactura the numeroFactura to set
     */
    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * @return the fechaFactura
     */
    public Date getFechaFactura() {
        return fechaFactura;
    }

    /**
     * @param fechaFactura the fechaFactura to set
     */
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    /**
     * @return the producto
     */
    public Inventario getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Inventario producto) {
        this.producto = producto;
    }

    /**
     * @return the secuencialFactura
     */
    public int getSecuencialFactura() {
        return secuencialFactura;
    }

    /**
     * @param secuencialFactura the secuencialFactura to set
     */
    public void setSecuencialFactura(int secuencialFactura) {
        this.secuencialFactura = secuencialFactura;
    }

}
