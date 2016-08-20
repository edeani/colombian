/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.entidades;

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
 * @author Jose Efren
 */
@Entity
@Table(name = "cuentas_puc")
public class CuentasPuc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_cta")
    private String codCta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivel")
    private int nivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clase")
    private int clase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_cta")
    private String nombreCta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "con_padre")
    private String conPadre;

    public CuentasPuc() {
    }

    public CuentasPuc(String codCta) {
        this.codCta = codCta;
    }

    public CuentasPuc(String codCta, String tipo, int nivel, int clase, String nombreCta, String conPadre) {
        this.codCta = codCta;
        this.tipo = tipo;
        this.nivel = nivel;
        this.clase = clase;
        this.nombreCta = nombreCta;
        this.conPadre = conPadre;
    }

    public String getCodCta() {
        return codCta;
    }

    public void setCodCta(String codCta) {
        this.codCta = codCta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public String getNombreCta() {
        return nombreCta;
    }

    public void setNombreCta(String nombreCta) {
        this.nombreCta = nombreCta;
    }

    public String getConPadre() {
        return conPadre;
    }

    public void setConPadre(String conPadre) {
        this.conPadre = conPadre;
    }

    
    
}
