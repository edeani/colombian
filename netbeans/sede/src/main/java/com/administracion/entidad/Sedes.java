/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "sedes")
@NamedQueries({
    @NamedQuery(name = "Sedes.findAll", query = "SELECT s FROM Sedes s")})
public class Sedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsedes")
    private Integer idsedes;
    @Size(max = 45)
    @Column(name = "sede")
    private String sede;

    public Sedes() {
    }

    public Sedes(Integer idsedes) {
        this.idsedes = idsedes;
    }

    public Integer getIdsedes() {
        return idsedes;
    }

    public void setIdsedes(Integer idsedes) {
        this.idsedes = idsedes;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsedes != null ? idsedes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sedes)) {
            return false;
        }
        Sedes other = (Sedes) object;
        if ((this.idsedes == null && other.idsedes != null) || (this.idsedes != null && !this.idsedes.equals(other.idsedes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.administracion.entidad.Sedes[ idsedes=" + idsedes + " ]";
    }
    
}
