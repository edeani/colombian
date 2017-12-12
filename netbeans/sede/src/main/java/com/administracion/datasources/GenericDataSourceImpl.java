/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.datasources;

import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Sedes;
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
public class GenericDataSourceImpl implements GenericDataSource {
    
    private DriverManagerDataSource dataSourcePrincipal;
    
    @Autowired
    public void initPrincipal(DriverManagerDataSource dataSource){
        this.dataSourcePrincipal = dataSource;
    }

    @Override
    public DriverManagerDataSource getDataSourcePrincipal() {
        return this.dataSourcePrincipal;
    }
    
}
