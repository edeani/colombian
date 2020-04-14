/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.entidad;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cons")
    private Integer cons;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;

    public Categoria() {
    }

    public Categoria(Integer cons) {
        this.cons = cons;
    }

    public Integer getCons() {
        return cons;
    }

    public void setCons(Integer cons) {
        this.cons = cons;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.cons == null && other.cons != null) || (this.cons != null && !this.cons.equals(other.cons))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domicilios.entidad.Categoria[ cons=" + cons + " ]";
    }
    
}
