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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "inventario")
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_producto_inventario")
    private Double codigoProductoInventario;
    @Basic(optional = false)
    @Column(name = "descripcion_producto")
    private String descripcionProducto;
    @Basic(optional = false)
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Basic(optional = false)
    @Column(name = "stock_minimo")
    private float stockMinimo;
    @Basic(optional = false)
    @Column(name = "stock_hoy")
    private float stockHoy;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "stock_real")
    private Float stockReal;
    @Column(name = "final_fisico")
    private Float finalFisico;

    public Inventario() {
    }

    public Inventario(Double codigoProductoInventario) {
        this.codigoProductoInventario = codigoProductoInventario;
    }

    public Inventario(Double codigoProductoInventario, String descripcionProducto, Date fechaInicial, float stockMinimo, float stockHoy) {
        this.codigoProductoInventario = codigoProductoInventario;
        this.descripcionProducto = descripcionProducto;
        this.fechaInicial = fechaInicial;
        this.stockMinimo = stockMinimo;
        this.stockHoy = stockHoy;
    }

    public Double getCodigoProductoInventario() {
        return codigoProductoInventario;
    }

    public void setCodigoProductoInventario(Double codigoProductoInventario) {
        this.codigoProductoInventario = codigoProductoInventario;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public float getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(float stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public float getStockHoy() {
        return stockHoy;
    }

    public void setStockHoy(float stockHoy) {
        this.stockHoy = stockHoy;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Float getStockReal() {
        return stockReal;
    }

    public void setStockReal(Float stockReal) {
        this.stockReal = stockReal;
    }

    public Float getFinalFisico() {
        return finalFisico;
    }

    public void setFinalFisico(Float finalFisico) {
        this.finalFisico = finalFisico;
    }
}
