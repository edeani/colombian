/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joseefren
 */
@Embeddable
public class MesaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_orden")
    private float numeroOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;

    public MesaPK() {
    }

    public MesaPK(float numeroOrden, Date fechaOrden) {
        this.numeroOrden = numeroOrden;
        this.fechaOrden = fechaOrden;
    }

    public float getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(float numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroOrden;
        hash += (fechaOrden != null ? fechaOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MesaPK)) {
            return false;
        }
        MesaPK other = (MesaPK) object;
        if (this.numeroOrden != other.numeroOrden) {
            return false;
        }
        if ((this.fechaOrden == null && other.fechaOrden != null) || (this.fechaOrden != null && !this.fechaOrden.equals(other.fechaOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.MesaPK[ numeroOrden=" + numeroOrden + ", fechaOrden=" + fechaOrden + " ]";
    }
    
}
