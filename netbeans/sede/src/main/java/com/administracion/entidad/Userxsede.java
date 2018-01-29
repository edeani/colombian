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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "userxsede")
public class Userxsede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cons")
    private Integer cons;
    
    @JoinColumn("idsede")
    @ManyToOne
    private Sedes idsede;
    
    @Column(name = "iduser")
    private Users iduser;

    public Userxsede() {
    }

    public Userxsede(Integer cons) {
        this.cons = cons;
    }

    public Integer getCons() {
        return cons;
    }

    public void setCons(Integer cons) {
        this.cons = cons;
    }

    public Sedes getIdsede() {
        return idsede;
    }

    public void setIdsede(Sedes idsede) {
        this.idsede = idsede;
    }

    public Users getIduser() {
        return iduser;
    }

    public void setIduser(Users iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cons != null ? cons.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userxsede)) {
            return false;
        }
        Userxsede other = (Userxsede) object;
        if ((this.cons == null && other.cons != null) || (this.cons != null && !this.cons.equals(other.cons))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.administracion.entidad.Userxsede[ cons=" + cons + " ]";
    }
    
}
