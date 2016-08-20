/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.entidades;
import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

//import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author Eslayfer
 */

@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 808L;

    @Id
    @Column(name = "ROLEID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

   // @NotEmpty
    @Column(name = "NOMBRE" , nullable = false , length = 99)
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {

        return this.getNombre();
    }

}