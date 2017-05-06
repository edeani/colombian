/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joseefren
 */
@Entity
@Table(name = "barrios")
public class Barrios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codigo_barrio")
    private Long codigoBarrio;
    @Column(name = "descripcion_barrio")
    private String descripcionBarrio;

    public Barrios() {
    }

    public Barrios(Long codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public Long getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(Long codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public String getDescripcionBarrio() {
        return descripcionBarrio;
    }

    public void setDescripcionBarrio(String descripcionBarrio) {
        this.descripcionBarrio = descripcionBarrio;
    }

    
    
}
