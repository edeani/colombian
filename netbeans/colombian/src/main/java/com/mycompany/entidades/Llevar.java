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
@Table(name = "llevar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Llevar.findAll", query = "SELECT l FROM Llevar l"),
    @NamedQuery(name = "Llevar.findByNumeroOrden", query = "SELECT l FROM Llevar l WHERE l.llevarPK.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "Llevar.findByFechaOrden", query = "SELECT l FROM Llevar l WHERE l.llevarPK.fechaOrden = :fechaOrden"),
    @NamedQuery(name = "Llevar.findByEstadoOrden", query = "SELECT l FROM Llevar l WHERE l.estadoOrden = :estadoOrden"),
    @NamedQuery(name = "Llevar.findByPagoOrden", query = "SELECT l FROM Llevar l WHERE l.pagoOrden = :pagoOrden"),
    @NamedQuery(name = "Llevar.findByObservacion", query = "SELECT l FROM Llevar l WHERE l.observacion = :observacion"),
    @NamedQuery(name = "Llevar.findByNumeroMesa", query = "SELECT l FROM Llevar l WHERE l.numeroMesa = :numeroMesa"),
    @NamedQuery(name = "Llevar.findByValorTotal", query = "SELECT l FROM Llevar l WHERE l.valorTotal = :valorTotal"),
    @NamedQuery(name = "Llevar.findByCodigoMesera", query = "SELECT l FROM Llevar l WHERE l.codigoMesera = :codigoMesera"),
    @NamedQuery(name = "Llevar.findByIdUsuario", query = "SELECT l FROM Llevar l WHERE l.idUsuario = :idUsuario")})
public class Llevar implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LlevarPK llevarPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_orden")
    private char estadoOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pago_orden")
    private float pagoOrden;
    @Size(max = 255)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "numero_mesa")
    private Integer numeroMesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private float valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_mesera")
    private int codigoMesera;
    @Size(max = 25)
    @Column(name = "id_usuario")
    private String idUsuario;

    public Llevar() {
    }

    public Llevar(LlevarPK llevarPK) {
        this.llevarPK = llevarPK;
    }

    public Llevar(LlevarPK llevarPK, char estadoOrden, float pagoOrden, float valorTotal, int codigoMesera) {
        this.llevarPK = llevarPK;
        this.estadoOrden = estadoOrden;
        this.pagoOrden = pagoOrden;
        this.valorTotal = valorTotal;
        this.codigoMesera = codigoMesera;
    }

    public Llevar(float numeroOrden, Date fechaOrden) {
        this.llevarPK = new LlevarPK(numeroOrden, fechaOrden);
    }

    public LlevarPK getLlevarPK() {
        return llevarPK;
    }

    public void setLlevarPK(LlevarPK llevarPK) {
        this.llevarPK = llevarPK;
    }

    public char getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(char estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public float getPagoOrden() {
        return pagoOrden;
    }

    public void setPagoOrden(float pagoOrden) {
        this.pagoOrden = pagoOrden;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (llevarPK != null ? llevarPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Llevar)) {
            return false;
        }
        Llevar other = (Llevar) object;
        if ((this.llevarPK == null && other.llevarPK != null) || (this.llevarPK != null && !this.llevarPK.equals(other.llevarPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Llevar[ llevarPK=" + llevarPK + " ]";
    }

}
