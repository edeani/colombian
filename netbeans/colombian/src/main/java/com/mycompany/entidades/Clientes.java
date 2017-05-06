/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author user
 */
@Entity
@Table(name="clientes")
public class Clientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="numero_telefono")
    private Long telefono;

    @Column(name="descripcion_cliente")
    private String descripcion_cliente;
    
    @Column(name="direccion_cliente")
    private String direccion_cliente;
    
    @Column(name="fecha_ingreso")
    private Date fecha_ingreso;
   
    @OneToOne
    @JoinColumn(name="codigo_barrio",nullable=false)
    private Barrios barrio;

    /**
     * @return the telefono
     */
    public Long getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the descripcion_cliente
     */
    public String getDescripcion_cliente() {
        return descripcion_cliente;
    }

    /**
     * @param descripcion_cliente the descripcion_cliente to set
     */
    public void setDescripcion_cliente(String descripcion_cliente) {
        this.descripcion_cliente = descripcion_cliente;
    }

    /**
     * @return the direccion_cliente
     */
    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    /**
     * @param direccion_cliente the direccion_cliente to set
     */
    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    /**
     * @return the fecha_ingreso
     */
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the barrio
     */
    public Barrios getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(Barrios barrio) {
        this.barrio = barrio;
    }

    
}
