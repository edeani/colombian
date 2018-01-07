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
public class UserItemDto implements Serializable{
    private String  userName;
    private Long idUser;
    private Integer idSedeUser;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Integer getIdSedeUser() {
        return idSedeUser;
    }

    public void setIdSedeUser(Integer idSedeUser) {
        this.idSedeUser = idSedeUser;
    }
    
    
}
