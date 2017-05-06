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
public class FacturaPK implements Serializable {
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
    @Column(name = "codigo_proveedor")
    private double codigoProveedor;

    public FacturaPK() {
    }

    public FacturaPK(double numeroFactura, Date fechaFactura, double codigoProveedor) {
        this.numeroFactura = numeroFactura;
        this.fechaFactura = fechaFactura;
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
        hash += (int) codigoProveedor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaPK)) {
            return false;
        }
        FacturaPK other = (FacturaPK) object;
        if (this.numeroFactura != other.numeroFactura) {
            return false;
        }
        if ((this.fechaFactura == null && other.fechaFactura != null) || (this.fechaFactura != null && !this.fechaFactura.equals(other.fechaFactura))) {
            return false;
        }
        if (this.codigoProveedor != other.codigoProveedor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.FacturaPK[ numeroFactura=" + numeroFactura + ", fechaFactura=" + fechaFactura + ", codigoProveedor=" + codigoProveedor + " ]";
    }
    
}
