/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.entidades;

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
 * @author user
 */
@Entity
@Table(name = "cambiosede")
public class CambioSede implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_factura")
    private Long numeroFactura;
    @Column(name = "sede_origen")
    private Long sedeOrigen;
    @Column(name = "sede_destino")
    private Long sedeDestino;
    @Column(name = "fecha_traslado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTraslado;
    @Column(name = "id_user")
    private Long idUser;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;

    public CambioSede() {
    }

    public CambioSede(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public CambioSede(Long consecutivo, Long numeroFactura) {
        this.consecutivo = consecutivo;
        this.numeroFactura = numeroFactura;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Long getSedeOrigen() {
        return sedeOrigen;
    }

    public void setSedeOrigen(Long sedeOrigen) {
        this.sedeOrigen = sedeOrigen;
    }

    public Long getSedeDestino() {
        return sedeDestino;
    }

    public void setSedeDestino(Long sedeDestino) {
        this.sedeDestino = sedeDestino;
    }

    public Date getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(Date fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }
    
}
