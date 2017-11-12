/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */
@Entity
@Table(name = "detallepedido")
public class Detallepedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetallepedido")
    private Long iddetallepedido;
    @Column(name = "cantidadorden")
    private Integer cantidadorden;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne
    private Producto idproducto;
    @NotNull
    @Column(name = "preciounitario")
    private Float preciounitario;
    @NotNull
    @Column(name = "totalproducto")
    private Float totalproducto;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedido pedido;

    public Detallepedido() {
    }

    public Detallepedido(Long iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Long getIddetallepedido() {
        return iddetallepedido;
    }

    public void setIddetallepedido(Long iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Integer getCantidadorden() {
        return cantidadorden;
    }

    public void setCantidadorden(Integer cantidadorden) {
        this.cantidadorden = cantidadorden;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Float getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(Float preciounitario) {
        this.preciounitario = preciounitario;
    }

    public Float getTotalproducto() {
        return totalproducto;
    }

    public void setTotalproducto(Float totalproducto) {
        this.totalproducto = totalproducto;
    }

    
    
}
