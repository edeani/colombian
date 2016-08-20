/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.entidades;

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
import javax.validation.constraints.Size;

/**
 *
 * @author Jose Efren
 */
@Entity
@Table(name = "caja_menor")
@NamedQueries({
    @NamedQuery(name = "CajaMenor.findAll", query = "SELECT c FROM CajaMenor c")})
public class CajaMenor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcajamenor")
    private Long idcajamenor;
    @Size(max = 45)
    @Column(name = "idbeneficiario")
    private String idbeneficiario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public CajaMenor() {
    }

    public CajaMenor(Long idcajamenor) {
        this.idcajamenor = idcajamenor;
    }

    public Long getIdcajamenor() {
        return idcajamenor;
    }

    public void setIdcajamenor(Long idcajamenor) {
        this.idcajamenor = idcajamenor;
    }

    public String getIdbeneficiario() {
        return idbeneficiario;
    }

    public void setIdbeneficiario(String idbeneficiario) {
        this.idbeneficiario = idbeneficiario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
        hash += (idcajamenor != null ? idcajamenor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajaMenor)) {
            return false;
        }
        CajaMenor other = (CajaMenor) object;
        if ((this.idcajamenor == null && other.idcajamenor != null) || (this.idcajamenor != null && !this.idcajamenor.equals(other.idcajamenor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.colombian.cali.colombiancaliycali.entidades.CajaMenor[ idcajamenor=" + idcajamenor + " ]";
    }
    
}
