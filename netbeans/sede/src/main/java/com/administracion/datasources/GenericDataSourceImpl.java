/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.datasources;

import com.administracion.entidad.Sedes;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author EderArmando
 */
@Service
public class GenericDataSourceImpl implements GenericDataSource{

    private DriverManagerDataSource dataSource_;
   
    @Autowired
    public void init(DriverManagerDataSource dataSourceSede){
        this.dataSource_=dataSourceSede;
    }
    
    @Override
    public void updateGenericDataSource(Sedes sede) {
        dataSource_.setPassword(sede.getPassword());
        dataSource_.setUrl(sede.getUrl());
        dataSource_.setUsername(sede.getUsername());
    }

    @Override
    public DataSource getGenericDataSource() {
        return (DataSource)dataSource_;
    }
    
}
