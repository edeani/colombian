/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joseefren
 */
@Entity
@Table(name = "gastos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g"),
    @NamedQuery(name = "Gastos.findByGasFecha", query = "SELECT g FROM Gastos g WHERE g.gastosPK.gasFecha = :gasFecha"),
    @NamedQuery(name = "Gastos.findByGasConcepto", query = "SELECT g FROM Gastos g WHERE g.gastosPK.gasConcepto = :gasConcepto"),
    @NamedQuery(name = "Gastos.findByGasSede", query = "SELECT g FROM Gastos g WHERE g.gastosPK.gasSede = :gasSede"),
    @NamedQuery(name = "Gastos.findByGasTipo", query = "SELECT g FROM Gastos g WHERE g.gastosPK.gasTipo = :gasTipo"),
    @NamedQuery(name = "Gastos.findByGasValor", query = "SELECT g FROM Gastos g WHERE g.gasValor = :gasValor"),
    @NamedQuery(name = "Gastos.findByGasCantidad", query = "SELECT g FROM Gastos g WHERE g.gasCantidad = :gasCantidad")})
public class Gastos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GastosPK gastosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gas_valor")
    private int gasValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gas_cantidad")
    private int gasCantidad;

    public Gastos() {
    }

    public Gastos(GastosPK gastosPK) {
        this.gastosPK = gastosPK;
    }

    public Gastos(GastosPK gastosPK, int gasValor, int gasCantidad) {
        this.gastosPK = gastosPK;
        this.gasValor = gasValor;
        this.gasCantidad = gasCantidad;
    }

    public Gastos(Date gasFecha, String gasConcepto, int gasSede, int gasTipo) {
        this.gastosPK = new GastosPK(gasFecha, gasConcepto, gasSede, gasTipo);
    }

    public GastosPK getGastosPK() {
        return gastosPK;
    }

    public void setGastosPK(GastosPK gastosPK) {
        this.gastosPK = gastosPK;
    }

    public int getGasValor() {
        return gasValor;
    }

    public void setGasValor(int gasValor) {
        this.gasValor = gasValor;
    }

    public int getGasCantidad() {
        return gasCantidad;
    }

    public void setGasCantidad(int gasCantidad) {
        this.gasCantidad = gasCantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gastosPK != null ? gastosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.gastosPK == null && other.gastosPK != null) || (this.gastosPK != null && !this.gastosPK.equals(other.gastosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Gastos[ gastosPK=" + gastosPK + " ]";
    }
    
}
