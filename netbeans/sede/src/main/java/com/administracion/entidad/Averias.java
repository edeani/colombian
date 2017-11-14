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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author edeani
 */
@Entity
@Table(name = "averias")
public class Averias implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_averia")
    private Long numeroAveria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_averia")
    @Temporal(TemporalType.DATE)
    private Date fechaAveria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_averia")
    private char estadoAveria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pago_orden")
    private double pagoOrden;
    @Size(max = 100)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private double valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_mesera")
    private int codigoMesera;
    @Size(max = 25)
    @Column(name = "id_usuario")
    private String idUsuario;

    public Averias() {
    }

    public Averias(Long numeroAveria) {
        this.numeroAveria = numeroAveria;
    }

    public Averias(Long numeroAveria, Date fechaAveria, char estadoAveria, double pagoOrden, double valorTotal, int codigoMesera) {
        this.numeroAveria = numeroAveria;
        this.fechaAveria = fechaAveria;
        this.estadoAveria = estadoAveria;
        this.pagoOrden = pagoOrden;
        this.valorTotal = valorTotal;
        this.codigoMesera = codigoMesera;
    }

    public Long getNumeroAveria() {
        return numeroAveria;
    }

    public void setNumeroAveria(Long numeroAveria) {
        this.numeroAveria = numeroAveria;
    }

    public Date getFechaAveria() {
        return fechaAveria;
    }

    public void setFechaAveria(Date fechaAveria) {
        this.fechaAveria = fechaAveria;
    }

    public char getEstadoAveria() {
        return estadoAveria;
    }

    public void setEstadoAveria(char estadoAveria) {
        this.estadoAveria = estadoAveria;
    }

    public double getPagoOrden() {
        return pagoOrden;
    }

    public void setPagoOrden(double pagoOrden) {
        this.pagoOrden = pagoOrden;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCodigoMesera() {
        return codigoMesera;
    }

    public void setCodigoMesera(int codigoMesera) {
        this.codigoMesera = codigoMesera;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    
}
