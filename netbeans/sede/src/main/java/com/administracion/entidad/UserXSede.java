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

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "userxsede")
public class UserXSede implements Serializable{
    
    @Id
    @Basic(optional = false)
    @Column(name = "cons")
    private Integer cons;
    
    @ManyToOne
    @JoinColumn(name = "idsede")
    private Sedes sede;
    
    @ManyToOne
    @JoinColumn(name = "iduser")
    private Users user;

    public Integer getCons() {
        return cons;
    }

    public void setCons(Integer cons) {
        this.cons = cons;
    }

    public Sedes getSede() {
        return sede;
    }

    public void setSede(Sedes sede) {
        this.sede = sede;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
}
