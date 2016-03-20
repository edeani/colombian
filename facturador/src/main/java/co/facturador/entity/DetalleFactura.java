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
@Table(name = "detalle_factura")
public class DetalleFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleFacturaPK detalleFacturaPK;
    @Basic(optional = false)
    @Column(name = "valor_producto")
    private long valorProducto;
    @Basic(optional = false)
    @Column(name = "numero_unidades")
    private int numeroUnidades;

    public DetalleFactura() {
    }

    public DetalleFactura(DetalleFacturaPK detalleFacturaPK) {
        this.detalleFacturaPK = detalleFacturaPK;
    }

    public DetalleFactura(DetalleFacturaPK detalleFacturaPK, long valorProducto, int numeroUnidades) {
        this.detalleFacturaPK = detalleFacturaPK;
        this.valorProducto = valorProducto;
        this.numeroUnidades = numeroUnidades;
    }

    public DetalleFactura(long numeroFactura, Date fechaFactura, int codigoProductoInventario, int secuencialFactura, long codigoProveedor) {
        this.detalleFacturaPK = new DetalleFacturaPK(numeroFactura, fechaFactura, codigoProductoInventario, secuencialFactura, codigoProveedor);
    }

    public DetalleFacturaPK getDetalleFacturaPK() {
        return detalleFacturaPK;
    }

    public void setDetalleFacturaPK(DetalleFacturaPK detalleFacturaPK) {
        this.detalleFacturaPK = detalleFacturaPK;
    }

    public long getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(long valorProducto) {
        this.valorProducto = valorProducto;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }   
}
