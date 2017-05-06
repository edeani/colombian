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
import javax.validation.constraints.Size;

/**
 *
 * @author joseefren
 */
@Embeddable
public class GastosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "gas_fecha")
    @Temporal(TemporalType.DATE)
    private Date gasFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "gas_concepto")
    private String gasConcepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gas_sede")
    private int gasSede;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gas_tipo")
    private int gasTipo;
    
    public GastosPK() {
    }

    public GastosPK(Date gasFecha, String gasConcepto, int gasSede, int gasTipo) {
        this.gasFecha = gasFecha;
        this.gasConcepto = gasConcepto;
        this.gasSede = gasSede;
        this.gasTipo = gasTipo;
    }

    public Date getGasFecha() {
        return gasFecha;
    }

    public void setGasFecha(Date gasFecha) {
        this.gasFecha = gasFecha;
    }

    public String getGasConcepto() {
        return gasConcepto;
    }

    public void setGasConcepto(String gasConcepto) {
        this.gasConcepto = gasConcepto;
    }

    public int getGasSede() {
        return gasSede;
    }

    public void setGasSede(int gasSede) {
        this.gasSede = gasSede;
    }

    public int getGasTipo() {
        return gasTipo;
    }

    public void setGasTipo(int gasTipo) {
        this.gasTipo = gasTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gasFecha != null ? gasFecha.hashCode() : 0);
        hash += (gasConcepto != null ? gasConcepto.hashCode() : 0);
        hash += (int) gasSede;
        hash += (int) gasTipo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GastosPK)) {
            return false;
        }
        GastosPK other = (GastosPK) object;
        if ((this.gasFecha == null && other.gasFecha != null) || (this.gasFecha != null && !this.gasFecha.equals(other.gasFecha))) {
            return false;
        }
        if ((this.gasConcepto == null && other.gasConcepto != null) || (this.gasConcepto != null && !this.gasConcepto.equals(other.gasConcepto))) {
            return false;
        }
        if (this.gasSede != other.gasSede) {
            return false;
        }
        if (this.gasTipo != other.gasTipo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.GastosPK[ gasFecha=" + gasFecha + ", gasConcepto=" + gasConcepto + ", gasSede=" + gasSede + ", gasTipo=" + gasTipo + " ]";
    }
    
}
