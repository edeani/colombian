/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EderArmando
 */
@Embeddable
public class DetalleFacturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "numero_factura")
    private long numeroFactura;
    @Basic(optional = false)
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Basic(optional = false)
    @Column(name = "codigo_producto_inventario")
    private int codigoProductoInventario;
    @Basic(optional = false)
    @Column(name = "secuencial_factura")
    private int secuencialFactura;
    @Basic(optional = false)
    @Column(name = "codigo_proveedor")
    private long codigoProveedor;

    public DetalleFacturaPK() {
    }

    public DetalleFacturaPK(long numeroFactura, Date fechaFactura, int codigoProductoInventario, int secuencialFactura, long codigoProveedor) {
        this.numeroFactura = numeroFactura;
        this.fechaFactura = fechaFactura;
        this.codigoProductoInventario = codigoProductoInventario;
        this.secuencialFactura = secuencialFactura;
        this.codigoProveedor = codigoProveedor;
    }

    public long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getCodigoProductoInventario() {
        return codigoProductoInventario;
    }

    public void setCodigoProductoInventario(int codigoProductoInventario) {
        this.codigoProductoInventario = codigoProductoInventario;
    }

    public int getSecuencialFactura() {
        return secuencialFactura;
    }

    public void setSecuencialFactura(int secuencialFactura) {
        this.secuencialFactura = secuencialFactura;
    }

    public long getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }
}
