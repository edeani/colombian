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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByNumeroFactura", query = "SELECT f FROM Factura f WHERE f.facturaPK.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "Factura.findByFechaFactura", query = "SELECT f FROM Factura f WHERE f.facturaPK.fechaFactura = :fechaFactura"),
    @NamedQuery(name = "Factura.findByEstadoFactura", query = "SELECT f FROM Factura f WHERE f.estadoFactura = :estadoFactura"),
    @NamedQuery(name = "Factura.findByValorTotal", query = "SELECT f FROM Factura f WHERE f.valorTotal = :valorTotal"),
    @NamedQuery(name = "Factura.findByCodigoProveedor", query = "SELECT f FROM Factura f WHERE f.facturaPK.codigoProveedor = :codigoProveedor")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacturaPK facturaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_factura")
    private char estadoFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private double valorTotal;

    public Factura() {
    }

    public Factura(FacturaPK facturaPK) {
        this.facturaPK = facturaPK;
    }

    public Factura(FacturaPK facturaPK, char estadoFactura, double valorTotal) {
        this.facturaPK = facturaPK;
        this.estadoFactura = estadoFactura;
        this.valorTotal = valorTotal;
    }

    public Factura(double numeroFactura, Date fechaFactura, double codigoProveedor) {
        this.facturaPK = new FacturaPK(numeroFactura, fechaFactura, codigoProveedor);
    }

    public FacturaPK getFacturaPK() {
        return facturaPK;
    }

    public void setFacturaPK(FacturaPK facturaPK) {
        this.facturaPK = facturaPK;
    }

    public char getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(char estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaPK != null ? facturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.facturaPK == null && other.facturaPK != null) || (this.facturaPK != null && !this.facturaPK.equals(other.facturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Factura[ facturaPK=" + facturaPK + " ]";
    }
    
}
