/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author edeani
 */
@Entity
@Table(name = "properties_sede")
public class PropertiesSede implements  Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "id_properties")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "value", nullable = false)
    private String value;
    
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "id_sede_principal")
    private Long idSedePrincipal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdSedePrincipal() {
        return idSedePrincipal;
    }

    public void setIdSedePrincipal(Long idSedePrincipal) {
        this.idSedePrincipal = idSedePrincipal;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
