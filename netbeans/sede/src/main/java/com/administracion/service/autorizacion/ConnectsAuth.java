/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.service.SedesService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author EderArmando
 */
@Component
public class ConnectsAuth{
    
    @Autowired
    private SedesService sedesService;
    @Autowired
    private AccesosSubsedes accesosSubsedes;
    
    private List<SedesDto> sedesConnect;
    
    
    @PostConstruct
    private void loadConnectsAut(){
        sedesConnect = sedesService.traerSedesDtos();
    }

    /**
     * Devuelve el datasource de la sede
     *
     * @param nameDataSource
     * @return
     */
    public DataSource getDataSourceSede(String nameDataSource) {
        SedesDto puntoSede = findSedeXName(nameDataSource);
        DriverManagerDataSource dataSource_ = new DriverManagerDataSource();
        dataSource_.setPassword(puntoSede.getPassword());
        dataSource_.setUrl(puntoSede.getUrl());
        dataSource_.setUsername(puntoSede.getUsername());
        return dataSource_;
    }
    /**
     * Devuelve el datasource de la subsede
     *
     * @param nameDataSource
     * @return
     */
    public DataSource getDataSourceSubSede(String nameDataSource) {
        SubSedesDto subSedesDto = findSubsedeXName(nameDataSource);
        DriverManagerDataSource dataSourceSub_ = new DriverManagerDataSource();
        dataSourceSub_.setPassword(subSedesDto.getPassword());
        dataSourceSub_.setUrl(subSedesDto.getUrl());
        dataSourceSub_.setUsername(subSedesDto.getUsername());
        return dataSourceSub_;
    }
    
    
    public SedesDto findSedeXName(String sede) {
        for (SedesDto sede1 : accesosSubsedes.getSedes()) {
            if (sede1.getSede().equals(sede)) {
                return sede1;
            }
        }
        return null;
    }
    /**
     * Devuelve los datos de conexión de las subsedes
     *
     * @param subsede
     * @return
     */
    public SubSedesDto findSubsedeXName(String subsede) {
        for (SubSedesDto subsede1 : accesosSubsedes.getSubsedes()) {
            if (subsede1.getSede().equals(subsede)) {
                return subsede1;
            }
        }
        return null;
    }
    public String findUserNameXSede(String sede) {
        for (SedesDto sede_ : accesosSubsedes.getSedes()) {
            if (sede_.getSede().equals(sede)) {
                return sede_.getUsersLogin();
            }
        }
        return "";
    }
    public String findSedeStringXName(String sede){
        for (SedesDto sede_ : accesosSubsedes.getSedes()) {
            if (sede_.getSede().equals(sede)) {
                return sede_.getSede();
            }
        }
        return null;
    }
    public List<SedesDto> getSedesConnect() {
        return sedesConnect;
    }

    public void setSedesConnect(List<SedesDto> sedesConnect) {
        this.sedesConnect = sedesConnect;
    }
    
    
}
