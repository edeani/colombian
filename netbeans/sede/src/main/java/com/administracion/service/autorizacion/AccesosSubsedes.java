/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.datasources.GenericDataSource;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Sedes;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author EderArmando
 */
@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccesosSubsedes {
    
    @Autowired
    private GenericDataSource genericDataSource;
    
    private Sedes sedePrincipal;
    private List<Sedes> sedes;
    private List<SubSedesDto> subsedes;   
    private String path;
    /**
     * Objetos de conexión para las sedes y subsedes
     */
    private DriverManagerDataSource dataSource_;
    private DriverManagerDataSource dataSourceSub_;
    
    public AccesosSubsedes(){
        //Inicializo lista de sedes
        this.sedes = new ArrayList<>();
    }
    
    @Autowired
    public void init(DriverManagerDataSource dataSourceSede) {
        this.dataSource_ = dataSourceSede;
    }

    @Autowired
    public void initSubSede(DriverManagerDataSource dataSourceSubSede) {
        this.dataSourceSub_ = dataSourceSubSede;
    }
    
    /**
     * Devuelve el datasource de la subsede
     * @param nameDataSource
     * @return 
     */
    public DataSource getDataSourceSubSede(String nameDataSource){
        SubSedesDto subSedesDto = findSubsedeXName(nameDataSource);
        dataSourceSub_.setPassword(subSedesDto.getPassword());
        dataSourceSub_.setUrl(subSedesDto.getUrl());
        dataSourceSub_.setUsername(subSedesDto.getUsername());
        return dataSourceSub_;
    }
    /**
     * Devuelve el datasource de la sede
     * @param nameDataSource
     * @return 
     */
    public DataSource getDataSourceSede(String nameDataSource){
        Sedes puntoSede = findSedeXName(nameDataSource);
        dataSource_.setPassword(puntoSede.getPassword());
        dataSource_.setUrl(puntoSede.getUrl());
        dataSource_.setUsername(puntoSede.getUsername());
        return dataSource_;
    }
    
    /**
     * Devuelve los datos de conexión de las subsedes
     * @param subsede
     * @return 
     */
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
