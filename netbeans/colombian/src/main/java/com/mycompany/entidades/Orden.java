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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joseefren
 */
@Entity
@Table(name = "orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o"),
    @NamedQuery(name = "Orden.findByNumeroTelefono", query = "SELECT o FROM Orden o WHERE o.ordenPK.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Orden.findByNumeroOrden", query = "SELECT o FROM Orden o WHERE o.ordenPK.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "Orden.findByFechaOrden", query = "SELECT o FROM Orden o WHERE o.fechaOrden = :fechaOrden"),
    @NamedQuery(name = "Orden.findByEstadoOrden", query = "SELECT o FROM Orden o WHERE o.estadoOrden = :estadoOrden"),
    @NamedQuery(name = "Orden.findByValorTotal", query = "SELECT o FROM Orden o WHERE o.valorTotal = :valorTotal"),
    @NamedQuery(name = "Orden.findByObservacion", query = "SELECT o FROM Orden o WHERE o.observacion = :observacion"),
    @NamedQuery(name = "Orden.findByCodigoDomiciliario", query = "SELECT o FROM Orden o WHERE o.codigoDomiciliario = :codigoDomiciliario")})
public class Orden implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenPK ordenPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;
    @Column(name = "estado_orden")
    private Character estadoOrden;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private Float valorTotal;
    @Size(max = 255)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 10)
    @Column(name = "codigo_domiciliario")
    private String codigoDomiciliario;

    public Orden() {
    }

    public Orden(OrdenPK ordenPK) {
        this.ordenPK = ordenPK;
    }

    public Orden(OrdenPK ordenPK, Date fechaOrden) {
        this.ordenPK = ordenPK;
        this.fechaOrden = fechaOrden;
    }

    public Orden(long numeroTelefono, int numeroOrden) {
        this.ordenPK = new OrdenPK(numeroTelefono, numeroOrden);
    }

    public OrdenPK getOrdenPK() {
        return ordenPK;
    }

    public void setOrdenPK(OrdenPK ordenPK) {
        this.ordenPK = ordenPK;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Character getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(Character estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCodigoDomiciliario() {
        return codigoDomiciliario;
    }

    public void setCodigoDomiciliario(String codigoDomiciliario) {
        this.codigoDomiciliario = codigoDomiciliario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenPK != null ? ordenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.ordenPK == null && other.ordenPK != null) || (this.ordenPK != null && !this.ordenPK.equals(other.ordenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Orden[ ordenPK=" + ordenPK + " ]";
    }
    
}
