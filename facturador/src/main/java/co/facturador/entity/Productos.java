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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "productos")
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_producto")
    private Float codigoProducto;
    @Column(name = "descripcion_producto")
    private String descripcionProducto;
    @Basic(optional = false)
    @Column(name = "valor_producto")
    private float valorProducto;
    @Column(name = "admin")
    private Float admin;
    @Column(name = "comi_1")
    private Float comi1;
    @Column(name = "comi_2")
    private Float comi2;

    public Productos() {
    }

    public Productos(Float codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Productos(Float codigoProducto, float valorProducto) {
        this.codigoProducto = codigoProducto;
        this.valorProducto = valorProducto;
    }

    public Float getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Float codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public float getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(float valorProducto) {
        this.valorProducto = valorProducto;
    }

    public Float getAdmin() {
        return admin;
    }

    public void setAdmin(Float admin) {
        this.admin = admin;
    }

    public Float getComi1() {
        return comi1;
    }

    public void setComi1(Float comi1) {
        this.comi1 = comi1;
    }

    public Float getComi2() {
        return comi2;
    }

    public void setComi2(Float comi2) {
        this.comi2 = comi2;
    }
}
