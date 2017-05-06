/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joseefren
 */
@Entity
@Table(name = "detalle_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d"),
    @NamedQuery(name = "DetalleFactura.findByNumeroFactura", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "DetalleFactura.findByFechaFactura", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.fechaFactura = :fechaFactura"),
    @NamedQuery(name = "DetalleFactura.findByCodigoProductoInventario", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.codigoProductoInventario = :codigoProductoInventario"),
    @NamedQuery(name = "DetalleFactura.findBySecuencialFactura", query = "SELECT d FROM DetalleFactura d WHERE d.secuencialFactura = :secuencialFactura"),
    @NamedQuery(name = "DetalleFactura.findByValorProducto", query = "SELECT d FROM DetalleFactura d WHERE d.valorProducto = :valorProducto"),
    @NamedQuery(name = "DetalleFactura.findByNumeroUnidades", query = "SELECT d FROM DetalleFactura d WHERE d.numeroUnidades = :numeroUnidades"),
    @NamedQuery(name = "DetalleFactura.findByCodigoProveedor", query = "SELECT d FROM DetalleFactura d WHERE d.detalleFacturaPK.codigoProveedor = :codigoProveedor")})
public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleFacturaPK detalleFacturaPK;
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
    private int numeroUnidades;

    public DetalleFactura() {
    }

    public DetalleFactura(DetalleFacturaPK detalleFacturaPK) {
        this.detalleFacturaPK = detalleFacturaPK;
    }

    public DetalleFactura(DetalleFacturaPK detalleFacturaPK, int secuencialFactura, double valorProducto, int numeroUnidades) {
        this.detalleFacturaPK = detalleFacturaPK;
        this.secuencialFactura = secuencialFactura;
        this.valorProducto = valorProducto;
        this.numeroUnidades = numeroUnidades;
    }

    public DetalleFactura(double numeroFactura, Date fechaFactura, int codigoProductoInventario, double codigoProveedor) {
        this.detalleFacturaPK = new DetalleFacturaPK(numeroFactura, fechaFactura, codigoProductoInventario, codigoProveedor);
    }

    public DetalleFacturaPK getDetalleFacturaPK() {
        return detalleFacturaPK;
    }

    public void setDetalleFacturaPK(DetalleFacturaPK detalleFacturaPK) {
        this.detalleFacturaPK = detalleFacturaPK;
    }

    public int getSecuencialFactura() {
        return secuencialFactura;
    }

    public void setSecuencialFactura(int secuencialFactura) {
        this.secuencialFactura = secuencialFactura;
    }

    public double getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(double valorProducto) {
        this.valorProducto = valorProducto;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleFacturaPK != null ? detalleFacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.detalleFacturaPK == null && other.detalleFacturaPK != null) || (this.detalleFacturaPK != null && !this.detalleFacturaPK.equals(other.detalleFacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.DetalleFactura[ detalleFacturaPK=" + detalleFacturaPK + " ]";
    }
    
}
