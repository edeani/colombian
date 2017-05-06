/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author joseefren
 */
@Entity
@Table(name="sede")
public class Sedes implements Serializable {
    @Id
    @Column(name="sed_cod")
    private Long sed_cod;

    @Column(name="sed_nombre")
    private String sed_nombre;
    
    @Column(name="sed_direccion")
    private String sed_direccion;
    
    @Column(name="sed_telefono")
    private String sed_telefono;

    @Column(name="identificador")
    private String identificador;
    
    @Column(name="persistencia")
    private String persistencia;
    
    @Column(name="bd")
    private String bd;
    
    @Column(name="password")
    private String password;
    
    @Column(name="usuario")
    private String usuario;
    /**
     * @return the sed_cod
     */
    public Long getSed_cod() {
        return sed_cod;
    }

    /**
     * @param sed_cod the sed_cod to set
     */
    public void setSed_cod(Long sed_cod) {
        this.sed_cod = sed_cod;
    }

    /**
     * @return the sed_nombre
     */
    public String getSed_nombre() {
        return sed_nombre;
    }

    /**
     * @param sed_nombre the sed_nombre to set
     */
    public void setSed_nombre(String sed_nombre) {
        this.sed_nombre = sed_nombre;
    }

    /**
     * @return the sed_direccion
     */
    public String getSed_direccion() {
        return sed_direccion;
    }

    /**
     * @param sed_direccion the sed_direccion to set
     */
    public void setSed_direccion(String sed_direccion) {
        this.sed_direccion = sed_direccion;
    }

    /**
     * @return the sed_telefono
     */
    public String getSed_telefono() {
        return sed_telefono;
    }

    /**
     * @param sed_telefono the sed_telefono to set
     */
    public void setSed_telefono(String sed_telefono) {
        this.sed_telefono = sed_telefono;
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the persistencia
     */
    public String getPersistencia() {
        return persistencia;
    }

    /**
     * @param persistencia the persistencia to set
     */
    public void setPersistencia(String persistencia) {
        this.persistencia = persistencia;
    }

    /**
     * @return the bd
     */
    public String getBd() {
        return bd;
    }

    /**
     * @param bd the bd to set
     */
    public void setBd(String bd) {
        this.bd = bd;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
