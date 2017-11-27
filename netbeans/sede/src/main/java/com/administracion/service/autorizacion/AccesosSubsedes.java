/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.datasources.GenericDataSource;
import com.administracion.dto.SubSedesDto;
import java.util.List;
import javax.sql.DataSource;
import org.bouncycastle.asn1.cmp.GenMsgContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author EderArmando
 */
@Component
public class AccesosSubsedes {
    
    @Autowired
    private GenericDataSource genericDataSource;
    
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
    
}
