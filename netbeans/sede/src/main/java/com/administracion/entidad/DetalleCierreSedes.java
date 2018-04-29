/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.validation.constraints.Size;

/**
 *
 * @author Jose Efren
 */
@Entity
@Table(name = "detalle_cierre_sedes")
public class DetalleCierreSedes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "idsede")
    private Long idsede;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Size(max = 45)
    @Column(name = "idcuenta")
    private String idcuenta;
    @Size(max = 45)
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "idcomprobantecierre")
    private Long idcomprobantecierre;
    @Column(name = "fecha_comprobante")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaComprobante;

    public DetalleCierreSedes() {
    }

    public DetalleCierreSedes(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(String idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Long getIdcomprobantecierre() {
        return idcomprobantecierre;
    }

    public void setIdcomprobantecierre(Long idcomprobantecierre) {
        this.idcomprobantecierre = idcomprobantecierre;
    }

    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }
    
}
