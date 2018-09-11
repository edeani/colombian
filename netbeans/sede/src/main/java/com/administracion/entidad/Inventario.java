/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edeani
 */
@Entity
@Table(name = "inventario")
public class Inventario  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto_inventario")
    private Long codigoProductoInventario;
    @Column(name = "descripcion_producto")
    private String descripcionProducto;
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "stock_minimo")
    private Double stockMinimo;
    @Column(name = "stock_real")
    private Double stockReal;
    @Column(name = "stock_hoy")
    private Double stockHoy;
    @Column(name = "promedio")
    private Double promedio;

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

    /**
     * @return the codigoProductoInventario
     */
    public Long getCodigoProductoInventario() {
        return codigoProductoInventario;
    }

    /**
     * @param codigoProductoInventario the codigoProductoInventario to set
     */
    public void setCodigoProductoInventario(Long codigoProductoInventario) {
        this.codigoProductoInventario = codigoProductoInventario;
    }

    /**
     * @return the stockMinimo
     */
    public Double getStockMinimo() {
        return stockMinimo;
    }

    /**
     * @param stockMinimo the stockMinimo to set
     */
    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    /**
     * @return the stockReal
     */
    public Double getStockReal() {
        return stockReal;
    }

    /**
     * @param stockReal the stockReal to set
     */
    public void setStockReal(Double stockReal) {
        this.stockReal = stockReal;
    }

    /**
     * @return the stockHoy
     */
    public Double getStockHoy() {
        return stockHoy;
    }

    /**
     * @param stockHoy the stockHoy to set
     */
    public void setStockHoy(Double stockHoy) {
        this.stockHoy = stockHoy;
    }

    /**
     * @return the promedio
     */
    public Double getPromedio() {
        return promedio;
    }

    /**
     * @param promedio the promedio to set
     */
    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
