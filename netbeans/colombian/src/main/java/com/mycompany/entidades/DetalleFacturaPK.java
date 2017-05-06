/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joseefren
 */
@Embeddable
public class DetalleFacturaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_factura")
    private double numeroFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_producto_inventario")
    private int codigoProductoInventario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_proveedor")
    private double codigoProveedor;

    public DetalleFacturaPK() {
    }

    public DetalleFacturaPK(double numeroFactura, Date fechaFactura, int codigoProductoInventario, double codigoProveedor) {
        this.numeroFactura = numeroFactura;
        this.fechaFactura = fechaFactura;
        this.codigoProductoInventario = codigoProductoInventario;
        this.codigoProveedor = codigoProveedor;
    }

    public double getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(double numeroFactura) {
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

    public double getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(double codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroFactura;
        hash += (fechaFactura != null ? fechaFactura.hashCode() : 0);
        hash += (int) codigoProductoInventario;
        hash += (int) codigoProveedor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFacturaPK)) {
            return false;
        }
        DetalleFacturaPK other = (DetalleFacturaPK) object;
        if (this.numeroFactura != other.numeroFactura) {
            return false;
        }
        if ((this.fechaFactura == null && other.fechaFactura != null) || (this.fechaFactura != null && !this.fechaFactura.equals(other.fechaFactura))) {
            return false;
        }
        if (this.codigoProductoInventario != other.codigoProductoInventario) {
            return false;
        }
        if (this.codigoProveedor != other.codigoProveedor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.DetalleFacturaPK[ numeroFactura=" + numeroFactura + ", fechaFactura=" + fechaFactura + ", codigoProductoInventario=" + codigoProductoInventario + ", codigoProveedor=" + codigoProveedor + " ]";
    }
    
}
