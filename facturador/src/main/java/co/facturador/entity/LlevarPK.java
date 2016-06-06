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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EderArmando
 */
@Embeddable
public class LlevarPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "numero_orden")
    private int numeroOrden;
    @Basic(optional = false)
    @Column(name = "fecha_orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;

    public LlevarPK() {
    }

    public LlevarPK(int numeroOrden, Date fechaOrden) {
        this.numeroOrden = numeroOrden;
        this.fechaOrden = fechaOrden;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }
}
