/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "detalle_llevar")
public class DetalleLlevar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "numero_orden")
    private float numeroOrden;
    @Basic(optional = false)
    @Column(name = "codigo_producto")
    private int codigoProducto;
    @Basic(optional = false)
    @Column(name = "secuencial_orden")
    private int secuencialOrden;
    @Basic(optional = false)
    @Column(name = "numero_unidades")
    private int numeroUnidades;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_producto")
    private Float valorProducto;

    public DetalleLlevar() {
    }

    public DetalleLlevar(Integer id) {
        this.id = id;
    }

    public DetalleLlevar(Integer id, float numeroOrden, int codigoProducto, int secuencialOrden, int numeroUnidades) {
        this.id = id;
        this.numeroOrden = numeroOrden;
        this.codigoProducto = codigoProducto;
        this.secuencialOrden = secuencialOrden;
        this.numeroUnidades = numeroUnidades;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(float numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getSecuencialOrden() {
        return secuencialOrden;
    }

    public void setSecuencialOrden(int secuencialOrden) {
        this.secuencialOrden = secuencialOrden;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public Float getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(Float valorProducto) {
        this.valorProducto = valorProducto;
    }
}
