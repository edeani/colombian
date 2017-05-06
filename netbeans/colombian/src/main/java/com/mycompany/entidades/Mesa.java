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
@Table(name = "mesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m"),
    @NamedQuery(name = "Mesa.findByNumeroOrden", query = "SELECT m FROM Mesa m WHERE m.mesaPK.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "Mesa.findByFechaOrden", query = "SELECT m FROM Mesa m WHERE m.mesaPK.fechaOrden = :fechaOrden"),
    @NamedQuery(name = "Mesa.findByEstadoOrden", query = "SELECT m FROM Mesa m WHERE m.estadoOrden = :estadoOrden"),
    @NamedQuery(name = "Mesa.findByPagoOrden", query = "SELECT m FROM Mesa m WHERE m.pagoOrden = :pagoOrden"),
    @NamedQuery(name = "Mesa.findByObservacion", query = "SELECT m FROM Mesa m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "Mesa.findByNumeroMesa", query = "SELECT m FROM Mesa m WHERE m.numeroMesa = :numeroMesa"),
    @NamedQuery(name = "Mesa.findByValorTotal", query = "SELECT m FROM Mesa m WHERE m.valorTotal = :valorTotal"),
    @NamedQuery(name = "Mesa.findByCodigoMesera", query = "SELECT m FROM Mesa m WHERE m.codigoMesera = :codigoMesera"),
    @NamedQuery(name = "Mesa.findByIdUsuario", query = "SELECT m FROM Mesa m WHERE m.idUsuario = :idUsuario")})
public class Mesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MesaPK mesaPK;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_mesa")
    private int numeroMesa;
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

    public Mesa() {
    }

    public Mesa(MesaPK mesaPK) {
        this.mesaPK = mesaPK;
    }

    public Mesa(MesaPK mesaPK, char estadoOrden, float pagoOrden, int numeroMesa, float valorTotal, int codigoMesera) {
        this.mesaPK = mesaPK;
        this.estadoOrden = estadoOrden;
        this.pagoOrden = pagoOrden;
        this.numeroMesa = numeroMesa;
        this.valorTotal = valorTotal;
        this.codigoMesera = codigoMesera;
    }

    public Mesa(float numeroOrden, Date fechaOrden) {
        this.mesaPK = new MesaPK(numeroOrden, fechaOrden);
    }

    public MesaPK getMesaPK() {
        return mesaPK;
    }

    public void setMesaPK(MesaPK mesaPK) {
        this.mesaPK = mesaPK;
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

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
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
        hash += (mesaPK != null ? mesaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.mesaPK == null && other.mesaPK != null) || (this.mesaPK != null && !this.mesaPK.equals(other.mesaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Mesa[ mesaPK=" + mesaPK + " ]";
    }

}
