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
    
    @JoinColumn("iduser")
    @ManyToOne
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

    
}
