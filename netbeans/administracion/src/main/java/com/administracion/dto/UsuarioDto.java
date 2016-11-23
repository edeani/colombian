/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author edeani
 */
public class UsuarioDto {
    @NotNull
    private Long idusuario;
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "[A-Za-z áéíóúñÁÉÍÓÚÑ]*")
    private String nombreusuario;
    @Email
    @Size(max = 100)
    private String correo;
    @Size(max = 15)
    @Pattern(regexp = "[0-9]*")
    private String identificacion;
    @NotEmpty   
    @Size(max = 10)
    @Pattern(regexp = "[0-9]*")
    private String telefono;
    @NotNull
    private Integer idrol;
    @NotEmpty
    @Pattern(regexp = "[A-Za-z]*")
    private String nombrerol;
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "[A-Za-z0-9 áéíóúñÁÉÍÓÚÑ\\.#\\-]*")
    private String direccion;
    
    private String password;
    @NotEmpty
    @Size(max = 1)
    private String estado;
    
    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
