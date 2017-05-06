/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author joseefren
 */
@Embeddable
public class OrdenPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_telefono")
    private long numeroTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_orden")
    private int numeroOrden;

    public OrdenPK() {
    }

    public OrdenPK(long numeroTelefono, int numeroOrden) {
        this.numeroTelefono = numeroTelefono;
        this.numeroOrden = numeroOrden;
    }

    public long getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(long numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroTelefono;
        hash += (int) numeroOrden;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenPK)) {
            return false;
        }
        OrdenPK other = (OrdenPK) object;
        if (this.numeroTelefono != other.numeroTelefono) {
            return false;
        }
        if (this.numeroOrden != other.numeroOrden) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.OrdenPK[ numeroTelefono=" + numeroTelefono + ", numeroOrden=" + numeroOrden + " ]";
    }
    
}
