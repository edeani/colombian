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
import javax.persistence.Table;

/**
 *
 * @author Jose Efren
 */
@Entity
@Table(name = "detalle_porcentaje_ventas")
public class DetallePorcentajeVentas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;
    @Column(name = "idsede")
    private Long idsede;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "porcentaje_venta")
    private Double porcentajeVenta;
    @Column(name = "idporcentajeventa")
    private Long idporcentajeventa;

    public DetallePorcentajeVentas() {
    }

    public DetallePorcentajeVentas(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getIdsede() {
        return idsede;
    }

    public void setIdsede(Long idsede) {
        this.idsede = idsede;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPorcentajeVenta() {
        return porcentajeVenta;
    }

    public void setPorcentajeVenta(Double porcentajeVenta) {
        this.porcentajeVenta = porcentajeVenta;
    }

    public Long getIdporcentajeventa() {
        return idporcentajeventa;
    }

    public void setIdporcentajeventa(Long idporcentajeventa) {
        this.idporcentajeventa = idporcentajeventa;
    }
}
