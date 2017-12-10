/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.datasources.GenericDataSource;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Sedes;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author EderArmando
 */
@Component
public class AccesosSubsedes {
    
    @Autowired
    private GenericDataSource genericDataSource;
    
    private Sedes sedePrincipal;
    
    private List<Sedes> sedes;
    
    private List<SubSedesDto> subsedes;
    
    private String path;

    /**
     * Obtiene las credenciales del dataSource
     * @param nameDataSource
     * @return 
     */
    public DataSource getDataSourceSubSede(String nameDataSource){
        SubSedesDto subSedesDto = findSubsedeXName(nameDataSource);
        genericDataSource.updateGenericDataSource(subSedesDto);
        return genericDataSource.getGenericDataSourceSubSede();
    }
    
    public SubSedesDto findSubsedeXName(String subsede){
        for (SubSedesDto subsede1 : subsedes) {
            if(subsede1.getSede().equals(subsede)){
                return subsede1;
            }
        }
        return null;
    }
    
    public Sedes findSedeXName(String sede){
        for (Sedes sede1 : sedes) {
            if(sede1.getSede().equals(sede)){
                return sede1;
            }
        }
        
        return null;
    }
    /**
     * Devuelvo el datasource de la conexión principal
     * @return 
     */
    public DataSource getPrincipalDataSource(){
        return genericDataSource.getDataSourcePrincipal();
    }
    /**
     * Seteo la base de datos principal atraves de un objeto sede
     * @param sede 
     */
    public void setPrincialDataSource(Sedes sede){
        genericDataSource.getDataSourcePrincipal().setUsername(sede.getUsername());
        genericDataSource.getDataSourcePrincipal().setPassword(sede.getPassword());
        genericDataSource.getDataSourcePrincipal().setUrl(sede.getUrl());
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

    public List<Sedes> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sedes> sedes) {
        this.sedes = sedes;
    }

    public Sedes getSedePrincipal() {
        return sedePrincipal;
    }

    public void setSedePrincipal(Sedes sedePrincipal) {
        this.sedePrincipal = sedePrincipal;
    }
    
}
