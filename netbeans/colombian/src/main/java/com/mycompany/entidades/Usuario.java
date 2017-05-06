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
 * @author eder
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="cedula_usuario")
    private Long cedula_usuario;
    @Column(name="nombre_usuario")
    private String nombre_usuario;
    @Column(name="usuario")
    private String usuario;
    @Column(name="pwd")
    private String pwd;
    @Column(name="telefono_usuario")
    private Long telefono_usuario;
    @Column(name="estado")
    private String estado;

    /**
     * @return the cedula_usuario
     */
    public Long getCedula_usuario() {
        return cedula_usuario;
    }

    /**
     * @param cedula_usuario the cedula_usuario to set
     */
    public void setCedula_usuario(Long cedula_usuario) {
        this.cedula_usuario = cedula_usuario;
    }

    /**
     * @return the nombre_usuario
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * @param nombre_usuario the nombre_usuario to set
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the telefono_usuario
     */
    public Long getTelefono_usuario() {
        return telefono_usuario;
    }

    /**
     * @param telefono_usuario the telefono_usuario to set
     */
    public void setTelefono_usuario(Long telefono_usuario) {
        this.telefono_usuario = telefono_usuario;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
