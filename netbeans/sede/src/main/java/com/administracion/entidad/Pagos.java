/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Jose Efren
 */
@Entity
@Table(name = "pagos")
public class Pagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpagos")
    private Long idpagos;
    @Size(max = 45)
    @Column(name = "idbeneficiario")
    private String idbeneficiario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    public Pagos() {
    }

    public Pagos(Long idpagos) {
        this.idpagos = idpagos;
    }

    public Long getIdpagos() {
        return idpagos;
    }

    public void setIdpagos(Long idpagos) {
        this.idpagos = idpagos;
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
}
