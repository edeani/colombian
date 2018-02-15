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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author EderArmando
 */
@Entity
@Table(name = "sloganxsede")
public class Sloganxsede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cons")
    private Integer cons;
    @Size(max = 45)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 45)
    @Column(name = "slogan")
    private String slogan;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "idsede")
    @OneToOne
    private Sedes idsede;

    public Sloganxsede() {
    }

    public Sloganxsede(Integer cons) {
        this.cons = cons;
    }

    public Sloganxsede(Integer cons, Sedes idsede) {
        this.cons = cons;
        this.idsede = idsede;
    }

    public Integer getCons() {
        return cons;
    }

    public void setCons(Integer cons) {
        this.cons = cons;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Sedes getIdsede() {
        return idsede;
    }

    public void setIdsede(Sedes idsede) {
        this.idsede = idsede;
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
        if (!(object instanceof Sloganxsede)) {
            return false;
        }
        Sloganxsede other = (Sloganxsede) object;
        if ((this.cons == null && other.cons != null) || (this.cons != null && !this.cons.equals(other.cons))) {
            return false;
        }
        return true;
    }
    
}
