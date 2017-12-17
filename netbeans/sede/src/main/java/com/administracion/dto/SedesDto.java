/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

/**
 *
 * @author EderArmando
 */
public class SedesDto {
    private Integer idsedes;
    private String sede,username,password,url;
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
    
    
}
