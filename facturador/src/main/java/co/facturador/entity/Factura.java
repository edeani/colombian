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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "factura")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacturaPK facturaPK;
    @Basic(optional = false)
    @Column(name = "estado_factura")
    private Character estadoFactura;
    @Basic(optional = false)
    @Column(name = "valor_total")
    private long valorTotal;

    public Factura() {
    }

    public Factura(FacturaPK facturaPK) {
        this.facturaPK = facturaPK;
    }

    public Factura(FacturaPK facturaPK, Character estadoFactura, long valorTotal) {
        this.facturaPK = facturaPK;
        this.estadoFactura = estadoFactura;
        this.valorTotal = valorTotal;
    }

    public Factura(long numeroFactura, Date fechaFactura, long codigoProveedor) {
        this.facturaPK = new FacturaPK(numeroFactura, fechaFactura, codigoProveedor);
    }

    public FacturaPK getFacturaPK() {
        return facturaPK;
    }

    public void setFacturaPK(FacturaPK facturaPK) {
        this.facturaPK = facturaPK;
    }

    public Character getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(Character estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(long valorTotal) {
        this.valorTotal = valorTotal;
    }
}
