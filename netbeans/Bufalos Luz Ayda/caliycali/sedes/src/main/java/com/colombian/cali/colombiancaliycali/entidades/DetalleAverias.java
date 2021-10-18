/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author edeani
 */
@Entity
@Table(name = "detalle_averias")
public class DetalleAverias implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Column(name="consecutivo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consecutivo;
    
    
    @JoinColumn(name="numero_averia")
    @ManyToOne
    private Averias averias;
    
    @JoinColumn(name="codigo_producto")
    @ManyToOne
    private Inventario producto;
    
    @Column(name="secuencial_averia")
    private int secuencialAveria;
    
    @Column(name="numero_unidades")
    private int numeroUnidades;
    
    @Column(name="valor_producto")
    private Double valorProducto;

    /**
     * @return the consecutivo
     */
    public Long getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */
    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    /**
     * @return the averias
     */
    public Averias getAverias() {
        return averias;
    }

    /**
     * @param averias the averias to set
     */
    public void setAverias(Averias averias) {
        this.averias = averias;
    }

    /**
     * @return the producto
     */
    public Inventario getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Inventario producto) {
        this.producto = producto;
    }

    /**
     * @return the secuencialAveria
     */
    public int getSecuencialAveria() {
        return secuencialAveria;
    }

    /**
     * @param secuencialAveria the secuencialAveria to set
     */
    public void setSecuencialAveria(int secuencialAveria) {
        this.secuencialAveria = secuencialAveria;
    }

    /**
     * @return the numeroUnidades
     */
    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    /**
     * @param numeroUnidades the numeroUnidades to set
     */
    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    /**
     * @return the valorProducto
     */
    public Double getValorProducto() {
        return valorProducto;
    }

    /**
     * @param valorProducto the valorProducto to set
     */
    public void setValorProducto(Double valorProducto) {
        this.valorProducto = valorProducto;
    }
    
    
}
