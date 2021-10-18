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

/**
 *
 * @author edeani
 */
@Entity
@Table(name = "traslado")
public class Traslado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtraslado")
    private Long idtraslado;
    @Column(name = "numero_factura_origen")
    private Long numeroFacturaOrigen;
    @Column(name = "idsede_origen")
    private Long idsedeOrigen;
    @Column(name = "numero_factura_destino")
    private Long numeroFacturaDestino;
    @Column(name = "idsede_destino")
    private Long idsedeDestino;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "usuario")
    private Long usuario;

    public Traslado() {
    }

    public Traslado(Long idtraslado) {
        this.idtraslado = idtraslado;
    }

    public Long getIdtraslado() {
        return idtraslado;
    }

    public void setIdtraslado(Long idtraslado) {
        this.idtraslado = idtraslado;
    }

    public Long getNumeroFacturaOrigen() {
        return numeroFacturaOrigen;
    }

    public void setNumeroFacturaOrigen(Long numeroFacturaOrigen) {
        this.numeroFacturaOrigen = numeroFacturaOrigen;
    }

    public Long getIdsedeOrigen() {
        return idsedeOrigen;
    }

    public void setIdsedeOrigen(Long idsedeOrigen) {
        this.idsedeOrigen = idsedeOrigen;
    }

    public Long getNumeroFacturaDestino() {
        return numeroFacturaDestino;
    }

    public void setNumeroFacturaDestino(Long numeroFacturaDestino) {
        this.numeroFacturaDestino = numeroFacturaDestino;
    }

    public Long getIdsedeDestino() {
        return idsedeDestino;
    }

    public void setIdsedeDestino(Long idsedeDestino) {
        this.idsedeDestino = idsedeDestino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    
    
}
