/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.dto.UserItemDto;
import com.administracion.entidad.Sedes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author EderArmando
 */
@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccesosSubsedes implements Serializable {

    private Sedes sedePrincipal;
    private List<UserItemDto> userLog;
    private List<SedesDto> sedes;
    private List<SubSedesDto> subsedes;
    private String path;
    private Boolean multiple;
    /**
     * Objetos de conexión para las sedes y subsedes
     */
    

    public AccesosSubsedes() {
        //Inicializo lista de sedes
        this.sedes = new ArrayList<>();
        this.userLog = new ArrayList<>();
        this.subsedes = new ArrayList<>();
        multiple = Boolean.FALSE;
    }

    public List<SubSedesDto> getSubsedes() {
        return subsedes;
    }

    public void setSubsedes(List<SubSedesDto> subsedes) {
        this.subsedes = subsedes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SedesDto> getSedes() {
        return sedes;
    }

    public void setSedes(List<SedesDto> sedes) {
        this.sedes = sedes;
    }

    public Sedes getSedePrincipal() {
        return sedePrincipal;
    }

    public void setSedePrincipal(Sedes sedePrincipal) {
        this.sedePrincipal = sedePrincipal;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public List<UserItemDto> getUserLog() {
        return userLog;
    }

    public void setUserLog(List<UserItemDto> userLog) {
        this.userLog = userLog;
    }

    
}
