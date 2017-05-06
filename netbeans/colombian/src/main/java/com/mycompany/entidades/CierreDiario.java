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
@Table(name = "cierre_diario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CierreDiario.findAll", query = "SELECT c FROM CierreDiario c"),
    @NamedQuery(name = "CierreDiario.findBySede", query = "SELECT c FROM CierreDiario c WHERE c.cierreDiarioPK.sede = :sede"),
    @NamedQuery(name = "CierreDiario.findByFecha", query = "SELECT c FROM CierreDiario c WHERE c.cierreDiarioPK.fecha = :fecha"),
    @NamedQuery(name = "CierreDiario.findByValorVentas", query = "SELECT c FROM CierreDiario c WHERE c.valorVentas = :valorVentas"),
    @NamedQuery(name = "CierreDiario.findByValorGastos", query = "SELECT c FROM CierreDiario c WHERE c.valorGastos = :valorGastos"),
    @NamedQuery(name = "CierreDiario.findByConsignaciones", query = "SELECT c FROM CierreDiario c WHERE c.consignaciones = :consignaciones"),
    @NamedQuery(name = "CierreDiario.findByCajaReal", query = "SELECT c FROM CierreDiario c WHERE c.cajaReal = :cajaReal"),
    @NamedQuery(name = "CierreDiario.findByCajaInicial", query = "SELECT c FROM CierreDiario c WHERE c.cajaInicial = :cajaInicial")})
public class CierreDiario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CierreDiarioPK cierreDiarioPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_ventas")
    private double valorVentas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_gastos")
    private double valorGastos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "consignaciones")
    private Double consignaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caja_real")
    private double cajaReal;
    @Column(name = "caja_inicial")
    private Double cajaInicial;

    public CierreDiario() {
    }

    public CierreDiario(CierreDiarioPK cierreDiarioPK) {
        this.cierreDiarioPK = cierreDiarioPK;
    }

    public CierreDiario(CierreDiarioPK cierreDiarioPK, double valorVentas, double valorGastos, double cajaReal) {
        this.cierreDiarioPK = cierreDiarioPK;
        this.valorVentas = valorVentas;
        this.valorGastos = valorGastos;
        this.cajaReal = cajaReal;
    }

    public CierreDiario(int sede, Date fecha) {
        this.cierreDiarioPK = new CierreDiarioPK(sede, fecha);
    }

    public CierreDiarioPK getCierreDiarioPK() {
        return cierreDiarioPK;
    }

    public void setCierreDiarioPK(CierreDiarioPK cierreDiarioPK) {
        this.cierreDiarioPK = cierreDiarioPK;
    }

    public double getValorVentas() {
        return valorVentas;
    }

    public void setValorVentas(double valorVentas) {
        this.valorVentas = valorVentas;
    }

    public double getValorGastos() {
        return valorGastos;
    }

    public void setValorGastos(double valorGastos) {
        this.valorGastos = valorGastos;
    }

    public Double getConsignaciones() {
        return consignaciones;
    }

    public void setConsignaciones(Double consignaciones) {
        this.consignaciones = consignaciones;
    }

    public double getCajaReal() {
        return cajaReal;
    }

    public void setCajaReal(double cajaReal) {
        this.cajaReal = cajaReal;
    }

    public Double getCajaInicial() {
        return cajaInicial;
    }

    public void setCajaInicial(Double cajaInicial) {
        this.cajaInicial = cajaInicial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cierreDiarioPK != null ? cierreDiarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CierreDiario)) {
            return false;
        }
        CierreDiario other = (CierreDiario) object;
        if ((this.cierreDiarioPK == null && other.cierreDiarioPK != null) || (this.cierreDiarioPK != null && !this.cierreDiarioPK.equals(other.cierreDiarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.CierreDiario[ cierreDiarioPK=" + cierreDiarioPK + " ]";
    }
    
}
