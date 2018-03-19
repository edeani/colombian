/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

import java.io.Serializable;

/**
 *
 * @author EderArmando
 */
public class SedesDto implements Serializable{
    private Integer idsedes,tipo_sede;
    private String sede,username,password,url,titulo,slogan;
    private String usersLogin;

    public Integer getIdsedes() {
        return idsedes;
    }

    public void setIdsedes(Integer idsedes) {
        this.idsedes = idsedes;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(String usersLogin) {
        this.usersLogin = usersLogin;
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

    public Integer getTipo_sede() {
        return tipo_sede;
    }

    public void setTipo_sede(Integer tipo_sede) {
        this.tipo_sede = tipo_sede;
    }
    
    
}
