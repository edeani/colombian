/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "consignaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consignaciones.findAll", query = "SELECT c FROM Consignaciones c"),
    @NamedQuery(name = "Consignaciones.findByIdconsignacion", query = "SELECT c FROM Consignaciones c WHERE c.idconsignacion = :idconsignacion"),
    @NamedQuery(name = "Consignaciones.findByFechaConsignacion", query = "SELECT c FROM Consignaciones c WHERE c.fechaConsignacion = :fechaConsignacion"),
    @NamedQuery(name = "Consignaciones.findByValorConsignacion", query = "SELECT c FROM Consignaciones c WHERE c.valorConsignacion = :valorConsignacion"),
    @NamedQuery(name = "Consignaciones.findByNombreCajero", query = "SELECT c FROM Consignaciones c WHERE c.nombreCajero = :nombreCajero"),
    @NamedQuery(name = "Consignaciones.findByFecha", query = "SELECT c FROM Consignaciones c WHERE c.fecha = :fecha")})
public class Consignaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_consignacion")
    private Integer idconsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_consignacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_consignacion")
    private float valorConsignacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_cajero")
    private String nombreCajero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Consignaciones() {
    }

    public Consignaciones(Integer idconsignacion) {
        this.idconsignacion = idconsignacion;
    }

    public Consignaciones(Integer idconsignacion, Date fechaConsignacion, float valorConsignacion, String nombreCajero, Date fecha) {
        this.idconsignacion = idconsignacion;
        this.fechaConsignacion = fechaConsignacion;
        this.valorConsignacion = valorConsignacion;
        this.nombreCajero = nombreCajero;
        this.fecha = fecha;
    }

    public Integer getIdconsignacion() {
        return idconsignacion;
    }

    public void setIdconsignacion(Integer idconsignacion) {
        this.idconsignacion = idconsignacion;
    }

    public Date getFechaConsignacion() {
        return fechaConsignacion;
    }

    public void setFechaConsignacion(Date fechaConsignacion) {
        this.fechaConsignacion = fechaConsignacion;
    }

    public float getValorConsignacion() {
        return valorConsignacion;
    }

    public void setValorConsignacion(float valorConsignacion) {
        this.valorConsignacion = valorConsignacion;
    }

    public String getNombreCajero() {
        return nombreCajero;
    }

    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconsignacion != null ? idconsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consignaciones)) {
            return false;
        }
        Consignaciones other = (Consignaciones) object;
        if ((this.idconsignacion == null && other.idconsignacion != null) || (this.idconsignacion != null && !this.idconsignacion.equals(other.idconsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Consignaciones[ idconsignacion=" + idconsignacion + " ]";
    }
    
}
