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
@Table(name = "cierre_sedes")
public class CierreSedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalhaber")
    private Double totalhaber;
    @Column(name = "totaldeber")
    private Double totaldeber;
    @Size(max = 45)
    @Column(name = "idsede")
    private Long idsede;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "fecha_comprobante")
    @Temporal(TemporalType.DATE)
    private Date fechaComprobante; 

    public CierreSedes() {
    }

    public CierreSedes(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Double getTotalhaber() {
        return totalhaber;
    }

    public void setTotalhaber(Double totalhaber) {
        this.totalhaber = totalhaber;
    }

    public Double getTotaldeber() {
        return totaldeber;
    }

    public void setTotaldeber(Double totaldeber) {
        this.totaldeber = totaldeber;
    }

    public Long getIdsede() {
        return idsede;
    }

    public void setIdsede(Long idsede) {
        this.idsede = idsede;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }
}
